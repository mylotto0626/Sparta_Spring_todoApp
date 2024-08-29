CREATE TABLE `user` (
                        userId BIGINT NOT NULL AUTO_INCREMENT,
                        username VARCHAR(255) NOT NULL UNIQUE,
                        email VARCHAR(255) NOT NULL,
                        createdAt DATETIME NOT NULL,
                        updatedAt DATETIME NOT NULL,
                        PRIMARY KEY (userId)
);

CREATE TABLE `todo` (
                        todoId BIGINT NOT NULL AUTO_INCREMENT,
                        userId BIGINT NOT NULL,
                        title VARCHAR(255) NOT NULL,
                        content TEXT NOT NULL,
                        createdAt DATETIME NOT NULL,
                        updatedAt DATETIME NOT NULL,
                        PRIMARY KEY (todoId),
                        FOREIGN KEY (userId) REFERENCES user(userId) ON DELETE CASCADE
);

CREATE TABLE `comment` (
                           commentId BIGINT NOT NULL AUTO_INCREMENT,
                           todoId BIGINT NOT NULL,
                           content TEXT NOT NULL,
                           username VARCHAR(255) NOT NULL,
                           createdAt DATETIME NOT NULL,
                           updatedAt DATETIME NOT NULL,
                           PRIMARY KEY (`commentId`),
                           FOREIGN KEY (todoId) REFERENCES todo(todoId) ON DELETE CASCADE
);

CREATE TABLE `user_todo` (
                             userId BIGINT NOT NULL,
                             todoId BIGINT NOT NULL,
                             PRIMARY KEY (userId, todoId),
                             FOREIGN KEY (userId) REFERENCES user(userId) ON DELETE CASCADE,
                             FOREIGN KEY (todoId) REFERENCES todo(todoId) ON DELETE CASCADE
);

ALTER TABLE `todo` add FOREIGN KEY (`username`) REFERENCES `user`(`username`) ON DELETE CASCADE;



