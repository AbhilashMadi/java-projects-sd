import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PaytmPaymentProcessor implements PaymentProcessor {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing payment of " + amount + " through Paytm.");
    }
}

class PushNotificationService implements NotificationService {
    @Override
    public void sendNotification(String message) {
        System.out.println("Sending push notification: " + message);
    }
}

public class ShoppingCartTest {

    private ShoppingCart shoppingCart;
    private PaymentProcessor paymentProcessorMock;
    private NotificationService notificationServiceMock;

    @BeforeEach
    void setUp() {
        paymentProcessorMock = mock(PaymentProcessor.class);
        notificationServiceMock = mock(NotificationService.class);
        shoppingCart = new ShoppingCart(paymentProcessorMock, notificationServiceMock);
    }

    @Test
    void testInitialState() {
        assertTrue(shoppingCart.getItems().isEmpty(), "Shopping cart should be empty initially.");
    }

    @Test
    void testRefactoredCodeFunctionality() {
        shoppingCart.addItem(new CartItem("Item1", 10.0));
        assertEquals(1, shoppingCart.getItems().size(), "Shopping cart should have one item.");
        assertEquals(10.0, shoppingCart.getTotalAmount(), "Total amount should be 10.0.");

        shoppingCart.checkout();
        verify(paymentProcessorMock, times(1)).processPayment(10.0);
        verify(notificationServiceMock, times(1)).sendNotification("Order placed for amount: 10.0");
    }

    @Test
    void testExtensibilityWithNewPaymentMethod() {
        shoppingCart.addItem(new CartItem("Item1", 10.0));
        PaymentProcessor paytmPaymentProcessor = spy(new PaytmPaymentProcessor());
        shoppingCart.setPaymentProcessor(paytmPaymentProcessor);
        shoppingCart.checkout();
        verify(paytmPaymentProcessor, times(1)).processPayment(10.0);
        verify(notificationServiceMock, times(1)).sendNotification("Order placed for amount: 10.0");
    }

    @Test
    void testExtensibilityWithNewNotificationChannel() {
        shoppingCart.addItem(new CartItem("Item1", 10.0));
        NotificationService pushNotificationService = spy(new PushNotificationService());
        shoppingCart.setNotificationService(pushNotificationService);
        shoppingCart.checkout();
        verify(paymentProcessorMock, times(1)).processPayment(10.0);
        verify(pushNotificationService, times(1)).sendNotification("Order placed for amount: 10.0");
    }
}

