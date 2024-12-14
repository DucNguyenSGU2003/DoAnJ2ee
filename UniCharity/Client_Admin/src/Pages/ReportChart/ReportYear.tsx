import React, { useEffect, useState } from "react";
import YearChart from "./YearChart";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import * as yup from "yup";
import { toast } from "react-toastify";
import Table from "../../Components/Table/Table";
import {
  CountByMonthChart,
  GetCampaignTableByYear,
} from "../../Service/CampaignService";
import { ItemTableYear, ResultChartYear } from "../../Models/CharYear";
import {
  PAGE_TABLE_CHART_YEAR,
  SIZE_TABLE_CHART_YEAR,
} from "../../Utils/ChartConstants";
import Paginate from "../../Components/Paginate/Paginate";
import { PageObject } from "../../Models/Paginate";

// Schema dùng Yup để xác thực
const validateSchema = yup.object().shape({
  year: yup
    .number()
    .typeError("Năm phải là số")
    .required("Năm không được để trống")
    .min(2000, "Năm phải từ 2000 trở đi")
    .max(new Date().getFullYear(), "Năm không được lớn hơn năm hiện tại"),
});

type FilterForm = {
  year: number;
};

const ReportYear: React.FC = () => {
  const [data, setData] = useState<ResultChartYear | null>();
  const [table, setTable] = useState<ItemTableYear[]>();
  const [page, setPage] = useState<PageObject>();
  const [year, setYear] = useState<number>();

  const getTable = (
    year: number,
    page: number = PAGE_TABLE_CHART_YEAR,
    limit: number = SIZE_TABLE_CHART_YEAR
  ) => {
    GetCampaignTableByYear(year, page, limit)
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

  const {
    register,
    formState: { errors },
    handleSubmit,
  } = useForm<FilterForm>({
    resolver: yupResolver(validateSchema),
    defaultValues: {
      year: new Date().getFullYear(), // Mặc định là năm hiện tại
    },
  });

  // Hàm xử lý khi lọc dữ liệu
  const handleClickFilter = (formData: FilterForm) => {
    const { year } = formData;
    setYear(year);
    CountByMonthChart(year).then((res) => {
      if (res?.status == 200) {
        if (res?.data) {
          console.log(`Dữ liệu năm ${year}:`, res.data);
          setData(res.data.result);
          getTable(year);
        } else {
          setData(null);
        }
      }
    });
  };

  // Lấy dữ liệu mặc định cho năm hiện tại
  useEffect(() => {
    CountByMonthChart(new Date().getFullYear()).then((res) => {
      if (res?.data) {
        setData(res.data.result);
        getTable(new Date().getFullYear());
      }
    });
  }, []);

  const configs = [
    {
      label: "#",
      render: (dto: ItemTableYear) => dto.id,
    },
    {
      label: "title",
      render: (dto: ItemTableYear) => dto.title.slice(0, 30),
    },
    {
      label: "createdAt",
      render: (dto: ItemTableYear) => dto.createdAt,
    },
    {
      label: "startDate",
      render: (dto: ItemTableYear) => dto.startDate,
    },
    {
      label: "endDate",
      render: (dto: ItemTableYear) => dto.endDate,
    },
    {
      label: "createdBy",
      render: (dto: ItemTableYear) => dto.createdBy.name,
    },
  ];

  const handlePageChange = (pageNumber: number) => {
    if (year) getTable(year, pageNumber, SIZE_TABLE_CHART_YEAR);
    else getTable(new Date().getFullYear(), pageNumber, SIZE_TABLE_CHART_YEAR);
  };

  console.log(0);
  return (
    <div className="container">
      {/* Form lọc */}
      <form onSubmit={handleSubmit(handleClickFilter)}>
        <div className="container bg-light col-12 mt-3">
          <div className="bg-light rounded h-100 p-4">
            <h6 className="mb-4">
              <h5>
                <b>Filter</b>
              </h5>
            </h6>
            <div className="row mb-3">
              <label htmlFor="yearInput" className="col-sm-2 col-form-label">
                Năm:
              </label>
              <div className="col-sm-10">
                <input
                  type="number"
                  className="form-control"
                  id="yearInput"
                  placeholder="Nhập năm"
                  {...register("year")}
                />
                {errors.year && (
                  <p className="text-danger mt-2">{errors.year.message}</p>
                )}
              </div>
            </div>
            <button className="btn btn-primary w-60 m-3" type="submit">
              Filter
            </button>
          </div>
        </div>
      </form>

      {/* Biểu đồ */}
      <div className="m-5 bg-light rounded shadow">
        <h1 className="m-5">Biểu đồ tổng tiền theo từng tháng của năm </h1>
        {data ? (
          <YearChart data={data} />
        ) : (
          <h1 className="text-center">Không có dữ liệu!</h1>
        )}
      </div>
      <div className="bg-light rounded shadow">
        <h1>Danh Sách chiến dịch trong năm </h1>
        {table && table.length > 0 && <Table configs={configs} data={table} />}
        <div>
          {page ? (
            <Paginate onPageChange={handlePageChange} page={page!} />
          ) : (
            <div>
              <h1>Không có chiến dịch trong năm đã lọc</h1>
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default ReportYear;
