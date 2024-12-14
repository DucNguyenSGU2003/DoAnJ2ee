package com.UniCharity.UniCharity.services.iservices;

import com.UniCharity.UniCharity.dto.request.TransactionCreateRequest;
import com.UniCharity.UniCharity.dto.response.transaction.TransactionResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface ITransactionService {
    public TransactionResponse createTransaction(TransactionCreateRequest request);
    public String updateTransaction(int transactionId, int campaignId, HttpServletRequest request);
}
