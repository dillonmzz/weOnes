CREATE DATABASE weonesdb;
USE weonesdb;

drop table we_activity;
CREATE TABLE we_activity (
	`activity_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '活动/商品ID',
	`title` VARCHAR (120) NOT NULL COMMENT '活动/商品标题',
	`subhead` VARCHAR (50) NOT NULL COMMENT '活动/商品副标题',
	`type` VARCHAR (10) NOT NULL COMMENT '活动/商品类别',
	`thumbnails` VARCHAR (254) NOT NULL COMMENT '活动/商品略缩图地址',
	`min_people` INT NOT NULL COMMENT '活动人数(最少)',
	`max_people` INT NOT NULL COMMENT '活动人数(最多)',
	`province` VARCHAR (120) NOT NULL COMMENT '活动/商品地址(省)',
	`city` VARCHAR (120) NOT NULL COMMENT '活动/商品地址(市)',
	`district` VARCHAR (120) NOT NULL COMMENT '活动/商品地址(区)',
	`address` VARCHAR (120) NOT NULL COMMENT '活动/商品地址(街道)',
	`start_time` TIMESTAMP NOT NULL COMMENT '活动/商品开始时间',
	`end_time` TIMESTAMP NOT NULL COMMENT '活动/商品结束时间',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`details` TEXT COMMENT '活动详情',
	PRIMARY KEY (activity_id),
	KEY idx_start_time (start_time),
	KEY idx_end_time (end_time),
	KEY idx_create_time (create_time)
) ENGINE = INNODB AUTO_INCREMENT = 1000 DEFAULT CHARSET = utf8 COMMENT = '活动/商品表';

SHOW CREATE TABLE we_activity\G;#显示表的创建信息

-- v2.0添加价格字段
alter table we_activity add price decimal(10,2) COMMENT '活动/商品价格';
alter table we_activity modify column price decimal(10,2) COMMENT '活动/商品价格';
-- v3.0修改price为new_price,添加原价old_price;
alter table we_activity change price new_price decimal(10,2) COMMENT '活动/商品现价';
alter table we_activity add old_price decimal(10,2) COMMENT '活动/商品原价';
--修改details为details_html,添加编辑内容details_content
alter table we_activity change details details_html TEXT COMMENT '活动/商品详情html';
alter table we_activity add details_content TEXT COMMENT '活动/商品详情content';
--修改new_price、old_price 字段为int类型(金额以分为单位)
alter table we_activity modify column new_price INT NOT NULL COMMENT '活动/商品现价';
alter table we_activity modify column old_price INT NOT NULL COMMENT '活动/商品原价';
--添加状态字段state
alter table we_activity add state tinyint(4) NOT NULL DEFAULT '0' COMMENT '发布状态,0:未发布  1:已发布   9:管理员已删除';
alter table we_activity modify column state VARCHAR (10) NOT NULL  DEFAULT '0' COMMENT '发布状态,0:未发布  1:已发布   9:管理员已删除';
--添加是否推荐至首页、活动排序字段
alter table we_activity add is_recommend VARCHAR (10) COMMENT '是否推荐至首页,on:是  off:否';
alter table we_activity add activity_order INT COMMENT '活动排序';

-- v3.0添加微信用户信息表
drop table we_wechat_user;
CREATE TABLE we_wechat_user (
	`openid` VARCHAR (120) NOT NULL COMMENT 'openid',
	`nickname` VARCHAR (50) NOT NULL COMMENT '昵称',
	`sex` VARCHAR (1) NOT NULL COMMENT '性别 1-男 2-女  0未知',
	`country` VARCHAR (20) NOT NULL COMMENT '国家',
	`province` VARCHAR (20) NOT NULL COMMENT '省',
	`city` VARCHAR (20) NOT NULL COMMENT '市',
	`headimgurl` VARCHAR (200) NOT NULL COMMENT '用户头像',
	`language` VARCHAR (50) NOT NULL COMMENT '语言',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	PRIMARY KEY (openid),
	KEY idx_sex (sex),
	KEY idx_city (city),
	KEY idx_create_time (create_time)
) ENGINE = INNODB  DEFAULT CHARSET = utf8 COMMENT = '微信用户信息表';
-- v4.0添加用户订单表 2016.06.08
drop table we_user_order;
CREATE TABLE we_user_order(
`transaction_id` VARCHAR (120) NOT NULL COMMENT '微信交易单号',
`out_trade_no` VARCHAR (120) NOT NULL COMMENT '商户单号',
`activity_id` BIGINT NOT NULL COMMENT '活动/商品ID',
`openid` VARCHAR (120) NOT NULL COMMENT 'openid',
`user_phone` BIGINT NOT NULL COMMENT '用户手机号',
`user_name` VARCHAR (50)  COMMENT '联系人姓名',
`user_addr` VARCHAR (120)  COMMENT '联系人地址',
`quantity` INT NOT NULL COMMENT '购买数量',
`total_fee` INT NOT NULL NOT NULL COMMENT '总计费用(分)',
`state` TINYINT NOT NULL DEFAULT 0 COMMENT '状态标识,0:未支付  1:已付款 2:已发货 9:用户已删除',
`time_end` TIMESTAMP NOT NULL COMMENT '购买结束时间',
`remark` TEXT COMMENT '订单备注',
PRIMARY KEY(transaction_id,out_trade_no,activity_id,openid),/*联合主键*/
KEY idx_transaction_id(transaction_id),
KEY idx_out_trade_no(out_trade_no),
KEY idx_activity_id(activity_id),
KEY idx_openid(openid),
KEY idx_time_end(time_end)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='订单明细表';
-- v4.1 修改用户订单表中  user_phone字段类型为VARCHAR 2016.06.15
alter table we_user_order modify column user_phone varchar(20);
-- v5.0 添加用户关注表
drop table we_user_favorite;
CREATE TABLE we_user_favorite (
`openid` VARCHAR (120) NOT NULL COMMENT 'openid',
`activity_id` BIGINT NOT NULL COMMENT '活动/商品ID',
`state` TINYINT NOT NULL COMMENT '状态标识,0:取消关注  1:已关注',
`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (openid,activity_id,state,create_time)
) ENGINE = INNODB  DEFAULT CHARSET = utf8 COMMENT = '用户关注表';
--v6.0 地点管理
drop table we_activity_addresses;
CREATE TABLE we_activity_addresses(
`address_id` VARCHAR (50) NOT NULL COMMENT '活动地点ID',
`effdt` date NOT NULL COMMENT '生效日期',
`status` VARCHAR (1) NOT NULL COMMENT '状态',
`title` VARCHAR (50) NOT NULL COMMENT '地点描述',
`province` VARCHAR (20) NOT NULL COMMENT '省',
`city` VARCHAR (20) NOT NULL COMMENT '市',
`address_desc` VARCHAR (50) NOT NULL COMMENT '详细地点',
`max_people` INT NOT NULL COMMENT '人数限制',
`device` VARCHAR (120) NOT NULL COMMENT '场地设备',
`create_user` VARCHAR (100) NOT NULL COMMENT '创建人',
`create_time` TIMESTAMP NOT NULL COMMENT '创建时间',
PRIMARY KEY(address_id,effdt,status),/*联合主键*/
KEY idx_address_id(address_id),
KEY idx_effdt(effdt),
KEY idx_status(status),
KEY idx_create_user(create_user)
)ENGINE = INNODB  DEFAULT CHARSET = utf8 COMMENT = '活动地点管理表';

--v7.0 时间管理
drop table we_time_management;
CREATE TABLE we_time_management(
`id` VARCHAR (50) NOT NULL COMMENT '时间ID',
`effdt` date NOT NULL COMMENT '生效日期',
`status` VARCHAR (1) NOT NULL COMMENT '状态',
`time_desc` VARCHAR (150) NOT NULL COMMENT '状态',
`time_typ` VARCHAR (50) NOT NULL COMMENT '日期类型:SIN:单一时间,CON:连续时间',
`section` INT NOT NULL COMMENT '期次:默认为1',
`start_date` date NOT NULL COMMENT '开始日期',
`end__date` date COMMENT '结束日期',
`start_time` VARCHAR (6) NULL COMMENT '开始时间',
`end_time` VARCHAR (6) NULL COMMENT '结束时间',
`create_user` VARCHAR (100) NULL COMMENT '创建人',
`create_time` TIMESTAMP NOT NULL COMMENT '创建时间',
PRIMARY KEY(id,effdt,status,start_date),/*联合主键*/
KEY idx_id(id),
KEY idx_effdt(effdt),
KEY idx_status(status),
KEY idx_start_date(start_date)
)ENGINE = INNODB  DEFAULT CHARSET = utf8 COMMENT = '时间管理表';

--v8.0产品管理
drop table we_product;
CREATE TABLE we_product(
`id` VARCHAR (50) NOT NULL COMMENT '产品ID',
`product_type` VARCHAR (10) NOT NULL COMMENT '产品类型(主题:subject、达人:talent、场地:place)',
`sub_type` VARCHAR (10) NOT NULL COMMENT '产品子类型(如主题的类型：体育、户外、...)',
`effdt` date NOT NULL COMMENT '生效日期',
`status` VARCHAR (1) NOT NULL COMMENT '状态',
`name` VARCHAR (50) NOT NULL COMMENT '产品名称',
`title` VARCHAR (120) NOT NULL COMMENT '产品标题',
`product_photo` VARCHAR (254) NOT NULL COMMENT '产品图像',
`city` VARCHAR (120) NOT NULL COMMENT '产品所在城市',
`address` VARCHAR (120) NOT NULL COMMENT '产品详细地址',
`introduction` TEXT COMMENT '产品详情介绍',
`create_user` VARCHAR (100) NOT NULL COMMENT '产品创建人',
`create_time` TIMESTAMP NOT NULL COMMENT '创建时间',
PRIMARY KEY(id,product_type,effdt,status),/*联合主键*/
KEY idx_id(id),
KEY idx_product_type(product_type),
KEY idx_effdt(effdt),
KEY idx_status(status)
)ENGINE = INNODB  DEFAULT CHARSET = utf8 COMMENT = '产品管理表';

--v9.0 产品类别表
drop table we_product_type;
CREATE TABLE we_product_type(
`id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '产品类别ID',
`product_type` VARCHAR (50) NOT NULL COMMENT '产品类别(主题:subject、达人:talent、场地:place)',
`name` VARCHAR (50) NOT NULL COMMENT '产品类别名称',
PRIMARY KEY(id),
KEY idx_id(id),
KEY idx_product_type(product_type)
)ENGINE = INNODB AUTO_INCREMENT = 1000 DEFAULT CHARSET = utf8 COMMENT = '产品类别表';

--v9.0 产品子类别表
drop table we_product_subtype;
CREATE TABLE we_product_subtype(
`id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '产品子类别ID',
`product_type_id` BIGINT NOT NULL COMMENT '产品父类别ID',
`sub_type` VARCHAR (50) NOT NULL COMMENT '产品子类别',
`name` VARCHAR (50) NOT NULL COMMENT '产品子类别名称',
PRIMARY KEY(id),
KEY idx_id(id),
KEY idx_product_type_id(product_type_id),
KEY idx_sub_type_id(sub_type_id)
)ENGINE = INNODB AUTO_INCREMENT = 1000 DEFAULT CHARSET = utf8 COMMENT = '产品子类别表';

--v10.0 场地设备表
drop table we_place_device;
CREATE TABLE we_place_device(
`id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
`place_device` VARCHAR (50) NOT NULL COMMENT '场地设备代码',
`name` VARCHAR (50) NOT NULL COMMENT '场地设备名称',
PRIMARY KEY(id),
KEY idx_id(id),
KEY idx_place_device(place_device)
)ENGINE = INNODB AUTO_INCREMENT = 1000 DEFAULT CHARSET = utf8 COMMENT = '场地设备表';

--v11.0 产品发布表
drop table we_product_collect;
CREATE TABLE we_product_collect(
`id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
`time_id` VARCHAR (50)  COMMENT '时间ID',
`price` INT COMMENT '价格',
`subject_id` VARCHAR (50) COMMENT '主题ID',
`talent_id` VARCHAR (50) COMMENT '达人ID',
`place_id` VARCHAR (50) COMMENT '场地ID',
`province` VARCHAR (50) COMMENT '省份',
`city` VARCHAR (50) COMMENT '城市',
`district` VARCHAR (50) COMMENT '区域',
`address` VARCHAR (100) COMMENT '详细地点',
`min_people` INT COMMENT '最少人数',
`max_people` INT COMMENT '最多人数',
`state` VARCHAR (2) NOT NULL COMMENT '状态,0:未审核  1:已拒绝  2:已审核  9:已删除',
`create_user` VARCHAR (100) NOT NULL COMMENT '产品创建人',
`create_time` TIMESTAMP NOT NULL COMMENT '创建时间',
PRIMARY KEY(id),
KEY idx_id(id),
KEY idx_create_user(create_user),
KEY idx_state(state)
)ENGINE = INNODB AUTO_INCREMENT = 1000 DEFAULT CHARSET = utf8 COMMENT = '产品发布表';

--v12.0 全国省份表
drop table we_province;
CREATE TABLE we_province (
  `province_id` bigint(20) NOT NULL,
  `province_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (province_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT = '全国省份表';

-- 全国城市表
drop table we_city;
CREATE TABLE we_city (
  `city_id` bigint(20) NOT NULL,
  `city_name` varchar(50) DEFAULT NULL,
  `zip_code` varchar(50) DEFAULT NULL,
  `province_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`city_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT = '全国城市表';

-- 全国地区表
drop table we_district;
CREATE TABLE we_district (
  `district_id` bigint(20) NOT NULL,
  `district_name` varchar(50) DEFAULT NULL,
  `city_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`district_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT = '全国地区表';

--v13.0 产品订单明细表
drop table we_collect_order;
CREATE TABLE we_collect_order(
`out_trade_no` VARCHAR (120) NOT NULL COMMENT '产品订单号',
`collect_id` BIGINT NOT NULL COMMENT '产品ID',
`time_id` VARCHAR (50) COMMENT '时间ID',
`section` INT COMMENT '时间期次',
`openid` VARCHAR (120) COMMENT 'openid',
`user_name` VARCHAR (50)  COMMENT '联系人姓名',
`user_phone` varchar(20) COMMENT '联系人手机',
`user_addr` VARCHAR (120)  COMMENT '联系人地址',
`price` INT NOT NULL COMMENT '产品单价',
`quantity` INT NOT NULL COMMENT '购买数量',
`total_fee` INT NOT NULL NOT NULL COMMENT '总计费用(分)',
`state` TINYINT NOT NULL DEFAULT 0 COMMENT '状态标识,0:未支付预定了  1:预定已付款 2:已发货 9:用户已删除',
`create_time` TIMESTAMP NOT NULL COMMENT '预定时间',
`pay_time` TIMESTAMP  COMMENT '支付时间',
`remark` TEXT COMMENT '备注',
`transaction_id` VARCHAR (120) COMMENT '微信交易单号',
PRIMARY KEY(out_trade_no,collect_id,openid),/*联合主键*/
KEY idx_out_trade_no(out_trade_no),
KEY idx_activity_id(collect_id),
KEY idx_openid(openid)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='产品订单明细表';

--v14.0 产品收藏表
CREATE TABLE `we_collect_favorite` (
`id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `openid` varchar(120) NOT NULL COMMENT 'openid',
  `product_collect_id` BIGINT NOT NULL COMMENT '产品发布Id',
  `state` tinyint(4) NOT NULL COMMENT '状态标识,0:取消关注  1:已关注',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE = INNODB AUTO_INCREMENT = 1000 DEFAULT CHARSET = utf8 COMMENT = '产品收藏表';

--v15.0产品评论表
CREATE TABLE `we_collect_comment` (
`id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
`openid` varchar(120) NOT NULL COMMENT 'openid',
`product_collect_id` BIGINT NOT NULL COMMENT '产品发布Id',
`comment_text` varchar(150) NOT NULL COMMENT '评论内容,',
`parent_id` BIGINT COMMENT '评论父节点ID',
`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '评论时间',
PRIMARY KEY (`id`)
) ENGINE = INNODB AUTO_INCREMENT = 1000 DEFAULT CHARSET = utf8 COMMENT = '产品评论表';

--v15.0意见反馈表
CREATE TABLE `we_feedback` (
`id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
`openid` varchar(120) NOT NULL COMMENT 'openid',
`text` varchar(150) NOT NULL COMMENT '反馈内容,',
`contacts` varchar(150) COMMENT '联系方式,',
`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '反馈时间',
PRIMARY KEY (`id`)
) ENGINE = INNODB AUTO_INCREMENT = 1000 DEFAULT CHARSET = utf8 COMMENT = '意见反馈表';

--v16.0后台用户信息
drop table we_system_user;
CREATE TABLE we_system_user (
`id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '用户编号',
`user_name` VARCHAR (50) COMMENT '用户名',
`password` VARCHAR (50)  COMMENT '密码',
`unionid` varchar(120)  COMMENT 'unionid',
`openid` varchar(120)  COMMENT 'openid',
`imgurl` VARCHAR (200)  COMMENT '头像logo',
`nick_name` VARCHAR (50) COMMENT '昵称',
`real_name` VARCHAR (50) COMMENT '真实姓名',
`id_card` VARCHAR(18) COMMENT '身份证号码',
`introduction` VARCHAR (30) COMMENT '简介',
`mobile` VARCHAR (11) COMMENT '电话',
`email` VARCHAR (30) COMMENT '邮箱',
`province` VARCHAR (20)  COMMENT '省',
`city` VARCHAR (20)  COMMENT '市',
`address` VARCHAR (50)  COMMENT '详细地址',
`is_authentication` VARCHAR (20)  COMMENT '是否认证过,Y已认证/N未认证 新用户默认为N',
`role_id` VARCHAR (100)  COMMENT '用户角色ID,',
`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (id)
) ENGINE = INNODB AUTO_INCREMENT = 1000  DEFAULT CHARSET = utf8 COMMENT = '后台用户信息表';
alter table we_system_user add certificate_img VARCHAR (200) COMMENT '认证的图片地址';


--2016.09.05 更新--商品属性表 参考:http://www.widlabs.com/article/item-unlimited-specifications
CREATE TABLE `we_goods_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类别ID',
  `name` varchar(120) NOT NULL COMMENT '类别描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品类别表';
CREATE TABLE `we_goods_category_child` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '子类别ID',
  `name` varchar(120) NOT NULL COMMENT '子类别描述',
  `parent_id` int(11) NOT NULL COMMENT '父类别ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品子类别表';

CREATE TABLE `we_goods_attr_key` (
  `attr_key_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品属性ID',
  `attr_name` varchar(120) NOT NULL COMMENT '属性名称',
  PRIMARY KEY (`attr_key_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='商品属性表';
--商品属性值表
CREATE TABLE `we_goods_attr_val` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `attr_key_id` int(11) NOT NULL COMMENT '对应we_item_attr_key.attr_key_id,一对多关联',
  `goods_id` int(11) NOT NULL COMMENT '商品ID',
  `symbol` int(10) NOT NULL COMMENT '属性值编码-商品ID下的属性值的一个序号标记,为提高检索效率,不同商品可重复,同一商品需唯一',
  `attr_value` varchar(255) NOT NULL COMMENT '属性值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='商品属性值表';
--商品SKU表
CREATE TABLE `we_goods_sku` (
  `sku_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品SKU ID',
  `goods_id` int(11) NOT NULL COMMENT '商品ID',
  `attr_symbol_path` varchar(255) NOT NULL COMMENT '属性组合出的规格路径',
  `old_price` int(11) DEFAULT NULL COMMENT '原价',
  `new_price` int(11) NOT NULL COMMENT '现价',
  `promotion_price` int(111) DEFAULT NULL,
  `freight` int(11) DEFAULT NULL COMMENT '运费',
  `stock` int(11) DEFAULT NULL COMMENT '库存数量',
  PRIMARY KEY (`sku_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='商品SKU表';
-- select * from `we_goods_sku` where `goods_id`=6 and `attr_symbol_path`='1,4,7';
--商品表
CREATE TABLE `we_goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `category_id` int(11) NOT NULL COMMENT '父种类ID',
  `category_child_id` int(11) NOT NULL COMMENT '子种类ID',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `title` varchar(120) DEFAULT NULL COMMENT '标题',
  `goods_photos` text COMMENT '图片地址',
  `goods_preview_photo` varchar(254) DEFAULT NULL COMMENT '封面图片',
  `province` varchar(50) DEFAULT NULL COMMENT '省份',
  `city` varchar(50) DEFAULT NULL COMMENT '城市',
  `district` varchar(50) DEFAULT NULL COMMENT '区域',
  `address` varchar(100) DEFAULT NULL COMMENT '详细地点',
  `price` int(11) DEFAULT NULL COMMENT '一口价',
  `is_join_promotion` varchar(1) DEFAULT NULL COMMENT '是否参与促销活动 Y:参加 N:不参加',
  `promotion_start_time` timestamp NULL  COMMENT '促销开始时间',
  `promotion_end_time` timestamp NULL  COMMENT '促销开始时间',
  `is_purchase` varchar(1) DEFAULT 'N' COMMENT '是否限制购买量 N:限制(默认) Y:不限制',
  `purchase_quantity` int(11) DEFAULT NULL COMMENT '限购数量',
  `is_new_goods` varchar(1) DEFAULT 'Y' COMMENT '是否新品 Y:是(默认) N:不是',
  `is_hot_goods` varchar(1) DEFAULT 'N' COMMENT '是否热门 Y:是 N:不是(默认)',
  `is_book_goods` varchar(1) DEFAULT NULL COMMENT '是否预约 Y:必须预约  N:无需预约(默认)',
  `introduction` text COMMENT '详情介绍',
  `create_user` varchar(100) NOT NULL COMMENT '产品创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `state_code` int(11) NOT NULL COMMENT '商品状态码 100:未发布(默认) 200:已发布/上架 300:下架 900:用户删除',
  PRIMARY KEY (`id`),
  KEY `idx_status_code` (`state_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品表';

--产品订单表
CREATE TABLE `we_goods_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `order_no` varchar(120) NOT NULL COMMENT '产品订单号(当前时间戳:20160728081138501)',
  `goods_id` int(11) NOT NULL  COMMENT '商品ID',
  `sku_id` bigint(20) NOT NULL  COMMENT '商品SKU ID',
  `user_name` varchar(50) DEFAULT NULL COMMENT '联系人姓名',
  `user_phone` varchar(20) DEFAULT NULL COMMENT '联系人手机',
  `start_date` date NOT NULL COMMENT '开始日期',
  `end_date` date DEFAULT NULL COMMENT '结束日期',
  `start_time` varchar(6) DEFAULT NULL COMMENT '开始时间',
  `end_time` varchar(6) DEFAULT NULL COMMENT '结束时间',
  `province` varchar(50) DEFAULT NULL COMMENT '省份',
  `city` varchar(50) DEFAULT NULL COMMENT '城市',
  `district` varchar(50) DEFAULT NULL COMMENT '区域',
  `address` varchar(100) DEFAULT NULL COMMENT '详细地点',
  `price` int(11) NOT NULL COMMENT '产品单价',
  `quantity` int(11) NOT NULL COMMENT '购买数量',
  `freight` int(11) NOT NULL DEFAULT '0' COMMENT '运费',
  `total_fee` int(11) NOT NULL COMMENT '总计费用(分)',
  `order_state` tinyint(4) NOT NULL DEFAULT '10' COMMENT '状态标识,10:预定了但未支付  20:已付款 30:退款中  40:已退款 50:已发货 60:已完成 ...',
  `delete_state` varchar(2) NOT NULL DEFAULT 'N' COMMENT '删除状态 N:未删除(默认) Y:已删除',
  `book_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '预定时间',
  `pay_time` timestamp NULL DEFAULT NULL COMMENT '支付时间',
  `remark` text COMMENT '备注',
  `transaction_id` varchar(120) DEFAULT NULL COMMENT '交易单号',
  `create_user` varchar(100) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品订单表';







