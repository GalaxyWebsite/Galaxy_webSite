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
    ArrayList<User> users=new ArrayList<User>() ;

    @GetMapping("/admin/students")
    public String  displayStudents(Model m){
       List<User> user=userRepository.findAll();
        m.addAttribute("students",user);
        return "allStudents.html";
    }

    @GetMapping("/DMQRzZWMDdGQtbndzBHNsawN0aXRsZQR0ZXN0AzcwMQR3b2UDMjQwMjEwNQ/331134333{id}31134333")
    public String displaySingleStudent(@PathVariable Integer id, Model m) {
        User user = userRepository.findById(id).get();
        User tasks=userRepository.findById(1).get();
        m.addAttribute("admin",tasks);
        m.addAttribute("student", user);
        return "studentProfile.html";
    }
    @GetMapping("/admin/tasks")
    public String  addTask(Model m){
        List<User> user=userRepository.findAll();
        List<Courses> courses=courseRepository.findAll();
        m.addAttribute("students",user);
        System.out.println(courses.get(0).getSubject()+" cccccccccccccccc");
        m.addAttribute("course",courses);
        return "taskAdd.html";
    }

    @PostMapping("/AddTask")
    public RedirectView addPost(@RequestParam String title,@RequestParam String body, @RequestParam String dueDate, @RequestParam String deadLine,@RequestParam Integer courseId, Principal p){
        user=userService.findByEmail(p.getName());
        Courses courses=courseRepository.findById(courseId).get();
        Tasks postPage=new Tasks(title,body,dueDate,deadLine,user,courses);
        taskRepository.save(postPage);

        return new RedirectView("/displayTasks");
    }

    @PostMapping("/AddCourse")
    public RedirectView addCourse(@RequestParam String subject, HttpServletRequest request, Principal p){
        user=userService.findByEmail(p.getName());
        String[] names = request.getParameterValues("names");
        List<String> list = Arrays.asList(names);
        for (String stu:list){
            Integer id=Integer.valueOf(stu);
            users.add(userRepository.findById(id).get());
        }
        Courses courses=new Courses(subject,user);
        courseRepository.save(courses);
        return new RedirectView("/displayTasks");
    }

    @GetMapping("/displayTasks")
    public String displayTasks(Principal p,Model m){
        m.addAttribute("admin",userRepository.findById(1).get());
        m.addAttribute("courses",courseRepository.findAll());
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

    @GetMapping("/courseSubject/{id}")
    public String displayCourses(@PathVariable Integer id, Model m, Principal p) {
        Courses courses=courseRepository.findById(id).get();
        List<Tasks> tasks=taskRepository.findByCoursesId(id);
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

        return "displayCourses.html";
    }
}
