package com.company.Summative2FerrellMarcel.dao;

import com.company.Summative2FerrellMarcel.dto.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AuthorDaoJDBCTemplateImpl implements AuthorDao {

    // Prepared Statements
    private static final String INSERT_AUTHOR_SQL = "insert into author (first_name, last_name, street, city, state,postal_code, phone,email) values(?,?,?,?,?,?,?,?)";

    private static final String SELECT_AUTHOR_SQL = "select * from author where author_id = ?";

    private static final String SELECT_ALL_AUTHORS_SQL = "select * from author";

    private static final String DELETE_AUTHOR_SQL = "delete from author where author_id = ?";

    private static final String UPDATE_AUTHOR_SQL = "update author set first_name = ? ,last_name = ?,street = ? ,city= ? ,state =? ,postal_code =? ,phone =? ,email =?";

    // Jdbctemplate
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthorDaoJDBCTemplateImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Author getAuthor(int authorId){

        try{
            return jdbcTemplate.queryForObject(SELECT_AUTHOR_SQL, this::mapRowToAuthor, authorId);
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public List<Author> getAllAuthors(){

        return jdbcTemplate.query(SELECT_ALL_AUTHORS_SQL, this::mapRowToAuthor);
    }

    @Override
    @Transactional
    public Author addAuthor(Author author){

        jdbcTemplate.update(INSERT_AUTHOR_SQL, author.getFirstName(),author.getLastName(),author.getStreet(), author.getCity(),author.getState(),author.getPostalCode(),author.getPhone(),author.getEmail());

        int authorId = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);

        author.setAuthorId(authorId);

        return author;
    }

    @Override
    public void updateAuthor(Author author){

        jdbcTemplate.update(UPDATE_AUTHOR_SQL,author.getFirstName(),author.getLastName(),author.getStreet(), author.getCity(),author.getState(),author.getPostalCode(),author.getPhone(),author.getEmail());

    }

    @Override
    public void deleteAuthor(int id){

        jdbcTemplate.update(DELETE_AUTHOR_SQL, id);
    }

    private Author mapRowToAuthor(ResultSet rs, int rowNum) throws SQLException{
        Author author = new Author();
        author.setAuthorId(rs.getInt("author_id"));
        author.setFirstName(rs.getString("first_name"));
        author.setLastName(rs.getString("last_name"));
        author.setCity(rs.getString("city"));
        author.setState(rs.getString("state"));
        author.setPostalCode(rs.getString("postal_code"));
        author.setPhone(rs.getString("phone"));
        author.setEmail(rs.getString("email"));
        author.setStreet(rs.getString("street"));

        return author;
    }

}
