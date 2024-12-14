package com.UniCharity.UniCharity.services;

import com.UniCharity.UniCharity.dto.request.DonationCreateRequest;
import com.UniCharity.UniCharity.dto.response.donation.DonationResponse;
import com.UniCharity.UniCharity.dto.response.page.PageResponse;
import com.UniCharity.UniCharity.entities.Campaign;
import com.UniCharity.UniCharity.entities.Donation;
import com.UniCharity.UniCharity.entities.User;
import com.UniCharity.UniCharity.exception.AppException;
import com.UniCharity.UniCharity.exception.ErrorCode;
import com.UniCharity.UniCharity.mapper.DonationMapper;
import com.UniCharity.UniCharity.repositories.CampaignRepository;
import com.UniCharity.UniCharity.repositories.DonationRepository;
import com.UniCharity.UniCharity.repositories.UserRepository;
import com.UniCharity.UniCharity.services.iservices.IDonationService;
import com.UniCharity.UniCharity.utils.PageUtils;
import com.UniCharity.UniCharity.utils.SortUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class DonationService implements IDonationService {
    CampaignRepository campaignRepository;
    UserRepository userRepository;
    DonationRepository donationRepository;
    UserService userService;

    @Override
    public DonationResponse createDonation(DonationCreateRequest request, String paymentMethod) {
        Campaign campaign = campaignRepository.findById(request.getCampaign()).orElseThrow(() -> new AppException(ErrorCode.CAMPAIGN_NOT_EXISTED));
        User user = userRepository.findByEmail(request.getEmail()).orElse(null);
        if(user == null) {
            user = userService.createUserWithEmail(request.getEmail(), request.getName());
        }
        Donation donation = DonationMapper.toDonation(request);
        donation.setCampaign(campaign);
        donation.setUser(user);
        donation.setPaymentMethod(paymentMethod);
        donation = donationRepository.save(donation);
        return DonationMapper.toDonationResponse(donation);
    }

    @Override
    public PageResponse<DonationResponse> getDonations(int page, int size, String sortField, String sortDirection) {
        List<DonationResponse> donationResponses = donationRepository.findAll().stream().map(DonationMapper::toDonationResponse).collect(Collectors.toList());

        SortUtils.sortList(donationResponses, sortField, sortDirection);

        Page<DonationResponse> donationPage = PageUtils.paginateList(donationResponses, page, size);

        return new PageResponse<>(
                donationPage.getContent(),
                com.UniCharity.UniCharity.dto.response.page.Page.builder()
                        .totalItem(donationPage.getTotalElements())
                        .currentPage(donationPage.getNumber())
                        .totalPages(donationPage.getTotalPages())
                        .pageSize(donationPage.getSize())
                        .build()
        );
    }

    @Override
    public DonationResponse getDonation(int donationId) {
        Donation donation = donationRepository.findById(donationId).orElseThrow(() -> new AppException(ErrorCode.DONATION_NOT_EXISTED));
        return DonationMapper.toDonationResponse(donation);
    }

    @Override
    public PageResponse<DonationResponse> getDonationsByUserId(int userId, int page, int size, String sortField, String sortDirection) {
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        List<DonationResponse> donationResponses = donationRepository.findAllByUserId(userId).stream().map(DonationMapper::toDonationResponse).collect(Collectors.toList());

        SortUtils.sortList(donationResponses, sortField, sortDirection);

        Page<DonationResponse> donationPage = PageUtils.paginateList(donationResponses, page, size);

        return new PageResponse<>(
                donationPage.getContent(),
                com.UniCharity.UniCharity.dto.response.page.Page.builder()
                        .totalItem(donationPage.getTotalElements())
                        .currentPage(donationPage.getNumber())
                        .totalPages(donationPage.getTotalPages())
                        .pageSize(donationPage.getSize())
                        .build()
        );
    }

    @Override
    public PageResponse<DonationResponse> getDonationsByCampaignId(int campaignId, int page, int size, String sortField, String sortDirection) {
        Campaign campaign = campaignRepository.findById(campaignId).orElseThrow(() -> new AppException(ErrorCode.CAMPAIGN_NOT_EXISTED));

        List<DonationResponse> donationResponses = donationRepository.findAllByCampaignId(campaign.getId()).stream().map(DonationMapper::toDonationResponse).collect(Collectors.toList());

        SortUtils.sortList(donationResponses, sortField, sortDirection);

        Page<DonationResponse> donationPage = PageUtils.paginateList(donationResponses, page, size);

        return new PageResponse<>(
                donationPage.getContent(),
                com.UniCharity.UniCharity.dto.response.page.Page.builder()
                        .totalItem(donationPage.getTotalElements())
                        .currentPage(donationPage.getNumber())
                        .totalPages(donationPage.getTotalPages())
                        .pageSize(donationPage.getSize())
                        .build()
        );
    }

    @Override
    public List<Object[]> getTopUserByCampaignId(int campaignId, int top) {
        Campaign campaign = campaignRepository.findById(campaignId).orElseThrow(() -> new AppException(ErrorCode.CAMPAIGN_NOT_EXISTED));
        return donationRepository.findTopUsersByCampaignId(campaignId, PageRequest.of(0, top));
    }
}
