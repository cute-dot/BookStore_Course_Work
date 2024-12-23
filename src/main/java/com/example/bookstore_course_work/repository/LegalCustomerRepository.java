package com.example.bookstore_course_work.repository;


import com.example.bookstore_course_work.interfaces.LegalCustomerRep;
import com.example.bookstore_course_work.models.LegalCustomer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LegalCustomerRepository implements LegalCustomerRep {

    private final JdbcTemplate jdbcTemplate;

    public LegalCustomerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<LegalCustomer> legalCustomerRowMapper = (rs, rowNum) -> {
      LegalCustomer legalCustomer = new LegalCustomer();
      legalCustomer.setId(rs.getInt("id"));
      legalCustomer.setCompanyName(rs.getString("companyname"));
      legalCustomer.setFirstname(rs.getString("firstname"));
      legalCustomer.setLastname(rs.getString("lastname"));
      legalCustomer.setSurname(rs.getString("surname"));
      legalCustomer.setNumberOfHouse(rs.getString("numberofhouse"));
      legalCustomer.setBankAccount(rs.getString("bankaccount"));
      legalCustomer.setDocumentInn(rs.getString("documentinn"));
      legalCustomer.setBankId(rs.getInt("bankid"));
      legalCustomer.setStreetId(rs.getInt("streetid"));
      legalCustomer.setCityId(rs.getInt("cityid"));
      return legalCustomer;
    };
    private static final RowMapper<LegalCustomer> legalCustomerRowMapperAll = (rs, rowNum) -> {
        LegalCustomer legalCustomer = new LegalCustomer();
        legalCustomer.setId(rs.getInt("id"));
        legalCustomer.setCompanyName(rs.getString("companyname"));
        legalCustomer.setFirstname(rs.getString("firstname"));
        legalCustomer.setLastname(rs.getString("lastname"));
        legalCustomer.setSurname(rs.getString("surname"));
        legalCustomer.setNumberOfHouse(rs.getString("numberofhouse"));
        legalCustomer.setBankAccount(rs.getString("bankaccount"));
        legalCustomer.setDocumentInn(rs.getString("documentinn"));
        legalCustomer.setBankName(rs.getString("banktittle"));
        legalCustomer.setStreetName(rs.getString("streetname"));
        legalCustomer.setCityName(rs.getString("cityname"));

        return legalCustomer;
    };
    @Override
    public Optional<LegalCustomer> findById(int id) {
        String sql = "select * from legalcustomer where id = ?";
        return Optional.of(jdbcTemplate.queryForObject(sql, legalCustomerRowMapper, id));

    }

    @Override
    public List<LegalCustomer> findAll() {
        String sql = "select l.id, l.companyname, l.firstname, l.lastname, l.surname, l.numberofhouse, " +
                "l.bankaccount, l.documentinn, b.banktittle, s.streetname, c.cityname " +
                "from legalcustomer l join public.bank b on b.id = l.bankid " +
                "join public.city c on c.id = l.cityid " +
                "join public.street s on s.id = l.streetid";
        return jdbcTemplate.query(sql, legalCustomerRowMapperAll);
    }

    @Override
    public void save(LegalCustomer legalCustomer) {
        String sql = "insert into legalcustomer (companyname, firstname," +
                " lastname, surname, numberofhouse, bankaccount," +
                " documentinn, bankid, streetid, cityid) values (?,?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, legalCustomer.getCompanyName(),
                legalCustomer.getFirstname(), legalCustomer.getLastname(),
                legalCustomer.getSurname(), legalCustomer.getNumberOfHouse(),
                legalCustomer.getBankAccount(), legalCustomer.getDocumentInn(),
                legalCustomer.getBankId(), legalCustomer.getStreetId(), legalCustomer.getCityId());
    }

    @Override
    public void update(LegalCustomer legalCustomer, int id) {
        String sql = "update legalcustomer set companyname = ?," +
                "firstname = ? , lastname = ?, surname = ?," +
                "numberofhouse = ?, bankaccount = ?, documentinn = ?," +
                "bankid = ? , streetid = ? , cityid = ? where id = ?";
        jdbcTemplate.update(sql, legalCustomer.getCompanyName(),
                legalCustomer.getFirstname(), legalCustomer.getLastname(), legalCustomer.getSurname(),
                legalCustomer.getNumberOfHouse(), legalCustomer.getBankAccount(),
                legalCustomer.getDocumentInn(), legalCustomer.getBankId(), legalCustomer.getStreetId(),
                legalCustomer.getCityId(), id);
    }

    @Override
    public void deleteById(int id) {
        String sql = "delete from legalcustomer where id = ?";
        jdbcTemplate.update(sql, id);
    }
}
