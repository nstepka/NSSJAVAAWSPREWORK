
/**
 * Write a description of LetterGrade here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public enum LetterGrade {
    A(90),
    B(80),
    C(70),
    D(60),
    F(0);

    private double minPercent;


    private LetterGrade(double minPercent) {
        this.minPercent = minPercent;
    }

    public Double getMinimumPercent() {
        return this.minPercent;
    }


}
    

