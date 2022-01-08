public class Question {
    private String text;
    private Answer correctAnswer;
    private Answer[] wrongAnswers; // There should always be at least 3 wrong answers

    public Question(final String text, final Answer correctAnswer, final Answer[] wrongAnswers) {
        this.text = text;
        this.correctAnswer = correctAnswer;
        this.wrongAnswers = wrongAnswers;
    }

    public void addWrongAnswer(final Answer answer) {
        // Copy the existing answers into an array one longer than the current array
        final Answer[] moreWrongAnswers = new Answer[wrongAnswers.length + 1];
        for (int i = 0; i < wrongAnswers.length; i++) {
            moreWrongAnswers[i] = wrongAnswers[i];
        }

        // Add the new answer onto the end.
        moreWrongAnswers[wrongAnswers.length] = answer;

        // Replace the old array with the new, bigger one
        wrongAnswers = moreWrongAnswers;
    }

    public String getText() {
        return text;
    }

    public Answer getCorrectAnswer() {
        return correctAnswer;
    }

    public Answer[] getWrongAnswers() {
        return wrongAnswers;
    }
}
