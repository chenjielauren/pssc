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
  remark            varchar(500)    default null               comment '备注',
  attribute1		varchar(30)     default ''                 comment '',
  attribute2		varchar(30)     default ''                 comment '',
  attribute3		varchar(30)     default ''                 comment '',
  primary key (vendor_id)
) engine=innodb auto_increment=100 comment = '合格供方名录';

create table standardinfo_processloss (
  processloss_id    bigint(20)      not null auto_increment    comment '工序损耗标准ID',
  process_number     varchar(30)     not null              		comment '工序编号',
  process_name   	varchar(30)     default ''             		comment '工序名称',
  process_capacity  varchar(30)     default ''              comment '工序产能',
  convert_time       datetime                                   comment '转换时间',
  loss_rate   		varchar(11)    default ''                  comment '损耗率',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  attribute1		varchar(30)     default ''                 comment '',
  attribute2		varchar(30)     default ''                 comment '',
  attribute3		varchar(30)     default ''                 comment '',
  primary key (processloss_id)
) engine=innodb auto_increment=100 comment = '工序损耗标准';

