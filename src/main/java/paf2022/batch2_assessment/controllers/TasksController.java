package paf2022.batch2_assessment.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import paf2022.batch2_assessment.Models.Task;

// TODO: Task 4, Task 8
@RestController
@RequestMapping(path = "/")
public class TasksController {

    // username=cscorpio87&description-0=eat&priority-0=3&dueDate-0=2023-03-28&description-1=swim&priority-1=1&dueDate-1=2023-04-10&description-2=game&priority-2=2&dueDate-2=2023-02-07
    @PostMapping(path = "task", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveTask(@RequestBody MultiValueMap<String, String> form) throws ParseException{


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

        return null;
    }
    
}
