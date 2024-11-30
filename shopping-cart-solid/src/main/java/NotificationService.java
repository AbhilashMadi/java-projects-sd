
interface NotificationService {
    void sendNotification(String message);
}

class EmailNotificationService implements NotificationService {
    @Override
    public void sendNotification(String message) {
        System.out.println("Sending email notification: " + message);
    }
}

class PushNotificationService implements NotificationService {
    @Override
    public void sendNotification(String message) {
        System.out.println("Sending push notification: " + message);
    }
}