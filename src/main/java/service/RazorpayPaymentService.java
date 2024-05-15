package service;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import controller.PaymentController;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service("razorpayPaymentService")
public class RazorpayPaymentService implements IPaymentService {
    private RazorpayClient razorpayClient;

    public RazorpayPaymentService(RazorpayClient razorpayClient) {
        this.razorpayClient = razorpayClient;
    }

    @Override
    public String doPayment(String email, double amount, String phoneNo, String orderId) throws RazorpayException {
        JSONObject requestBody = getRequestBodyForRazorpay((int) amount, orderId);

        PaymentLink paymentLink = razorpayClient.paymentLink.create(requestBody);
        return paymentLink.toString();
    }

    private static JSONObject getRequestBodyForRazorpay(int amount, String orderId) {
        JSONObject customerInfo = new JSONObject();
        customerInfo.put("phone", "9100000000");
        customerInfo.put("email", "test@test.com");

        JSONObject notify = new JSONObject();
        notify.put("sms", true);
        notify.put("email", true);

        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", amount);
        orderRequest.put("currency", "INR");
        orderRequest.put("receipt", orderId);
        orderRequest.put("customer", customerInfo);
        orderRequest.put("notify", notify);
        orderRequest.put("callback_url", "https://localhost:8080/razorpay/webhook");
        orderRequest.put("callback_method", "post");

        return orderRequest;
    }
}
