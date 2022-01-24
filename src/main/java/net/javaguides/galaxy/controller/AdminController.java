package net.javaguides.galaxy.controller;

import net.javaguides.galaxy.entities.*;
import net.javaguides.galaxy.repositories.*;
import net.javaguides.galaxy.service.MessageService;
import net.javaguides.galaxy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.*;

@Controller
public class AdminController {

    @Autowired
    UserRepository userRepository ;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    private UserService userService;
    User user ;
    @Autowired
    GradeRepository gradeRepository ;
    @Autowired
    MessageService messageService ;
    @Autowired
    TaskDeliveryRepository taskDeliveryRepository;
    @Autowired
    CourseRepository courseRepository ;
    @Autowired
            GroupsRepository groupsRepository ;

    @GetMapping("/admin/students/145aw741{groupId}wq516q")
    public String  displayStudents(Model m,@PathVariable Integer groupId){
        List<User> userOne=userRepository.findByGroupsId(groupId);
        m.addAttribute("students",userOne);
        List<User> user=userRepository.findAll();
        return "allStudents.html";
    }
    @RequestMapping(
            value = "/deleteStu",
            produces = "application/json",
            method = {RequestMethod.GET, RequestMethod.DELETE})
    public RedirectView deleteStu(Model m,@RequestParam Integer userId){
        User delUser=userRepository.findById(userId).get();
        Integer groupId=delUser.getGroups().get(0).getId();
        List<Grade> grades=gradeRepository.findByUserId(userId);
        for (Grade g:grades)
        gradeRepository.delete(g);
        userRepository.delete(delUser);
        return new RedirectView("/admin/students/145aw741"+groupId+"wq516q");
    }

    @RequestMapping(
            value = "/deleteTask",
            produces = "application/json",
            method = {RequestMethod.GET, RequestMethod.DELETE})
    public String deleteTask(Model m,@RequestParam Integer taskId){
        Tasks tasks=taskRepository.findById(taskId).get();
        taskRepository.delete(tasks);
        return "aboutUs.html";
    }

    @GetMapping("/DMQRzZWMDdGQtbndzBHNsawN0aXRsZQR0ZXN0AzcwMQR3b2UDMjQwMjEwNQ/331134333{id}31134333")
    public String displaySingleStudent(@PathVariable Integer id, Model m) {
        User user = userRepository.findById(id).get();
        User tasks=userRepository.findById(1).get();


        List<Grade> grade=gradeRepository.findByUserId(id);
        System.out.println(" ffffffffffffffffffff "+user.getId());
        float grd = 0,outOf=1;
        for (Grade grade1:grade){
            if (grade1!=null){
                grd=grd+grade1.getContent();
                outOf+=grade1.getOutOf();}
        }

        grd=(grd/outOf)*100 ;

        m.addAttribute("userGrade",Integer.valueOf((int) grd));


        m.addAttribute("admin",tasks.getTasks());
        m.addAttribute("student", user);
        return "studentProfile.html";
    }
    @GetMapping("/admin/tasks/e5s8s4d1{groupId}we5dww")
    public String  addTask(Model m,@PathVariable Integer groupId){
        List<User> user=userRepository.findAll();
        List<Courses> courses=courseRepository.findByGroupsId(groupId);
        Groups groups=groupsRepository.findById(groupId).get();
        m.addAttribute("students",user);
        m.addAttribute("course",courses);
        m.addAttribute("group",groups);
        return "taskAdd.html";
    }

    @PostMapping("/AddTask")
    public RedirectView addPost(@RequestParam String title,@RequestParam String body, @RequestParam String dueDate, @RequestParam String deadLine,@RequestParam Integer courseId,@RequestParam Integer groupId, Principal p){
        user=userService.findByEmail(p.getName());
        Courses courses=courseRepository.findById(courseId).get();
        Groups groups=groupsRepository.findById(groupId).get();
        Tasks postPage=new Tasks(title,body,dueDate,deadLine,user,courses,groups);
        taskRepository.save(postPage);

        return new RedirectView("/displayTasks/ftrg4d64d8"+groupId+"d54dr412"+courseId);
    }

    @PostMapping("/AddCourse")
    public RedirectView addCourse(@RequestParam String subject,@RequestParam Integer groupId, HttpServletRequest request, Principal p){
        user=userService.findByEmail(p.getName());
        Groups groups=groupsRepository.findById(groupId).get();
        Courses courses=new Courses(subject,user,groups);
        courseRepository.save(courses);
        return new RedirectView("/dashBord/4rcc31"+groupsRepository.findByUsersId(user.getId()).getId()+"98de92c");
    }

    @GetMapping("/displayTasks/ftrg4d64d8{groupId}d54dr412{courseId}")
    public String displayTasks(Principal p,Model m ,@PathVariable Integer groupId,@PathVariable Integer courseId){
        List<Tasks> tasks=taskRepository.findByCoursesIdAndGroupsId(courseId,groupId);
        m.addAttribute("admin",tasks);
        m.addAttribute("courses",courseRepository.findByIdAndGroupId(courseId,groupId));

        return "test.html";
    }

    @PostMapping("/grade")
    public RedirectView gradeStudent(@RequestParam Integer grade,@RequestParam Integer ouutOf,@RequestParam Integer userId,@RequestParam Integer taskId,@RequestParam Integer courseId){
        Tasks task = taskRepository.findById(taskId).get();
        User user=userRepository.findById(userId).get();
        Courses courses=courseRepository.findById(courseId).get();
        Grade addGrade=new Grade(grade,ouutOf,user,task,courses);
        gradeRepository.save(addGrade);

        return new RedirectView("/DMQRzZWMDdGQtbn/331134333"+taskId+"MDdGQtbn/DMQRzZ11343"+userId);

    }

    @GetMapping("/courseSubject/331134333{id}GQtb/zZ11{groupId}")
    public String displayCourses(@PathVariable Integer id,@PathVariable Integer groupId, Model m, Principal p) {
        Courses courses=courseRepository.findById(id).get();
        List<Tasks> tasks=taskRepository.findByCoursesIdAndGroupsId(id,groupId);
        user=userService.findByEmail(p.getName());
        List<Grade> grade=gradeRepository.findByUserIdAndCoursesId(id,user.getId());
        System.out.println(id+" ffffffffffffffffffff "+user.getId());
        float grd = 0,outOf=1;
        for (Grade grade1:grade){
            if (grade1!=null){
            grd=grd+grade1.getContent();
            outOf+=grade1.getOutOf();}
        }

        grd=(grd/outOf)*100 ;
        List<User> userOne=userRepository.findByGroupsId(groupId);
        m.addAttribute("course",courses);
        m.addAttribute("task",tasks);
        m.addAttribute("users",user);
        m.addAttribute("userGrade",Integer.valueOf((int) grd));
        m.addAttribute("group",userOne.get(0).getGroups().get(0));

        return "displayCourses.html";
    }


    @RequestMapping(
            value = "/reGradeTask",
            produces = "application/json",
            method = {RequestMethod.GET, RequestMethod.PUT})
    public @ResponseBody  RedirectView reGradeTask(@RequestParam Integer grade,@RequestParam Integer ouutOf,@RequestParam Integer userId,@RequestParam Integer taskId,@RequestParam Integer gradeId,Principal p)
    {
        User  user=userService.findByEmail(p.getName());

        Grade reGradeTasks=gradeRepository.findByUserIdAndCoursesId(userId,gradeId,taskId);
        reGradeTasks.setContent(grade);
        reGradeTasks.setOutOf(ouutOf);
        gradeRepository.save(reGradeTasks);

        return new RedirectView("/DMQRzZWMDdGQtbn/331134333"+taskId+"MDdGQtbn/DMQRzZ11343"+userId);

    }

    @RequestMapping(
            value = "/deleteCourse",
            produces = "application/json",
            method = {RequestMethod.GET, RequestMethod.DELETE})
    public RedirectView deleteCourse(Model m,@RequestParam Integer courseId,Principal principal){
Courses courses=courseRepository.findById(courseId).get();
        User user=userService.findByEmail(principal.getName());
        List<Grade> grades=gradeRepository.findByCoursesId(courseId);
        for (Grade g:grades)
            gradeRepository.delete(g);

        List<Tasks> tasks=taskRepository.findByCoursesId(courseId);
        for (Tasks t:tasks)
            taskRepository.delete(t);

        courseRepository.delete(courses);
        return new RedirectView("/dashBord/4rcc31"+groupsRepository.findByUsersId(user.getId()).getId()+"98de92c");
    }


}
