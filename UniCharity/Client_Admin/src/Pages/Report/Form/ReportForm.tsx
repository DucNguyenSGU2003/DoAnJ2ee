import React, { useState } from "react";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import * as Yup from "yup";
import { CreateReportDto, RootUpdateReport } from "../../../Models/Report";
import { ResultReportById } from "../../../Models/Donation";

type Props = {
  handle?: (data: CreateReportDto) => void;
  handleUpdate?: (data: RootUpdateReport) => void;
  isUpdate: boolean;
  data?: ResultReportById;
};

const ReportForm = ({ handle, data, isUpdate, handleUpdate }: Props) => {
  const [isSubmitting, setIsSubmitting] = useState(false);

  // Schema validation với Yup
  const schema = Yup.object().shape({
    campaignId: Yup.string()
      .required("Campaign ID is required")
      .matches(/^\d+$/, "Campaign ID must be a number"),
    total_donations: Yup.number()
      .typeError("Total donations must be a number")
      .required("Total donations are required")
      .min(0, "Total donations must be at least 0"),
    total_recipients: Yup.number()
      .typeError("Total recipients must be a number")
      .required("Total recipients are required")
      .min(0, "Total recipients must be at least 0"),
    results_summary: Yup.string()
      .required("Results summary is required")
      .max(1000, "Results summary must not exceed 1000 characters"),
    lessons_learned: Yup.string()
      .required("Lessons learned is required")
      .max(1000, "Lessons learned must not exceed 1000 characters"),
  });

  // React Hook Form với Yup Resolver
  const {
    register,
    handleSubmit,
    formState: { errors, isValid },
    getValues,
  } = useForm({
    resolver: yupResolver(schema),
    mode: "onChange",
    defaultValues: data
      ? {
          campaignId: data?.campaign.id + "",
          total_donations: Number(data?.totalDonations),
          total_recipients: Number(data?.totalRecipients),
          results_summary: data?.resultsSummary + "",
          lessons_learned: data?.lessonsLearned + "",
        }
      : {
          campaignId: "",
          total_donations: 0,
          total_recipients: 0,
          results_summary: "",
          lessons_learned: "",
        },
  });

  const onSubmit = async () => {
    setIsSubmitting(true);
    const currentDate = new Date().toISOString().split("T")[0];
    if (isUpdate && data) {
      const enrichedData = new RootUpdateReport(
        getValues("total_donations"),
        getValues("total_recipients"),
        getValues("results_summary"),
        getValues("lessons_learned"),
        data.reportDate,
        currentDate
      );
      if (handleUpdate) await handleUpdate(enrichedData);
    } else {
      const enrichedData = new CreateReportDto(
        Number(getValues("campaignId")),
        getValues("total_donations"),
        getValues("total_recipients"),
        getValues("results_summary"),
        getValues("lessons_learned"),
        currentDate,
        currentDate,
        currentDate
      );
      if (handle) await handle(enrichedData);
    }
    setIsSubmitting(false);
  };

  return (
    <div className="container-fluid pt-4 px-4">
      <h1 className="py-3">
        <b>{isUpdate ? "Update Report" : "Create Report"}</b>
      </h1>
      <form onSubmit={handleSubmit(onSubmit)}>
        <div className="shadow rounded bg-light custom-container h-100 p-4">
          <h6 className="mb-4">Report Form</h6>
          {/* Campaign ID */}
          <div className="form-floating m-2">
            <input
              type="text"
              className="form-control"
              placeholder="Campaign Id"
              {...register("campaignId")}
              disabled={isUpdate}
            />
            <label>Campaign Id</label>
            {errors.campaignId && (
              <p className="text-danger">{errors.campaignId.message}</p>
            )}
          </div>
          {/* Total Donations */}
          <div className="form-floating m-2">
            <input
              type="text"
              className="form-control"
              placeholder="Total Donations"
              {...register("total_donations")}
            />
            <label>Total Donations</label>
            {errors.total_donations && (
              <p className="text-danger">{errors.total_donations.message}</p>
            )}
          </div>
          {/* Total Recipients */}
          <div className="form-floating m-2">
            <input
              type="text"
              className="form-control"
              placeholder="Total Recipients"
              {...register("total_recipients")}
            />
            <label>Total Recipients</label>
            {errors.total_recipients && (
              <p className="text-danger">{errors.total_recipients.message}</p>
            )}
          </div>
          {/* Results Summary */}
          <div className="form-floating m-2">
            <textarea
              className="form-control"
              placeholder="Results Summary"
              style={{ height: 150 }}
              {...register("results_summary")}
            ></textarea>
            <label>Results Summary</label>
            {errors.results_summary && (
              <p className="text-danger">{errors.results_summary.message}</p>
            )}
          </div>
          {/* Lessons Learned */}
          <div className="form-floating m-2">
            <textarea
              className="form-control"
              placeholder="Lessons Learned"
              style={{ height: 150 }}
              {...register("lessons_learned")}
            ></textarea>
            <label>Lessons Learned</label>
            {errors.lessons_learned && (
              <p className="text-danger">{errors.lessons_learned.message}</p>
            )}
          </div>
          {/* Submit Button */}
          <div>
            <button
              type="submit"
              className="btn btn-primary"
              disabled={!isValid || isSubmitting}
            >
              {isSubmitting ? "Submitting..." : "Submit"}
            </button>
          </div>
        </div>
      </form>
    </div>
  );
};

export default ReportForm;
