package com.pgbezerra.sample.repository;

import java.util.List;

public interface JdbcTemplate {

    <T> List<T> query(String sql, Object[] params, RowMapper<T> rowMapper);
    <T> T queryForObject(String sql, Object[] params, RowMapper<T> rowMapper);
    boolean update(String sql, Object[] params);
}
