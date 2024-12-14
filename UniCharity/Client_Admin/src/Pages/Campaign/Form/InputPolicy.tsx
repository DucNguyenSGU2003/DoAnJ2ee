/* eslint-disable @typescript-eslint/no-unused-vars */
/* eslint-disable @typescript-eslint/no-explicit-any */
import React, { useEffect, useRef, useState } from "react";
import Table from "../../../Components/Table/Table";
import { RiDeleteBin6Fill } from "react-icons/ri";
import {
  CampaignPolicyDto,
  CreateCampaignDto,
  UpdateCampaignPolicyDto,
  
} from "../../../Models/Campaign";
import { UseFormGetValues, UseFormSetValue } from "react-hook-form";
import { toast } from "react-toastify";

type Props = {
  errors: any;
  register: any;
  initData?: CampaignPolicyDto[];
  isUpdate: boolean;
  getValues: UseFormGetValues<CreateCampaignDto>;
  setValue: UseFormSetValue<CreateCampaignDto>;
  handleCreateNewPolicy: (newPolicy: any) => void;
  handleDeletePolicy: (i: number) => void
};
const InputPolicy = ({
  register,
  errors,
  initData,
  handleCreateNewPolicy,
  handleDeletePolicy,
  isUpdate
}: Props) => {
  // console.log("init policy", initData)
//   const [policies, setPolicies] = useState<CampaignPolicyDto[] >();
// // 
//   useEffect(()=>{
//     setPolicies(initData)
//   }
//     ,[])

  // ref
  const descriptionRef = useRef<HTMLTextAreaElement | null>(null);
  const eligibilityCriteriaRef = useRef<HTMLTextAreaElement | null>(null);

  const handleClearForm = () => {
    if (descriptionRef.current) {
      descriptionRef.current.value = "";
    }
    if (eligibilityCriteriaRef.current) {
      eligibilityCriteriaRef.current.value = "";
    }
  };

  const isNullOrEmpty = (value: string | null | undefined): boolean => {
    return !value || value.trim().length === 0;
  };
  
  const responsePolicy = () => {
    const description = descriptionRef.current?.value;
    const eligibilityCriteria = eligibilityCriteriaRef.current?.value;

    if (!isNullOrEmpty(description) && !isNullOrEmpty(eligibilityCriteria)) {
      return new CampaignPolicyDto(description!, eligibilityCriteria!, "pending");
    } else {
      toast.error("description and eligibility Criteria is require");
    }
    return null;

  };


    
  const responseUpdatePolicy = () => {
    const description = descriptionRef.current?.value;
    const eligibilityCriteria = eligibilityCriteriaRef.current?.value;

    if (!isNullOrEmpty(description) && !isNullOrEmpty(eligibilityCriteria)) {
      const date = new Date().toUTCString;
      return new UpdateCampaignPolicyDto(description!, eligibilityCriteria!, "approved",date,date);
    } else {
      toast.error("description and eligibility Criteria is require");
    }
    return null;

  };

  const handleCreatePolicy = () => {
    if(!isUpdate)
    {
      const result  = responsePolicy();
      if(result)
      {
        // console.log("result:",result)
        handleCreateNewPolicy(result);
        // handleClearForm();
      }
    } 
    else
    {
      const result  = responseUpdatePolicy();
      if(result)
      {
        handleCreateNewPolicy(result);
      }
    }
  };


  const handleDelete= (i:number) => {
   handleDeletePolicy(i)
  }
  const configs = [
    {
      label: "#",
      render: (policy: any, index: number) => index + 1,
    },
    {
      label: "Description",
      render: (policy: any) => policy.policyDescription,
    },
    {
      label: "eligibility Criteria",
      render: (policy: any) => policy.eligibilityCriteria,
    },
    {
      label: "Action",
      render: (policy: any,index:number) => (<div>
        <button className="btn btn-danger"
        onClick={()=>handleDelete(index)}
        >  <RiDeleteBin6Fill /></button>
      </div>),
    },
  ];

  return (
    <div>
      <div className=" rounded h-100 p-4">
        <div className="form-floating mb-3">
          <textarea
            className="form-control"
            //   placeholder="Leave a comment here"
            id="floatingDescription"
            style={{ height: `100px` }}
            ref={descriptionRef}
            // {...register()}
          ></textarea>
          <label htmlFor="floatingDescription">Description</label>
        </div>

        <div className="form-floating mb-3">
          <textarea
            ref={eligibilityCriteriaRef}
            className="form-control"
            //   placeholder="Leave a comment here"
            id="floatingEligibilityCriteria"
            style={{ height: `100px` }}
            // {...register("")}
          ></textarea>
          <label htmlFor="floatingEligibilityCriteria">
            Eligibility Criteria
          </label>
        </div>
        <div className="d-flex">
          <button
            type="button"
            className="btn btn-primary rounded-pill m-2"
            onClick={handleCreatePolicy}
          >
            Insert policy
          </button>
          <button
            type="button"
            onClick={handleClearForm}
            className="btn btn-danger  rounded-pill m-2"
          >
            Clear form
          </button>
        </div>

        <div className="d-flex justify-content-center align-items-center">
          <h1> List Policies</h1>
        </div>
        {initData && (
          <Table data={initData || []} configs={configs} />
        )}
      </div>
    </div>
  );
};

export default InputPolicy;
