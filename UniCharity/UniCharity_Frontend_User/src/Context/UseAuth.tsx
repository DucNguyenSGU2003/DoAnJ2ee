import React, { useContext, useEffect, useState } from "react";
import { UserGet } from "../Models/User"
import { createContext } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { toast } from "react-toastify";
import { loginAPI, registerAPI } from "../Service/AuthService";
import { UserRegisterRequest } from "../Page/Register/Register";
type UserContextType = {
    user: UserGet | null;
    token: string | null;
    registerUser: (data: UserRegisterRequest) => void;
    login: (username: string, password: string) => void;
    logout: () => void;
    isLoggedIn: () => boolean;
};

type Props = { children: React.ReactNode }

const UserContext = createContext<UserContextType>({} as UserContextType);

export const UserProvider = ({ children }: Props) => {
    const navigate = useNavigate();
    const [token, setToken] = useState<string | null>(null);
    const [user, setUser] = useState<UserGet | null>(null);
    const [isReady, setIsReady] = useState<boolean>(false);

    useEffect(() => {
        const user = localStorage.getItem("user");
        const token = localStorage.getItem("token");
        if (user && token) {
            setUser(JSON.parse(user));
            setToken(token);
            // console.log("token", token);

            axios.defaults.headers.common["Authorization"] = "Bearer " + token;
        }
        setIsReady(true);
    }, [token]);

    console.log("token", token);
    

    const registerUser = async (data: UserRegisterRequest) => {
        await registerAPI(data)
            .then(res => {
                if (res?.data.code === 1000) {
                    localStorage.setItem("token", res?.data.result.token);
                    const userRes = res?.data.result.userResponse

                    const UserObject: UserGet = {
                        id: userRes.id,
                        role: userRes.role,
                        name: userRes.name,
                        email: userRes.email,
                        phone: userRes.phone
                    }
                    localStorage.setItem("user", JSON.stringify(UserObject));
                    setUser(UserObject);
                    setToken(res.data.result.token);
                    navigate("/")
                    toast.success("Register success")
                }
            }).catch(ex => {
                toast.warning("Server error occured")
                console.log("ex", ex);
            })
    };

    const login = async (username: string, password: string) => {
        await loginAPI(username, password)
            .then(res => {
                if (res) {
                    localStorage.setItem("token", res?.data.result.token);
                    const userRes = res?.data.result.user

                    const UserObject: UserGet = {
                        id: userRes.id,
                        role: userRes.role,
                        name: userRes.name,
                        email: userRes.email,
                        phone: userRes.phone
                    }
                    localStorage.setItem("user", JSON.stringify(UserObject));
                    setUser(UserObject);
                    setToken(res.data.result.token);
                    navigate("/")
                    toast.success("Login success")
                }
            }).catch(ex => toast.warning("Server error occured"))
    }

    const isLoggedIn = () => {
        return !!user;
    };
    const logout = () => {
        localStorage.removeItem("user")
        localStorage.removeItem("token")
        setUser(null)
        setToken(null)
        navigate("/login")
    }
    return (
        <UserContext.Provider value={{ user, token, registerUser, login, logout, isLoggedIn }} >
            {isReady ? children : null}
        </UserContext.Provider>
    )
}
export const useAuth = () => {
    return useContext(UserContext)
}