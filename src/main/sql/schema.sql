CREATE DATABASE neuq_info;
use neuq_info;
CREATE TABLE user_like_post(
`postId` bigint NOT NULL  COMMENT '文章id',
`userId` bigint NOT NULL  COMMENT'用户id',
`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (postId,userId)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='点赞表';

CREATE TABLE post(
`postId` bigint NOT NULL AUTO_INCREMENT COMMENT '文章id',
`userId` bigint NOT NULL COMMENT '创建者id',
`title` VARCHAR (30) NOT NULL COMMENT '文章标题',
`content` VARCHAR (120) NOT NULL COMMENT '文章内容',
`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`secret` tinyint NOT NULL DEFAULT 0 COMMENT '是否匿名 0：匿名 1：非匿名',
`comment_count` int(4) NOT NULL DEFAULT 0 COMMENT '评论数量',
`like_count` int(4) NOT NULL DEFAULT 0 COMMENT '点赞数量',
PRIMARY KEY (postId),
KEY idx_create_time(create_time),
key idx_like_count(like_count),
KEY idx_comment_count(comment_count)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章表';

INSERT INTO post (userId,title,content, secret)
        VALUES
        (1000,'测试1','测试1',1),
        (1001,'测试2','测试2',0),
        (1002,'测试3','测试3',1),
        (1000,'测试4','测试4',1),
        (1001,'测试5','测试5',1),
        (1002,'测试6','测试6',0),
        (1003,'测试7','测试7',1),
        (1004,'测试8','测试8',2),
        (1005,'测试9','测试9',1),
        (1006,'测试10','测试10',0),
        (1005,'测试11','测试11',1),
        (1005,'测试12','测试12',1),
        (1005,'测试13','测试13',1),
        (1005,'测试14','测试14',1),
        (1005,'测试15','测试15',1);

CREATE TABLE comment(
`commentId` bigint NOT NULL AUTO_INCREMENT COMMENT '评论id',
`postId` bigint NOT NULL COMMENT '文章id',
`avatarUrl` VARCHAR(200) NOT NULL COMMENT '头像url',
`userId` bigint NOT NULL COMMENT '创建者id',
`like_count` int(4) NOT NULL DEFAULT 0 COMMENT '点赞数量',
`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (commentId),
KEY idx_create_time(create_time),
key idx_like_count(like_count)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评论表';

INSERT INTO comment(postId,avatarUrl,userId)
VALUES
(1,'avater',1000),
(2,'avater',1000),
(3,'avater',1000),
(1,'avater',1001),
(2,'avater',1001),
(3,'avater',1002);



CREATE TABLE user(
`userId` bigint NOT NULL AUTO_INCREMENT COMMENT'用户id',
`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`openId` VARCHAR(100) NOT NULL COMMENT 'openId',
`avatarUrl` VARCHAR(200) NOT NULL COMMENT '头像url',
`nickName` VARCHAR (50) NOT NULL COMMENT '用户昵称',
`gender` VARCHAR (4) NOT NULL COMMENT '用户性别 性别 0：未知、1：男、2：女',
`city` VARCHAR (50) NOT NULL COMMENT '用户城市',
`province` VARCHAR (50) NOT NULL COMMENT '用户省份',
`country` VARCHAR (50) NOT NULL COMMENT '用户国家',
`unionId` VARCHAR(100) NOT NULL COMMENT 'unionId',
`jwUser` VARCHAR(100) NOT NULL COMMENT '教务系统用户名',
`jwPwd` VARCHAR(100) NOT NULL COMMENT '教务系统密码',
PRIMARY KEY (userId),
KEY idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='用户表';

INSERT user(openId,avatarUrl,nickName,gender,city,province,country,unionId,jwUser,jwPwd)
VALUES
('openId','avatarUrl','小明','1','衡水','河北','中国','unionid','1157103','1157103'),
('openId','avatarUrl','小红','2','衡水','河北','中国','unionid','1157103','1157103'),
('openId','avatarUrl','小放','1','衡水','河北','中国','unionid','1157103','1157103'),
('openId','avatarUrl','小和','2','衡水','河北','中国','unionid','1157103','1157103'),
('openId','avatarUrl','小饿','1','衡水','河北','中国','unionid','1157103','1157103'),
('openId','avatarUrl','小的','2','衡水','河北','中国','unionid','1157103','1157103'),
('openId','avatarUrl','小对','1','衡水','河北','中国','unionid','1157103','1157103');


