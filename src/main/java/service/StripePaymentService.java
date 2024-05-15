package service;

import org.springframework.stereotype.Service;

@Service("stripePaymentService")
public class StripePaymentService implements IPaymentService{
    @Override
    public String doPayment(String email, double amount, String phoneNo, String orderId) {
        return "";
    }
}
