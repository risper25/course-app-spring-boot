--course levels
CREATE TYPE course_levels AS ENUM(
'BEGGINER',
'INTERMEDIATE',
'ADVANCED'

);
--course categories
CREATE TABLE course_categories(
 category_id SERIAL
  PRIMARY KEY,
 category_name TEXT UNIQUE
 );

--course table
CREATE TABLE courses(
    course_id SERIAL PRIMARY KEY,
    tutor_id SERIAL,
    category_id SERIAL,
    course_title VARCHAR(255) UNIQUE,
    course_description VARCHAR(200),
    course_duration  VARCHAR(200),
    course_level course_levels,
    course_price DECIMAL(10,2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (tutor_id) REFERENCES users_(user_id),
    FOREIGN KEY (category_id) REFERENCES course_categories(category_id_id)
);

--module table
CREATE TABLE modules(
    module_id SERIAL PRIMARY KEY,
    course_id SERIAL,
    module_title VARCHAR(100),
    module_description VARCHAR(225),
    module_order INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    FOREIGN KEY (course_id) REFERENCES course(course_id)
);

--content table
CREATE TABLE contents(
    content_id SERIAL PRIMARY KEY,
    module_id SERIAL,
    content_order INT,
    video_url VARCHAR(300),
    content_text VARCHAR(300),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    FOREIGN KEY (module_id) REFERENCES module(module_id)
);





