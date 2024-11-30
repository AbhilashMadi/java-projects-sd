import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
// import static org.junit.Assert.*;

public class GoogleFormTest {

    @Test
    public void testShortAnswerQuestion() {
        ShortAnswerQuestion shortQ = new ShortAnswerQuestion("What is your name?");
        assertEquals(shortQ.getQuestionText(), "What is your name?");
        assertTrue(shortQ.acceptResponse("John"));
        assertFalse(shortQ.acceptResponse("This response exceeds the character limit of thirty."));
    }


    @Test
    public void testLongAnswerQuestion() {
        LongAnswerQuestion longQ = new LongAnswerQuestion("Describe your weekend plans.");
        assertEquals(longQ.getQuestionText(), "Describe your weekend plans.");
        assertTrue(longQ.acceptResponse("I plan to go hiking and spend time with family."));
    }

    @Test
    public void testSingleChoiceMCQ() {
        String[] choices = {"Paris", "London", "Berlin", "Madrid"};
        SingleChoiceMCQ singleMCQ = new SingleChoiceMCQ("What is the capital of France?", choices);
        assertEquals(singleMCQ.getQuestionText(), "What is the capital of France?");
        assertTrue(singleMCQ.acceptResponse("Paris"));
        assertFalse(singleMCQ.acceptResponse("Paris, London"));
    }

    @Test
    public void testMultipleChoiceMCQ() {
        String[] choices = {"Red", "Blue", "Green", "Yellow"};
        MultipleChoiceMCQ multiMCQ = new MultipleChoiceMCQ("Select your favorite colors.", choices);
        assertEquals(multiMCQ.getQuestionText(), "Select your favorite colors.");
        assertTrue(multiMCQ.acceptResponse("Red, Blue"));
        assertFalse(multiMCQ.acceptResponse("Red, Purple"));
    }
}
