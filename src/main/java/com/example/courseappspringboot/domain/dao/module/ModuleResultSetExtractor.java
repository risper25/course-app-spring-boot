package com.example.courseappspringboot.domain.dao.module;

import com.example.courseappspringboot.domain.model.course.Content;
import com.example.courseappspringboot.domain.model.course.Course;
import com.example.courseappspringboot.domain.model.course.Module;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModuleResultSetExtractor implements ResultSetExtractor<List<Module>> {
        @Nullable
        @Override
        public List<Module> extractData(ResultSet rs) throws SQLException, DataAccessException {
            Map<Integer, Module> moduleMap = new HashMap<>();
            List<Module> modules = new ArrayList<>();

            while (rs.next()) {
                int moduleId = rs.getInt("module_id");
                Module module = moduleMap.get(moduleId);

                if (module == null) {
                    module = Module.builder()
                            .module_id(moduleId)
                            .course_id(rs.getInt("course_id"))
                            .module_title(rs.getString("module_title"))
                            .module_description(rs.getString("module_description"))
                            .module_order(rs.getInt("module_order"))
                            .created_at(rs.getTimestamp("created_at"))
                            .contents(new ArrayList<>())
                            .build();

                    moduleMap.put(moduleId, module);
                    modules.add(module);
                }

                int contentId = rs.getInt("content_id");
                if (!rs.wasNull()) {
                    Content content = Content.builder()
                            .content_id(contentId)
                            .content_order(rs.getInt("content_order"))
                            .content_text(rs.getString("content_text"))
                            .video_url(rs.getString("video_url"))
                            .created_at(rs.getTimestamp("created_at"))
                            .module_id(moduleId)
                            .build();

                    module.getContents().add(content);
                }
            }

            return modules;
        }
    }


