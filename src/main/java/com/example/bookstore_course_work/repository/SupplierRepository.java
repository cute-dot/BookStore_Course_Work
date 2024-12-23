package com.example.bookstore_course_work.repository;


import com.example.bookstore_course_work.interfaces.SupplierRep;
import com.example.bookstore_course_work.models.Supplier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SupplierRepository implements SupplierRep {

    private final JdbcTemplate jdbcTemplate;

    public SupplierRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<Supplier> supplierRowMapper = (rs, rowNum) -> {
        Supplier supplier = new Supplier();
        supplier.setId(rs.getInt("id"));
        supplier.setFirstname(rs.getString("firstname"));
        supplier.setLastname(rs.getString("lastname"));
        supplier.setSurname(rs.getString("surname"));
        supplier.setDocumentInn(rs.getString("documentinn"));
        supplier.setNumberOfHouse(rs.getString("numberofhouse"));
        supplier.setBankAccount(rs.getString("bankaccount"));
        supplier.setBankId(rs.getInt("bankid"));
        supplier.setStreetId(rs.getInt("streetid"));
        supplier.setCityId(rs.getInt("cityid"));
        return supplier;
    };
    private static final RowMapper<Supplier> supplierRowMapperAll = (rs, rowNum) -> {
        Supplier supplier = new Supplier();
        supplier.setId(rs.getInt("id"));
        supplier.setFullName(rs.getString("fullname"));
        supplier.setDocumentInn(rs.getString("documentinn"));
        supplier.setNumberOfHouse(rs.getString("numberofhouse"));
        supplier.setBankAccount(rs.getString("bankaccount"));
        supplier.setBankTittle(rs.getString("banktittle"));
        supplier.setStreetName(rs.getString("streetname"));
        supplier.setCityName(rs.getString("cityname"));

        return supplier;
    };
    @Override
    public Optional<Supplier> findById(int id) {
        String sql = "select * from supplier where id = ?";
        return Optional.of(jdbcTemplate.queryForObject(sql, supplierRowMapper, id));
    }

    @Override
    public List<Supplier> findAll() {
        String sql = "select s.id, CONCAT(s.firstname, ' ', s.lastname, ' ', s.surname) AS fullname," +
                "s.documentinn, s.numberofhouse, s.bankaccount, b.banktittle, s2.streetname, c.cityname " +
                "from supplier s " +
                "join public.bank b on b.id = s.bankid " +
                "join public.city c on c.id = s.cityid join public.street s2 on s2.id = s.streetid";
        return jdbcTemplate.query(sql, supplierRowMapperAll);
    }

    @Override
    public void save(Supplier supplier) {
        String sql = "insert into supplier (firstname, lastname, surname, documentinn, numberofhouse, bankaccount, bankid, streetid, cityid) values (?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,
                supplier.getFirstname(), supplier.getLastname(),
                supplier.getSurname(), supplier.getDocumentInn(),
                supplier.getNumberOfHouse(), supplier.getBankAccount(),
                supplier.getBankId(), supplier.getStreetId(), supplier.getCityId());
    }

    @Override
    public void update(Supplier supplier, int id) {
        String sql = "update supplier set id = ? , firstname = ? , lastname = ? ," +
                " surname = ? , documentinn = ?, numberofhouse = ? , bankaccount = ? ," +
                " bankid = ? , streetid = ?, cityid = ? where id = ?";
        jdbcTemplate.update(sql, supplier.getId(),
                supplier.getFirstname(), supplier.getLastname(),
                supplier.getSurname(), supplier.getDocumentInn(),
                supplier.getNumberOfHouse(), supplier.getBankAccount(),
                supplier.getBankId(), supplier.getStreetId(), supplier.getCityId(), id);
    }

    @Override
    public void deleteById(int id) {
        String sql = "delete from supplier where id = ?";
        jdbcTemplate.update(sql, id);
    }
}
