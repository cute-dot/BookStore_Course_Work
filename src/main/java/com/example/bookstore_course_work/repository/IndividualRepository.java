package com.example.bookstore_course_work.repository;

import com.example.bookstore_course_work.interfaces.IndividualRep;
import com.example.bookstore_course_work.models.Individual;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class IndividualRepository implements IndividualRep {

    private final JdbcTemplate jdbcTemplate;

    public IndividualRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<Individual> individualRowMapper = (rs, rowNum) -> {
        Individual individual = new Individual();
        individual.setId(rs.getInt("id"));
        individual.setFirstname(rs.getString("firstname"));
        individual.setLastname(rs.getString("lastname"));
        individual.setSurname(rs.getString("surname"));
        individual.setPhoneNumber(rs.getString("phonenumber"));
        individual.setPassportSeries(rs.getInt("passportseries"));
        individual.setPassportNumber(rs.getInt("passportnumber"));
        individual.setPassportIssueDate(rs.getDate("passportissuedate"));
        individual.setWhoIssuedThePassport(rs.getString("whoissuedthepassport"));
        individual.setCityId(rs.getInt("cityid"));
        individual.setStreetId(rs.getInt("streetid"));
        individual.setBankAccount(rs.getString("bankaccount"));
        individual.setNumberOfHouse(rs.getString("numberofhouse"));
        return individual;
    };

    private static final RowMapper<Individual> individualRowMapperAll = (rs, rowNum) -> {
        Individual individual = new Individual();
        individual.setId(rs.getInt("id"));
        individual.setFirstname(rs.getString("firstname"));
        individual.setLastname(rs.getString("lastname"));
        individual.setSurname(rs.getString("surname"));
        individual.setPhoneNumber(rs.getString("phonenumber"));
        individual.setPassportSeries(rs.getInt("passportseries"));
        individual.setPassportNumber(rs.getInt("passportnumber"));
        individual.setPassportIssueDate(rs.getDate("passportissuedate"));
        individual.setWhoIssuedThePassport(rs.getString("whoissuedthepassport"));
        individual.setCityName(rs.getString("cityname"));
        individual.setStreetName(rs.getString("streetname"));
        individual.setBankAccount(rs.getString("bankaccount"));
        individual.setNumberOfHouse(rs.getString("numberofhouse"));
        return individual;
    };
    @Override
    public Optional<Individual> findById(int id) {
        String sql = "select * from individual where id = ?";
        return Optional.of(jdbcTemplate.queryForObject(sql, individualRowMapper, id));
    }

    @Override
    public List<Individual> findAll() {
        String sql = "select i.id, i.firstname, i.lastname, i.surname," +
                "i.phonenumber, i.passportseries, i.passportnumber, i.passportissuedate, i.whoissuedthepassport, c.cityname, s.streetname, i.bankaccount, i.numberofhouse "
                 +
                "from individual i " +
                "join public.city c on c.id = i.cityid join public.street s on s.id = i.streetid";
        return jdbcTemplate.query(sql, individualRowMapperAll);
    }

    @Override
    public void save(Individual individual) {
        String sql = "insert into individual (firstname, lastname," +
                " surname, phonenumber, passportseries, passportnumber,passportissuedate, " +
                "whoissuedthepassport, cityid, streetid, bankaccount, numberofhouse)" +
                "values (?,?,?,?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, individual.getFirstname(),
                individual.getLastname(), individual.getSurname(), individual.getPhoneNumber(),
                individual.getPassportSeries(), individual.getPassportNumber(),
                individual.getPassportIssueDate(), individual.getWhoIssuedThePassport(),
                individual.getCityId(), individual.getStreetId(), individual.getBankAccount(), individual.getNumberOfHouse());
    }

    @Override
    public void update(Individual individual, int id) {
        String sql = "update individual set firstname =?," +
                "lastname=?, surname = ?, phonenumber = ?, passportseries =?," +
                "passportnumber = ?, passportissuedate =? , whoissuedthepassport =?," +
                "cityid = ?, streetid =? ,bankaccount =? , numberofhouse =? where id = ?";
        jdbcTemplate.update(sql, individual.getFirstname(),
                individual.getLastname(), individual.getSurname(),
                individual.getPhoneNumber(),individual.getPassportSeries(),
                individual.getPassportNumber(), individual.getPassportIssueDate(),
                individual.getWhoIssuedThePassport(), individual.getCityId(), individual.getStreetId(),
                individual.getBankAccount(), individual.getNumberOfHouse(), id);
    }

    @Override
    public void deleteById(int id) {
        String sql = "delete from individual where id = ?";
        jdbcTemplate.update(sql,id);
    }
}
