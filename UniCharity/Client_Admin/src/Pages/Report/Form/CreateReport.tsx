import React from "react";
import FormReport from "./ReportForm";
import { CreateReportDto } from "../../../Models/Report";
import { CreateReportAPI } from "../../../Service/ReportService";
import { toast } from "react-toastify";
import { useNavigate } from "react-router";

const CreateReport = () => {
  const navigate = useNavigate();
  const handleCreate = (data: CreateReportDto) => {
    CreateReportAPI(data).then((res) => {
      if (res?.status == 200) {
        if (res.data) {
          toast.success("Create successfully!");
          navigate("/admin/reports");
        }
      } else  {
        toast.error("Campaign has already been reported");
      }
    });
  };

  return (
    <div>
      <FormReport handle={handleCreate} isUpdate={false}/>
    </div>
  );
};

export default CreateReport;
