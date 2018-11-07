#--表结构，在程序上线时请先在生产环境执行本sql文件
#--请确保生产环境的mysql数据库的character_set_database和character_set_server的值为utf8
USE material_management;

DROP TABLE IF EXISTS brand;
CREATE TABLE brand(
	brandId int PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
	terminalType varchar(30) NOT NULL COMMENT '终端类型',
	manufacturer varchar(60) NOT NULL COMMENT '厂家',
	terminalModel varchar(60) NOT NULL COMMENT '型号',
	property varchar(120) COMMENT '属性',
	networkMode varchar(30) COMMENT '默认网络模式',
	isRouterSupported tinyint COMMENT '是否支持路由',
	license varchar(60) COMMENT '牌照方',
	enterTime varchar(30) COMMENT '入网时间',
	remark varchar(240) COMMENT '备注'
) COMMENT='品牌管理';

DROP TABLE IF EXISTS warehouse;
CREATE TABLE warehouse(
	warehouseId int PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
	oldWarehouseId int COMMENT '旧主键', 
	warehouseName varchar(75) NOT NULL COMMENT '仓库名称',
	warehouseType varchar(30) COMMENT '仓库类型',
	provinceCode varchar(3) NOT NULL COMMENT '省编码',
	cityCode varchar(3) NOT NULL COMMENT '市编码',
	address varchar(240) NOT NULL COMMENT '地址',
	createTime datetime NOT NULL COMMENT '创建时间',
	status tinyint NOT NULL DEFAULT 1 COMMENT '在用状态（0停用，1在用）'
) COMMENT='仓库基本信息';

DROP TABLE IF EXISTS user;
CREATE TABLE user(
	userId int PRIMARY KEY AUTO_INCREMENT COMMENT '用户id',
	loginName varchar(18) UNIQUE NOT NULL COMMENT '登录名',
	hashPassword varchar(35) NOT NULL COMMENT '密码',
	name varchar(45) COMMENT '姓名',
	staffNo varchar(30) COMMENT '员工号',
	phone varchar(15) COMMENT '电话',
	employDate datetime COMMENT '入职时间',
	status tinyint NOT NULL DEFAULT 1 COMMENT '用户状态（0已离职，1普通员工，2管理员）'
) COMMENT='用户';

#--插入系统用户，默认密码abc123
INSERT INTO user(loginName, hashPassword, name, staffNo, phone, employDate, status) VALUES
('admin', 'FD500D379587135AE08F9B86185C4D49', '系统用户', null, null, null, 2);

DROP TABLE IF EXISTS warehouse_staff;
CREATE TABLE warehouse_staff(
	id int PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
	warehouseId int NOT NULL COMMENT '仓库主键',
	userId int NOT NULL COMMENT '用户id',
	isManager tinyint NOT NULL DEFAULT 0 COMMENT '是否管理员'
) COMMENT='仓库与员工对应关系';

DROP TABLE IF EXISTS stock;
CREATE TABLE stock(
	terminalId int PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
	terminalSn varchar(25) UNIQUE NOT NULL COMMENT '终端串码（sn）',
	brandId int COMMENT '品牌编号',
	warehouseId int COMMENT '仓库编号',
	status smallint NOT NULL COMMENT '终端状态'
) COMMENT='终端库存';

DROP TABLE IF EXISTS terminal_status;
CREATE TABLE terminal_status(
	statusCode smallint PRIMARY KEY COMMENT '终端状态代码',
	statusName varchar(21) UNIQUE NOT NULL COMMENT '终端状态名称'
) COMMENT='终端状态';

INSERT INTO terminal_status(statusCode, statusName) VALUES (100, '可用');
INSERT INTO terminal_status(statusCode, statusName) VALUES (201, '预领');
#--INSERT INTO terminal_status(statusCode, statusName) VALUES (202, '预占');
INSERT INTO terminal_status(statusCode, statusName) VALUES (200, '在用');
INSERT INTO terminal_status(statusCode, statusName) VALUES (301, '待回收');
INSERT INTO terminal_status(statusCode, statusName) VALUES (300, '已回收');
#--INSERT INTO terminal_status(statusCode, statusName) VALUES (401, '待返修');
INSERT INTO terminal_status(statusCode, statusName) VALUES (402, '返修');
INSERT INTO terminal_status(statusCode, statusName) VALUES (400, '报废');
INSERT INTO terminal_status(statusCode, statusName) VALUES (101, '备件');

DROP TABLE IF EXISTS stock_in;
CREATE TABLE stock_in(
	id int PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
	stockInId int UNIQUE NOT NULL COMMENT '入库单主键（随机）',
	operatorId int NOT NULL COMMENT '操作人id',
	operateTime datetime NOT NULL COMMENT '入库时间',
	warehouseId int COMMENT '仓库id',
	brandId int COMMENT '品牌id',
	remark varchar(240) COMMENT '备注'
) COMMENT='入库单';

DROP TABLE IF EXISTS stock_in_sn;
CREATE TABLE stock_in_sn(
	id int PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
	stockInId int NOT NULL COMMENT '入库单主键',
	terminalSn varchar(25) NOT NULL COMMENT '终端sn'
) COMMENT='入库单终端sn列表';

DROP TABLE IF EXISTS stock_move;
CREATE TABLE stock_move(
	id int PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
	stockMoveId int UNIQUE NOT NULL COMMENT '调拨单主键',
	operatorId int NOT NULL COMMENT '操作人id',
	operateTime datetime NOT NULL COMMENT '操作时间',
	warehouseInId int COMMENT '调入仓库id',
	warehouseOutId int COMMENT '调出仓库id',
	remark varchar(240) COMMENT '备注'
) COMMENT='调拨单';

DROP TABLE IF EXISTS stock_move_sn;
CREATE TABLE stock_move_sn(
	id int PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
	stockMoveId int NOT NULL COMMENT '调拨单主键',
	terminalSn varchar(25) NOT NULL COMMENT '终端sn'
) COMMENT='调拨单终端sn列表';

DROP TABLE IF EXISTS stock_use;
CREATE TABLE stock_use(
	stockUseId int PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
	operatorId int NOT NULL COMMENT '操作人id',
	installerId int COMMENT '装维人员id',
	terminalSn varchar(25) NOT NULL COMMENT '终端sn',
	operateTime datetime NOT NULL COMMENT '领用时间',
	customerAccount varchar(25) COMMENT '用户账号',
	installTime datetime COMMENT '安装（激活）时间',
	remark varchar(240) COMMENT '备注'
) COMMENT='领用单';

DROP TABLE IF EXISTS stock_recycle;
CREATE TABLE stock_recycle(
	stockRecycleId int PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
	terminalSn varchar(25) NOT NULL COMMENT '终端sn',
	installerId int NOT NULL COMMENT '装维人员id',
	operatorId int COMMENT '操作人id',
	operateTime datetime NOT NULL COMMENT '操作时间',
	customerAccount varchar(25) COMMENT '用户账号',
	recycleTime datetime COMMENT '入库时间',
	warehouseId int COMMENT '仓库id',
	remark varchar(240) COMMENT '备注'
) COMMENT='回收单';

DROP TABLE IF EXISTS stock_maintenance;
CREATE TABLE stock_maintenance(
	stockMaintenanceId int PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
	terminalSn varchar(25) NOT NULL COMMENT '终端sn',
	operatorId int NOT NULL COMMENT '操作人id',
	operateTime datetime NOT NULL COMMENT '操作时间',
	warehouseId int COMMENT '仓库id',
	remark varchar(240) COMMENT '备注'
) COMMENT='返修单';

DROP TABLE IF EXISTS stock_discard;
CREATE TABLE stock_discard(
	stockDiscardId int PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
	terminalSn varchar(25) NOT NULL COMMENT '终端sn',
	operatorId int NOT NULL COMMENT '操作人id',
	operateTime datetime NOT NULL COMMENT '报废时间',
	remark varchar(240) COMMENT '备注'
) COMMENT='报废单';

DROP TABLE IF EXISTS stock_reuse;
CREATE TABLE stock_reuse(
	stockReuseId int PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
	terminalSn varchar(25) NOT NULL COMMENT '终端sn',
	operatorId int NOT NULL COMMENT '操作人id',
	operateTime datetime NOT NULL COMMENT '重新入库时间',
	warehouseId int COMMENT '重新入库仓库id',
	remark varchar(240) COMMENT '备注'
) COMMENT='备件单';

COMMIT;