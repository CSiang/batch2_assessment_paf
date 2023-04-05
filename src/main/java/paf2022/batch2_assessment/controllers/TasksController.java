package paf2022.batch2_assessment.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import paf2022.batch2_assessment.Models.Task;
import paf2022.batch2_assessment.services.TodoService;

// TODO: Task 4, Task 8
@Controller
@RequestMapping(path = "/")
public class TasksController {

    @Autowired
    TodoService todoSvc;

    @PostMapping(path = "task")
    public ModelAndView saveTask(@RequestBody MultiValueMap<String, String> form, Model model) 
    throws Exception{

        List<String> keyset =  new LinkedList<>(form.keySet());
        List<Task> tasks = new LinkedList<>();
        
        for(int i=1; i< keyset.size(); i+=3){

            Task task = new Task();
            task.setUserName(form.getFirst(keyset.get(0)));
            task.setDescription(form.getFirst(keyset.get(i)));
            task.setPriority(Integer.parseInt(form.getFirst(keyset.get(i+1))));

            Date utilDate = new SimpleDateFormat("yyyy-MM-dd")
            .parse(form.getFirst(keyset.get(i+2)));
            java.sql.Date dueDate = new java.sql.Date(utilDate.getTime());
            task.setDueDate(dueDate);

            tasks.add(task);
        }

        for(Task t: tasks){
            System.out.printf("\n\nAdded task: %s", t.toString());
        }

        if(todoSvc.upsertTask(tasks)){
            
            ModelAndView mav = new ModelAndView("result.html");
            mav.addObject("username",tasks.get(0).getUserName());
            mav.addObject("taskCount", tasks.size());
            mav.setStatus(HttpStatus.ACCEPTED);

            return mav;

        } else {
            ModelAndView mav = new ModelAndView("error.html", HttpStatus.INTERNAL_SERVER_ERROR);

            return mav;
        }

    }
}
