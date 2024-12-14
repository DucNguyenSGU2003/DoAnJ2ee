import axios from "axios";
import { BASE_URL } from "../Utils/Constant";
import { handleError } from "../Helper/ErrorHandler";
import { AuthenRes } from "../Models/Auth";
import { UserRegisterRequest } from "../Page/Register/Register";
import { RegisterRes } from "../Models/User";

export const loginAPI = async (email: string, password: string) => {
    try {
        const data = await axios.post<AuthenRes>(`${BASE_URL}/auth/login`, {
            email, password
        });
        return data;
    } catch (error) {
        handleError(error);
    }
};

export const registerAPI = async (dataPost: UserRegisterRequest) => {
    try {
        const data = await axios.post<RegisterRes>(`${BASE_URL}/auth/register`, {
            ...dataPost, role: "user"
        });
        return data;
    } catch (error) {
        handleError(error);
    }
};


export interface DonationPost {
    campaign: string
    amount: string
    email: string
    name: string
  }

