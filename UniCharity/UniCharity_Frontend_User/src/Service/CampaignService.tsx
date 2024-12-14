import axios from "axios";
import { BASE_URL, CAMPAIGN_API } from "../Utils/Constant";
import { CampaignFormRequest, CampaignGet, CampaignReponse, CampaignRequestRes, CampaignsReponse } from "../Models/Campaign";
import { handleError } from "../Helper/ErrorHandler";

export const CampaignRequest = async (dataPost: CampaignFormRequest) => {
    try {
        const data = await axios.post<CampaignRequestRes>(`${BASE_URL}/campaigns/create-request-campaign`, dataPost);
        return data;
    } catch (error) {
        handleError(error);
    }
};

export const CampaignGetAPI = async (page: number = 1, size: number = 4) => {
    try {
        const data = await axios.get<CampaignsReponse>(CAMPAIGN_API, {
            params: { page, size },
        });
        return data;
    } catch (error) {
        handleError(error);
    }
};

export const CampaignGetByIDAPI = async (idCam: number = 4) => {
    try {
        const data = await axios.get<CampaignReponse>(`${CAMPAIGN_API}/get-by-id/${idCam}`);
        return data;
    } catch (error) {
        handleError(error);
    }
};

export const CampaignGetByStatusAPI = async (status: string, page: number = 1, size: number = 4) => {
    try {
        const data = await axios.get<CampaignsReponse>(`${CAMPAIGN_API}/get-by-status/${status}`, {
            params: { page, size, sortField: "id" }
        });
        return data;
    } catch (error) {
        handleError(error);
    }
};

