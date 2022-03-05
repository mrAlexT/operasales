package com.example.operasales.repository.jdbc;

import com.example.operasales.domain.Opera;
import com.example.operasales.repository.interfaces.CommonRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Primary
@Repository
public class JdbcOperaRepository implements CommonRepository<Opera> {

    private static final String SQL_INSERT = "insert into OPERA (ID, NAME, DESCRIPTION, AGE_CATEGORY)\n" +
            "values (:ID, :NAME, :DESCRIPTION, :AGE_CATEGORY)";

    private static final String SQL_UPDATE = "update OPERA\n" +
            "set NAME = :NAME,\n" +
            "    DESCRIPTION = :DESCRIPTION,\n" +
            "    AGE_CATEGORY = :AGE_CATEGORY\n" +
            "where ID = :ID";

    private static final String SQL_FIND_ALL = "select ID, NAME, DESCRIPTION, AGE_CATEGORY\n" +
            "from OPERA";

    private static final String SQL_FIND_BY_ID = "select ID, NAME, DESCRIPTION, AGE_CATEGORY\n" +
            "from OPERA\n" +
            "where ID = :ID";

    private static final String SQL_DELETE = "delete\n" +
            "from OPERA\n" +
            "where ID = :ID";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public JdbcOperaRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Opera> operaRowMapper = (ResultSet rs, int rowNum) -> {
        Opera opera = new Opera(rs.getString("NAME"), rs.getString("DESCRIPTION"), rs.getInt("AGE_CATEGORY"));
        return opera;
    };

    @Override
    public Opera save(Opera domain) {
        Opera result = findById(domain.getId());
        if (result != null) {
            result.setName(domain.getName());
            result.setDesc(domain.getDesc());
            result.setAgeCategory(domain.getAgeCategory());
            return upsert(result, SQL_UPDATE);
        }
        return upsert(domain, SQL_INSERT);
    }

    private Opera upsert(final Opera opera, final String sql) {
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("ID", opera.getId());
        namedParameters.put("NAME", opera.getName());
        namedParameters.put("DESCRIPTION", opera.getDesc());
        namedParameters.put("AGE_CATEGORY", opera.getAgeCategory());

        this.jdbcTemplate.update(sql, namedParameters);
        return findById(opera.getId());
    }

    @Override
    public void save(Collection<Opera> domains) {
        domains.forEach(this::save);
    }

    @Override
    public void delete(Opera domain) {
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("ID", domain.getId());

        this.jdbcTemplate.update(SQL_DELETE, namedParameters);
    }

    @Override
    public Opera findById(String id) {
        try {
            Map<String, Object> namedParameters = Collections.singletonMap("ID", id);
            return this.jdbcTemplate.queryForObject(SQL_FIND_BY_ID, namedParameters, operaRowMapper);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public Collection<Opera> findAll() {
        return this.jdbcTemplate.query(SQL_FIND_ALL, operaRowMapper);
    }
}
