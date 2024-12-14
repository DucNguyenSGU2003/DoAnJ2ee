import { toast } from "react-toastify";
import axiosInstance from "./axios_instance";
import { ResponseDeleteImageAPI, ResponseListImageByCampaignIdAPI } from "../Models/ResponseAPI";

const api = "/images/";

// upload list images
export const UploadListCampaignImagesAPI = async (
  campaignId: number,
  images: FileList | null
) => {
  if (!images) {
    toast.error("No files to upload");
    return;
  }
  const formData = new FormData();
  Array.from(images).forEach((file) => {
    formData.append("files", file);
  });

  try {
    const response = await axiosInstance.post<ResponseListImageByCampaignIdAPI>(
      `${api}upload-images/${campaignId}`,
      formData,
      {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      }
    );
    console.log("service list images:", response);
    return response;
  } catch (error) {
    console.error("Error uploading images:", error);
    throw error;
  }
};

// Dowload list image by campaign's id
export const DowloadListCampaignImagesAPI = ( campaignId:string)=>{
 try {
  const response = axiosInstance.get<ResponseListImageByCampaignIdAPI>(`${api}dowload-by-campaignId/${campaignId}`);
  console.log("list images service: ", response)
  return response;
 } catch (error) {
  console.log(error);
  toast.error("No Images");
  
 }

}

export const DeleteImageAPI = async (imageId: number) => {
  try {
    const response = axiosInstance.delete<ResponseDeleteImageAPI>(`${api}delete/${imageId}`);
    console.log("delete images service: ", response)
    return response;
   } catch (error) {
    console.log(error);
    toast.error("No Images");
   }

}
