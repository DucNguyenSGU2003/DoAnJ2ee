package com.UniCharity.UniCharity.mapper;

import com.UniCharity.UniCharity.dto.request.UserCreateRequest;
import com.UniCharity.UniCharity.dto.request.UserUpdateRequest;
import com.UniCharity.UniCharity.dto.response.campaign.CampaignResponse;
import com.UniCharity.UniCharity.dto.response.donation.DonationResponse;
import com.UniCharity.UniCharity.dto.response.fundAllocation.FundAllocationResponse;
import com.UniCharity.UniCharity.dto.response.user.UserResponse;
import com.UniCharity.UniCharity.dto.response.user.UserSimple;
import com.UniCharity.UniCharity.entities.Campaign;
import com.UniCharity.UniCharity.entities.Donation;
import com.UniCharity.UniCharity.entities.FundAllocation;
import com.UniCharity.UniCharity.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UserMapper {
    public static User toUser(UserCreateRequest request) {
        if ( request == null ) {
            return null;
        }

        User user = new User();

        user.setName( request.getName() );
        user.setEmail( request.getEmail() );
        user.setPhone( request.getPhone() );
        user.setPassword( request.getPassword() );
        user.setRole( request.getRole() );

        return user;
    }

    public static UserResponse toUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse.UserResponseBuilder userResponse = UserResponse.builder();

        userResponse.id( user.getId() );
        userResponse.name( user.getName() );
        userResponse.email( user.getEmail() );
        userResponse.phone( user.getPhone() );
        userResponse.password( user.getPassword() );
        userResponse.role( user.getRole() );
        userResponse.campaigns(campaignSetToCampaignResponseList(user.getCampaigns()));
        userResponse.fundAllocations(fundAllocationSetToFundAllocationResponseList( user.getFundAllocations()));
        userResponse.donations(donationSetToDonationResponse(user.getDonations()));

        return userResponse.build();
    }

    public static UserSimple toUserSimple(User user) {
        if ( user == null ) {
            return null;
        }

        UserSimple.UserSimpleBuilder userSimple = UserSimple.builder();

        userSimple.id( user.getId() );
        userSimple.name( user.getName() );
        userSimple.email( user.getEmail() );
        userSimple.phone( user.getPhone() );
        userSimple.password( user.getPassword() );
        userSimple.role( user.getRole() );

        return userSimple.build();
    }

    public static void updateUser(User user, UserUpdateRequest request){
        if ( request == null ) {
            return;
        }

        user.setName( request.getName() );
        user.setEmail( request.getEmail() );
        user.setPhone( request.getPhone() );
        user.setPassword( request.getPassword() );
        user.setRole( request.getRole() );
    }

    protected static List<FundAllocationResponse> fundAllocationSetToFundAllocationResponseList(Set<FundAllocation> set) {
        if ( set == null ) {
            return null;
        }

        List<FundAllocationResponse> list = new ArrayList<FundAllocationResponse>( set.size() );
        for ( FundAllocation fundAllocation : set ) {
            list.add( FundAllocationMapper.toFundAllocationResponse( fundAllocation ) );
        }

        return list;
    }

    protected static List<CampaignResponse> campaignSetToCampaignResponseList(Set<Campaign> set) {
        if ( set == null ) {
            return null;
        }

        List<CampaignResponse> list = new ArrayList<CampaignResponse>( set.size() );
        for ( Campaign campaign : set ) {
            list.add( CampaignMapper.toCampaignResponse( campaign ) );
        }

        return list;
    }

    protected static List<DonationResponse> donationSetToDonationResponse(Set<Donation> set) {
        if (set == null) {
            return null;
        }

        List<DonationResponse> list = new ArrayList<>(set.size());
        for (Donation donation : set) {
            list.add(DonationMapper.toDonationResponse(donation));
        }

        return list;
    }
}
