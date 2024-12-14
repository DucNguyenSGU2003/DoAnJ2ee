/* eslint-disable @typescript-eslint/no-explicit-any */
/* eslint-disable @typescript-eslint/no-unused-vars */
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router";
import { toast } from "react-toastify";
// import "./CampaignTab.scss";
import { GetListCampaignByStatus } from "../../Service/CampaignService";
// import { ResponseListCampaign } from "../../Models/ResponseAPI";
import Table from "../../Components/Table/Table";
import {
  CAMPAIGN_STATUS,
  LIMIT_CAMPAIGN,
  PAGE_CAMPAIGN,
  STATUS_PENDING,
} from "../../Utils/CampaignConstant";
import { PageObject } from "../../Models/Paginate";
import Paginate from "../../Components/Paginate/Paginate";
import {
  GetListDonationAPI,
  GetListDonationByCampaignIdAPI,
} from "../../Service/DonationService";
import { DonationGet } from "../../Models/Donation";
import {
  PAGE_DONATION,
  SIZE_DONATION,
  SORT_DONATION,
} from "../../Utils/DonationConstant";
import { useForm } from "react-hook-form";
import {
  GetListPolicyAPI,
  GetListPolicyByCampaignIdAPI,
} from "../../Service/PolicyService";
import { ItemPolicy, ResultPolicy } from "../../Models/Policy";

const Donation = () => {
  const [policies, setPolicies] = useState<ItemPolicy[] | null>(null);
  const [pageObject, setPageObject] = useState<PageObject>();
  const navigate = useNavigate();

  useEffect(() => {
    getListPolicy(PAGE_DONATION, SIZE_DONATION);
  }, []);

  useEffect(() => {
    getListPolicy(PAGE_DONATION, SIZE_DONATION);
  }, [status]);

  const handleClickTab = (tab: string) => {};
  const getListPolicy = (
    page: number = PAGE_DONATION,
    limit: number = SIZE_DONATION
  ) => {
    GetListPolicyAPI(page, limit)
      .then((res) => {
        if (res?.status == 200) {
          if (res?.data) {
            console.log("donation response", res);
            setPolicies(res.data.result.items);
            setPageObject(res?.data?.result.page);
          }
        }
      })
      .catch((error) => {
        toast.warning(error);
        setPolicies(null);
      });
  };

  const configs = [
    {
      label: "#",
      render: (ItemPpolicy: ItemPolicy) => ItemPpolicy.id,
    },
    {
      label: "Campaign",
      render: (ItemPpolicy: ItemPolicy) =>
        ItemPpolicy.campaign.title.slice(0, 20),
    },
    {
      label: "Created",
      render: (ItemPpolicy: ItemPolicy) => ItemPpolicy.createdAt,
    },
    {
      label: "eligibilityCriteria",
      render: (ItemPpolicy: ItemPolicy) => ItemPpolicy.eligibilityCriteria,
    },
    {
      label: "policyDescription",
      render: (ItemPpolicy: ItemPolicy) => ItemPpolicy.policyDescription,
    },
    {
      label: "Updated",
      render: (ItemPpolicy: ItemPolicy) => ItemPpolicy.updatedAt,
    },
  ];

  const handlePageChange = (pageNumber: number) => {
    getListPolicy(pageNumber, SIZE_DONATION);
  };

  const {
    register,
    handleSubmit,
    formState: { errors },
    setValue,
    getValues,
  } = useForm({
    // resolver: yupResolver(schema), // Sử dụng schema đơn giản
    defaultValues: {
      filter: "",
    },
  });

  const handleClickFilter = () => {
    toast.success(getValues("filter"));
    if (getValues("filter") != "") {
      const campaignId = Number(getValues("filter"));
      GetListPolicyByCampaignIdAPI(campaignId).then((res) => {
        if (res?.status == 200) {
          setPolicies(res.data.result.items);
          setPageObject(res.data.result.page);
        }
      });
    } else {
      getListPolicy();
    }
  };

  return (
    <div>
      <div className="container-fluid pt-4 px-4">
        <h1 className="py-3">Donation Management</h1>
        <div className="col-12">
          <div className="shadow rounded bg-light custom-container  h-100 p-4">
            <div className="col-sm-12 col-xl-6">
              <div className="bg-light rounded h-100 p-4">
                <h1 className="mb-4">Filter</h1>
                <form onSubmit={handleSubmit(handleClickFilter)}>
                  <div className="mb-3 d-">
                    <label htmlFor="exampleInputEmail1" className="form-label">
                      Campaign Code
                    </label>
                    <input
                      type="text"
                      className="form-control"
                      id="exampleInputEmail1"
                      aria-describedby="emailHelp"
                      {...register("filter")}
                    />
                    <div id="emailHelp" className="form-text"></div>
                  </div>

                  <button type="submit" className="btn btn-primary">
                    Filter
                  </button>
                </form>
              </div>
            </div>
            <div className="d-flex py-2">
              <h6 className="mb-4">Policies List</h6>
              {/* <button
                className="btn btn-primary ms-auto"
                onClick={() => {
                  navigate("/admin/campaigns/create");
                }}
              >
                Create a new campaign
              </button> */}
            </div>
            <div className="bg-light rounded  table-responsive"></div>

            {pageObject ? (
              <Paginate onPageChange={handlePageChange} page={pageObject!} />
            ) : (
              <div><h1>Loading</h1></div>
            )}
            {policies && policies.length > 0 ? (
              <div className="p-lg-1 rounded bg-white">
                {/* content */}
                <Table configs={configs} data={policies} />
              </div>
            ) : (
              <h1>(No Record)</h1>
            )}
          </div>
        </div>
      </div>
    </div>
  );
};

export default Donation;