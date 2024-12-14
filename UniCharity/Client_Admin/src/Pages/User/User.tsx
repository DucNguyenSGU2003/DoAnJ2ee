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
import { GetListDonationByCampaignIdAPI } from "../../Service/DonationService";
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
import { ItemGetListUser } from "../../Models/User";
import { GetListUser } from "../../Service/UserService";

const User = () => {
  const [user, setUser] = useState<ItemGetListUser[] | null>(null);
  const [pageObject, setPageObject] = useState<PageObject>();
  const navigate = useNavigate();

  useEffect(() => {
    getListPolicy(PAGE_DONATION, SIZE_DONATION);
  }, []);

  useEffect(() => {
    getListPolicy(PAGE_DONATION, SIZE_DONATION);
  }, [status]);

  const handleClickTab = (tab: string) => {};
  const getListPolicy = async (
    page: number = PAGE_DONATION,
    limit: number = SIZE_DONATION
  ) => {
    await GetListUser(page, limit)
      .then((res) => {
        if (res?.status == 200) {
          if (res?.data) {
            console.log("donation response", res);
            setUser(res.data.result.items);
            setPageObject(res?.data?.result.page);
          }
        }
      })
      .catch((error) => {
        toast.warning(error);
        setUser(null);
      });
  };

  const configs = [
    {
      label: "#",
      render: (dto: ItemGetListUser) => dto.id,
    },
    {
      label: "name",
      render: (dto: ItemGetListUser) => dto.name,
    },
    {
      label: "email",
      render: (dto: ItemGetListUser) => dto.email,
    },
    {
      label: "phone",
      render: (dto: ItemGetListUser) => dto.phone,
    },
    {
      label: "role",
      render: (dto: ItemGetListUser) => dto.role,
    },
    {
      label: "Action",
      render: (dto: ItemGetListUser) => <h1>Action</h1>,
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

  //   const handleClickFilter = () => {
  //     toast.success(getValues("filter"));
  //     if (getValues("filter") != "") {
  //       const campaignId = Number(getValues("filter"));
  //       GetListUser(campaignId).then((res) => {
  //         if (res?.status == 200) {
  //           setUser(res.data.result.items);
  //           setPageObject(res.data.result.page);
  //         }
  //       });
  //     } else {
  //       getListPolicy();
  //     }
  //   };

  return (
    <div>
      <div className="container-fluid pt-4 px-4">
        <h1 className="py-3">User Management</h1>
        <div className="col-12">
          <div className="shadow rounded bg-light custom-container  h-100 p-4">
            <div className="col-sm-12 col-xl-12">
              <div className="bg-light rounded h-100 p-4"></div>
              <div className="col-sm-12 col-xl-12">
                <div className="bg-light rounded h-100 p-4">
                  <h6 className="mb-4">Create Form</h6>
                    <div className="form-floating mb-3">
                      <input
                        type="email"
                        className="form-control"
                        id="floatingInput"
                        placeholder="name@example.com"
                      />
                      <label htmlFor="floatingInput">Name</label>
                    </div>
                    <div className="form-floating mb-3">
                      <input
                        type="email"
                        className="form-control"
                        id="floatingInput"
                        placeholder="name@example.com"
                      />
                      <label htmlFor="floatingInput">Email</label>
                    </div>
                  <div className="form-floating mb-3">
                    <input
                      type="password"
                      className="form-control"
                      id="floatingPassword"
                      placeholder="Password"
                    />
                    <label htmlFor="floatingPassword">Password</label>
                  </div>
                  <div className="form-floating mb-3">
                      <input
                        type="text"
                        className="form-control"
                        id="floatingInput"
                        placeholder="name@example.com"
                      />
                      <label htmlFor="floatingInput">Phone</label>
                    </div>
                    <input className="btn btn-primary" value={"submit"}/>
                
                
                  {/* <div className="form-floating">
                                    <textarea className="form-control" placeholder="Leave a comment here"
                                        id="floatingTextarea" style="height: 150px;"></textarea>
                                    <label htmlFor="floatingTextarea">Comments</label>
                                </div> */}
                </div>
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
              <div>
                <h1>Loading</h1>
              </div>
            )}
            {user && user.length > 0 ? (
              <div className="p-lg-1 rounded bg-white">
                {/* content */}
                <Table configs={configs} data={user} />
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

export default User;
