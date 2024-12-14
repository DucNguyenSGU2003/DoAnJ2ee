package com.UniCharity.UniCharity.dto.response.donation;

import com.UniCharity.UniCharity.dto.response.campaign.CampaignSimple;
import com.UniCharity.UniCharity.dto.response.transaction.TransactionSimple;
import com.UniCharity.UniCharity.dto.response.user.UserSimple;
import com.UniCharity.UniCharity.dto.response.transaction.TransactionResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DonationResponse {
    Integer id;
    Long amount;
    String paymentMethod;
    LocalDateTime donationDate;

    CampaignSimple campaign;
    UserSimple user;

    List<TransactionSimple> transactions;
}
