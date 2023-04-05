package paf2022.batch2_assessment.Repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import paf2022.batch2_assessment.Models.User;
import paf2022.batch2_assessment.Rowmappers.UserMapper;

@Repository
public class UserRepository {
    
    @Autowired
    JdbcTemplate template;

    private final String findUserSQL = "select * from user where username = ?;";
    private final String insertUserSQL = "insert into user(user_id, username, _name) values (?, ?, ?);";


    public Optional<User> findUserByUsername(String username){

        SqlRowSet rs =  template.queryForRowSet(findUserSQL, username);

        if(rs.next()){
           return Optional.of(template.queryForObject(findUserSQL, new UserMapper(), username));
        } else {
            return Optional.empty();
        }
    }

    public String insertUser(User user){

        String id = UUID.randomUUID().toString().substring(0, 8);
        int updated =  template.update(insertUserSQL, id, user.getUserName(), user.getName());
        if(updated>0){
            return id;
        } else{
            return "fail";
        }
    }


}
