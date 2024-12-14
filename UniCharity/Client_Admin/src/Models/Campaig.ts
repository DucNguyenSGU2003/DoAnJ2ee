


export interface CampaignDto {
    id: number;
    title: string;
    description: string;
    targetAmount: number;
    currentAmount: number;
    createdAt: string;
    startDate: string;
    endDate: string;
    status: string;
    createdBy: number;
    imageDto: CampaignImageDto[];
    policyDtos: CampaignPolicyDto[];
}

export interface CampaignImageDto {
    imageId: number;
    campaignId: number;
    imagePath: string;
    imageType: string;
}

export interface CampaignPolicyDto {
    policyId: number;
    campaignId: number;
    policyDescription: string;
    eligibilityCriteria: string;
    approvalRequired: string;
    createdAt: string | null;
    updatedAt: string | null;
}