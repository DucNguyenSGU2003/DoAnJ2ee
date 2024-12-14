import React, { useEffect, useState } from 'react';
import { CampaignGet, Page } from '../../Models/Campaign';
import { CampaignGetByStatusAPI } from '../../Service/CampaignService';
import { CAMPAIGN_PAGE_LIT, STATUS_ACTIVE } from '../../Utils/Constant';
import Paginations from '../Paginations/Paginations';
import Campaign from './Campaign';
import DecoSection from './DecoSection';

const Campaigns: React.FC = () => {
    const [campaigns, setCampaigns] = useState<CampaignGet[]>([]);
    const [page, setPage] = useState<Page | null>(null);

    const fetchCampaigns = (status: string, currentPage: number) => {
        CampaignGetByStatusAPI(status, currentPage, CAMPAIGN_PAGE_LIT)
            .then(res => {
                if (res?.data) {
                    setCampaigns(res.data.result.items);
                    setPage(res.data.result.page);
                }
            })
            .catch(err => {
                console.error("Error fetching campaigns:", err);
            });
    };

    useEffect(() => {
        fetchCampaigns(STATUS_ACTIVE, 0);
    }, []);

    const handlePageChange = (pageNumber: number) => {
        fetchCampaigns(STATUS_ACTIVE, pageNumber);
    };

    return (
        <>
            <section className="section-padding" id="section_3">
                <div className="container">
                    <div className="row">
                        <div className="col-lg-12 col-12 text-center mb-4">
                            <h2>Our Causes</h2>
                        </div>
                        {campaigns.sort((a, b) => a.id - b.id).map(campaign => (
                            <Campaign campaign={campaign} className="col-lg-4 col-md-6 col-12" />
                        ))}
                        {page && (
                            <Paginations
                                totalItem={page.totalItem}
                                currentPage={page.currentPage}
                                totalPages={page.totalPages}
                                pageSize={page.pageSize}
                                onPageChange={handlePageChange}
                            />
                        )}
                    </div>
                </div>
            </section>

            <DecoSection />
        </>
    );
};

export default Campaigns;
