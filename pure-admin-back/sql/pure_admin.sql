create schema if not exists pure_admin;

create table if not exists users_basic_info
(
    id      int AUTO_INCREMENT comment '用户的唯一标识'
        primary key,
    name    varchar(20) null comment '用户的姓名',
    gender  tinyint(1)  null comment '用户的性别 1男 2女',
    age     int         null comment '用户的年龄',
    id_card varchar(18) null comment '用户的身份证号 18位'
)
    charset = utf8mb3
    row_format = DYNAMIC;

create table if not exists users_img_info
(
    id         int          not null comment '用户唯一标识'
        primary key,
    image      varchar(255) null comment '用户人脸图片名',
    video      varchar(255) null comment '用户步态视频名',
    video_data mediumblob   null comment '步态数据'
)
    charset = utf8mb3
    row_format = DYNAMIC;

INSERT INTO `users_basic_info` VALUES (1,'钟子韬', 1, 39, '528095203710318186');
INSERT INTO `users_basic_info` VALUES (2,'孙詩涵', 0, 88, '50540919301030423X');
INSERT INTO `users_basic_info` VALUES (3,'汪子异', 0, 36, '502393201001105759');
INSERT INTO `users_basic_info` VALUES (4, '蒋詩涵', 0, 12, '264252190711205923');
INSERT INTO `users_basic_info` VALUES (5, '苏子异', 1, 77, '506516200710102590');
INSERT INTO `users_basic_info` VALUES (6, '孙詩涵', 0, 88, '50540919301030423X');
INSERT INTO `users_basic_info` VALUES (7, '汪子异', 0, 36, '502393201001105759');
INSERT INTO `users_basic_info` VALUES (8, '蒋詩涵', 0, 12, '264252190711205923');
INSERT INTO `users_basic_info` VALUES (9, '苏子异', 1, 77, '506516200710102590');
INSERT INTO `users_basic_info` VALUES (10, '黄致远', 0, 85, '501403209010051283');
INSERT INTO `users_basic_info` VALUES (11, '戴子异', 1, 35, '151956184810200627');
INSERT INTO `users_basic_info` VALUES (12, '于岚', 0, 83, '13646418381223554X');
INSERT INTO `users_basic_info` VALUES (13, '汪睿', 1, 8, '51140518381131227x');
INSERT INTO `users_basic_info` VALUES (14, '潘嘉伦', 1, 22, '473333204611315812');
INSERT INTO `users_basic_info` VALUES (15, '周杰宏', 0, 77, '171257199004059113');
INSERT INTO `users_basic_info` VALUES (16, '钱岚', 1, 27, '500438192011304607');
INSERT INTO `users_basic_info` VALUES (17, '谭安琪', 0, 29, '360247180111312447');
INSERT INTO `users_basic_info` VALUES (18, '田秀英', 1, 80, '503479182407107977');
INSERT INTO `users_basic_info` VALUES (19, '邵子韬', 0, 65, '503916209611202945');
INSERT INTO `users_basic_info` VALUES (20, '郭子异', 0, 79, '502887188812107184');

INSERT INTO `users_img_info` VALUES (1, '1.png', '2.mp4',  NULL);
INSERT INTO `users_img_info` VALUES (2, '2.png', '1.mp4',  NULL);
INSERT INTO `users_img_info` VALUES (3, '4.png', '4.mp4',  NULL);
INSERT INTO `users_img_info` VALUES (4, '4.png', '1.mp4',  NULL);
INSERT INTO `users_img_info` VALUES (5, '4.png', '4.mp4',  NULL);
INSERT INTO `users_img_info` VALUES (6, '3.png', '3.mp4',  NULL);
INSERT INTO `users_img_info` VALUES (7, '3.png', '1.mp4',  NULL);
INSERT INTO `users_img_info` VALUES (8, '3.png', '3.mp4',  NULL);
INSERT INTO `users_img_info` VALUES (9, '4.png', '2.mp4',  NULL);
INSERT INTO `users_img_info` VALUES (10, '1.png', '3.mp4',  NULL);
INSERT INTO `users_img_info` VALUES (11, '1.png', '2.mp4',  NULL);
INSERT INTO `users_img_info` VALUES (12, '1.png', '1.mp4',  NULL);
INSERT INTO `users_img_info` VALUES (13, '3.png', '3.mp4',  NULL);
INSERT INTO `users_img_info` VALUES (14, '2.png', '4.mp4',  NULL);
INSERT INTO `users_img_info` VALUES (15, '1.png', '4.mp4',  NULL);
INSERT INTO `users_img_info` VALUES (16, '3.png', '1.mp4',  NULL);
INSERT INTO `users_img_info` VALUES (17, '3.png', '2.mp4',  NULL);
INSERT INTO `users_img_info` VALUES (18, '3.png', '1.mp4',  NULL);
INSERT INTO `users_img_info` VALUES (19, '4.png', '2.mp4',  NULL);
INSERT INTO `users_img_info` VALUES (20, '4.png', '2.mp4',  NULL);
INSERT INTO `users_img_info` VALUES (21, '4.png', '2.mp4',  NULL);