/* eslint-disable @typescript-eslint/no-explicit-any */
/* eslint-disable @typescript-eslint/no-unused-vars */
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router";
import { toast } from "react-toastify";
// import "./CampaignTab.scss";
import { CampaignDto } from "../../Models/Campaign";
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


const Campaign = () => {
  const [campaigns, setCampaigns] = useState<CampaignDto[] | null>(null);
  const [pageObject, setPageObject] = useState<PageObject>();
  const [tabs, setTabs] = useState(CAMPAIGN_STATUS);

  const [status, setStatus] = useState(STATUS_PENDING);

  const navigate = useNavigate();

  useEffect(() => {
    setStatus(STATUS_PENDING);
    getListCampaigns(STATUS_PENDING);
    setTabs(CAMPAIGN_STATUS);
  }, []);

  useEffect(() => {
    getListCampaigns(status);
  }, [status]);

  // render tab
  const  renderLabel = () => {
    const  render = tabs.map((item,index)=> {
      return(<React.Fragment key={item.status}>

                <input 
                      type="radio"
                      name="pcss3t"
                      id={`tab${index+1}`}
                      className={`tab-content-${index+1==1 ?"first":(index+1 == tabs.length ? "last":index+1)}`}
                      checked={item.status === status}
                    />
                    <label
                      htmlFor={`tab${index+1}`}
                      onClick={() => handleClickTab(item.status)}
                    >
                      <i className="icon-picture"></i>
                      <h6>{item.status}</h6>
                    </label>
      </React.Fragment>)
      
    })
    render.join("")
      return render;
  }

  //render content
  const renderContentTabs = () => {
    const render = tabs.map((item:any,_index:number) => {
      return (
        <li
          key={item.id}
          className={`form-create tab-content tab-content-${
            _index+1 == 1 ? "first" :( _index+1 == tabs.length ? "last" : _index + 1)
          } typography`}
        >
          {(campaigns && campaigns.length > 0) ? (
            <div>
              {" "}
              <Paginate
                onPageChange={handlePageChange}
                page= {pageObject!}
                
              />
              <Table data={campaigns.sort((a, b) => a.id - b.id)} configs={configs} />
            </div>
          ):
          <div className="d-flex justify-content-center align-items-center vh-100">
          <h2 className="display-5" >(No record)</h2>
        </div>
          }
        </li>
      );
    });

    // <div className="shadow m-3 my-tab">
    //   <div className="pcss3t pcss3t-effect-scale pcss3t-theme-1">
    //   {renderLabel()}
    //   <ul></ul>
    // </div>
    // </div>
    

    render.join(" ");
    // console.log(render);
    return render;
  };

  const handleClickTab = (tab: string) => {
    // console.log(tab);
    setStatus(tab);
  };
  const getListCampaigns = (
    status: string,
    page: number = PAGE_CAMPAIGN,
    limit: number = LIMIT_CAMPAIGN
  ) => {
    GetListCampaignByStatus(status, page, limit)
      .then((res) => {
        if (res?.data) {
          // console.log("campaigns",res)
          setCampaigns(res?.data?.result.items);
          setPageObject(res?.data?.result.page);
        }
      })
      .catch((error) => {
        toast.warning(error);
        setCampaigns(null);
      });
  };

  const handlePageChange = (pageNumber: number) => {
    GetListCampaignByStatus(status, pageNumber, LIMIT_CAMPAIGN)
      .then((res) => {
        if (res?.data?.result.items) {
          setCampaigns(res?.data?.result.items);
          setPageObject(res?.data.result.page);
        }
      })
      .catch((error) => toast.error(error));
  };
  // console.log("campaign", campaigns);
  const configs = [
    {
      label: "# ",
      render: (campaign: CampaignDto) => campaign.id,
    },
    {
      label: "Title",
      render: (campaign: CampaignDto) => {
        if (campaign.title.length > 20)
          return campaign.title.slice(0, 10) + "...";
        return campaign.title;
      },
    },
    {
      label: "Create By",
      render: (campaign: CampaignDto) => campaign.createdBy.name
    },
    
    {
      label: "Created date",
      render: (campaign: CampaignDto) => campaign.createdAt,
    },

    {
      label: "Target amount",
      render: (campaign: CampaignDto) => campaign.targetAmount,
    },
    {
      label: "Current amount",
      render: (campaign: CampaignDto) => campaign.currentAmount,
    },
    {
      label: "Images",
      render: (campaign: CampaignDto) => <button className="btn-sm btn-info rounded" onClick={() =>
        navigate(`/admin/campaigns/update-images/${campaign.id}`)
      }>Detail</button>,
    },

    {
      label: "Action",
      render: (campaign: CampaignDto) => {
        return (
          <td className="d-flex">
          
         {status == "Pending" &&
          
            <button
              type="button"
              className="btn-sm btn-success d-flex align-items-center me-2"
              onClick={() =>
                navigate(`/admin/campaigns/update/${campaign.id}`)
              }
            >
              Update
            </button>

         
        }
         
            <button
              type="button"
              className="btn-sm btn-warning d-flex align-items-center me-2"
              onClick={() =>
                navigate(`/admin/campaigns/get-by-id/${campaign.id}`)
              }
            >
              Detail
            </button>
          </td>
        );
      },
    },
  ];

  return (
    <div>
      <div className="container-fluid pt-4 px-4">
        <h1 className="py-3">Campaign Management</h1>
        <div className="col-12">
          <div className="shadow rounded bg-light custom-container  h-100 p-4">
            <div className="d-flex py-2">
              <h6 className="mb-4">Campaign List</h6>
              <button
                className="btn btn-primary ms-auto"
                onClick={() => {
                  navigate("/admin/campaigns/create");
                }}
              >
                Create a new campaign
              </button>
            </div>
            <div className="bg-light rounded  table-responsive"></div>
            {campaigns ? (
              <div>
                {" "}
                <div className="shadow my-tab">
                  <div className="pcss3t pcss3t-effect-scale pcss3t-theme-1">
                    {renderLabel()}
                    <ul>{renderContentTabs()}</ul>
                  </div>
                </div>
              </div>
            ) : (
              <h1>Loading...</h1>
            )}
          </div>
        </div>
      </div>
    </div>
  );
};

export default Campaign;
