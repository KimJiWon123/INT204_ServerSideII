package sit.int204.classicmodelsservice.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sit.int204.classicmodelsservice.models.Student;

import java.util.Scanner;

@Service
public class StudentGradeService {
    public Student getGrade(Student student) {
        if(student.getScore()==null || student.getScore()>100 || student.getScore()<0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Invalid Score ("+ student.getScore()+ ") !!!");
        }
        return student.calculateGrade();
    }
}
