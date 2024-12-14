import { RootGetListUser } from "../Models/User";
import axiosInstance from "./axios_instance";

const api = "/users";

export const GetListUser = async (page:number=0,size:number=10)=>{
    try {
        const response  = await axiosInstance.get<RootGetListUser>(api,{
            params:{
                page:page,
                size:size
            }
        });
        return response;
    } catch (error) {
        console.log(error)
    }
}