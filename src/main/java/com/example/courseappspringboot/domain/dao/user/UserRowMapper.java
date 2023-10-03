package com.example.courseappspringboot.domain.dao.user;

import com.example.courseappspringboot.domain.model.user.Role;
import com.example.courseappspringboot.domain.model.user.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user=User.builder()
                .user_id(rs.getInt("user_id"))
                .first_name(rs.getString("first_name"))
                .last_name(rs.getString("last_name"))
                .email(rs.getString("email"))
                .phone_number(rs.getString("phone_number"))
                .password(rs.getString("password"))
                .role(Role.USER)
                .build();

        return user;
    }
}
