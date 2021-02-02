package com.pgbezerra.sample.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T> {

    T mapRow(ResultSet rs, Long rowNum) throws SQLException;

}
