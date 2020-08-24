package com.bhavya.dw.repository.mapper;

import com.bhavya.dw.model.User;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Installed with special installer, registered by JDBI bundle.
 *
 * @author Vyacheslav Rusakov
 * @since 01.11.2018
 */
public class UserMapper implements RowMapper<User> {

    @Override
    public User map(ResultSet r, StatementContext ctx) throws SQLException {
        User user = new User();
        user.setId(r.getLong("id"));
        user.setVersion(r.getInt("version"));
        user.setName(r.getString("name"));
        return user;
    }
}
