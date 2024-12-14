import React, { useEffect, useState } from "react";
import FormReport from "./ReportForm";
import { RootUpdateReport } from "../../../Models/Report";
import {
  GetReportByIdAPI,
  UpdateReportAPI,
} from "../../../Service/ReportService";
import { useNavigate, useParams } from "react-router";
import { ResultReportById } from "../../../Models/Donation";
import { toast } from "react-toastify";

const UpdateReport = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [data, setData] = useState<ResultReportById | null>(null);
  const handleUpdate = (data: RootUpdateReport) => {
    UpdateReportAPI(Number(id), data).then((res) => {
      if (res?.status == 200) {
        toast.success("Update successfully!");
        navigate("/admin/reports");
      }
    });
  };

  useEffect(() => {
    if (id) getReportById(id);
  }, []);

  const getReportById = (id: string) => {
    console.log("update");
    GetReportByIdAPI(Number(id)).then((res) => {
      if (res?.status == 200) {
        if (res?.data) {
          setData(res.data.result);
        }
      }
    });
  };
  console.log("update", data);
  return (
    <div>
      {data && (
        <FormReport handleUpdate={handleUpdate} isUpdate={true} data={data} />
      )}
    </div>
  );
};

export default UpdateReport;
