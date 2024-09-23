DROP TABLE IF EXISTS users;
CREATE TABLE users  (
                         `user_id` int AUTO_INCREMENT, -- '用户Id'
                         `user_name` varchar(64) NOT NULL, -- '用户名称'
                         `sex` varchar(4) NULL, -- '性别'
                         `age` int NULL, -- '年龄'
                         `dept` int NOT NULL, -- '部门'
                         PRIMARY KEY (`user_id`)
);

-- Records of user
INSERT INTO users VALUES (1, '尤泽', '男', 18, 1);
INSERT INTO users VALUES (2, '张三', '男', 80, 2);
INSERT INTO users VALUES (3, '李二', '男', 16, 1);
INSERT INTO users VALUES (4, '欣如', '女', 18, 1);
