package sit.int204.classicmodelsservice.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import sit.int204.classicmodelsservice.models.Student;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@Service
public class StudentGradeService {
    public Student calculateGrade(Student student) {
        if(student.getScore()==null || student.getScore()>100) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,
                    "Invalid score :  !!! (" + student.getScore() + ")");
        }
        student.calculateGrade();
        return student;
    }
    public void foo() {
       String x = null;
       x.trim();
    }
}
