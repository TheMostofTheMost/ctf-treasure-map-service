package com.example.ctftreasuremapservice.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class TestRepo {
    private final JdbcTemplate jdbcTemplate;
    public TestRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public boolean executeQuery(String sql) {

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        return !rows.isEmpty();
    }
}
