--role enum type
CREATE TYPE user_role AS ENUM(
    'USER',
    'ADMIN',
    'STUDENT',
    'INSTRUCTOR'
);
---create user role tables
CREATE TABLE user_roles(
    user_id SERIAL,
    role user_role,
    FOREIGN KEY (user_id) REFERENCES users_(user_id)

);


