package com.example.courseappspringboot.domain.dao.module;

import com.example.courseappspringboot.domain.model.course.Module;
import com.example.courseappspringboot.exceptions.CustomDatabaseException;
import com.example.courseappspringboot.exceptions.ModuleAlreadyExistsException;
import com.example.courseappspringboot.exceptions.ModuleNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@RequiredArgsConstructor
public class ModuleDaoImpl implements ModuleDao{
    private final JdbcTemplate jdbcTemplate;
    private final Logger logger= LoggerFactory.getLogger(ModuleDaoImpl.class);
    @Override
    public Module addModule(Module module) {
        try{
        String sql="INSERT INTO modules(course_id,module_title,module_description,module_order) VALUES(?,?,?,?)";
        jdbcTemplate.update(sql,module.getCourse_id(),module.getModule_title(), module.getModule_description(),module.getModule_order());
        return module;
        }
        catch (DuplicateKeyException e){
            logger.error("Module with id "+module.getModule_id()+" already exists:" + e.getMessage());
            throw new ModuleAlreadyExistsException("Module with id"+" "+module.getModule_id()+" "+"already exists");

        }
        catch (DataAccessException e){
            logger.error("Failure to add module to database:" + e.getMessage());
            throw new CustomDatabaseException("Failure to add module to database");


        }
    }

    @Override
    public void updateModule(Module module) {
        try {
            String sql="UPDATE modules SET course_id=?,module_title=?,module_description=?,module_order=?";
            jdbcTemplate.update(sql,module.getCourse_id(),module.getModule_title(), module.getModule_description(),module.getModule_order());
        }
        catch (DataAccessException e){
            logger.error("Failure to update module :"+ e.getMessage());
            throw new CustomDatabaseException("Failure to update module");

        }

    }

    @Override
    public Module findModuleById(int id) {
        try{
            String sql="SELECT * FROM modules AS m " +
                    "LEFT JOIN contents AS ct ON m.module_id = ct.module_id " +
                    "WHERE m.module_id=?";
            Object[] args ={id};
            List<Module> modules= jdbcTemplate.query(sql,new ModuleResultSetExtractor(),args);

            assert modules != null;
            if(modules.isEmpty()){
                throw new ModuleNotFoundException("Module with id "+id+" does not exist");
            }
            return modules.get(0);
        }
        catch(DataAccessException e){
            throw new ModuleNotFoundException("Module with id: "+id+" not found");
        }
    }

    @Override
    public Module findModuleByTitle(String title) {
        try{
            String sql="SELECT * FROM modules AS m " +
                    "LEFT JOIN contents AS ct ON m.module_id = ct.module_id " +
                    "WHERE m.module_title=?";
            Object[] args ={title};
            List<Module> modules= jdbcTemplate.query(sql,new ModuleResultSetExtractor(),args);
            assert modules != null;
            if(modules.isEmpty()){
                throw new ModuleNotFoundException("Module with title "+title+" does not exist");
            }
            return modules.get(0);
        }

        catch(DataAccessException e){
            throw new ModuleNotFoundException("Module with title "+title+" not found");
        }

    }

    @Override
    public List<Module> findModulesByCourseId(int course_id) {
        try{
            String sql="SELECT * FROM modules AS m " +
                    "LEFT JOIN contents AS ct ON m.module_id = ct.module_id " +
                    "WHERE m.course_id=? ORDER BY m.module_order";
            Object[] args ={course_id};
            return jdbcTemplate.query(sql,new ModuleResultSetExtractor(),args);
        }
        catch(DataAccessException e){
            throw new CustomDatabaseException("Failed to find modules of course_id: "+course_id);
        }
    }
    @Override
    public boolean deleteModulesByCourseId(int course_id) {
        try{
        String sql="DELETE  FROM modules AS m " +
                "USING contents AS ct " +
                "WHERE m.module_id = ct.module_id " +
                "AND m.course_id=?";

        Object[] args={course_id};
        int rows_affected=jdbcTemplate.update(sql,args);
        return rows_affected>0;
        }

        catch(DataAccessException e){
            throw new CustomDatabaseException("Failed to delete Modules of course_id: "+course_id);
        }

    }

    @Override
    public boolean deleteModuleById(int id) {
        try{
            String sql="DELETE  FROM modules AS m " +
                    "USING contents AS ct " +
                    "WHERE m.module_id = ct.module_id " +
                    "AND m.module_id=?";
            Object[] args={id};
            int rows_affected=jdbcTemplate.update(sql,args);
            return rows_affected>0;
        }
        catch(DataAccessException e){
            throw new CustomDatabaseException("Failed to delete module with id : "+id);
        }

    }
}
