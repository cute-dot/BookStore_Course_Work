package com.example.bookstore_course_work.repository;


import com.example.bookstore_course_work.interfaces.SupplyRep;

import com.example.bookstore_course_work.models.Supply;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SupplyRepository implements SupplyRep {
    private final JdbcTemplate jdbcTemplate;

    public SupplyRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<Supply> supplyRowMapper = (rs, rowNum) -> {
        Supply supply = new Supply();
        supply.setId(rs.getInt("id"));
        supply.setDateOfSupply(rs.getDate("dateofsupply"));
        supply.setPurchasePrice(rs.getBigDecimal("purchaseprice"));
        supply.setSupplierId(rs.getInt("supplierid"));
        return supply;
    };

    private static final RowMapper<Supply> supplyRowMapperAll = (rs, rowNum) -> {
        Supply supply = new Supply();
        supply.setId(rs.getInt("id"));
        supply.setDateOfSupply(rs.getDate("dateofsupply"));
        supply.setPurchasePrice(rs.getBigDecimal("purchaseprice"));
        supply.setFullSupplierName(rs.getString("full_supplier_name"));
        supply.setSupplierId(rs.getInt("supplierid"));
        return supply;
    };
    @Override
    public Optional<Supply> findById(int id) {
        String sql = "select * from supply where id = ?";
        return Optional.of(jdbcTemplate.queryForObject(sql, supplyRowMapper, id));
    }

    @Override
    public List<Supply> findAll() {
        String sql = "SELECT s.id, s.dateofsupply, s.purchaseprice," +
                "CONCAT(sp.firstname, ' ', sp.lastname, ' ', sp.surname) AS full_supplier_name, s.supplierid " +
                "FROM supply s join supplier sp ON s.supplierid = sp.id";
        return jdbcTemplate.query(sql, supplyRowMapperAll);
    }

    @Override
    public void save(Supply supply) {
        String sql = "insert into supply (dateofsupply, purchaseprice, supplierid) values (?,?,?);";
        jdbcTemplate.update(sql, supply.getDateOfSupply(),
                supply.getPurchasePrice(), supply.getSupplierId());
    }

    @Override
    public void update(Supply supply, int id) {
        String sql = "update supply set dateofsupply = ? , purchaseprice = ?, supplierid = ? where id = ?";
        jdbcTemplate.update(sql, supply.getDateOfSupply(),
                supply.getPurchasePrice(), supply.getSupplierId(), id);
    }

    @Override
    public void deleteById(int id) {
        String sql = "delete from supply where id = ?";
        jdbcTemplate.update(sql, id);
    }
}
