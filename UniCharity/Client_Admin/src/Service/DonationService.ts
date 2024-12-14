import { toast } from "react-toastify";
import { ResponseListDonationAPI } from "../Models/ResponseDonationAPI";
import axiosInstance from "./axios_instance";
import { RootListDonationByCampaignId, RootTopDontaionByCampaignId } from "../Models/Donation";

const api = "/donation";

export const GetListDonationAPI = async (
  page: number,
  limit: number,
  sort: string
) => {
  try {
    const response = await axiosInstance.get<ResponseListDonationAPI>(api, {
      params: {
        page: page,
        size: limit,
        sort: sort,
      },
    });
    console.log("response list donation api: ", response);
    return response;
  } catch (error) {
    toast.warning("erorr:" + error);
  }
};

export const GetListDonationByCampaignIdAPI = async (
  campaignId: number,
  page: number,
  limit: number
) => {
  try {
    const response = await axiosInstance.get<ResponseListDonationAPI>(
      api + `/get-by-campaign-id/${campaignId}`,
      {
        params: {
          page: page,
          size: limit,
        },
      }
    );
    console.log("response list donation api: ", response);
    return response;
  } catch (error) {
    toast.warning("erorr:" + error);
  }
};


export const GetTopDonationAPI = async (
    campaignId: number,
    top:number
  ) => {
    try {
      const response = await axiosInstance.get<RootTopDontaionByCampaignId>(
        api + `/top-donors-of-campaign/${campaignId}`,
        {
          params: {
            top: top,
          },
        }
      );
      return response;
    } catch (error) {
      toast.warning("erorr:" + error);
    }
  };

  export const GetListDonationByCampaignIdAPIChart = async (
    campaignId: number,
    page:number =0,
    size:number =10
  ) => {
    try {
      const response = await axiosInstance.get<RootListDonationByCampaignId>(
        api + `/get-by-campaign-id/${campaignId}`,{
          params:{
            page:page,
            size:size
          }
        }
      );
      return response;
    } catch (error) {
      toast.warning("erorr:" + error);
    }
  };

 