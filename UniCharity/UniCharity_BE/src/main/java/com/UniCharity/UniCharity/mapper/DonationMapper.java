package com.UniCharity.UniCharity.mapper;

import com.UniCharity.UniCharity.dto.request.DonationCreateRequest;
import com.UniCharity.UniCharity.dto.response.donation.DonationResponse;
import com.UniCharity.UniCharity.dto.response.donation.DonationSimple;
import com.UniCharity.UniCharity.dto.response.transaction.TransactionResponse;
import com.UniCharity.UniCharity.dto.response.transaction.TransactionSimple;
import com.UniCharity.UniCharity.entities.Donation;
import com.UniCharity.UniCharity.entities.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DonationMapper {
    public static Donation toDonation(DonationCreateRequest request) {
        if(request == null) {
            return null;
        }

        Donation donation = new Donation();

        donation.setAmount(request.getAmount());
        donation.setDonationDate(request.getDonationDate());

        return donation;
    }

    public static DonationResponse toDonationResponse(Donation donation) {
        if(donation == null) {
            return null;
        }

        DonationResponse.DonationResponseBuilder donationResponse = DonationResponse.builder();

        donationResponse.id(donation.getId());
        donationResponse.amount(donation.getAmount());
        donationResponse.paymentMethod(donation.getPaymentMethod());
        donationResponse.donationDate(donation.getDonationDate());
        donationResponse.campaign(CampaignMapper.toCampaignSimple(donation.getCampaign()));
        donationResponse.user(UserMapper.toUserSimple(donation.getUser()));
        donationResponse.transactions(transactionSetToTransactionResponseList(donation.getTransactions()));

        return donationResponse.build();
    }

    public static DonationSimple toDonationSimple(Donation donation) {
        if (donation == null) {
            return null;
        }

        DonationSimple.DonationSimpleBuilder donationSimple = DonationSimple.builder();

        donationSimple.id(donation.getId());
        donationSimple.amount(donation.getAmount());
        donationSimple.paymentMethod(donation.getPaymentMethod());
        donationSimple.donationDate(donation.getDonationDate());

        return donationSimple.build();
    }

    protected static List<TransactionSimple> transactionSetToTransactionResponseList(Set<Transaction> set) {
        if (set == null) {
            return null;
        }

        List<TransactionSimple> list = new ArrayList<>(set.size());
        for (Transaction transaction : set) {
            list.add(TransactionMapper.toTransactionSimple(transaction));
        }
        return list;
    }

}
