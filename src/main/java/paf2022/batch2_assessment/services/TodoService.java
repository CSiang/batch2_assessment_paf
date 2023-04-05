package paf2022.batch2_assessment.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import paf2022.batch2_assessment.Models.Task;
import paf2022.batch2_assessment.Models.User;
import paf2022.batch2_assessment.Repositories.TaskRepository;
import paf2022.batch2_assessment.Repositories.UserRepository;

@Service
public class TodoService {
    
    @Autowired
    TaskRepository taskRepo;
    
    @Autowired
    UserRepository userRepo;

    @Transactional
    public Boolean upsertTask(List<Task> tasks) throws Exception{

        Task task = tasks.get(0);
        String userName = task.getUserName();
        Optional<User> opt = userRepo.findUserByUsername(userName);

        String userID = "";

        if(opt.isEmpty()){
            User user = new User();
            user.setName(userName);
            user.setUserName(userName);

            try{ userID = userRepo.insertUser(user); }
                catch(Exception ex) { ex.getStackTrace(); }
            
        } else {
            
            userID = opt.get().getUserId();
        }
        Integer updatedTotal=0;

        try{
            for(Task t: tasks){ updatedTotal += taskRepo.insertTask(userID, t); } 

        } catch (Exception ex){
            ex.getStackTrace();
        }

        if(updatedTotal< tasks.size()){
            throw new Exception("Not all records were inserted.");
        } else {
            return true;
        }
    }



}
