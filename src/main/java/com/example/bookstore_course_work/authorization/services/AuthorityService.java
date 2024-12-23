package com.example.bookstore_course_work.authorization.services;

import com.example.bookstore_course_work.authorization.models.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthorityService {
    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public AuthorityService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<User> userRowMapper = (rs, rowNum) -> {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setRole(rs.getString("role"));
        return user;
    };

    public User authenticate(String username, String password) {
        String sql = "select * from users where username = ?";
        try {

            User user = jdbcTemplate.queryForObject(sql, userRowMapper, username);

            if (user.getPassword() != null && BCrypt.checkpw(password, user.getPassword())) {
                return user;
            } else {
                return null;
            }
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
