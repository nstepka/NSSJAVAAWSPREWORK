
/**
 * Write a description of Grades here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Grades {

    
    private int numQuestions;
    private int numCorrect;
    
    public Grades(int numQuestions, int numCorrect){
     this.numQuestions = numQuestions;
     this.numCorrect = numCorrect;
    }
    
    public Double getPercentScore(){
        return (double) numCorrect / numQuestions * 100;
        
    }
}
