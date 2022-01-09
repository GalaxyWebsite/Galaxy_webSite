package net.javaguides.galaxy.controller;

import net.javaguides.galaxy.entities.*;
import net.javaguides.galaxy.repositories.*;
import net.javaguides.galaxy.service.MessageService;
import net.javaguides.galaxy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository ;
    @Autowired
    private UserService userService;
    @Autowired
    TaskRepository taskRepository ;
    @Autowired
    GradeRepository gradeRepository ;
    @Autowired
    TaskDeliveryRepository taskDeliveryRepository ;
    @Autowired
        private MessageService messageService ;
    @Autowired
    MessageRepository messageRepository ;


    @GetMapping("/myProfile")
    public String displayProfile(Principal p, Model m){
     User user=userService.findByEmail(p.getName());
        m.addAttribute("admin",userRepository.findById(1).get());
        m.addAttribute("student", user);
        return "studentProfile.html";
    }


    @GetMapping("/DMQRzZWMDdGQtbn/331134333{id}MDdGQtbn/DMQRzZ11343{uId}")
    public String displayTaskDetail(@PathVariable Integer id,@PathVariable Integer uId, Model m) {
        Tasks tasks = taskRepository.findById(id).get();
        List<Grade> grade=gradeRepository.findByTasksId(id);
        User user=userRepository.findById(uId).get();
        List<Message> message=messageRepository.findByPostIdAndUserId(id,uId);
        List<TaskDelivery> taskDelivery=taskDeliveryRepository.findByUserTasksId(id);

        m.addAttribute("user",userRepository.findById(uId).get());
        m.addAttribute("task", tasks);
        m.addAttribute("msgs",message);

for(TaskDelivery t:taskDelivery) {
    if (t.getStudent().getId() == user.getId()) {
        m.addAttribute("finalSubmit", t);

    }
}
    for(Grade g:grade){
        if (g.getUser().getId()==uId){
            System.out.println(g.getUser().getId() +"ssssssssssssssssss" +user.getId());
            m.addAttribute("gradeStu", g);

        }
}

        return "displayTaskDetail.html";
    }


    @PostMapping("/submitTask")
    public RedirectView submitTask(@RequestParam String body, @RequestParam String comment ,@RequestParam Integer userId,@RequestParam Integer taskId, Principal p){
        User  user=userService.findByEmail(p.getName());
        Tasks tasks=taskRepository.findById(taskId).get();
        TaskDelivery stuTask=new TaskDelivery(body,comment,user,tasks);
        taskDeliveryRepository.save(stuTask);

        return new RedirectView("/DMQRzZWMDdGQtbn/331134333"+taskId+"MDdGQtbn/DMQRzZ11343"+userId);
    }


}
