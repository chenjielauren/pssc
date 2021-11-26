INSERT INTO pssc.sys_dict_type
(dict_id, dict_name, dict_type, status, create_by, create_time, update_by, update_time, remark)
VALUES(102, '是否有效', 'sys_is_valid', '0', 'admin', '2021-07-19 14:11:47', '', NULL, NULL);


INSERT INTO pssc.sys_dict_data
(dict_code, dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(105, 1, '无效', '0', 'sys_is_valid', '', 'danger', 'Y', '0', 'admin', '2021-07-19 14:12:22', 'admin', '2021-07-19 14:13:09', '');
INSERT INTO pssc.sys_dict_data
(dict_code, dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(106, 2, '有效', '1', 'sys_is_valid', '', 'primary', 'Y', '0', 'admin', '2021-07-19 14:12:48', 'admin', '2021-07-19 14:13:00', '');

INSERT INTO sys_dict_type
(dict_id, dict_name, dict_type, status, create_by, create_time, update_by, update_time, remark)
VALUES(103, '检验结果', 'pp_qc_result', '0', 'admin', '2021-07-24 16:21:10', 'admin', '2021-07-27 10:48:19', '');

INSERT INTO sys_dict_data
(dict_code, dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(107, 0, '异常', '0', 'pp_qc_result', '', 'danger', 'Y', '0', 'admin', '2021-07-24 16:22:17', 'admin', '2021-07-24 16:22:28', '');
INSERT INTO sys_dict_data
(dict_code, dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(108, 2, '合格', '1', 'pp_qc_result', '', 'primary', 'Y', '0', 'admin', '2021-07-24 16:23:04', 'admin', '2021-07-24 16:23:13', '');


INSERT INTO sys_dict_type (dict_name,dict_type,status,create_by,create_time,update_by,update_time,remark) VALUES
	 ('物料属性','bs_bom_attr','0','admin','2021-08-01 11:01:58','',NULL,NULL);

INSERT INTO sys_dict_data (dict_sort,dict_label,dict_value,dict_type,css_class,list_class,is_default,status,create_by,create_time,update_by,update_time,remark) VALUES
	 (1,'生产','0','bs_bom_attr','','primary','Y','0','admin','2021-08-01 11:02:46','admin','2021-08-01 11:03:18',''),
	 (2,'采购','1','bs_bom_attr','','info','Y','0','admin','2021-08-01 11:03:05','admin','2021-08-01 11:03:23','');

ALTER TABLE pssc.bs_craft_sop_main MODIFY COLUMN id varchar(50) NOT NULL COMMENT '工艺ID';
ALTER TABLE pssc.bs_craft_sop_detail MODIFY COLUMN id varchar(50) NOT NULL COMMENT '明细ID';
ALTER TABLE pssc.bs_craft_sop_detail MODIFY COLUMN main_id varchar(50) NOT NULL COMMENT '主表ID';

ALTER TABLE pssc.bs_mcn_main MODIFY COLUMN id varchar(50) NOT NULL COMMENT 'ID';
ALTER TABLE pssc.bs_mcn_detail MODIFY COLUMN id varchar(50) NOT NULL COMMENT '明细ID';
ALTER TABLE pssc.bs_mcn_detail MODIFY COLUMN main_id varchar(50) NOT NULL COMMENT '主表ID';

ALTER TABLE pssc.bs_pqc_detail DROP FOREIGN KEY FK_bs_pqc_detail_main_id;
ALTER TABLE pssc.bs_pqc_main MODIFY COLUMN id varchar(50) NOT NULL COMMENT 'ID';
ALTER TABLE pssc.bs_pqc_detail MODIFY COLUMN id varchar(50) NOT NULL COMMENT '明细ID';
ALTER TABLE pssc.bs_pqc_detail MODIFY COLUMN main_id varchar(50) NOT NULL COMMENT '主表ID';


ALTER TABLE pssc.bs_craft_sop_detail ADD file_url varchar(300) NULL;
ALTER TABLE pssc.bs_craft_sop_detail CHANGE file_url file_url varchar(300) NULL AFTER file_name
alter table bs_craft_sop_detail add foreign key(main_id) references bs_craft_sop_main(id);

ALTER TABLE pssc.bs_mcn_main ADD spec varchar(255) NULL COMMENT '产品规格';
ALTER TABLE pssc.bs_mcn_main CHANGE spec spec varchar(255) NULL COMMENT '产品规格' AFTER name;
ALTER TABLE pssc.bs_mcn_detail ADD file_url varchar(300) NULL COMMENT '文件路径';
ALTER TABLE pssc.bs_mcn_detail CHANGE file_url file_url varchar(300) NULL COMMENT '文件路径' AFTER attachment;
alter table bs_mcn_detail add foreign key(main_id) references bs_mcn_main(id);

ALTER TABLE pssc.bs_pqc_main ADD spec varchar(255) NULL COMMENT '产品规格';
ALTER TABLE pssc.bs_pqc_main CHANGE spec spec varchar(255) NULL COMMENT '产品规格' AFTER name;
alter table bs_pqc_detail add foreign key(main_id) references bs_pqc_main(id);

ALTER TABLE pssc.qc_mat_check_detail DROP FOREIGN KEY FK_qc_mat_ail_main_idB52F;
ALTER TABLE pssc.qc_mat_check_main MODIFY COLUMN id varchar(50) NOT NULL COMMENT 'ID';
ALTER TABLE pssc.qc_mat_check_detail MODIFY COLUMN id varchar(50) NOT NULL COMMENT '明细ID';
ALTER TABLE pssc.qc_mat_check_detail MODIFY COLUMN main_id varchar(50) NOT NULL COMMENT '主表ID';
alter table qc_mat_check_detail add foreign key(main_id) references qc_mat_check_main(id);


ALTER TABLE pssc.qc_process_check_detail DROP FOREIGN KEY FK_qc_procail_main_idE3C0;
ALTER TABLE pssc.qc_process_check_main MODIFY COLUMN id varchar(50) NOT NULL COMMENT 'ID';
ALTER TABLE pssc.qc_process_check_detail MODIFY COLUMN id varchar(50) NOT NULL COMMENT '明细ID';
ALTER TABLE pssc.qc_process_check_detail MODIFY COLUMN main_id varchar(50) NOT NULL COMMENT '主表ID';
alter table qc_process_check_detail add foreign key(main_id) references qc_process_check_main(id);

ALTER TABLE pssc.qc_prod_check_detail DROP FOREIGN KEY FK_qc_prodail_main_idED3C;
ALTER TABLE pssc.qc_prod_check_main MODIFY COLUMN id varchar(50) NOT NULL COMMENT 'ID';
ALTER TABLE pssc.qc_prod_check_detail MODIFY COLUMN id varchar(50) NOT NULL COMMENT '明细ID';
ALTER TABLE pssc.qc_prod_check_detail MODIFY COLUMN main_id varchar(50) NOT NULL COMMENT '主表ID';
alter table qc_prod_check_detail add foreign key(main_id) references qc_prod_check_main(id);

ALTER TABLE pssc.sd_delivery MODIFY COLUMN id varchar(50) NOT NULL COMMENT '发货ID';

ALTER TABLE pssc.sd_order MODIFY COLUMN id varchar(50) NOT NULL COMMENT '订单ID';

ALTER TABLE pssc.qc_mat_check_main ADD handle_result varchar(50) NULL COMMENT '处理结果0合格入库,1让步接受,2退货';
ALTER TABLE pssc.qc_mat_check_main CHANGE handle_result handle_result varchar(50) NULL COMMENT '处理结果0合格入库,1让步接受,2退货' AFTER qc_result;

ALTER TABLE pssc.qc_mat_check_main ADD pspec varchar(100) NULL COMMENT '材料规格';
ALTER TABLE pssc.qc_mat_check_main CHANGE pspec pspec varchar(100) NULL COMMENT '材料规格' AFTER pname;

ALTER TABLE pssc.qc_process_check_main ADD pspec varchar(100) NULL COMMENT '产品规格';
ALTER TABLE pssc.qc_process_check_main CHANGE pspec pspec varchar(100) NULL COMMENT '产品规格' AFTER pname;
ALTER TABLE pssc.qc_process_check_main ADD cstation varchar(100) NULL COMMENT '检验机台';
ALTER TABLE pssc.qc_process_check_main CHANGE cstation cstation varchar(100) NULL COMMENT '检验机台' AFTER ctype;

ALTER TABLE pssc.qc_prod_check_main ADD handle_result varchar(50) NULL COMMENT '处理结果0合格入库,1让步接受,2退货';
ALTER TABLE pssc.qc_prod_check_main CHANGE handle_result handle_result varchar(50) NULL COMMENT '处理结果0合格入库,1让步接受,2退货' AFTER qc_result;

ALTER TABLE pssc.qc_prod_check_main ADD pspec varchar(100) NULL COMMENT '成品规格';
ALTER TABLE pssc.qc_prod_check_main CHANGE pspec pspec varchar(100) NULL COMMENT '成品规格' AFTER pname;

ALTER TABLE pssc.qc_wo_yield_rate MODIFY COLUMN id varchar(50) NOT NULL COMMENT '计划工单号ID';

INSERT INTO sys_menu (menu_name,parent_id,order_num,url,target,menu_type,visible,is_refresh,perms,icon,create_by,create_time,update_by,update_time,remark) VALUES
	 ('批次屏蔽',1,9,'/system/dict/dlotkeyword','','C','0','1','system:dict:view','fa fa-table','admin','2021-11-22 16:17:06','',NULL,'批次屏蔽菜单');
