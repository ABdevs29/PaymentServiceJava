package controller;

import dto.PaymentsRequestDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import service.IPaymentService;

@RestController
public class PaymentController {
    private IPaymentService razorPayService;
    private IPaymentService stripePayService;

    public PaymentController (@Qualifier("razorpayPaymentService") IPaymentService razorPayService, @Qualifier("stripePaymentService") IPaymentService stripePayService) {
        this.razorPayService = razorPayService;
        this.stripePayService = stripePayService;
    }

    @PostMapping("/payment")
    public String initiatePayment (@RequestBody PaymentsRequestDto dto) {
        int gatewayType = getPaymentGatewayType();
        String response = switch (gatewayType) {
            case 1 -> razorPayService.doPayment(dto.getEmail(), dto.getAmount(), dto.getPhoneNo(), dto.getOrderId());
            case 2 -> stripePayService.doPayment(dto.getEmail(), dto.getAmount(), dto.getPhoneNo(), dto.getOrderId());
            default -> "";
        };

        return response;
    }

    private int getPaymentGatewayType() {
        return 1;
    }
}
