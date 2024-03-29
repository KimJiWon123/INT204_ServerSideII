package sit.int204.classicmodelsservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {
    private Integer id;
    private String name;
    private Integer score;
    private String grade;

    public void calculateGrade() {
        if (score >= 80) {
            grade = "A";
        } else if (score >= 70) {
            grade = "B";
        } else if (score >= 60) {
            grade = "C";
        } else if (score >= 50) {
            grade = "D";
        } else {
            grade = "F";
        }
    }
}
