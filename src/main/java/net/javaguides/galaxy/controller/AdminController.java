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
    ArrayList<User> users=new ArrayList<User>() ;

    @GetMapping("/admin/students/145aw741{groupId}wq516q")
    public String  displayStudents(Model m,@PathVariable Integer groupId){
        List<User> userOne=userRepository.findByGroupsId(groupId);
        m.addAttribute("students",userOne);
        List<User> user=userRepository.findAll();
//        List<Grade> grade=gradeRepository.findByUserIdAndCoursesId(id,user.getId());
        return "allStudents.html";
    }

    @GetMapping("/DMQRzZWMDdGQtbndzBHNsawN0aXRsZQR0ZXN0AzcwMQR3b2UDMjQwMjEwNQ/331134333{id}31134333")
    public String displaySingleStudent(@PathVariable Integer id, Model m) {
        User user = userRepository.findById(id).get();
        User tasks=userRepository.findById(1).get();
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

        return new RedirectView("/displayTasks/ftrg4d64d8"+groupId+"d54dr412");
    }

    @PostMapping("/AddCourse")
    public RedirectView addCourse(@RequestParam String subject,@RequestParam Integer groupId, HttpServletRequest request, Principal p){
        user=userService.findByEmail(p.getName());
        Groups groups=groupsRepository.findById(groupId).get();
//        String[] names = request.getParameterValues("names");
//        List<String> list = Arrays.asList(names);
//        for (String stu:list){
//            Integer id=Integer.valueOf(stu);
//            users.add(userRepository.findById(id).get());
//        }
        Courses courses=new Courses(subject,user,groups);
        courseRepository.save(courses);
        return new RedirectView("/displayTasks/ftrg4d64d8"+groupId+"d54dr412");
    }

    @GetMapping("/displayTasks/ftrg4d64d8{groupId}d54dr412")
    public String displayTasks(Principal p,Model m ,@PathVariable Integer groupId){
        List<Tasks> tasks=taskRepository.findByGroupsId(groupId);
        m.addAttribute("admin",tasks.get(0).getId());
        m.addAttribute("courses",courseRepository.findByGroupsId(groupId));
//        m.addAttribute("courses",taskRepository.findByCoursesIdAndGroupsId(t,groupId));

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
//        User userOne=userRepository.findByGroupsId(groupId);
        user=userService.findByEmail(p.getName());
        List<Grade> grade=gradeRepository.findByUserIdAndCoursesId(id,user.getId());
        Integer grd = 0,outOf=1;
        for (Grade grade1:grade){
            if (grade1!=null){
            grd=grd+grade1.getContent();
            outOf+=grade1.getOutOf();}
        }
        grd=(grd/outOf)*100 ;
        m.addAttribute("course",courses);
        m.addAttribute("task",tasks);
        m.addAttribute("users",user);
        m.addAttribute("userGrade",grd);
//        m.addAttribute("group",userOne.getGroups().get(0));

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

}
