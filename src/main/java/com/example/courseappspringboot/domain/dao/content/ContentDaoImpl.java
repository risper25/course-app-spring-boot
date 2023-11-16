package com.example.courseappspringboot.domain.dao.content;

import com.example.courseappspringboot.domain.model.course.Content;
import com.example.courseappspringboot.exceptions.*;
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
public class ContentDaoImpl implements ContentDao{
    private final JdbcTemplate jdbcTemplate;
    Logger logger= LoggerFactory.getLogger(ContentDaoImpl.class);
    @Override
    public Content addContent(Content content) {
        try{String sql ="INSERT INTO contents(module_id,content_order,video_url,content_text) VALUES(?,?,?,?)";
            jdbcTemplate.update(sql,content.getModule_id(),content.getContent_order(),content.getVideo_url(),content.getContent_text());
            return content;
        }
        catch (DuplicateKeyException e){
            logger.error("Content already exists:");
            throw new ContentAlreadyExistsException("Content already exists:" + e.getMessage());

        }
        catch (DataAccessException e){
            logger.error("Failure to add content to  database:");
            throw new CustomDatabaseException("Failure to add content to  database:" + e.getMessage());



        }}

    @Override
    public void updateContent(Content content) {
        try{
            String sql="UPDATE contents SET module_id=?,content_order=?,video_url=?,content_text=?";
            jdbcTemplate.update(sql,content.getModule_id(),content.getContent_order(),content.getVideo_url(),content.getContent_text());
        }
        catch (DataAccessException e){
            logger.error("Failure to update content :");
            throw new CustomDatabaseException("Failure to update content " + e.getMessage());

    }}

    @Override
    public Content findContentById(int id) {
        try {
            String sql="SELECT * FROM contents WHERE content_id=? ORDER BY content_order";
            Object[] args={id};
            Content content= jdbcTemplate.queryForObject(sql,new ContentRowMapper(),args);
            if(content==null){
                throw new ContentNotFoundException("Content with id "+id+" does not exist");
            }
            return content;
        }
        catch (DataAccessException e){
            logger.error("Error in finding Content with id "+id);
            throw new CustomDatabaseException("Error in finding Content with id "+id+ "\n" +e.getMessage());
        }

    }

    @Override
    public List<Content> findContentsByModuleId(int module_id) {
        try{
            String sql="SELECT * FROM contents WHERE module_id=? ORDER BY content_order";
            Object[] args={module_id};
            return jdbcTemplate.query(sql,new ContentRowMapper(),args);
        }
        catch(DataAccessException e){
            throw new CustomDatabaseException("Failed to find contents with module id"+module_id);
        }
    }

    @Override
    public void deleteContentById(int id) {
        try{
            String sql="DELETE * FROM contents WHERE content_id=?";
            Object[] args={id};
            jdbcTemplate.update(sql,args);
        }
        catch(DataAccessException e){
            throw new CustomDatabaseException("Failure in deleting contents with id"+id);
        }


    }

    @Override
    public void deleteContentByModuleId(int module_id) {
        try{
            String sql="DELETE * FROM contents WHERE module_id=?";
            Object[] args={module_id};
            jdbcTemplate.update(sql,args);
        }
        catch(DataAccessException e){
            throw new CustomDatabaseException("Failure in deleting contents with module id"+module_id);
        }


    }
}
