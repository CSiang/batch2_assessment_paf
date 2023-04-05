package paf2022.batch2_assessment.Rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import paf2022.batch2_assessment.Models.User;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {

        User user = new User();
        user.setUserId(rs.getString("user_id"));
        user.setUserName(rs.getString("username"));
        user.setName(rs.getString("_name"));
        
        return user;
    }
    
}
