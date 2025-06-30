-- 禁用外键检查
SET FOREIGN_KEY_CHECKS = 0;

-- 创建position_need表
CREATE TABLE `position_need` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `team_id` int NOT NULL,
    `position_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '岗位名称',
    `person_num` int NOT NULL COMMENT '岗位需求人数',
    `position` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '职位类型(教师/辅导员/系主任等)',
    `education` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '学历要求',
    `major` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT '专业要求',
    `max_age` int COMMENT '最大年龄',
    `min_age` int COMMENT '最小年龄',
    `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idx_team_id`(`team_id` ASC) USING BTREE,
    CONSTRAINT `fk_position_need_team` FOREIGN KEY (`team_id`) REFERENCES `teams` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- 创建position_need_members表
CREATE TABLE `position_need_members` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `position_need_id` bigint NOT NULL COMMENT '关联的岗位需求ID',
    `personnel_id` int NOT NULL COMMENT '推荐的人员ID',
    `match_degree` decimal(5,2) DEFAULT 0.00 COMMENT '匹配度',
    `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idx_position_need_id`(`position_need_id` ASC) USING BTREE,
    INDEX `idx_personnel_id`(`personnel_id` ASC) USING BTREE,
    CONSTRAINT `fk_position_need_members_position` FOREIGN KEY (`position_need_id`) REFERENCES `position_need` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `fk_position_need_members_personnel` FOREIGN KEY (`personnel_id`) REFERENCES `personnel_basic_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- 启用外键检查
SET FOREIGN_KEY_CHECKS = 1; 