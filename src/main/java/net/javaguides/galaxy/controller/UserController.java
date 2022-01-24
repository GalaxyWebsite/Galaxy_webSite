package net.javaguides.galaxy.controller;

import net.javaguides.galaxy.entities.*;
import net.javaguides.galaxy.repositories.*;
import net.javaguides.galaxy.service.MessageService;
import net.javaguides.galaxy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PutMapping;
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
    @Autowired
    GroupsRepository groupsRepository ;
    @Autowired
    CourseRepository courseRepository;


    @GetMapping("/myProfile/1d5sad57dsa{groupId}sds5d")
    public String displayProfile(Principal p, Model m,@PathVariable Integer groupId ){
        List<Tasks> tasks=taskRepository.findByGroupsId(groupId);
        User user=userService.findByEmail(p.getName());


        List<Grade> grade=gradeRepository.findByUserId(user.getId());
        System.out.println(" ffffffffffffffffffff "+user.getId());
        float grd = 0,outOf=1;
        for (Grade grade1:grade){
            if (grade1!=null){
                grd=grd+grade1.getContent();
                outOf+=grade1.getOutOf();}
        }

        grd=(grd/outOf)*100 ;

        m.addAttribute("userGrade",Integer.valueOf((int) grd));
        m.addAttribute("admin",tasks);
        m.addAttribute("student", user);
        return "studentProfile.html";
    }

    @GetMapping("/myProfileInfo")
    public RedirectView displayProfileInfo(Principal principal,Model model){
        User user=userService
                .findByEmail(
                        principal.getName()
                );
        model.addAttribute("courses",courseRepository.findAll());
        return new RedirectView("/myProfile/1d5sad57dsa"+groupsRepository.findByUsersId(user.getId()).getId()+"sds5d");
    }
    @GetMapping("/myGradeInfo")
    public RedirectView displayGradeInfo(Principal principal,Model model){
        User user=userService
                .findByEmail(
                        principal.getName()
                );
        model.addAttribute("courses",courseRepository.findAll());
        return new RedirectView("/myGrade/1d5sad57dsa"+groupsRepository.findByUsersId(user.getId()).getId()+"sds5d");
    }

    @GetMapping("/myGrade/1d5sad57dsa{groupId}sds5d")
    public String displayGrade(Principal p, Model m,@PathVariable Integer groupId ){
        List<Tasks> tasks=taskRepository.findByGroupsId(groupId);
        User user=userService.findByEmail(p.getName());
        List<Grade> grade=gradeRepository.findByUserId(user.getId());

        float grd = 0,outOf=1;
        for (Grade grade1:grade){
            if (grade1!=null){
                grd=grd+grade1.getContent();
                outOf+=grade1.getOutOf();}
        }
        grd=(grd/outOf)*100 ;

        m.addAttribute("userGrade",Integer.valueOf((int) grd));
        m.addAttribute("admin",tasks);
        m.addAttribute("student", user);
        m.addAttribute("gradeRepo", gradeRepository);
        return "grade.html";
    }

    @GetMapping("/search")
    public RedirectView findByDescription(@RequestParam String search,Model m,Principal p) {
        Tasks searchTask=taskRepository.findByTitle(search);
        User user=userService.findByEmail(p.getName());
        return new RedirectView("/DMQRzZWMDdGQtbn/331134333"+searchTask.getId()+"MDdGQtbn/DMQRzZ11343"+user.getId());
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

    @RequestMapping(
            value = "/employees",
            produces = "application/json",
            method = {RequestMethod.GET, RequestMethod.PUT})
    public @ResponseBody  RedirectView reSubmitTask(@RequestParam String body, @RequestParam String comment ,@RequestParam Integer userId,@RequestParam Integer taskId,@RequestParam Integer deliveryId,Principal p)
    {
        User  user=userService.findByEmail(p.getName());

        TaskDelivery updateTasks=taskDeliveryRepository.findByIdAndStudentId(user.getId(),deliveryId);
        updateTasks.setBody(body);
        updateTasks.setComment(comment);
        taskDeliveryRepository.save(updateTasks);

        return new RedirectView("/DMQRzZWMDdGQtbn/331134333"+taskId+"MDdGQtbn/DMQRzZ11343"+userId);

    }
    @GetMapping("/side")
    public String side(){
        return "sidebars.html";
    }

    @GetMapping("/dashBoard")
    public RedirectView displayDashboard(Principal principal,Model model){
        User user=userService
                .findByEmail(
                        principal.getName()
                );
        model.addAttribute("courses",courseRepository.findAll());
        return new RedirectView("/dashBord/4rcc31"+groupsRepository.findByUsersId(user.getId()).getId()+"98de92c");
    }
    @GetMapping("/dashBord/4rcc31{id}98de92c")
    public String dashBoard(Principal principal,Model model,@PathVariable Integer id){
        User user=userService
                .findByEmail(
                        principal.getName()
                );
        List<User> userOne=userRepository.findByGroupsId(id);
        model.addAttribute("group",userOne.get(0).getGroups().get(0));
        model.addAttribute("courses",courseRepository.findByGroupsId(id));

        return "displayDashboard.html";
    }
}
