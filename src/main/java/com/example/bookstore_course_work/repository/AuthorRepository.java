package com.example.bookstore_course_work.repository;

import com.example.bookstore_course_work.interfaces.AuthorRep;
import com.example.bookstore_course_work.models.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepository implements AuthorRep {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<Author> authorRowMapper = (rs, rowNum) -> {
        Author author = new Author();
        author.setId(rs.getInt("id"));
        author.setFirstname(rs.getString("firstname"));
        author.setLastname(rs.getString("lastname"));
        author.setSurname(rs.getString("surname"));
        return author;
    };
    private static final RowMapper<Author> authorRowMapperAll = (rs, rowNum) -> {
        Author author = new Author();
        author.setId(rs.getInt("id"));
        author.setFullName(rs.getString("fullname"));
        return author;
    };

    @Override
    public Optional<Author> findById(int id) {
        String sql = "select * from author where id = ?";
        return Optional.of(jdbcTemplate.queryForObject(sql, authorRowMapper, id));
    }

    @Override
    public List<Author> findAll() {
        String sql = "select id, concat(firstname,' ', lastname, ' ', surname ) as fullname from author";
        return jdbcTemplate.query(sql, authorRowMapperAll);
    }

    @Override
    public void save(Author author) {
        String sql = "insert into author (firstname, lastname, surname) values (?,?,?)";
        jdbcTemplate.update(sql, author.getFirstname(), author.getLastname(), author.getSurname());
    }

    @Override
    public void update(Author author, int id) {
        String sql = "update author set firstname = ? , lastname = ?, surname = ? where id = ?";
        jdbcTemplate.update(sql, author.getFirstname(), author.getLastname(), author.getSurname(), id);
    }

    @Override
    public void deleteById(int id) {
        String sql = "delete from author where id = ?";
        jdbcTemplate.update(sql, id);
    }
}
