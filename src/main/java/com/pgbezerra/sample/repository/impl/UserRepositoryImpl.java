package com.pgbezerra.sample.repository.impl;

import com.pgbezerra.sample.model.User;
import com.pgbezerra.sample.model.enums.UserType;
import com.pgbezerra.sample.repository.JdbcTemplate;
import com.pgbezerra.sample.repository.RowMapper;
import com.pgbezerra.sample.repository.UserRepository;

import java.util.List;

public final class UserRepositoryImpl implements UserRepository {

    private JdbcTemplate jdbcTemplate;
    
    private RowMapper<User> userRowMapper = (rs, rowNum) -> {
    	Integer userID = rs.getInt("USER_ID");
    	String userName = rs.getString("USER_NM");
    	Integer type = rs.getInt("USER_TP");
    	UserType userType = switch(type) {
        	case 1 -> UserType.ADMIN;
        	case 2 -> UserType.COMMOM;
        	default -> UserType.UNKNOW;
    	};
        return new User(userID, userName, userType);
    };

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> findAll() {

        String sql = """
                SELECT 
                  USER_ID,
                  USER_NM,
                  USER_TP
                FROM
                  TB_USER
                """;

        return jdbcTemplate.query(sql, new Object[]{}, userRowMapper);
    }

    @Override
    public User findById(Integer id) {
        String sql = """
                SELECT 
                  USER_ID,
                  USER_NM,
                  USER_TP
                FROM
                  TB_USER
                WHERE
                  USER_ID = ?
                """;
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, userRowMapper);
    }

    @Override
    public boolean insert(User user) {
        String sql = """
                INSERT INTO TB_USER
                  (USER_NM, 
                  USER_TP)
                VALUES
                  (?,
                   ?)
                """;
        return jdbcTemplate.update(sql, new Object[]{user.name(), user.type().getCode()});
    }

    @Override
    public boolean deleteById(Integer id) {
        String sql = """
                DELETE FROM
                  TB_USER
                WHERE
                  USER_ID = ?
                """;
        return jdbcTemplate.update(sql, new Object[]{id});
    }

    @Override
    public boolean update(User user) {
        String sql = """
                UPDATE 
                  TB_USER
                SET
                  USER_NM = ?,
                  USER_TP = ?
                WHERE
                  USER_ID = ?
                """;
        return jdbcTemplate.update(sql, new Object[]{user.name(), user.type().getCode(), user.id()});
    }
    
    
}
