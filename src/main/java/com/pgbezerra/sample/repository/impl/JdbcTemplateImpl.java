package com.pgbezerra.sample.repository.impl;

import com.pgbezerra.sample.database.DB;
import com.pgbezerra.sample.database.DatabaseException;
import com.pgbezerra.sample.repository.JdbcTemplate;
import com.pgbezerra.sample.repository.RowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JdbcTemplateImpl implements JdbcTemplate {

    @Override
    public <T> List<T> query(String sql, Object[] params, RowMapper<T> rowMapper) {
        List<T> result = new ArrayList<>();
        try(Connection conn = DB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);){
            if(Objects.nonNull(params))
                for(int i = 0; i < params.length; i++)
                    ps.setObject(i+1, params[i]);
            extractResultSet(rowMapper, result, ps);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public <T> T queryForObject(String sql, Object[] params, RowMapper<T> rowMapper) {
        T t = null;
        try(Connection conn = DB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);){
            if(Objects.nonNull(params))
                for(int i = 0; i < params.length; i++)
                    ps.setObject(i+1, params[i]);
            t = extractResultSet(rowMapper, ps);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return t;
    }

    private <T> T extractResultSet(RowMapper<T> rowMapper, PreparedStatement ps) {
        T t = null;
        try(ResultSet rs = ps.executeQuery()) {
            if(rs.next())
                 t =  rowMapper.mapRow(rs, 1L);
            if(rs.next())
                throw new DatabaseException("More than 1 object");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }

    private <T> void extractResultSet(RowMapper<T> rowMapper, List<T> result, PreparedStatement ps) throws SQLException {
        long rowNum = 1L;
        try(ResultSet rs = ps.executeQuery()) {
            while(rs.next())
                result.add(rowMapper.mapRow(rs, rowNum++));
        }
    }

    @Override
    public boolean update(String sql, Object[] params) {
        try(Connection conn = DB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);){
            if(Objects.nonNull(params))
                for(int i = 0; i < params.length; i++)
                    ps.setObject(i+1, params[i]);
            return  ps.executeUpdate() > 0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

}
