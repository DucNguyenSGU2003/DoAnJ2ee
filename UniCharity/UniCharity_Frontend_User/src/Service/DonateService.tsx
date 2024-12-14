import axios from "axios";
import { handleError } from "../Helper/ErrorHandler";
import { DonationRes } from "../Models/Donation";
import { BASE_URL } from "../Utils/Constant";
import { DonationPost } from "./AuthService";

export const donateAPI = async (dataPost: DonationPost) => {
    try {
        
        return axios.post<DonationRes>(`${BASE_URL}/vnpay/create_payment`, dataPost);
        // return data;
    } catch (error) {
        handleError(error);
    }
};
