package com.pokemon.app.services;

import com.pokemon.app.PokemonRowMapper;
import com.pokemon.app.dto.PokemonDto;
import com.pokemon.app.rowmapper.PokemonRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@Service
public class PokemonJdbcService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void methodInit() {
    }

    public void addToPokemonTable(PokemonDto pokemonDto) {
        jdbcTemplate.update("INSERT INTO Pokemons VALUES (?, ?, ?, ?, ?, ?, ?)",
                pokemonDto.getId(), pokemonDto.getName(), pokemonDto.getHeight(), pokemonDto.getWeight(),
                pokemonDto.getBase_experience(), pokemonDto.getSpeciesUrl(), pokemonDto.getSpeciesName());
    }

    public List<PokemonDto> getPokemonList() throws IOException {
        return jdbcTemplate.query("SELECT * FROM Pokemons", new PokemonRowMapper());
    }

    public PokemonDto getPokemonWithId(String id) throws IOException {
        try {
            String query = "SELECT id, name, height, weight, base_experience, speciesUrl, speciesName FROM Pokemons WHERE id = " + id;
            PokemonDto pokemon = jdbcTemplate.queryForObject(query, new PokemonRowMapper());
            return pokemon;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    //SIMPLE EXECUTE
    //// jdbcTemplate.execute("create table user (id int, name varchar)");

//    SIMPLE UPDATE
//    public int addEmplyee(int id) {
//        return jdbcTemplate.update(
//                "INSERT INTO EMPLOYEE VALUES (?, ?, ?, ?)", 5, "Bill", "Gates", "USA");
//    }

    //MAP PARAMETERS
//
//        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", 1);
//        return namedParameterJdbcTemplate.queryForObject(
//                "SELECT FIRST_NAME FROM EMPLOYEE WHERE ID = :id", namedParameters, String.class);
//

    // ROWMAPPER
//    public class EmployeeRowMapper implements RowMapper<Employee> {
//        @Override
//        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
//            Employee employee = new Employee();
//
//            employee.setId(rs.getInt("ID"));
//            employee.setFirstName(rs.getString("FIRST_NAME"));
//            employee.setLastName(rs.getString("LAST_NAME"));
//            employee.setAddress(rs.getString("ADDRESS"));
//
//            return employee;
//        }
//    }
//String query = "SELECT * FROM EMPLOYEE WHERE ID = ?";
//    List<Employee> employees = jdbcTemplate.queryForObject(
//            query, new Object[] { id }, new EmployeeRowMapper());

    //https://docs.spring.io/spring/docs/5.0.7.RELEASE/spring-framework-reference/data-access.html#jdbc
}