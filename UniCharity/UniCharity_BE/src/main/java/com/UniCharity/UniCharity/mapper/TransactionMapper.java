package com.UniCharity.UniCharity.mapper;

import com.UniCharity.UniCharity.dto.request.TransactionCreateRequest;
import com.UniCharity.UniCharity.dto.request.TransactionUpdateRequest;
import com.UniCharity.UniCharity.dto.response.transaction.TransactionResponse;
import com.UniCharity.UniCharity.dto.response.transaction.TransactionSimple;
import com.UniCharity.UniCharity.entities.Transaction;

public class TransactionMapper {
    public static Transaction toTransaction(TransactionCreateRequest request) {
        if (request == null) {
            return null;
        }

        Transaction transaction = new Transaction();

        transaction.setTransactionStatus(request.getTransactionStatus());

        return transaction;
    }

    public static TransactionResponse toTransactionResponse(Transaction transaction) {
        if (transaction == null) {
            return null;
        }

        TransactionResponse.TransactionResponseBuilder transactionResponse = TransactionResponse.builder();

        transactionResponse.id(transaction.getId());
        transactionResponse.transactionCode(transaction.getTransactionCode());
        transactionResponse.paymentGateway(transaction.getPaymentGateway());
        transactionResponse.transactionDate(transaction.getTransactionDate());
        transactionResponse.transactionStatus(transaction.getTransactionStatus());
        transactionResponse.amount(transaction.getAmount());
        transactionResponse.responseCode(transaction.getResponseCode());
        transactionResponse.transactionDescription(transaction.getTransactionDescription());
        transactionResponse.donation(DonationMapper.toDonationSimple(transaction.getDonation()));

        return transactionResponse.build();
    }

    public static TransactionSimple toTransactionSimple(Transaction transaction) {
        if (transaction == null) {
            return null;
        }

        TransactionSimple.TransactionSimpleBuilder transactionSimple = TransactionSimple.builder();

        transactionSimple.id(transaction.getId());
        transactionSimple.transactionCode(transaction.getResponseCode());
        transactionSimple.paymentGateway(transaction.getPaymentGateway());
        transactionSimple.transactionDate(transaction.getTransactionDate());
        transactionSimple.transactionStatus(transaction.getTransactionStatus());
        transactionSimple.amount(transaction.getAmount());
        transactionSimple.responseCode(transaction.getResponseCode());
        transactionSimple.transactionDescription(transaction.getTransactionCode());

        return transactionSimple.build();
    }

    public static void updateTransaction(Transaction transaction, TransactionUpdateRequest request) {
        if (request == null) {
            return;
        }

        transaction.setTransactionCode(request.getTransactionCode());
        transaction.setPaymentGateway(request.getPaymentGateway());
        transaction.setTransactionDate(request.getTransactionDate());
        transaction.setTransactionStatus(request.getTransactionStatus());
        transaction.setAmount(request.getAmount());
        transaction.setResponseCode(request.getResponseCode());
        transaction.setTransactionDescription(request.getTransactionDescription());
    }
}
