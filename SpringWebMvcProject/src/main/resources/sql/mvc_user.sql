CREATE TABLE mvc_user (
	user_id CHAR(40) PRIMARY KEY,
	user_pw CHAR(40) NOT NULL,
	user_name CHAR(40) NOT NULL,
	user_reg_date TIMESTAMP DEFAULT NOW()
);

-- 자동로그인 관련 컬럼 추가
ALTER TABLE mvc_user ADD COLUMN session_id CHAR(60) NOT NULL DEFAULT 'none';
ALTER TABLE mvc_user ADD COLUMN limit_time TIMESTAMP;