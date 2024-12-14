/* eslint-disable @typescript-eslint/no-unused-vars */
import React from "react";
import FormCampaign from "./FormCampaign";
import { toast, ToastContainer } from "react-toastify";
import {
  CampaignDto,
  CampaignPolicyDto,
  CreateCampaignDto,
} from "../../../Models/Campaign";
import { useNavigate } from "react-router";
import { CreateCampaignAPI } from "../../../Service/CampaignService";
import { UploadListCampaignImagesAPI } from "../../../Service/ImageSevice";

const CreateCampaign = () => {
  const navigate = useNavigate();

  const handleSubmit = async (
    _formInput: CreateCampaignDto,
    images: FileList | null
  ) => {
    try {
      console.log("input", _formInput);
      console.log("images", images);

      CreateCampaignAPI(_formInput).then((res) => {
        console.log("success create campaign: ", res);
        if (res?.status == 200 && res?.data) {
          UploadListCampaignImagesAPI(res.data.result.id, images)
          navigate("/admin/campaigns");
          toast.success("Create a Campaign Successfully!");
        }
      });
    } catch (error) {
      toast.error("Create Fail!");
    }
  };

  return (
    <div className=" bg-white custom-container m-4 rounded h-100 p-4">
      <h6 className="mb-4">
        <h1>Create new campaign and policy</h1>
      </h6>
      <div className="rounded shadow ">
        <FormCampaign handleCampaign={handleSubmit} isUpdate={false} />
      </div>
    </div>
  );
};

export default CreateCampaign;
