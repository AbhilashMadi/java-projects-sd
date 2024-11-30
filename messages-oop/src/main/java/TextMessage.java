
public class TextMessage extends Message {
    public TextMessage(String sender, String message) {
        super(sender, message);
    }

    public String displayMessage(){
        return String.format("%s: %s", getSender(), getContent());
    }

    public String sendMessage(){
        return String.format("Message sent: '%s'", getContent());
    }
}
