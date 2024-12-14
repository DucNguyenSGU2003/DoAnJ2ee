import React, { useEffect, useState } from "react";
import BarChartTop from "./BarChartTop";
import PieChartTop from "./PieChartTop";
import * as yup from "yup";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import { toast } from "react-toastify";
import { RangeDateByCampaign } from "../../Models/Campaign";
import {  ItemListDonationByCampaignId, TopDontaionByCampaignId } from "../../Models/Donation";
import { GetListDonationByCampaignIdAPI, GetListDonationByCampaignIdAPIChart, GetTopDonationAPI } from "../../Service/DonationService";
import { PageObject } from "../../Models/Paginate";
import { PAGE_TABLE_CHART_YEAR, SIZE_TABLE_CHART_YEAR } from "../../Utils/ChartConstants";
import Paginate from "../../Components/Paginate/Paginate";
import Table from "../../Components/Table/Table";

// Validation schema
const validateSchema = yup.object({
  campaignId: yup
    .number()
    .min(1, "Campaign ID must be greater than 0")
    .required("Campaign ID is required"),
  top: yup
    .number()
    .min(1, "Top must be greater than 0")
    .required("Top is required"),
});

const ReportChart = () => {
  const [data, setData] = useState<TopDontaionByCampaignId | null>(null);
  const [filter, setFilter] = useState<RangeDateByCampaign | null>(null);

  const [table, setTable] = useState< ItemListDonationByCampaignId[]>();
  const [page, setPage] = useState<PageObject>();
  // Fetch data when filter changes
  useEffect(() => {
    if (filter) {
      GetDataChart(filter.campaignId, filter.top);
      getTable(filter.campaignId)
    }
  }, [filter]);

  // Fetch data initially


  const getTable =async (
    year: number,
    page: number = PAGE_TABLE_CHART_YEAR,
    limit: number = SIZE_TABLE_CHART_YEAR
  ) => {
    await GetListDonationByCampaignIdAPIChart(Number(filter?.campaignId), page, limit)
      .then((res) => {
        if (res?.data) {
          setPage(res.data.result.page);
          setTable(res.data.result.items);
        }
      })
      .catch((err) => {
        console.error(err);
      });
  };
  // Function to fetch data from API
  const GetDataChart = async (campaignId: number, top: number) => {
    try {
      const res = await GetTopDonationAPI(campaignId, top);
      if (res?.data?.result) {
        console.log('Dữ liệu trả về:', res.data.result);
        setData(res.data.result);
      } else {
        toast.error("Không có dữ liệu.");
      }
    } catch (error) {
      console.error("Lỗi khi lấy dữ liệu:", error);
      toast.error("Không thể lấy dữ liệu");
    }
  };

  // React Hook Form setup
  const {
    register,
    formState: { errors },
    handleSubmit,
    getValues,
  } = useForm<RangeDateByCampaign>({
    resolver: yupResolver(validateSchema),
    defaultValues: {
      campaignId: 1,
      top: 5,
    },
  });

  // Function to handle filter change
  const handleChangFilter = (campaignId: number, top: number) => {
    setFilter(new RangeDateByCampaign(campaignId, top));
  };

  // Function to handle form submit
  const clickSubmit = () => {
    const { campaignId, top } = getValues();
    handleChangFilter(campaignId, top);
  };


  const handlePageChange = (pageNumber: number) => {
    if (filter) getTable(filter.campaignId, pageNumber, SIZE_TABLE_CHART_YEAR);
   
  };
  const configs = [
    {
      label: "#",
      render: (dto: ItemListDonationByCampaignId) => dto.id,
    },
    {
      label: "title",
      render: (dto: ItemListDonationByCampaignId) => dto.campaign.title.slice(0,30),
    },
    {
      label: "donationDate",
      render: (dto: ItemListDonationByCampaignId) => dto.donationDate,
    },
    {
      label: "paymentMethod",
      render: (dto: ItemListDonationByCampaignId) => dto.paymentMethod,
    },
    // {
    //   label: "transactions",
    //   render: (dto: ItemListDonationByCampaignId) => dto.transactions.,
    // },
    {
      label: "createdBy",
      render: (dto: ItemListDonationByCampaignId) => dto.user.name,
    },
  ];
  return (
    <div className="container">
      {/* Filter Form */}
      <form onSubmit={handleSubmit(clickSubmit)}>
        <div className="row mt-5 m-5">
          <div className="col-3">
            <h5>Campaign ID:</h5>
            <input
              type="number"
              placeholder="Campaign ID"
              className="form-control"
              {...register("campaignId")}
            />
            {errors.campaignId && <p>{errors?.campaignId.message}</p>}
          </div>
          <div className="col-3">
            <h5>Top:</h5>
            <input
              type="number"
              placeholder="Top"
              className="form-control"
              {...register("top")}
            />
            {errors.top && <p>{errors?.top.message}</p>}
          </div>
          <div className="col-2 d-flex align-items-end">
            <button type="submit" className="btn btn-primary w-100">
              Filter
            </button>
          </div>
        </div>
      </form>

      {/* Display Charts if data exists */}
      {data  ? (
        <div className="m-2 p-2 rounded shadow row bg-light">
          {/* <div className="col-6 shadow bg-light rounded">
            <BarChartTop data={data} />
          </div> */}
          <div className="col-12 shadow bg-light rounded">
            <PieChartTop data={data} />
          </div>
        </div>
      ) : (
        <p>Không có dữ liệu để hiển thị biểu đồ.</p>
      )}
       <div className="bg-light rounded shadow">
        <h1>Danh Sách Donation Của chiến dịch </h1>
        {table && table.length > 0 && <Table configs={configs} data={table} />}
        <div>
          {page ? (
            <Paginate onPageChange={handlePageChange} page={page!} />
          ) : (
            <div>
              <h1>Loading</h1>
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default ReportChart;
