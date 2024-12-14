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

const Donation = () => {
  const [donation, setDonation] = useState<DonationGet[] | null>(null);
  const [pageObject, setPageObject] = useState<PageObject>();
  const navigate = useNavigate();

  useEffect(() => {
    getListDonations(PAGE_DONATION, SIZE_DONATION, SORT_DONATION);
  }, []);

  useEffect(() => {
    getListDonations(PAGE_DONATION, SIZE_DONATION, SORT_DONATION);
  }, [status]);

  const handleClickTab = (tab: string) => {};
  const getListDonations = (
    page: number = PAGE_DONATION,
    limit: number = SIZE_DONATION,
    sort: string = SORT_DONATION
  ) => {
    GetListDonationAPI(page, limit, sort)
      .then((res) => {
        if (res?.data) {
          console.log("donation response", res);
          setDonation(res?.data.result.items);
          setPageObject(res?.data?.result.page);
        }
      })
      .catch((error) => {
        toast.warning(error);
        setDonation(null);
      });
  };

  const configs = [
    {
      label: "#",
      render: (DonationGet: DonationGet) => DonationGet.id,
    },
    {
      label: "name",
      render: (DonationGet: DonationGet) => DonationGet.user.name,
    },
    {
      label: "amount",
      render: (DonationGet: DonationGet) => DonationGet.amount + " VNĐ",
    },
    {
      label: "Campaign",
      render: (DonationGet: DonationGet) =>
        DonationGet.campaign.title.slice(0, 20) + "...",
    },
    {
      label: "donationDate",
      render: (DonationGet: DonationGet) => DonationGet.donationDate,
    },
    {
      label: "paymentMethod",
      render: (DonationGet: DonationGet) => DonationGet.paymentMethod,
    },
  ];

  const handlePageChange = (pageNumber: number) => {
    getListDonations(pageNumber, SIZE_DONATION, SORT_DONATION);
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
    if (getValues("filter") != "") {
      const campaignId = Number(getValues("filter"));
      GetListDonationByCampaignIdAPI(campaignId, 0, SIZE_DONATION).then(
        (res) => {
          if (res?.status == 200) {
            setDonation(res.data.result.items);
            setPageObject(res.data.result.page);
          }
        }
      );
    }else
    {
      getListDonations()
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
              <h6 className="mb-4">Donation List</h6>
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
              <div></div>
            )}
            {donation && donation.length > 0 ? (
              <div className="p-lg-1 rounded bg-white">
                {/* content */}
                <Table configs={configs} data={donation} />
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