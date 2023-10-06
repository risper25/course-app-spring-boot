package com.example.courseappspringboot.domain.dao.category;

import com.example.courseappspringboot.domain.model.course.Category;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryRowMapper implements RowMapper<Category> {
    @Nullable
    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Category.builder()
        .category_id(rs.getInt("category_id"))
        .category_name(rs.getString("category_name"))
                .build();
    }
}
