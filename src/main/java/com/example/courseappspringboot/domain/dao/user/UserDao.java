package com.example.courseappspringboot.domain.dao.user;

import com.example.courseappspringboot.domain.model.user.User;

import java.util.List;

public interface UserDao {
    public void save(User user);
    public User getUserById(int id);
    public User getUserByEmail(String email);
    public void update(User user);
    public void deleteById(int id);
    public List<User> getAllUsers();
    public void add_role(int userId,String role_name);

}
