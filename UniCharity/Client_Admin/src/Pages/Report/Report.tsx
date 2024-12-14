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
import { getAllReportAPI } from "../../Service/ReportService";
import { PAGE_REPORT, SIZE_REPORT } from "../../Utils/ReportConstant";
import { ReportItem } from "../../Models/Report";

const Report = () => {
  const [report, setReport] = useState<ReportItem[] | null>(null);
  const [pageObject, setPageObject] = useState<PageObject>();
  const navigate = useNavigate();

  useEffect(() => {
    GetListReport();
  }, []);

  useEffect(() => {
    GetListReport();
  }, [status]);

  const handleClickTab = (tab: string) => {};
  const GetListReport = (
    page: number = PAGE_REPORT,
    limit: number = SIZE_REPORT
  ) => {
    getAllReportAPI(page, limit)
      .then((res) => {
        if (res?.status == 200) {
          if (res?.data) {
            console.log("donation response", res);
            setReport(res.data.result.items);
            setPageObject(res?.data?.result.page);
          }
        }
      })
      .catch((error) => {
        toast.warning(error);
        setReport(null);
      });
  };

  const configs = [
    {
      label: "#",
      render: (item: ReportItem) => item.id,
    },
    {
        label: "Campaign",
        render: (item: ReportItem) =>
          item.campaign.title.slice(0, 20),
      },
    {
      label: "Results Summary",
      render: (item: ReportItem) =>
        item.resultsSummary,
    },
    {
      label: "Lessons Learned",
      render: (item: ReportItem) => item.lessonsLearned,
    },
    {
      label: "Created At",
      render: (item: ReportItem) => item.createdAt,
    },

    {
      label: "Action",
      render: (item: ReportItem) => 
         <div className="d-flex">
           <button
        type="button"
        className="btn-sm btn-success d-flex align-items-center me-2"
        onClick={() =>
          navigate(`/admin/reports/update/${item.id}`)
        }
      >
        Update
      </button>
      <button
        type="button"
        className="btn-sm btn-warning d-flex align-items-center me-2"
        onClick={() =>
          navigate(`/admin/reports/fund-alocation/${item.id}`)
        }
      >
        Create Fund alocation
      </button>
         </div>
    }

  
  ];

  const handlePageChange = (pageNumber: number) => {
    GetListReport(pageNumber, SIZE_REPORT);
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

 

  return (
    <div>
      <div className="container-fluid pt-4 px-4">
        <h1 className="py-3">Report Management</h1>
        <div className="col-12">
          <div className="shadow rounded bg-light custom-container  h-100 p-4">
            {/* <div className="col-sm-12 col-xl-6">
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
            </div> */}
            <div className="d-flex py-2">
              <h6 className="mb-4">Report List</h6>
              <button
                className="btn btn-primary ms-auto"
                onClick={() => {
                  navigate("/admin/reports/create");
                }}
              >
                Create a new Report
              </button>
            </div>
            <div className="bg-light rounded  table-responsive"></div>

            {pageObject ? (
              <Paginate onPageChange={handlePageChange} page={pageObject!} />
            ) : (
              <div><h1>Loading</h1></div>
            )}
            {report && report.length > 0 ? (
              <div className="p-lg-1 rounded bg-white">
                {/* content */}
                <Table configs={configs} data={report} />
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

export default Report;
