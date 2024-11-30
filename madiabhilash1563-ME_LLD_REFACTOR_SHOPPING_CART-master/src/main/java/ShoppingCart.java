import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<CartItem> items;
    private PaymentProcessor paymentProcessor;
    private NotificationService notificationService;

    public ShoppingCart(PaymentProcessor paymentProcessor, NotificationService notificationService) {
        this.items = new ArrayList<>();
        this.paymentProcessor = paymentProcessor;
        this.notificationService = notificationService;
    }

    public void addItem(CartItem item) {
        items.add(item);
    }

    public double getTotalAmount() {
        return items.stream().mapToDouble(CartItem::getPrice).sum();
    }

    public void checkout() {
        double totalAmount = getTotalAmount();
        paymentProcessor.processPayment(totalAmount);
        notificationService.sendNotification("Order placed for amount: " + totalAmount);
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setPaymentProcessor(PaymentProcessor paymentProcessor){
        this.paymentProcessor = paymentProcessor;
    }

    public void setNotificationService(NotificationService notificationService){
        this.notificationService = notificationService;
    }
}

