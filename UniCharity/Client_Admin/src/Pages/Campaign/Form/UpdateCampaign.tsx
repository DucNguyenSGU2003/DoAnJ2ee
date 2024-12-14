/* eslint-disable @typescript-eslint/no-unused-vars */
import { useNavigate, useParams } from "react-router";
import FormCampaign from "./FormCampaign";
import { CampaignDto, CreateCampaignDto, UpdateCampaignDto } from "../../../Models/Campaign";
import { useEffect, useState } from "react";
import { toast } from "react-toastify";
import { GetCampaignById, UpdateCampaignAPI } from "../../../Service/CampaignService";

const UpdateCampaign = () => {
  const { id } = useParams();
  const [initData, setInitData] = useState<CreateCampaignDto | null>(null);
const navigate = useNavigate();

  useEffect(() => {
    GetCampaignById(id!)
      .then((res) => {
        // console.log("res Update",res)
        if(res)
        {
        setInitData(res?.result);
        }
      })
      .catch(() => {
        toast.error("campaign is not exist!");
      });
  }, []);

  const handleSubmit = (data: UpdateCampaignDto, images: FileList | null) => {
    console.log("id:",id)
    console.log(" data before:",data)
    
    UpdateCampaignAPI(Number(id),data)
    .then(
      (res)=>{
        console.log("res update ", res)
        if(res?.status == 200)
        {
          if(res.data)
          {
            console.log("success",res.data);
            toast.success("Update campaign successfully!");
            navigate("/admin/campaigns")
          }
         
        }
      }
    )
 

  };
  console.log("initData Update",initData)
  return (
    <div>
      {initData && (
        <FormCampaign
          handleCampaign={handleSubmit}
          isUpdate={true}
          initData={initData}
          id={id}
          
        />
      )}
    </div>
  );
};

export default UpdateCampaign;
