package configuration;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class RazorpayConfig {

    @Value("${razorpay.key.id}")
    String razorpayId;

    @Value("${razorpay.key.secret}")
    String razorpaySecret;

    @Bean
    public RazorpayClient createRazorpayClient () throws RazorpayException {
        return new RazorpayClient(razorpayId, razorpaySecret);
    }
}