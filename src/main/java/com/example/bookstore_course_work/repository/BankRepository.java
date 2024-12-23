package com.example.bookstore_course_work.repository;

import com.example.bookstore_course_work.interfaces.BankRep;
import com.example.bookstore_course_work.models.Author;
import com.example.bookstore_course_work.models.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;



@Repository
public class BankRepository implements BankRep {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BankRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<Bank> bankRowMapper = (rs, rowNum) -> {
        Bank bank = new Bank();
        bank.setId(rs.getInt("id"));
        bank.setBankTittle(rs.getString("banktittle"));
        return bank;
    };

    @Override
    public Optional<Bank> findById(int id) {
        String sql = "select * from bank where id = ?";
        return Optional.of(jdbcTemplate.queryForObject(sql, bankRowMapper, id));
    }

    @Override
    public List<Bank> findAll() {
        String sql = "select * from bank";
        return jdbcTemplate.query(sql, bankRowMapper);
    }

    @Override
    public void save(Bank bank) {
        String sql = "insert into bank (id, banktittle) values (?,?)";
        jdbcTemplate.update(sql, bank.getId(), bank.getBankTittle());
    }

    @Override
    public void update(Bank bank, int id) {
        String sql = "update bank set id =?, banktittle = ? where id = ?";
        jdbcTemplate.update(sql, bank.getId(), bank.getBankTittle());
    }

    @Override
    public void deleteById(int id) {
        String sql = "delete from bank where id = ?";
        jdbcTemplate.update(sql, id );
    }
}
