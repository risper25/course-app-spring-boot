package com.example.courseappspringboot.domain.dao.user;

import com.example.courseappspringboot.domain.model.user.Role;
import com.example.courseappspringboot.domain.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao{
    private final JdbcTemplate jdbcTemplate;
    @Override
    public void save(User user) {
        String sql = "INSERT INTO users_(first_name, last_name, email, phone_number, password) " +
                "VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.update(
                sql,
                user.getFirst_name(),
                user.getLast_name(),
                user.getEmail(),
                user.getPhone_number(),
                user.getPassword()
        );
        //add role user
    }

    @Override
    public User getUserById(int user_id) {
        String sql="SELECT * FROM users_ WHERE user_id=?";
        Object[] args = {user_id };

        return jdbcTemplate.queryForObject(sql,  new BeanPropertyRowMapper<>(User.class),args);
    }

    @Override
    public User getUserByEmail(String email) {

            String sql="SELECT * FROM users_ WHERE email=?";
            Object[] args = {email};

            return jdbcTemplate.queryForObject(sql, new UserRowMapper(),args);

    }

    @Override
    public void update(User user) {
        String sql="UPDATE users_ SET first_name=? , last_name=? , email=? , phone_number=?,password=?,code=?,code_sent_at=?";
        jdbcTemplate.update(sql,
                user.getFirst_name(),
                user.getLast_name(),
                user.getEmail(),
                user.getPhone_number(),
                user.getPassword(),
                user.getCode(),
                user.getCode_sent_At());

    }




    @Override
    public void deleteById(int user_id) {
        String sql="DELETE FROM users_ WHERE user_id=? ";
        Object[] args = {user_id };
        jdbcTemplate.update(sql,args);

    }

    @Override
    public List<User> getAllUsers() {
        String sql ="SELECT * FROM users_";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public void add_role(int userId,String role_name) {
        String sql="INSERT INTO user_roles(user_id,role) VALUES(?,?)";
        Object[] args = {role_name,userId };
        jdbcTemplate.update(sql,args);

    }
    public List<Role> getRolesByUserId(int userId){
        String sql="SELECT role FROM user_roles WHERE user_id=?";

        Object[] args = {userId };

        return jdbcTemplate.queryForList(sql,Role.class,args);

    }
    public List<Role> getRolesByEmail(String email){
        String sql="SELECT ur.role FROM user_roles ur " +
                "INNER JOIN users_  u ON ur.user_id=u.user_id" +
                "WHERE u.email=?";
        Object[] args ={email};
        return jdbcTemplate.queryForList(sql,Role.class,args);
    }
}
