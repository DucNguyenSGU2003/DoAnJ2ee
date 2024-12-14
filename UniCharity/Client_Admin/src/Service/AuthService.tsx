import axios from "axios";
import { handleError } from "../Helper/HandleError";
import axiosInstance from "./axios_instance";

export const loginAPI = async (email: string, password: string) => {
    try {
        const data = await axiosInstance.post<AuthenRes>(`/auth/login`, {
            email, password
        });
        return data;
    } catch (error) {
        handleError(error);
    }
};

export interface UserGet {
    id: number;
    name: string;
    email: string;
    phone: string;
    role: string;
  }


export interface AuthenRes {
    code: number
    result: Result
  }
  
export interface Result {
    token: string
    authenticated: boolean
    user: UserGet
}
