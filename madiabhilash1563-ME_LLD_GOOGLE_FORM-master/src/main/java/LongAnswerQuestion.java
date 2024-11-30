
public class LongAnswerQuestion extends Question {
    public LongAnswerQuestion(String questionText) {
        super(questionText);
    }

    @Override
    public boolean acceptResponse(String response) {
        if (response == null || response.isEmpty())
            return false;

        return true;
    }
}
