import axiosInstance from "./axios_instance";
import { toast } from "react-toastify";
import { LIMIT_POLICY, PAGE_POLICY, SORTDIRECTION_POLICY, SORTFIELD_POLICY } from "../Utils/PolicyConstant";
import { ResponseAllPolicy } from "../Models/Policy";

const api = "/policies";

export const GetListPolicyAPI = async (page:number,size:number)=>
    {
        //http://localhost:8080/UniCharity/policies?page =0&size=3&sortDirection=asc
  try {
    const response = await axiosInstance.get<ResponseAllPolicy>(api, {
        params:
        {
            page:page,
            size:size,
            sortDirection:SORTDIRECTION_POLICY,
            sortField:SORTFIELD_POLICY
        }
    });
    console.log("policy Response list:" ,response)
    return response;
}
  catch (err) {
   toast.error(err+""); 
  }
}

export const GetListPolicyByCampaignIdAPI = async (campaignId:number,page:number=PAGE_POLICY,size:number=LIMIT_POLICY)=>
    {
  try {
    const response = await axiosInstance.get<ResponseAllPolicy>(api+`/get-by-campaign-id/${campaignId}`, {
        params:
        {
            page:page,
            size:size,
            sortDirection:SORTDIRECTION_POLICY,
            sortField:SORTFIELD_POLICY
        }
    });
    console.log("policy Response list:" ,response)
    return response;
}
  catch (err) {
   toast.error(err+""); 
  }
}