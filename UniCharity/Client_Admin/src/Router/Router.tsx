import { createBrowserRouter } from "react-router-dom";
import AdminLayout from "../Layout/AdminLayout";
import Campaign from "../Pages/Campaign/Campaign";
import CreateCampaign from "../Pages/Campaign/Form/CreateCampaign";
import UpdateCampaign from "../Pages/Campaign/Form/UpdateCampaign";
import CampaignImagesDetail from "../Pages/Campaign/Form/CampaignImagesDetail";
import Policy from "../Pages/Policy/Policy";
import Donation from "../Pages/Donation/Donation";
import Report from "../Pages/Report/Report";
import ReportYear from "../Pages/ReportChart/ReportYear";
import ReportChart from "../Pages/ReportChart/ReportChart";
import UpdateReport from "../Pages/Report/Form/UpdateReport";
import User from "../Pages/User/User";
import CreateReport from "../Pages/Report/Form/CreateReport";
import LoginPage from "../Pages/LoginPage/LoginPage";
import InputFund from "../Pages/Fund/Fund";

const Router = createBrowserRouter([
  {
    path: "",
    element: <LoginPage />,
  },
  {
    path: "Admin",
    element: <AdminLayout />,
    children: [
      {
        path: "campaigns",
        element: <Campaign />,
      },
      {
        path: "campaigns/create",
        element: <CreateCampaign />,
      },
      {
        path: "campaigns/update/:id",
        element: <UpdateCampaign />,
      },
      {
        path: "campaigns/update-images/:id",
        element: <CampaignImagesDetail />,
      },
      // policy
      {
        path: "policies",
        element: <Policy />,
      },
      {
        path: "donations",
        element: <Donation />,
      },
      {
        path: "reports",
        element: <Report />,
      },
      {
        path: "reports/create",
        element: <CreateReport />,
      }, {
        path: "reports/update/:id",
        element: <UpdateReport />,
      }, {
        path: "reports/fund-alocation/:id",
        element: <InputFund />,
      },
     
      {
        path: "chart/top",
        element: <ReportChart />,
      },
      {
        path: "chart/year",
        element: <ReportYear />,
      },
     

    ],
  },
]);

export default Router;
