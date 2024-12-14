/* eslint-disable @typescript-eslint/no-unused-vars */
/* eslint-disable @typescript-eslint/no-explicit-any */
import { Editor } from "@tinymce/tinymce-react";
import React from "react";
import { UseFormGetValues, UseFormSetValue } from "react-hook-form";
import { CreateCampaignDto } from "../../../Models/Campaign";

type Props = {
  register: any;
  errors: any;
  isUpdate: boolean;
  initData?: CreateCampaignDto;
  getValues: UseFormGetValues<CreateCampaignDto>;
  setValue: UseFormSetValue<CreateCampaignDto>;
  handleChangImage: (event: React.ChangeEvent<HTMLInputElement>) => void;
  handleEditorChange: (content: string) => void;
};
const InputCampaign = ({
  initData,
  isUpdate,
  register,
  errors,
  getValues,
  setValue,
  handleEditorChange,
  handleChangImage,
}: Props) => {
  // console.dir("register:",{...register("title")})
  // console.log("getVallues:",getValues())

  return (
    <div className=" rounded h-100 ">
      {/* Title */}
      <div className="form-floating mb-3">
        <input
          type="text"
          className={`form-control ${errors.title ? "is-invalid" : ""}`}
          id="title"
          {...register("title")}
        />
        <label htmlFor="floatingInput">Title</label>
        {errors.title && (
          <div className="invalid-feedback">{errors.title.message}</div>
        )}
      </div>

      {/* Target Amount */}
      <div className="form-floating mb-3">
        <input
          type="number"
          className={`form-control ${errors.targetAmount ? "is-invalid" : ""}`}
          id="targetAmount"
          placeholder="Target amount"
          {...register("targetAmount")}
          disabled={isUpdate}
        />
        <label htmlFor="targetAmount">Target amount</label>
        {errors.targetAmount && (
          <div className="invalid-feedback">{errors.targetAmount.message}</div>
        )}
      </div>

      {/* Created By */}
      {isUpdate || (<div className="form-floating mb-3">
        <input
          type="number"
          className={`form-control ${errors.createdBy ? "is-invalid" : ""}`}
          id="createBy"
          placeholder="Created by"
          disabled={isUpdate}
          {...register("createdBy")}
        />
        <label htmlFor="createBy">Created by</label>
        {errors.createdBy && (
          <div className="invalid-feedback">{errors.createdBy.message}</div>
        )}
      </div>)}

      {/* Start Date */}
      {/* create */}
      {!isUpdate && (
        <div className="form-floating mb-3">
          <input
            type="date"
            className={`form-control ${errors.startDate ? "is-invalid" : ""}`}
            // className="form-control"
            id="startDate"
            placeholder="Start date"
            {...register("startDate")}
          />
          <label htmlFor="startDate">Start date</label>
          {errors.startDate && (
            <div className="invalid-feedback">{errors.startDate.message}</div>
          )}
        </div>
      )}

      {/* End Date */}
      {!isUpdate && (
        <div className="form-floating mb-3">
          <input
            type="date"
            // className={`form-control ${errors.endDate ? "is-invalid" : ""}`}
            className="form-control"
            id="endDate"
            placeholder="End date"
            {...register("endDate")}
          />
          <label htmlFor="endDate">End date</label>
          {/* {errors.endDate && (
          <div className="invalid-feedback">{errors.endDate.message}</div>
        )} */}
        </div>
      )}

      {/* Input File for Multiple Images */}
      <div className={`mb-3 ${isUpdate && "d-none"}`}>
        <div className="mb-3">
          <label htmlFor="formFileMultiple" className="form-label">
            Multiple files input
          </label>
          <input
            className="form-control"
            type="file"
            id="formFileMultiple"
            multiple
            onChange={handleChangImage}
          />
        </div>
      </div>

      {/* Word Component for Description */}
      <div className=" form-floating mb-3">
        <label className="form-label">Description</label>
        <Editor
          apiKey="bjvro15xzp578awed76jjd439yuuldwig8ojluroj18stkik"
          init={{
            height: 1000,
            menubar: true,
            toolbar_mode: "floating",
            plugins: "image link",
            toolbar: "undo redo | bold italic | image | link",

            // Cho phép chọn file từ máy tính
            file_picker_types: "image",

            // Cấu hình file picker để chọn ảnh từ máy tính
            file_picker_callback: (callback) => {
              const input = document.createElement("input");
              input.setAttribute("type", "file");
              input.setAttribute("accept", "image/*"); // Chỉ cho phép chọn file ảnh

              // Khi người dùng chọn file
              input.onchange = function () {
                const file = input.files?.[0]; // Kiểm tra xem có file không
                if (file) {
                  const reader = new FileReader();
                  reader.onload = function (e) {
                    if (e.target) {
                      // Chèn ảnh vào vị trí con trỏ
                      callback(e.target.result as string, {
                        alt: file.name,
                      });
                    }
                  };
                  reader.readAsDataURL(file); // Đọc file dưới dạng Data URL
                }
              };

              input.click(); // Mở hộp thoại chọn file
            },
          }}
          initialValue={initData?.description || ""}
          onEditorChange={handleEditorChange}
        />
        {/* Status */}
        <div
          className="form-floating mb-3 mb-4 m"
        >
          <label htmlFor="floatingSelect">
            <p>Status</p>
          </label>
          <select
            className="form-select"
            id="floatingSelect"
            aria-label="Floating label select example"
            {...register("status")}
          >
            <option value="Cancelled" style={{ color: "red" }}>
              Cancelled
            </option>
            <option value="Pending">Pending</option>
            <option value="Active">Active</option>
            <option value="Completed">Completed</option>
          </select>
        </div>
      </div>
    </div>
  );
};

export default InputCampaign;
