package controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RazorpayWebhook {
    @PostMapping("/razorpay/webhook")
    public String razorpayCallback () {
        return "redirecting cusotmer....";
    }
}
