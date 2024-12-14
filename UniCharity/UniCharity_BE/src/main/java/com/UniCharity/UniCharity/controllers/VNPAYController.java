package com.UniCharity.UniCharity.controllers;

import com.UniCharity.UniCharity.dto.request.DonationCreateRequest;
import com.UniCharity.UniCharity.dto.request.TransactionCreateRequest;
import com.UniCharity.UniCharity.dto.response.ApiResponse;
import com.UniCharity.UniCharity.dto.response.campaign.CampaignResponse;
import com.UniCharity.UniCharity.dto.response.donation.DonationResponse;
import com.UniCharity.UniCharity.dto.response.transaction.TransactionResponse;
import com.UniCharity.UniCharity.services.CampaignService;
import com.UniCharity.UniCharity.services.DonationService;
import com.UniCharity.UniCharity.services.TransactionService;
import com.UniCharity.UniCharity.services.VNPAYService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;import java.text.NumberFormat;
import java.util.Locale;

@RestController
@RequestMapping("/vnpay")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VNPAYController {
    VNPAYService vnPayService;
    DonationService donationService;
    TransactionService transactionService;
    CampaignService campaignService;
    @NonFinal
    DonationResponse donationResponse;
    @NonFinal
    TransactionResponse transactionResponse;
    @NonFinal
    CampaignResponse campaignResponse;

    // Chuyển hướng người dùng đến cổng thanh toán VNPAY
    @PostMapping("/create_payment")
    public ApiResponse<String> submidOrder(HttpServletRequest request, @RequestBody @Valid DonationCreateRequest donationCreateRequest) {
        this.donationResponse = donationService.createDonation(donationCreateRequest, "online_payment");
        this.transactionResponse = transactionService.createTransaction(new TransactionCreateRequest(donationResponse.getId(), "pending"));
        campaignResponse = campaignService.getCampaign(donationResponse.getCampaign().getId());

        // Định dạng tiền VNĐ
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        String formattedAmount = currencyFormatter.format(donationResponse.getAmount());

        // Loại bỏ ký hiệu tiền tệ (₫) nếu không cần
        formattedAmount = formattedAmount.replace("₫", "").trim() + " VNĐ";

        String orderInfor = "Đóng góp '" + campaignResponse.getTitle() + "' , Số tiền " + formattedAmount;
        String vnpayUrl = vnPayService.createOrder(request, donationResponse.getAmount(), orderInfor);
        return ApiResponse.<String>builder().result(vnpayUrl).build();
    }

    // Sau khi hoàn tất thanh toán, VNPAY sẽ chuyển hướng trình duyệt về URL này
    @GetMapping("/payment-return")
    public ResponseEntity<Void> paymentCompleted(HttpServletRequest request) {
        String completeUrl = transactionService.updateTransaction(transactionResponse.getId(), campaignResponse.getId(), request);
        return ResponseEntity.status(302).header("Location", completeUrl).build();
    }
}
