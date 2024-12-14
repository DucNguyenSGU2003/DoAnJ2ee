/* eslint-disable @typescript-eslint/no-explicit-any */
/* eslint-disable @typescript-eslint/no-unused-vars */
import { Editor } from "@tinymce/tinymce-react";
import React, { useState } from "react";
import * as yup from "yup";
import {
  CampaignDto,
  CampaignPolicyDto,
  CampaignFormFiles,
  UploadListImageDto,
  CreateCampaignDto,
  UpdateCampaignDto,
  UpdateCampaignPolicyDto,
} from "../../../Models/Campaign";
import "../Campaign.scss";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import {
  TAB_CREATE_CAMPAIGN,
  TAB_CREATE_POLICIES,
  TABS_CREATE_CAMPAIGN,
} from "../../../Utils/CampaignConstant";
import CreateCampaign from "./CreateCampaign";
import InputCampaign from "./InputCampaign";
import InputPolicy from "./InputPolicy";
import { useNavigate } from "react-router";
import { toast } from "react-toastify";
// import UploadListImage from "../../../Components/UploadImage/UploadListImage";
// Props
type Props = {
  handleCampaign: (data: any, images: FileList | null) => void;
  initData?: CreateCampaignDto | null;
  isUpdate: boolean;
  id?: string | null;
};
const FormCampaign = ({ handleCampaign, initData, isUpdate, id }: Props) => {
  const [selectedImages, setSelectedImages] = useState<FileList | null>(null);
  const [policies, setPolicies] = useState<any[]>(() => {
    if (initData) return initData.policies;
    else return [];
  });
  const [tab, setTab] = useState(TAB_CREATE_CAMPAIGN);
  const [tabs, setTabs] = useState(TABS_CREATE_CAMPAIGN);

  const navigate = useNavigate();
  const {
    register,
    handleSubmit,
    formState: { errors },
    setValue,
    getValues,
  } = useForm<CreateCampaignDto>({
    // resolver: yupResolver(schema), // Sử dụng schema đơn giản
    defaultValues: initData || {
      title: "",
      description: "",
      targetAmount: 0,
      currentAmount: 0,
      startDate: new Date(),
      endDate: new Date(),
      status: "Pending",
      createdBy: 0,
    },
  });

  // hàm tạo CampaignPosst
  const resquestCreateCampaignPost = (
    data: any,
    images: FileList | null,
    policies: CampaignPolicyDto[]
  ) => {
    const createdAt = new Date();
    return new CreateCampaignDto(
      data.title,
      getValues("description"),
      data.targetAmount,
      0,
      createdAt,
      data.startDate,
      data.endDate,
      data.status,
      data.createdBy,
      policies
    );
  };

  const getCurrentDate = () => {
    const today = new Date();
    const year = today.getFullYear();
    const month = String(today.getMonth() + 1).padStart(2, "0"); // Tháng bắt đầu từ 0 nên cần +1
    const day = String(today.getDate()).padStart(2, "0");
    return `${year}-${month}-${day}`;
  };
  const resquestUpdateCampaignPost = (
    data: any,
    images: FileList | null,
    policies: UpdateCampaignPolicyDto[]
  ) => {
    console.log("list policy:",policies)

    const listPolicyUpdate = policies.map(item => {
      const date = getCurrentDate();
      return new UpdateCampaignPolicyDto(item.policyDescription,item.eligibilityCriteria,item.approvalRequired,date,date);
    })

        return new UpdateCampaignDto(
      data.title,
      getValues("description"),
      data.targetAmount,
      0,
      data.createdAt,
      data.startDate,
      data.endDate,
      data.status,
      data.createdBy.id,
      listPolicyUpdate
    );
  };

  // Hàm submit
  const onSubmit = (data: any) => {
    console.log("sent", data);
    console.log("submit");

    if (isUpdate) {

      const result = resquestUpdateCampaignPost(data, null, policies);

      console.log("result", result)

      handleCampaign(result, null);
    } else {
      const result = resquestCreateCampaignPost(data, selectedImages, policies);
      handleCampaign(result, selectedImages);
    }
  };

  // Hàm thay đổi hình ảnh
  const handleImageChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    // console.log("change");
    setSelectedImages(event.target.files);
  };

  // Hàm thay đổi Editor
  const handleEditorChange = (content: string) => {
    setValue("description", content);
  };

  const handleClickTab = (tab: string) => {
    setTab(tab);
  };

  // handle create policy
  const handleCreatePolicy = (newPolicy: any) => {
    setPolicies((prev) => [...prev, newPolicy]);
  };

  const handleDeletePolicy = (i: number) => {
    toast.success(i);
    // console.log("index: ",i)
    const result = policies;
    result.splice(i, 1);
    setPolicies(result);
  };
  
  const renderLabel = () => {
    const render = tabs.map((item: any, index: number) => {
      return (
        <React.Fragment key={item.title}>
          <input
            type="radio"
            name="pcss3t"
            id={`tab${index + 1}`}
            className={`tab-content-${
              index + 1 === 1
                ? "first"
                : index + 1 == tabs.length
                ? "last"
                : index + 1
            }`}
            checked={item.title === tab}
          />
          <label
            htmlFor={`tab${index + 1}`}
            onClick={() => handleClickTab(item.title)}
          >
            <i className="icon-picture"></i>
            <h6>{item.title}</h6>
          </label>
        </React.Fragment>
      );
    });
    render.join("");
    return render;
  };

  // console.log("policies Form", policies)
  const renderContentTabs = () => {
    const render = tabs.map((item: any, _index: number) => {
      return (
        <li
          key={item.id}
          className={`shadow rounded form-create tab-content tab-content-${
            _index == 0
              ? "first"
              : _index == tabs.length - 1
              ? "last"
              : _index + 1
          } typography`}
        >
          {item.title == TAB_CREATE_CAMPAIGN && (
            // <div className="">campaign</div>
            <InputCampaign
              handleEditorChange={handleEditorChange}
              errors={errors}
              register={register}
              initData={initData!}
              isUpdate={isUpdate}
              getValues={getValues}
              setValue={setValue}
              handleChangImage={handleImageChange}
            />
          )}

          {item.title == TAB_CREATE_POLICIES && (
            // <div>policies</div>
            <InputPolicy
              errors={errors}
              register={register}
              isUpdate={isUpdate}
              getValues={getValues}
              setValue={setValue}
              initData={policies}
              handleCreateNewPolicy={handleCreatePolicy}
              handleDeletePolicy={handleDeletePolicy}
            />
          )}
        </li>
      );
    });

    render.join(" ");
    // console.log(render);
    return render;
  };

  const handleRouteCampaign = () => {
    navigate("/admin/campaigns");
  };

  // console.dir("register:",{...register("title")})

  // console.log("selectedImages:", selectedImages);
  // console.log("policies",policies)

  // console.log("init Form: ", initData);
  return (
    <div className="bg-light" style={{ marginTop: 12 }}>
      <form onSubmit={handleSubmit(onSubmit)}>
        {/* <div className="">
          <h1>Form Create Campaign (Policies,Images)</h1>
        </div> */}
        <div className="">
          <button
            style={{
              marginTop: "1.25rem",
              marginRight: "12px !important",
              marginLeft: 12,
              fontSize: 20,
            }}
            type="submit"
            className="btn btn-danger"
            onClick={handleRouteCampaign}
          >
            Back
          </button>
          <button
            style={{
              marginTop: "1.25rem",
              marginRight: "12px !important",
              fontSize: 20,
              marginLeft: 12,
            }}
            type="submit"
            className="btn btn-success"
          >
            Submit
          </button>
          .
        </div>
        <div className="p-2 my-tab">
          <div className=" shadow bg-secondary pcss3t pcss3t-effect-scale pcss3t-theme-">
            {renderLabel()}
            <div></div>
            <ul>{renderContentTabs()}</ul>
          </div>
        </div>
      </form>
    </div>
  );
};

export default FormCampaign;
