import { toast } from "react-toastify";
import axiosInstance from "./axios_instance";
import { CreateCampaignDto, UpdateCampaignDto } from "../Models/Campaign";
import { ResponseCreateCampaignAPI, ResponseListCampaignAPI, ResponseUpdateCampaignAPI } from "../Models/ResponseAPI";
import { RootChartYear, RootTableYear } from "../Models/CharYear";
import { PAGE_TABLE_CHART_YEAR, SIZE_TABLE_CHART_YEAR } from "../Utils/ChartConstants";

const api = "/campaigns";

export const GetListCampaignByStatus = async (
  status: string,
  page: number = 1,
  limit: number = 5
) => {
  try {
    // const x = `${api}/get-by-status/${status}`;
    // console.log("x:",x);
    const response = await axiosInstance.get<ResponseListCampaignAPI>(
      `${api}/get-by-status/${status}`,
      {
        params: {
          page: page,
          size: limit,
          sortDirection:"sn"
        },
      }
    );
    console.log("response service:", response);
    return response;
  } catch (error) {
    console.log("error", error);
    toast.warning("List empty!");
  }
};

export const GetCampaignById = async (campaignId: string) => {
  try {
    const response = await axiosInstance.get<ResponseCreateCampaignAPI | null>(
      `${api}/get-by-id/${campaignId}`
    );
    return response.data;
  } catch (error) {
    console.log(error);
    toast.error("Campaign does not exist!");
  }
};


export const CreateCampaignAPI = async (formInput: CreateCampaignDto) => {
  try {
    console.log("into:",formInput)
    const response = await axiosInstance.post<ResponseCreateCampaignAPI>(`${api}/create`,formInput);
    return response;
  } catch (error) {
    console.log(error);
    throw error;
  }
};

export const UpdateCampaignAPI = async (campaitnId: number,formInput: UpdateCampaignDto) => {
  try {
    console.log("into:",formInput)
    console.log("+++++++++++++++++++++++==============")
    const response = await axiosInstance.put<ResponseUpdateCampaignAPI>(`${api}/update/${campaitnId}`,formInput)
    console.log("Update Campaign response: ", response)
    return response;
  } catch (error) {
    console.dir(error);
  }
};


//chart
export const CountByMonthChart = async (year:number) => {
  try {
    const response = await axiosInstance.get<RootChartYear>(`${api}/count-by-month/${year}`)
    return response;
  } catch (error) {
    console.log(error);
  }
};

export const GetCampaignByYear = async (year:number) => {
  try {
    const response = await axiosInstance.get<RootChartYear>(`${api}/count-by-month/${year}`)
    return response;
  } catch (error) {
    console.log(error);
  }
};

export const GetCampaignTableByYear = async (year:number,page:number=PAGE_TABLE_CHART_YEAR,size:number = SIZE_TABLE_CHART_YEAR) => {
  try {
    const response = await axiosInstance.get<RootTableYear>(`${api}/get-by-year/${year}`,{
      params:{
        page:page,
        size:size
      }
    })
    console.log("table:", response.data.result.items)
    return response;
  } catch (error) {
    console.log(error);
  }
};
