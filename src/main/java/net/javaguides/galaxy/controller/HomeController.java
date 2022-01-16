package net.javaguides.galaxy.controller;

import java.security.Principal;
import java.util.List;

import net.javaguides.galaxy.entities.Groups;
import net.javaguides.galaxy.entities.Tasks;
import net.javaguides.galaxy.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import net.javaguides.galaxy.entities.Message;
import net.javaguides.galaxy.entities.User;
import net.javaguides.galaxy.service.MessageService;
import net.javaguides.galaxy.service.UserService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @author Caio Fernando
 */

 @Controller
public class HomeController {

     @Autowired
    TaskRepository taskRepository;
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;
    private User user;
    @Autowired
    CourseRepository courseRepository ;
    @Autowired
    GroupsRepository groupsRepository ;
    @Autowired
    UserRepository userRepository ;

    @GetMapping("/home/4rcc31{id}98de92c")
    public String home(Principal principal,Model model,@PathVariable Integer id){
        user=userService
                .findByEmail(
                        principal.getName()
                );
        List<User> userOne=userRepository.findByGroupsId(id);
        model.addAttribute("group",userOne.get(0).getGroups().get(0));
        model.addAttribute("courses",courseRepository.findByGroupsId(id));

        return "userhome";
    }

    @GetMapping("/home")
    public RedirectView displayGroups(Principal principal,Model model){
        user=userService
        .findByEmail(
            principal.getName()
        );
        model.addAttribute("courses",courseRepository.findAll());
        return new RedirectView("/home/4rcc31"+groupsRepository.findByUsersId(user.getId()).getId()+"98de92c");
    }

    @PostMapping("/messages/{userId}")
    public RedirectView saveMessage(Principal principal, Message message, @RequestParam Integer id,@PathVariable Integer userId){
        User  user=userService.findByEmail(principal.getName());
        Tasks tasks=taskRepository.findById(id).get();
        messageService.saveMessage(user,tasks, message);
        return new RedirectView("/DMQRzZWMDdGQtbn/331134333"+id+"MDdGQtbn/DMQRzZ11343"+user.getId());

    }
    @GetMapping("/aboutUs")
    public String displayAboutUs(){
        return "aboutUs.html";
    }

}