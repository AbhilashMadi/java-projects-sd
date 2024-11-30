
public abstract class Message {
    private final String sender;
    private final String content;

    public Message(String sender, String content) {
        this.sender = sender;
        this.content = content;
    }

    public abstract String displayMessage();
    public abstract String sendMessage();

    public String getSender() {
        return this.sender;
    }

    public String getContent(){
        return this.content;
    }
}
