
/**
 * Write a description of Student here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Student {
    private String id;
    private Grades[] grades = new Grades[0];


    public Student(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Grades[] getGrades() {
        return grades;
    }

    public void addGrade(Grades grade) {
        Grades[] gradesUpdates = new Grades[grades.length + 1];
        for (int i = 0; i < grades.length; i++) {
            gradesUpdates[i] = grades[i];
        }
        gradesUpdates[grades.length] = grade;
        grades = gradesUpdates;
    }
}
