import { toast } from "react-toastify";
import axiosInstance from "./axios_instance";

const api = "/policy_violations";

export const GetAllPolicyViolation = async ()=>{

    try {
    const response = await axiosInstance.get(`${api}`);
    return response;
    } catch (err) {
        toast.error(err+"");
    }
}