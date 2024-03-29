package sit.int204.classicmodelsservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sit.int204.classicmodelsservice.models.Student;
import sit.int204.classicmodelsservice.services.StudentGradeService;

@RestController
@RequestMapping("/api/grades")
public class StudentFGradeController {
    @Autowired
    private StudentGradeService service;
    @GetMapping("")
    public Student calculateGrade(@RequestBody Student student) {
        return service.calculateGrade(student);
    }
}
