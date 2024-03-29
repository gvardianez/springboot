package lesson_6;

import com.geekbrains.geekspring.entities.Course;
import com.geekbrains.geekspring.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
import java.util.List;

@Controller
@RequestMapping("/students")
@Transactional
public class StudentsController {
    private StudentsService studentsService;

    @Autowired
    public void setStudentsService(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @RequestMapping("/list")
    @Transactional
    public String showStudentsList(Model model) {
        List<Student> allStudents = studentsService.getAllStudentsList();
        model.addAttribute("studentsList", allStudents);
        return "students-list";
    }

    @GetMapping("/remove/{id}")
    public String removeStudent(@PathVariable("id") Long id) {
        studentsService.removeStudentById(id);
        return "redirect:students/list";
    }

    @GetMapping("/courses/{id}")
    public String showStudentCourses(Model model, @PathVariable("id") Long studentId) {
        List<Course> studentCourses = studentsService.getCoursesByStudentIdByQuery(studentId);
        model.addAttribute(studentCourses);
        model.addAttribute(studentId);
        model.addAttribute(studentsService.getMissingCoursesByStudentId(studentId));//вроде для вашей формы это надо
        return "student-courses-list";
    }


}
