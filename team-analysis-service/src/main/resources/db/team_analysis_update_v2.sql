-- 首先禁用外键检查
SET FOREIGN_KEY_CHECKS = 0;

-- 检查并删除外键约束（如果存在）
SELECT COUNT(1) INTO @exists FROM information_schema.TABLE_CONSTRAINTS 
WHERE CONSTRAINT_SCHEMA = DATABASE() 
AND TABLE_NAME = 'team_screening_results' 
AND CONSTRAINT_NAME = 'team_screening_results_ibfk_1';

SET @drop_fk_1 = IF(@exists > 0, 
    'ALTER TABLE team_screening_results DROP FOREIGN KEY team_screening_results_ibfk_1',
    'SELECT 1');
PREPARE stmt1 FROM @drop_fk_1;
EXECUTE stmt1;
DEALLOCATE PREPARE stmt1;

SELECT COUNT(1) INTO @exists FROM information_schema.TABLE_CONSTRAINTS 
WHERE CONSTRAINT_SCHEMA = DATABASE() 
AND TABLE_NAME = 'teams' 
AND CONSTRAINT_NAME = 'teams_ibfk_1';

SET @drop_fk_2 = IF(@exists > 0, 
    'ALTER TABLE teams DROP FOREIGN KEY teams_ibfk_1',
    'SELECT 1');
PREPARE stmt2 FROM @drop_fk_2;
EXECUTE stmt2;
DEALLOCATE PREPARE stmt2;

-- 检查并删除可能存在的旧约束
SELECT COUNT(1) INTO @exists FROM information_schema.TABLE_CONSTRAINTS 
WHERE CONSTRAINT_SCHEMA = DATABASE() 
AND TABLE_NAME = 'team_screening_results' 
AND CONSTRAINT_NAME = 'fk_screening_requirement';

SET @drop_fk_3 = IF(@exists > 0, 
    'ALTER TABLE team_screening_results DROP FOREIGN KEY fk_screening_requirement',
    'SELECT 1');
PREPARE stmt3 FROM @drop_fk_3;
EXECUTE stmt3;
DEALLOCATE PREPARE stmt3;

SELECT COUNT(1) INTO @exists FROM information_schema.TABLE_CONSTRAINTS 
WHERE CONSTRAINT_SCHEMA = DATABASE() 
AND TABLE_NAME = 'teams' 
AND CONSTRAINT_NAME = 'fk_team_requirement';

SET @drop_fk_4 = IF(@exists > 0, 
    'ALTER TABLE teams DROP FOREIGN KEY fk_team_requirement',
    'SELECT 1');
PREPARE stmt4 FROM @drop_fk_4;
EXECUTE stmt4;
DEALLOCATE PREPARE stmt4;

SELECT COUNT(1) INTO @exists FROM information_schema.TABLE_CONSTRAINTS 
WHERE CONSTRAINT_SCHEMA = DATABASE() 
AND TABLE_NAME = 'team_analysis_results' 
AND CONSTRAINT_NAME = 'fk_analysis_requirement';

SET @drop_fk_5 = IF(@exists > 0, 
    'ALTER TABLE team_analysis_results DROP FOREIGN KEY fk_analysis_requirement',
    'SELECT 1');
PREPARE stmt5 FROM @drop_fk_5;
EXECUTE stmt5;
DEALLOCATE PREPARE stmt5;

-- 修改所有相关表的字段类型
ALTER TABLE team_requirements MODIFY COLUMN id BIGINT NOT NULL AUTO_INCREMENT;
ALTER TABLE team_screening_results MODIFY COLUMN requirement_id BIGINT NOT NULL;
ALTER TABLE teams MODIFY COLUMN requirement_id BIGINT NOT NULL;

-- 重新添加外键约束（使用唯一的约束名称）
ALTER TABLE team_screening_results ADD CONSTRAINT fk_screening_requirement_v2 
    FOREIGN KEY (requirement_id) REFERENCES team_requirements(id);
ALTER TABLE teams ADD CONSTRAINT fk_team_requirement_v2 
    FOREIGN KEY (requirement_id) REFERENCES team_requirements(id);

-- 删除旧的team_analysis_results表（如果存在）
DROP TABLE IF EXISTS team_analysis_results;

-- 创建班子分析结果表
CREATE TABLE team_analysis_results (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    requirement_id BIGINT NOT NULL,
    status INT DEFAULT 0 COMMENT '分析状态（0:进行中, 1:已完成, 2:失败）',
    description VARCHAR(255) DEFAULT NULL COMMENT '分析描述',
    recommended_staff_ids TEXT COMMENT '推荐的人员ID列表（JSON格式）',
    analysis_report TEXT COMMENT '班子结构分析报告',
    match_score INT COMMENT '匹配度评分（0-100）',
    age_distribution VARCHAR(255) COMMENT '年龄分布',
    gender_ratio VARCHAR(255) COMMENT '性别比例',
    skill_coverage VARCHAR(255) COMMENT '技能覆盖度',
    team_structure TEXT COMMENT '团队结构（JSON格式，包含职称、学历等分布）',
    recommendation_reason TEXT COMMENT '推荐原因（JSON格式）',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_requirement_id (requirement_id),
    INDEX idx_create_time (create_time),
    CONSTRAINT fk_analysis_requirement_v2 FOREIGN KEY (requirement_id) REFERENCES team_requirements(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 重新启用外键检查
SET FOREIGN_KEY_CHECKS = 1; 