public class Quiz {

    private Question[] questions = new Question[0];

    public Question[] getQuestions() {
        return questions;
    }

    /**
     * In a real implementation, this would print questions and four randomly
     * selected answers (3 wrong and 1 right), get the user's choice, and
     * return how many questions the user answered correctly.
     *
     * In this implementation, we skip all that hard stuff and just return a
     * random number.
     *
     * @return The number of questions the user answered correctly.
     */
    public int administer() {
        // This is where all the hard work would be. We skip all that and
        // assume the user got a score between 0 and the number of questions.
        return (int)(Math.random() * (questions.length + 1));
    }

    public int getNumberOfQuestions() {
        return questions.length;
    }

    public void addQuestion(final Question question) {
        // Copy the old questions into an array one Question bigger
        final Question[] moreQuestions = new Question[questions.length + 1];
        for (int i = 0; i < questions.length; i++) {
            moreQuestions[i] = questions[i];
        }

        // Add the new Questions
        moreQuestions[questions.length] = question;

        // Swap the old questions for the new, bigger array
        questions = moreQuestions;
    }
}
