create table standardinfo_vendor (
  vendor_id         bigint(20)      not null auto_increment    comment '供应商ID',
  vendor_category   char(1)         default '0'                comment '供应商分类（0主材供应商 1辅材供应商 2未知）',
  vendor_number     varchar(30)     not null                   comment '供应商编号',
  vendor_name   	varchar(30)     default ''                 comment '供应商名称',
  vendor_phone      varchar(30)     default ''                 comment '供方电话',
  contract_name     varchar(30)     default ''                 comment '联系人',
  contract_mobile   varchar(11)    default ''                  comment '联系手机',
  vendor_addr       varchar(500)     default ''                comment '地址',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  is_valid        varchar(1)     default 'Y'                 comment 'Y:有效;N:无效',
  remark            varchar(500)    default null               comment '备注',
  attribute1		varchar(30)     default ''                 comment '',
  attribute2		varchar(30)     default ''                 comment '',
  attribute3		varchar(30)     default ''                 comment '',
  primary key (vendor_id)
) engine=innodb auto_increment=100 comment = '合格供方名录';
-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('合格供方名录', '2000', '4', '/standardinfo/vendor', 'C', '0', 'standardinfo:vendor:view', '#', 'admin', sysdate(), '', null, '合格供方名录菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('合格供方名录查询', @parentId, '1',  '#',  'F', '0', 'standardinfo:vendor:list',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('合格供方名录新增', @parentId, '2',  '#',  'F', '0', 'standardinfo:vendor:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('合格供方名录修改', @parentId, '3',  '#',  'F', '0', 'standardinfo:vendor:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('合格供方名录删除', @parentId, '4',  '#',  'F', '0', 'standardinfo:vendor:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('合格供方名录导出', @parentId, '5',  '#',  'F', '0', 'standardinfo:vendor:export',       '#', 'admin', sysdate(), '', null, '');


create table standardinfo_processloss (
  processloss_id    bigint(20)      not null auto_increment    comment '工序损耗标准ID',
  process_number     varchar(30)     not null              		comment '工序编号',
  process_name   	varchar(30)     default ''             		comment '工序名称',
  process_capacity  varchar(30)     default ''              comment '工序产能',
  convert_time       varchar(30)                               comment '转换时间',
  loss_rate   		varchar(11)    default ''                  comment '损耗率',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  is_valid        varchar(1)     default 'Y'                 comment 'Y:有效;N:无效',
  remark            varchar(500)    default null               comment '备注',
  attribute1		varchar(30)     default ''                 comment '',
  attribute2		varchar(30)     default ''                 comment '',
  attribute3		varchar(30)     default ''                 comment '',
  primary key (processloss_id)
) engine=innodb auto_increment=100 comment = '工序损耗标准';

-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('工序损耗标准', '2000', '4', '/standardinfo/processloss', 'C', '0', 'standardinfo:processloss:view', '#', 'admin', sysdate(), '', null, '工序损耗标准菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('工序损耗标准查询', @parentId, '1',  '#',  'F', '0', 'standardinfo:processloss:list',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('工序损耗标准新增', @parentId, '2',  '#',  'F', '0', 'standardinfo:processloss:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('工序损耗标准修改', @parentId, '3',  '#',  'F', '0', 'standardinfo:processloss:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('工序损耗标准删除', @parentId, '4',  '#',  'F', '0', 'standardinfo:processloss:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('工序损耗标准导出', @parentId, '5',  '#',  'F', '0', 'standardinfo:processloss:export',       '#', 'admin', sysdate(), '', null, '');


create table standardinfo_product (
  product_id    bigint(20)      not null auto_increment    comment '产品ID',
  product_number     varchar(30)     not null              		comment '工序编号',
  product_name   	varchar(30)     default ''             		comment '工序名称',
  inspection_step  varchar(30)     default ''              comment '检验工序产能',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  is_valid        varchar(1)     default 'Y'                 comment 'Y:有效;N:无效',
  attribute1		varchar(30)     default ''                 comment '',
  attribute2		varchar(30)     default ''                 comment '',
  attribute3		varchar(30)     default ''                 comment '',
  primary key (product_id)
) engine=innodb auto_increment=100 comment = '产品检验标准';

create table standardinfo_product_inspection(
  inspection_id    bigint(20)      not null auto_increment    comment '检验ID',
  product_id    bigint(20)      		not null			 comment '产品ID',
  inspection_type   char(1)         default '0'                comment '检验类型（0首检 1自检 2巡检 3未知）',
  inspection_number     varchar(30)     not null              	comment '检验项目编号',
  inspection_name   	varchar(30)     default ''             	comment '检验项目名称',
  inspection_standard  varchar(500)     default ''              comment '检验工序标准',
  inspection_method  varchar(30)     default ''              comment '检验方法',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  is_valid        varchar(1)     default 'Y'                 comment 'Y:有效;N:无效',
  attribute1		varchar(30)     default ''                 comment '',
  attribute2		varchar(30)     default ''                 comment '',
  attribute3		varchar(30)     default ''                 comment '',
  primary key (inspection_id),
  FOREIGN KEY (product_id) REFERENCES standardinfo_product (product_id)
) engine=innodb auto_increment=100 comment = '产品检验标准明细信息';

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('工序检验标准', '2000', '4', '/standardinfo/product', 'C', '0', 'standardinfo:product:view', '#', 'admin', sysdate(), '', null, '工序检验标准菜单');
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('工艺标准与CCP', '2000', '5', '/standardinfo/ccp', 'C', '0', 'standardinfo:ccp:view', '#', 'admin', sysdate(), '', null, '工艺标准与CCP菜单');
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('4M变更单', '2000', '6', '/standardinfo/change', 'C', '0', 'standardinfo:change:view', '#', 'admin', sysdate(), '', null, '4M变更单');
INSERT INTO sys_dict_type (dict_name,dict_type,status,create_by,create_time,update_by,update_time,remark) VALUES
	 ('检验类型','standard_inspection_type','0','admin',sysdate(),'admin',sysdate(),'');

INSERT INTO sys_dict_data (dict_sort,dict_label,dict_value,dict_type,css_class,list_class,is_default,status,create_by,create_time,update_by,update_time,remark) VALUES
	 (1,'首检','0','standard_inspection_type',NULL,NULL,'Y','0','admin',sysdate(),'',NULL,NULL),
	 (2,'自检','1','standard_inspection_type',NULL,NULL,'Y','0','admin',sysdate(),'',NULL,NULL),
	 (3,'巡检','2','standard_inspection_type',NULL,NULL,'Y','0','admin',sysdate(),'',NULL,NULL);