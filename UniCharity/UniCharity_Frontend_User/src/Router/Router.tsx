import React from 'react';
import { createBrowserRouter } from 'react-router-dom';
import Layout from '../Layout/Layout';
import Home from '../Page/HomePage/Home';
import DetailCampaignPage from '../Page/DetailCampaignPage/DetailCampaignPage';
import Login from '../Page/Login/Login';
import SuccessPage from '../Page/SuccessPage/SuccessPage';
import RequestCampaign from '../Page/RequestCampaign/RequestCampaign';
import HistoryDonation from '../Page/HistoryDonation/HistoryDonation';
import About from '../Page/About/About';
import Register from '../Page/Register/Register';

const Router = createBrowserRouter([
    {
        path: "",
        element: <Layout />,
        children: [
            {
                path: "",
                element: <Home />,
            },
            {
                path: "/campaignDetails/:id",
                element: <DetailCampaignPage />,
            },
            {
                path: "/login",
                element: <Login />,
            },
            {
                path: "/banking/success/",
                element: <SuccessPage />,
            },
            {
                path: "/request-campaign",
                element: <RequestCampaign />,
            },
            {
                path: "/about",
                element: <About />,
            },
            {
                path: "/history-donation",
                element: <HistoryDonation />,
            },
            {
                path: "/register",
                element: <Register />,
            },
        ]
    }
]);

export default Router;
