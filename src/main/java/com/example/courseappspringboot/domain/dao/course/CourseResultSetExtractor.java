package com.example.courseappspringboot.domain.dao.course;

import com.example.courseappspringboot.domain.model.course.Content;
import com.example.courseappspringboot.domain.model.course.Course;
import com.example.courseappspringboot.domain.model.course.Module;
import com.example.courseappspringboot.domain.model.user.Role;
import com.example.courseappspringboot.domain.model.user.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseResultSetExtractor implements ResultSetExtractor<List<Course>> {

    @Nullable
    @Override
    public List<Course> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Long,Course> courseMap=new HashMap<>();

        while(rs.next()){
            Long courseId = rs.getLong("course_id");
            Course course = courseMap.get(courseId);
            if(course==null){
                User tutor=User.builder()
                        .user_id(rs.getInt("user_id"))
                        .first_name(rs.getString("first_name"))
                        .last_name(rs.getString("last_name"))
                        .email(rs.getString("email"))
                        .phone_number(rs.getString("phone_number"))
                        .role(Role.USER)
                        .build();
                course=Course.builder()
                        .course_id(rs.getInt("course_id"))
                        .tutor(tutor)
                        .course_title(rs.getString("course_title"))
                        .course_duration_weeks(rs.getInt("course_duration_weeks"))
                        .course_price(rs.getDouble("course_price"))
                        .modules(new ArrayList<>())
                        .created_at(rs.getTimestamp("created_at"))
                        .build();
                courseMap.put(courseId,course);

            }
            Long moduleId=rs.getLong("module_id");
            while(!rs.wasNull()){
                Module module= Module.builder()
                        .module_id(rs.getInt("module_id"))
                        .module_title(rs.getString("module_title"))
                        .module_description(rs.getString("module_description"))
                        .module_order(rs.getInt("module_order"))
                        .contents(new ArrayList<>())
                        .created_at(rs.getTimestamp("created_at"))
                        .course_id(rs.getInt("course_id")).build();
                course.getModules().add(module);
            }
            Long contentId=rs.getLong("content_id");
            while (!rs.wasNull()){
                Content content= Content.builder()
                        .content_id(rs.getInt("content_id"))
                        .content_order(rs.getInt("content_order"))
                        .content_text(rs.getString("content_text"))
                        .video_url(rs.getString("video_url"))
                        .created_at(rs.getTimestamp("created_at"))
                        .module_id(rs.getInt("moduleId")).build();
                course.getModules().get(course.getModules().size() - 1).getContents().add(content);
            }


        }
        return new ArrayList<>(courseMap.values());


    }
}
