import React, { useEffect, useState } from "react";
import { toast } from "react-toastify";
import {  DeleteImageAPI, DowloadListCampaignImagesAPI, UploadListCampaignImagesAPI } from "../../../Service/ImageSevice";
import { ImageGet } from "../../../Models/Image";
import UploadListImages from "../../../Components/UploadImage/UploadListImages";
import ImageItems from "../../../Components/ImageItem/ImageItems";
import { useParams } from "react-router";


const CampaignImagesDetail = () => {

  const {id} = useParams();
  const [selectedImages, setSelectedImages] = useState<FileList | null>(null);
  const [images, setImages] = useState<ImageGet[]>([]);

  useEffect(() => {
    
    const fetchImages = async () => {
      if (id) {
        try {
          const res = await DowloadListCampaignImagesAPI(id);
          if (res?.data) {
            console.log("Images detail: ",res)
            setImages(res.data.result);
          } else {
            toast.error("No images found for this Campaign.");
          }
        } catch (error) {
          toast.error("Error fetching images: " + error);
        }
      }
    };

    fetchImages();
  }, [id]);

  const handleImageChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setSelectedImages(event.target.files);
  };

  const handleUpload = () => {
    if (id) {
      UploadListCampaignImagesAPI(Number(id),selectedImages)
        .then((res) => {
          if (res?.status == 200) {
            if(res?.data)
            {
            setImages(prev => [...prev, ...res.data.result]);
            toast.success("Images uploaded successfully!");
          }
          }
        })
        .catch((error) => toast.error(error));
    }
  };

  const handleDelete = (idImage: number) => {
    if (idImage) {
       DeleteImageAPI(idImage)
        .then((res) => {
          if (res?.status === 200) {
            setImages(res?.data.result)
            toast.success("Delete successfully!");
          }
        })
        .catch((err) => toast.error(err));
    }
  };

  console.log("campaignId:",id)
  return (
    <div className="container-fluid service p-4">
      <div className="container row flex-column" style={{ padding: "0 62px" }}>
        <div className="section-title mb-5 wow fadeInUp" data-wow-delay="0.2s">
          <h4 className="display-3 text-center mb-4">
            Campaign Images Management
          </h4>
        </div>

        <div className="d-flex justify-content-center">
          <UploadListImages
            handleImageChange={handleImageChange}
            handleUpload={handleUpload}
            selectedImages={selectedImages}
          />
        </div>

        <div className="row g-4 justify-content-center">
      {images && <ImageItems images={images} handleDelete={handleDelete} />}  
        </div>
      </div>
    </div>
  );
};

export default CampaignImagesDetail;
