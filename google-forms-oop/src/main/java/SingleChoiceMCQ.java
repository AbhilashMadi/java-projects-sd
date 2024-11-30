import java.util.Arrays;

public class SingleChoiceMCQ extends Question {
    private final String[] choices;

    public SingleChoiceMCQ(String questionText, String[] choices) {
        super(questionText);
        this.choices = choices;
    }

    @Override
    public boolean acceptResponse(String response){
        if(response == null || response.isEmpty()) return false;

        return Arrays.asList(choices).contains(response);
    }

    public String[] getChoices() {
        return this.choices;
    }
}
