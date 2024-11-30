
public class ImageMessage extends Message {
    private final String[] VALID_IMAGE_EXTENSIONS = {"jpg", "jpeg", "png"};

    public ImageMessage(String sender, String content) {
        super(sender, content);
    }

    public boolean isValidImageExtension() {
        for (String ext : VALID_IMAGE_EXTENSIONS) {
            if (getContent().endsWith("." + ext)) return true;
        }

        return false;
    }

    public String getExtension() {
        int i = getContent().lastIndexOf(".");

        return i == -1 ? "" : getContent().substring(i + 1);
    }

    @Override
    public String displayMessage() {
        if (isValidImageExtension()) {
            return String.format("%s sent an image: %s", getSender(), getContent());
        } else {
            return "Invalid content type for image message.";
        }
    }

    @Override
    public String sendMessage() {
        if (isValidImageExtension()) {
            return String.format("Image message sent: '%s'", getContent());
        } else {
            return String.format("Cannot send message with content type '%s'.", getExtension());
        }
    }
}
