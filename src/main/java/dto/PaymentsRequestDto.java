package dto;

import lombok.Data;

@Data
public class PaymentsRequestDto {
    String email;
    double amount;
    String phoneNo;
    String orderId;
}
