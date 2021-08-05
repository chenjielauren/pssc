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