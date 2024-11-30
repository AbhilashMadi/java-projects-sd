
public class VideoMessage extends Message {
    private static final String[] VALID_VIDEO_EXTENSIONS = {"mp4"};

    public VideoMessage(String sender, String content) {
        super(sender, content);
    }

    private boolean isValidVideo() {
        for (String ext : VALID_VIDEO_EXTENSIONS) {
            if (getContent().endsWith("." + ext)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String displayMessage() {
        if (isValidVideo()) {
            return String.format("%s sent a video: %s", getSender(), getContent());
        } else {
            return "Invalid content type for video message.";
        }
    }

    @Override
    public String sendMessage() {
        if (isValidVideo()) {
            return String.format("Video message sent: '%s'", getContent());
        } else {
            return String.format("Cannot send message with content type '%s'.", getExtension());
        }
    }

    private String getExtension() {
        int index = getContent().lastIndexOf('.');
        return index == -1 ? "" : getContent().substring(index + 1);
    }
}
