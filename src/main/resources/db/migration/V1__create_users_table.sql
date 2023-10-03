
--create users table
CREATE TABLE users_(
    user_id SERIAL PRIMARY KEY,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    email VARCHAR(100),
    phone_number VARCHAR(100),
    password VARCHAR(256),
    code VARCHAR(100),
    code_sent_at DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
