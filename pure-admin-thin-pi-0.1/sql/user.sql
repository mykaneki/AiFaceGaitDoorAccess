CREATE TABLE users (
    id INT(10) COMMENT '用户的唯一标识',
    name VARCHAR(20) COMMENT '用户的姓名',
    gender TINYINT(1) COMMENT '用户的性别',
    age INT(3) COMMENT '用户的年龄',
    id_number VARCHAR(18) COMMENT '用户的身份证号',
    face_feature BLOB COMMENT '用户的人脸特征向量',
    gait_feature BLOB COMMENT '用户的步态特征向量'
);

INSERT INTO users (id, name, gender, age, id_number, face_feature, gait_feature)
VALUES
    (1, '张三', 1, 25, '123456789012345678', NULL, NULL),
    (2, '李四', 0, 30, '234567890123456789', NULL, NULL),
    (3, '王五', 1, 35, '345678901234567890', NULL, NULL),
    (4, '赵六', 0, 40, '456789012345678901', NULL, NULL),
    (5, '孙七', 1, 45, '567890123456789012', NULL, NULL),
    (6, '周八', 0, 50, '678901234567890123', NULL, NULL),
    (7, '吴九', 1, 55, '789012345678901234', NULL, NULL),
