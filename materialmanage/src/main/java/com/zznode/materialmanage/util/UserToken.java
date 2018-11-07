package com.zznode.materialmanage.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zznode.materialmanage.controller.helper.UserTokenHelper;
import com.zznode.materialmanage.exception.ConverterException;
import com.zznode.materialmanage.exception.MaterialManageException;
import com.zznode.materialmanage.model.User;

/**
 * 生成用户token的工具类
 * @author yanjisheng
 *
 */
@Component
public class UserToken {
	
	private static Logger log = LoggerFactory.getLogger(UserToken.class);
	
	private static boolean isInitialized = false;
	private static final long MILLISECONDS_PER_HOUR = 3600000;
	
	@Autowired
	PropertyReader property;
	
	private String key;
	private int validHours;	
	
	/**
	 * 根据用户登录名和当前时间生成token
	 */
	public UserTokenHelper generateToken(User user){
		init();
		try {
			UserTokenHelper token = new UserTokenHelper();
			token.setLoginName(user.getLoginName());
			token.setAvailableUntil(new Date(System.currentTimeMillis() + validHours*MILLISECONDS_PER_HOUR));
			String preparedTokenString = user.getLoginName() + "," + System.currentTimeMillis() + ",materialManage";
			String tokenString = encrypt(preparedTokenString, key);
			token.setTokenString(tokenString);
			return token;
		}
		catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException 
				| NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 检查token是否有效
	 */
	public boolean authorizeToken(UserTokenHelper token) throws MaterialManageException{
		init();
		try {
			try {
				String preparedTokenString = decrypt(token.getTokenString(), key);
				String[] splittedTokenString = preparedTokenString.split(",");
				if(System.currentTimeMillis() - Long.valueOf(splittedTokenString[1]) < 0 ||
						System.currentTimeMillis() - Long.valueOf(splittedTokenString[1]) > validHours*MILLISECONDS_PER_HOUR){
					throw new MaterialManageException("错误：用户token已过期");
				}
				if(token.getLoginName().equals(splittedTokenString[0]) && "materialManage".equals(splittedTokenString[2])){
					return true;
				}
			} catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException 
					| IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException | ConverterException 
					| NumberFormatException | NullPointerException e) {
				e.printStackTrace();
				throw new MaterialManageException("错误：用户token无效", e);
			}
			throw new MaterialManageException("错误：用户token无效");
		} catch (MaterialManageException e) {
			if(property.isEnableTokenAuth()){
				throw e;
			}
		}		
		return false;
	}
	
	/**
	 * 检查token是否有效
	 */
	public boolean authorizeToken(String loginName, String tokenString) throws MaterialManageException{
		UserTokenHelper token = new UserTokenHelper();
		token.setLoginName(loginName);
		token.setTokenString(tokenString);
		return this.authorizeToken(token);
	}
	
	private static String encrypt(String dataSource, String key) 
			throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{
		SecureRandom random = new SecureRandom();
		DESKeySpec desKey = new DESKeySpec(key.getBytes());
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(desKey);
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, random);
		byte[] doFinal = cipher.doFinal(dataSource.getBytes());		
		return ByteArrayHexStringConverter.toHexString(doFinal);
	}
	
	private static String decrypt(String secretHexString, String key) 
			throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, ConverterException{
		SecureRandom random = new SecureRandom();
		DESKeySpec desKey = new DESKeySpec(key.getBytes());
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(desKey);
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.DECRYPT_MODE, secretKey, random);
		byte[] doFinal = cipher.doFinal(ByteArrayHexStringConverter.decodeHexString(secretHexString));
		return new String(doFinal,"UTF-8");
	}
	
	private void init(){
		if(isInitialized){
			return;
		}
		String key = property.getTokenKey();
		Integer validHours = property.getTokenValidHours();
		log.debug("key="+key+",validHours="+validHours);
		if(key==null || key.equals("")){
			this.key = new String(this.getRandomByteArray());
		}else if(key.length() < 8){
			this.key = key.concat("        ");
		}else{
			this.key = key;
		}
		if(validHours == null){
			this.validHours = 24*365*10;
		}else{
			this.validHours = validHours;
		}
		log.info("用户token验证模块初始化成功");
		isInitialized = true;
	}
	
	private byte[] getRandomByteArray(){
		Random random = new Random();
		byte[] bytes = new byte[8];
		random.nextBytes(bytes);
		return bytes;
	}
	
}
