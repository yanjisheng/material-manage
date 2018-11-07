#--备份的测试数据，请勿在生产环境执行本sql文件
USE material_management;
INSERT INTO user(loginName, hashPassword, name, staffNo, phone, employDate, status) VALUES
('staff', '3BB6B955507D71C2408D39C45B59AC92', '普通员工测试用户', null, null, null, 1);
INSERT INTO brand(terminalType, manufacturer, terminalModel, property, networkMode, 
isRouterSupported, license, enterTime, remark) VALUES
('终端类型', '厂家', '型号', '属性', '网络模式', 0, '牌照方', null, null);
INSERT INTO warehouse(oldWarehouseId, warehouseName, warehouseType, provinceCode, cityCode,
address, createTime, status) VALUES
(null, '仓库名称', '类型', 'AA', 'BB', '详细地址', now(), 1);

COMMIT;
