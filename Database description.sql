-- 创建数据库，命名为 university_personnel_management
CREATE DATABASE IF NOT EXISTS university_personnel_management
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

-- 使用该数据库
USE university_personnel_management;

-- 创建用户表，存储系统用户信息
CREATE TABLE IF NOT EXISTS users (
    -- 用户 ID，自增主键
    id INT AUTO_INCREMENT PRIMARY KEY,
    -- 用户名，唯一且不能为空
    username VARCHAR(255) NOT NULL UNIQUE,
    -- 用户密码，加密存储
    password VARCHAR(255) NOT NULL,
    -- 用户角色，如超级管理员、校区领导等
    role ENUM('super_admin', 'campus_leader', 'personnel_specialist', 'department_head', 'cadre') NOT NULL,
    -- 创建时间，默认为当前时间
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    -- 更新时间，在数据更新时自动更新
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 创建人员基础信息表，存储人员的基本资料
CREATE TABLE IF NOT EXISTS personnel_basic_info (
    -- 人员 ID，自增主键
    id INT AUTO_INCREMENT PRIMARY KEY,
    -- 姓名
    name VARCHAR(255) NOT NULL,
    -- 性别
    gender ENUM('male', 'female') NOT NULL,
    -- 年龄
    age INT,
    -- 职务
    position VARCHAR(255),
    -- 职称
    title VARCHAR(255),
    -- 所属部门
    department VARCHAR(255),
    -- 入职时间
    entry_time DATE,
    -- 学历
    education VARCHAR(255),
    -- 学位
    degree VARCHAR(255),
    -- 毕业院校
    university VARCHAR(255),
    -- 专业
    major VARCHAR(255),
    -- 手机号码
    phone VARCHAR(20),
    -- 电子邮箱
    email VARCHAR(255),
    -- 政治面貌
    political_status VARCHAR(255),
    -- 籍贯
    native_place VARCHAR(255),
    -- 婚姻状况
    marital_status ENUM('single', 'married', 'divorced', 'widowed'),
    -- 民族
    ethnicity VARCHAR(255),
    -- 创建时间，默认为当前时间
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    -- 更新时间，在数据更新时自动更新
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 创建人员详细信息表，存储人员的详细资料
CREATE TABLE IF NOT EXISTS personnel_detail_info (
    -- 详细信息 ID，自增主键
    id INT AUTO_INCREMENT PRIMARY KEY,
    -- 关联的人员 ID
    personnel_id INT NOT NULL,
    -- 身份证号
    id_card_number VARCHAR(18),
    -- 紧急联系人姓名
    emergency_contact_name VARCHAR(255),
    -- 紧急联系人电话
    emergency_contact_phone VARCHAR(20),
    -- 家庭住址
    home_address VARCHAR(255),
    -- 主讲课程名称
    teaching_courses TEXT,
    -- 课程学时
    course_hours INT,
    -- 授课班级数量
    teaching_classes INT,
    -- 教学评价分数
    teaching_evaluation_score DECIMAL(5, 2),
    -- 教学成果奖名称及等级
    teaching_awards TEXT,
    -- 编写教材名称及出版时间
    teaching_materials TEXT,
    -- 论文发表期刊名称
    research_journals TEXT,
    -- 论文题目
    research_papers TEXT,
    -- 发表时间
    publication_time DATE,
    -- 影响因子
    impact_factor DECIMAL(5, 2),
    -- 科研项目名称
    research_projects TEXT,
    -- 项目编号
    project_numbers TEXT,
    -- 项目经费
    project_funds DECIMAL(10, 2),
    -- 项目起止时间
    project_time_period TEXT,
    -- 专利名称
    patents TEXT,
    -- 专利类型
    patent_types TEXT,
    -- 授权时间
    patent_authorization_time DATE,
    -- 所管理部门名称
    management_departments TEXT,
    -- 管理职责描述
    management_responsibilities TEXT,
    -- 管理团队人数
    management_team_size INT,
    -- 重大管理决策事项描述
    management_decisions TEXT,
    -- 参加培训名称
    training_names TEXT,
    -- 培训时间
    training_times TEXT,
    -- 培训地点
    training_locations TEXT,
    -- 培训内容概述
    training_contents TEXT,
    -- 培训证书名称
    training_certificates TEXT,
    -- 创建时间，默认为当前时间
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    -- 更新时间，在数据更新时自动更新
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    -- 关联人员基础信息表的外键约束
    FOREIGN KEY (personnel_id) REFERENCES personnel_basic_info(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 创建标签表，存储人员的标签信息
CREATE TABLE IF NOT EXISTS tags (
    -- 标签 ID，自增主键
    id INT AUTO_INCREMENT PRIMARY KEY,
    -- 标签名称
    tag_name VARCHAR(255) NOT NULL,
    -- 标签类型，如系统生成或用户自定义
    tag_type ENUM('system_generated', 'user_defined') NOT NULL,
    -- 创建时间，默认为当前时间
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    -- 更新时间，在数据更新时自动更新
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 创建人员标签关联表，记录人员与标签的关联关系
CREATE TABLE IF NOT EXISTS personnel_tags (
    -- 关联 ID，自增主键
    id INT AUTO_INCREMENT PRIMARY KEY,
    -- 人员 ID
    personnel_id INT NOT NULL,
    -- 标签 ID
    tag_id INT NOT NULL,
    -- 创建时间，默认为当前时间
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    -- 关联人员基础信息表的外键约束
    FOREIGN KEY (personnel_id) REFERENCES personnel_basic_info(id),
    -- 关联标签表的外键约束
    FOREIGN KEY (tag_id) REFERENCES tags(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 创建会议操作日志表，记录用户在会议中的操作行为
CREATE TABLE IF NOT EXISTS meeting_operation_logs (
    -- 日志 ID，自增主键
    id INT AUTO_INCREMENT PRIMARY KEY,
    -- 用户 ID
    user_id INT NOT NULL,
    -- 操作类型，如点击人员条目、标签筛选等
    operation_type VARCHAR(255) NOT NULL,
    -- 操作时间
    operation_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    -- 关联用户表的外键约束
    FOREIGN KEY (user_id) REFERENCES users(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 创建班子需求表，存储班子的需求信息
CREATE TABLE IF NOT EXISTS team_requirements (
    -- 需求 ID，自增主键
    id INT AUTO_INCREMENT PRIMARY KEY,
    -- 需求发起用户 ID
    user_id INT NOT NULL,
    -- 班子规模
    team_size INT,
    -- 岗位需求
    position_requirements TEXT,
    -- 能力要求
    ability_requirements TEXT,
    -- 团队协作能力要求
    teamwork_ability_requirements TEXT,
    -- 是否需要团队组建
    need_team_formation BOOLEAN DEFAULT false,
    -- 创建时间，默认为当前时间
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    -- 更新时间，在数据更新时自动更新
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    -- 关联用户表的外键约束
    FOREIGN KEY (user_id) REFERENCES users(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 创建班子筛选结果表，存储班子筛选的结果信息
CREATE TABLE IF NOT EXISTS team_screening_results (
    -- 结果 ID，自增主键
    id INT AUTO_INCREMENT PRIMARY KEY,
    -- 班子需求 ID
    requirement_id INT NOT NULL,
    -- 人员 ID
    personnel_id INT NOT NULL,
    -- 岗位
    position VARCHAR(255),
    -- 能力匹配度评分
    ability_match_score DECIMAL(5, 2),
    -- 创建时间，默认为当前时间
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    -- 更新时间，在数据更新时自动更新
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    -- 关联班子需求表的外键约束
    FOREIGN KEY (requirement_id) REFERENCES team_requirements(id),
    -- 关联人员基础信息表的外键约束
    FOREIGN KEY (personnel_id) REFERENCES personnel_basic_info(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 创建班子表，存储组建的班子信息
CREATE TABLE IF NOT EXISTS teams (
    -- 班子 ID，自增主键
    id INT AUTO_INCREMENT PRIMARY KEY,
    -- 班子需求 ID
    requirement_id INT NOT NULL,
    -- 班子名称
    team_name VARCHAR(255),
    -- 创建时间，默认为当前时间
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    -- 更新时间，在数据更新时自动更新
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    -- 关联班子需求表的外键约束
    FOREIGN KEY (requirement_id) REFERENCES team_requirements(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 创建班子成员表，记录班子的成员信息
CREATE TABLE IF NOT EXISTS team_members (
    -- 成员 ID，自增主键
    id INT AUTO_INCREMENT PRIMARY KEY,
    -- 班子 ID
    team_id INT NOT NULL,
    -- 人员 ID
    personnel_id INT NOT NULL,
    -- 创建时间，默认为当前时间
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    -- 更新时间，在数据更新时自动更新
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    -- 关联班子表的外键约束
    FOREIGN KEY (team_id) REFERENCES teams(id),
    -- 关联人员基础信息表的外键约束
    FOREIGN KEY (personnel_id) REFERENCES personnel_basic_info(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 创建班子分析报告表，存储班子的分析报告信息
CREATE TABLE IF NOT EXISTS team_analysis_reports (
    -- 报告 ID，自增主键
    id INT AUTO_INCREMENT PRIMARY KEY,
    -- 班子 ID
    team_id INT NOT NULL,
    -- 报告内容
    report_content TEXT,
    -- 创建时间，默认为当前时间
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    -- 更新时间，在数据更新时自动更新
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    -- 关联班子表的外键约束
    FOREIGN KEY (team_id) REFERENCES teams(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 创建班子动态监视表，记录班子的动态信息
CREATE TABLE IF NOT EXISTS team_dynamic_monitoring (
    -- 监视 ID，自增主键
    id INT AUTO_INCREMENT PRIMARY KEY,
    -- 班子 ID
    team_id INT NOT NULL,
    -- 人员变动信息
    personnel_changes TEXT,
    -- 档案变动信息
    profile_changes TEXT,
    -- 风险评估信息
    risk_assessment TEXT,
    -- 优化建议信息
    optimization_suggestions TEXT,
    -- 创建时间，默认为当前时间
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    -- 更新时间，在数据更新时自动更新
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    -- 关联班子表的外键约束
    FOREIGN KEY (team_id) REFERENCES teams(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 创建人员培训需求表，存储人员的培训需求信息
CREATE TABLE IF NOT EXISTS personnel_training_needs (
    -- 需求 ID，自增主键
    id INT AUTO_INCREMENT PRIMARY KEY,
    -- 人员 ID
    personnel_id INT NOT NULL,
    -- 能力短板
    ability_shortcomings TEXT,
    -- 推荐培训课程
    recommended_courses TEXT,
    -- 创建时间，默认为当前时间
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    -- 更新时间，在数据更新时自动更新
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    -- 关联人员基础信息表的外键约束
    FOREIGN KEY (personnel_id) REFERENCES personnel_basic_info(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 创建培训人员名单表，存储培训人员的名单信息
CREATE TABLE IF NOT EXISTS training_personnel_lists (
    -- 名单 ID，自增主键
    id INT AUTO_INCREMENT PRIMARY KEY,
    -- 培训课程或主题
    training_course TEXT,
    -- 人员 ID
    personnel_id INT NOT NULL,
    -- 岗位
    position VARCHAR(255),
    -- 培训优先级
    training_priority INT,
    -- 创建时间，默认为当前时间
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    -- 更新时间，在数据更新时自动更新
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    -- 关联人员基础信息表的外键约束
    FOREIGN KEY (personnel_id) REFERENCES personnel_basic_info(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 创建任务工单表，存储任务工单的信息
CREATE TABLE IF NOT EXISTS task_orders (
    -- 工单 ID，自增主键
    id INT AUTO_INCREMENT PRIMARY KEY,
    -- 任务发起用户 ID
    initiator_id INT NOT NULL,
    -- 任务接收用户 ID
    receiver_id INT NOT NULL,
    -- 任务需求内容
    task_requirements TEXT,
    -- 任务优先级
    task_priority ENUM('high', 'medium', 'low') NOT NULL,
    -- 期望完成时间
    expected_completion_time DATE,
    -- 任务状态，如待办、执行中、已完成
    task_status ENUM('pending', 'in_progress', 'completed') NOT NULL,
    -- 创建时间，默认为当前时间
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    -- 更新时间，在数据更新时自动更新
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    -- 关联用户表的外键约束，任务发起用户
    FOREIGN KEY (initiator_id) REFERENCES users(id),
    -- 关联用户表的外键约束，任务接收用户
    FOREIGN KEY (receiver_id) REFERENCES users(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 创建任务执行记录表，记录任务的执行情况
CREATE TABLE IF NOT EXISTS task_execution_records (
    -- 记录 ID，自增主键
    id INT AUTO_INCREMENT PRIMARY KEY,
    -- 任务工单 ID
    task_order_id INT NOT NULL,
    -- 执行进度信息
    execution_progress TEXT,
    -- 执行时间
    execution_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    -- 关联任务工单表的外键约束
    FOREIGN KEY (task_order_id) REFERENCES task_orders(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 创建任务结果反馈表，存储任务的结果反馈信息
CREATE TABLE IF NOT EXISTS task_result_feedbacks (
    -- 反馈 ID，自增主键
    id INT AUTO_INCREMENT PRIMARY KEY,
    -- 任务工单 ID
    task_order_id INT NOT NULL,
    -- 反馈内容
    feedback_content TEXT,
    -- 反馈时间
    feedback_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    -- 关联任务工单表的外键约束
    FOREIGN KEY (task_order_id) REFERENCES task_orders(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
