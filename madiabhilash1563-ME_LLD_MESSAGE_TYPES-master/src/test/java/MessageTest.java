import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MessageTest {

    @Test
    void testTextMessage() {
        Message textMessage = new TextMessage("Alice", "Hello, how are you?");
        assertEquals("Alice: Hello, how are you?", textMessage.displayMessage());
        assertEquals("Message sent: 'Hello, how are you?'", textMessage.sendMessage());
    }

    @Test
    void testValidImageMessage() {
        Message imageMessage = new ImageMessage("Bob", "sunset.jpg");
        assertEquals("Bob sent an image: sunset.jpg", imageMessage.displayMessage());
        assertEquals("Image message sent: 'sunset.jpg'", imageMessage.sendMessage());
    }

    @Test
    void testInvalidImageMessage() {
        Message imageMessage = new ImageMessage("Eve", "notes.txt");
        assertEquals("Invalid content type for image message.", imageMessage.displayMessage());
        assertEquals("Cannot send message with content type 'txt'.", imageMessage.sendMessage());
    }

    @Test
    void testValidVideoMessage() {
        Message videoMessage = new VideoMessage("Charlie", "vacation.mp4");
        assertEquals("Charlie sent a video: vacation.mp4", videoMessage.displayMessage());
        assertEquals("Video message sent: 'vacation.mp4'", videoMessage.sendMessage());
    }

    @Test
    void testInvalidVideoMessage() {
        Message videoMessage = new VideoMessage("Charlie", "document.pdf");
        assertEquals("Invalid content type for video message.", videoMessage.displayMessage());
        assertEquals("Cannot send message with content type 'pdf'.", videoMessage.sendMessage());
    }

    @Test
    void testValidAudioMessage() {
        Message audioMessage = new AudioMessage("David", "song.wav");
        assertEquals("David sent an audio: song.wav", audioMessage.displayMessage());
        assertEquals("Audio message sent: 'song.wav'", audioMessage.sendMessage());
    }

    @Test
    void testInvalidAudioMessage() {
        Message audioMessage = new AudioMessage("David", "recording.txt");
        assertEquals("Invalid content type for audio message.", audioMessage.displayMessage());
        assertEquals("Cannot send message with content type 'txt'.", audioMessage.sendMessage());
    }
}

