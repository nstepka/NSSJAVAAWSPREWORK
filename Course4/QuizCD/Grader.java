
/**
 * Write a description of Grader here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Grader {
    Student[] students = new Student[0];

    public void addStudent(Student student){
        Student[] updatedStudents = new Student[students.length];
        for (int i = 0; i < students.length; i++) {
            updatedStudents[i] = students[i];
        }
        updatedStudents[students.length] = student;
        students = updatedStudents;
    }


    //NOT SURE ABOUT THIS ONE
    public LetterGrade getLetterGrade(String id) {
        Student student = new Student(id);
        double total = 0;
        Grades[] studentGrades = student.getGrades();
        for(int i = 0; i < studentGrades.length; i++){
            total = total + studentGrades[i].getPercentScore();
        }
        double gradeAvg = total / studentGrades.length;
        for(LetterGrade grades: LetterGrade.values()){
            if(grades.getMinimumPercent() <= gradeAvg){
                return grades;
            }
        }
        return null;
    }

    public int administer(String studentID, Quiz quiz){
        for(int i =0; i < students.length; i++){
            if(students[i].getId().contentEquals(studentID)){
                Grades grade = new Grades(quiz.administer(), quiz.getNumberOfQuestions());
                students[i].addGrade(grade);
            }
        }
        //I dont know what to return.
        return 0;
    }
}
