
public class ShortAnswerQuestion extends Question{
    private static final int RES_MAX_LENGTH = 30;

    public ShortAnswerQuestion(String questionText){
        super(questionText);
    }

    @Override
    public boolean acceptResponse(String response){
        if(response == null || response.isEmpty()) return false;

        return response.length() <= RES_MAX_LENGTH;
    }
}