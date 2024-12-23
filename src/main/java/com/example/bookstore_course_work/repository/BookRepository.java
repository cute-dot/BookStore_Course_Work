package com.example.bookstore_course_work.repository;


import com.example.bookstore_course_work.interfaces.BookRep;
import com.example.bookstore_course_work.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookRepository implements BookRep {
    private final JdbcTemplate jdbcTemplate;
    @Autowired

    public BookRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<Book> bookRowMapperId = (rs, rowNum) -> {
        Book book = new Book();
        book.setId(rs.getInt("id"));
        book.setBookTitle(rs.getString("booktitle"));
        book.setYearOfPublication(rs.getInt("yearofpublication"));
        book.setNumberOfPages(rs.getInt("numberofpages"));
        book.setPublishingHouseId(rs.getInt("publishinghouseid"));
        book.setSupplyId(rs.getInt("supplyid"));
        book.setPrice(rs.getBigDecimal("price"));
        return book;
    };

    private static final RowMapper<Book> bookRowMapper = (rs, rowNum) -> {
        Book book = new Book();
        book.setId(rs.getInt("id"));
        book.setBookTitle(rs.getString("booktitle"));
        book.setYearOfPublication(rs.getInt("yearofpublication"));
        book.setNumberOfPages(rs.getInt("numberofpages"));
        book.setPublishingHouse(rs.getString("nameofpublishinghouse"));
        book.setSupplyId(rs.getInt("supplyid"));
        book.setPrice(rs.getBigDecimal("price"));
        return book;
    };

    @Override
    public Optional<Book> findById(int id) {
        String sql = "select * from book where id = ?";
        return Optional.of(jdbcTemplate.queryForObject(sql, bookRowMapperId, id));
    }

    @Override
    public List<Book> findAll() {
        String sql = "select b.id, " +
                "b.booktitle, b.yearofpublication," +
                " b.numberofpages, p.nameofpublishinghouse," +
                " b.supplyid, b.price " +
                "from book b join public.publishinghouse" +
                " p on p.id = b.publishinghouseid"                ;
        return jdbcTemplate.query(sql, bookRowMapper);
    }

    @Override
    public void save(Book book) {
        String sql = "insert into book (booktitle, yearofpublication, numberofpages, publishinghouseid, supplyid, price) values (?,?,?,?,?,?)";
        jdbcTemplate.update(sql, book.getBookTitle(), book.getYearOfPublication(),book.getNumberOfPages(), book.getPublishingHouseId(), book.getSupplyId(), book.getPrice());
    }

    @Override
    public void update(Book book, int id) {
        String sql = "update book set booktitle = ?, yearofpublication =?, numberofpages = ?, publishinghouseid = ?, supplyid =?, price = ? where id = ?";
        jdbcTemplate.update(sql, book.getBookTitle(), book.getYearOfPublication(), book.getNumberOfPages(), book.getPublishingHouseId(), book.getSupplyId(), book.getPrice(), id);
    }

    @Override
    public void deleteById(int id) {
        String sql = "delete from book where id =?";
        jdbcTemplate.update(sql, id);
    }
}
