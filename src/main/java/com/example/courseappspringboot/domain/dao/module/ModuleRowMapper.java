package com.example.courseappspringboot.domain.dao.module;

import com.example.courseappspringboot.domain.model.course.Module;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ModuleRowMapper implements RowMapper<Module> {
    //course_id,module_title,module_description,module_order
    @Nullable
    @Override
    public Module mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Module.builder()
                .module_id(rs.getInt("module_id"))
                .course_id(rs.getInt("course_id"))
                .module_title(rs.getString("module_title"))
                .module_description(rs.getString("module_description"))
                .module_order(rs.getInt("module_order"))
                .created_at(rs.getTimestamp("created_at")).build();
    }
}
