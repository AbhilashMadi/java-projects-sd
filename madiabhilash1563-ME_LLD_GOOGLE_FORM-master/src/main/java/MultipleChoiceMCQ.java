import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MultipleChoiceMCQ extends Question{
    private final String[] choices;

    public MultipleChoiceMCQ(String questionText, String[] choices){
        super(questionText);
        this.choices = choices;
    }

    @Override
    public boolean acceptResponse(String response){
        if(response == null || response.isEmpty()) return false;

        String[] selectedChoices = response.split(",\\s*");
        Set<String> validChoices = new HashSet<>(Arrays.asList(choices));

        for(String choice: selectedChoices){
            if(!validChoices.contains(choice)) return false;
        }

        return true;
    }
    
    public String[] getChoices(){
        return this.choices;
    }
}