package paf2022.batch2_assessment.Repositories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import paf2022.batch2_assessment.Models.Task;


@Repository
public class TaskRepository {
    
    @Autowired
    JdbcTemplate template;

    @Autowired
    UserRepository userRepo;

    private final String insertTaskSQL = """
        insert into task (user_id, description, priority, due_date) values (?, ?, ?, ?)""";


    public Integer insertTask(String userID, Task task){
        // (user_id, description, priority, due_date)

        int updated = template.update(insertTaskSQL,
         userID, task.getDescription() ,task.getPriority(), task.getDueDate());

        return updated;
    }



}
