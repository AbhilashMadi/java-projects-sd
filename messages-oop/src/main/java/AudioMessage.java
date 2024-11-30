public class AudioMessage extends Message {
    private static final String[] VALID_AUDIO_EXTENSIONS = {"wav", "mp3"};

    public AudioMessage(String sender, String content) {
        super(sender, content);
    }

    private boolean isValidAudio() {
        for (String ext : VALID_AUDIO_EXTENSIONS) {
            if (getContent().endsWith("." + ext)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String displayMessage() {
        if (isValidAudio()) {
            return String.format("%s sent an audio: %s", getSender(), getContent());
        } else {
            return "Invalid content type for audio message.";
        }
    }

    @Override
    public String sendMessage() {
        if (isValidAudio()) {
            return String.format("Audio message sent: '%s'", getContent());
        } else {
            return String.format("Cannot send message with content type '%s'.", getExtension());
        }
    }

    private String getExtension() {
        int index = getContent().lastIndexOf('.');
        return index == -1 ? "" : getContent().substring(index + 1);
    }
}
