package com.example.courseappspringboot.domain.dao.content;

import com.example.courseappspringboot.domain.model.course.Content;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContentRowMapper implements RowMapper<Content> {
    @Nullable
    @Override
    public Content mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Content.builder()
                .content_id(rs.getInt("content_id"))
                .module_id(rs.getInt("module_id"))
                .content_order(rs.getInt("content_order"))
                .content_text(rs.getString("content_text"))
                .video_url(rs.getString("video_url"))
                .created_at(rs.getTimestamp("created_at")).build();
    }
}
