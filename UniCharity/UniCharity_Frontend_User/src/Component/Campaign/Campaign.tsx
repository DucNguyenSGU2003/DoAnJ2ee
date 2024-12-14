import React from 'react'
import { CampaignGet } from '../../Models/Campaign'
import DefaultImage from '/images/Defaults.jpg'
import { useNavigate } from 'react-router-dom';



export type Props = {
    campaign: CampaignGet;
    className?: string;
    handleDonateNowClick: () => void
}

const Campaign = ({ campaign, className, handleDonateNowClick }: Props) => {
    const navigate = useNavigate()
    return (
        <div key={campaign.id}
            style={{ cursor: "pointer" }}
            onClick={() => navigate(`/campaignDetails/${campaign.id}`)}
            className={`mt-4 mb-4 mb-lg-0 ${className}`} >
            <div className="custom-block-wrap">
                <img src={campaign.images[0]?.imagePath || DefaultImage} className="custom-block-image img-fluid" alt={campaign.title} />

                <div className="custom-block">
                    <div className="custom-block-body">
                        <h5 className="mb-3 one-line">{campaign.title}</h5>
                        {/* <p className='one-line' >{campaign.description}</p> */}

                        <div className="progress mt-4">
                            <div
                                className="progress-bar"
                                style={{ width: `${(campaign.currentAmount / campaign.targetAmount) * 100}%` }}
                                role="progressbar"
                                aria-valuenow={campaign.currentAmount}
                                aria-valuemin={0}
                                aria-valuemax={campaign.targetAmount}
                            ></div>
                        </div>

                        <div className="d-flex align-items-center my-2">
                            <p className="mb-0">
                                <strong>Raised:</strong> {campaign.currentAmount.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' })}
                            </p>
                            <p className="ms-auto mb-0">
                                <strong>Goal:</strong> {campaign.targetAmount.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' })}
                            </p>
                        </div>
                    </div>
                    <a
                        onClick={handleDonateNowClick}
                        className="custom-btn btn">Donate now
                    </a>
                </div>
            </div>
        </div>
    )
}

export default Campaign
