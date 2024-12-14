import axios from "axios";
import { BASE_URL } from "../Utils/Constant";
import { HistoryResponse } from "../Models/History";
import { handleError } from "../Helper/ErrorHandler";

export const HistoryDonationGetAPI = async (idUser: number) => {
    try {

        return axios.get<HistoryResponse>(`${BASE_URL}/donation/get-by-user-id/${idUser}`, {
            params: { page: 1, size: 50 }
        });
        // return data;
    } catch (error) {
        handleError(error);
    }
};