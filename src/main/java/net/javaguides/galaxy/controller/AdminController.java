package net.javaguides.galaxy.controller;

import net.javaguides.galaxy.entities.Role;
import net.javaguides.galaxy.entities.Tasks;
import net.javaguides.galaxy.entities.User;
import net.javaguides.galaxy.repositories.TaskRepository;
import net.javaguides.galaxy.repositories.UserRepository;
import net.javaguides.galaxy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {

    @Autowired
    UserRepository userRepository ;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    private UserService userService;
    User user ;

//    @ModelAttribute("/admin/students")
//    public List<User> displayStudents(){
//        return userRepository.findAll();
//    }


    @GetMapping("/admin/students")
    public String  displayStudents(Model m){
       List<User> user=userRepository.findAll();
//        System.out.println(user.get(0).getEmail());
        m.addAttribute("students",user);

        return "allStudents.html";
    }

    @GetMapping("/studentId/{id}")
    public String displaySingleStudent(@PathVariable Integer id, Model m) {
        User user = userRepository.findById(id).get();
        System.out.println(user.getEmail()+"sssssssssssssssss");
        m.addAttribute("student", user);
        return "studentProfile.html";
    }
    @GetMapping("/admin/tasks")
    public String  addTask(Model m){
//        List<User> user=userRepository.findAll();
//        System.out.println(user.get(0).getEmail());
//        m.addAttribute("students",user);

        return "taskAdd.html";
    }

    @PostMapping("/AddTask")
    public RedirectView addPost(@RequestParam String body, @RequestParam String dueDate, @RequestParam String deadLine, Principal p){
         user=userService.findByEmail(p.getName());
        Tasks postPage=new Tasks(body,dueDate,deadLine,user);
        taskRepository.save(postPage);
        return new RedirectView("/displayTasks");
    }
    @GetMapping("/displayTasks")
    public String displayTasks(Principal p,Model m){
        user=userService.findByEmail(p.getName());
        m.addAttribute("user",userRepository.findById(user.getId()).get() );
        System.out.println(user.getTasks().get(1).getBody()+"ddddddddddddddddddddddddd");
        return "test.html";
    }
}
