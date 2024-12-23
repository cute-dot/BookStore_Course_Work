package com.example.bookstore_course_work.repository;


import com.example.bookstore_course_work.interfaces.OrderRep;
import com.example.bookstore_course_work.models.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepository implements OrderRep {

    private final JdbcTemplate jdbcTemplate;

    public OrderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<Order> orderRowMapper = (rs, rowNum) -> {
      Order order = new Order();
      order.setId(rs.getInt("id"));
      order.setNumberOfOrder(rs.getString("numberoforder"));
      order.setDateOfOrder(rs.getDate("dateoforder"));
      order.setSumToPay(rs.getBigDecimal("sumtopay"));
      order.setIndividualId(rs.getInt("individualid"));
      order.setLegalCustomerId(rs.getInt("legalcustomerid"));
      return order;
    };

    @Override
    public Optional<Order> findById(int id) {
        String sql = "select * from orderfromclient where id = ?";
        return Optional.of(jdbcTemplate.queryForObject(sql, orderRowMapper, id));
    }

    @Override
    public List<Order> findAll() {
        String sql = "select * from orderfromclient";
        return jdbcTemplate.query(sql, orderRowMapper);
    }

    @Override
    public void save(Order order) {
        String sql = "insert into orderfromclient (numberoforder, " +
                "dateoforder, sumtopay, individualid," +
                " legalcustomerid) values (?,?,?,?,?)";
        jdbcTemplate.update(sql, order.getNumberOfOrder(),
                order.getDateOfOrder(), order.getSumToPay(),
                order.getIndividualId(), order.getLegalCustomerId());
    }

    @Override
    public void update(Order order, int id) {
        String sql = "update orderfromclient set " +
                "numberoforder = ? , dateoforder = ? ," +
                "sumtopay = ? , individualid = ? , legalcustomerid = ? where id = ?";
        jdbcTemplate.update(sql, order.getNumberOfOrder(),
                order.getDateOfOrder(), order.getSumToPay(),
                order.getIndividualId(), order.getLegalCustomerId());

    }

    @Override
    public void deleteById(int id) {

        String sql = "delete from orderfromclient where id = ?";
        jdbcTemplate.update(sql, id);

    }
}
