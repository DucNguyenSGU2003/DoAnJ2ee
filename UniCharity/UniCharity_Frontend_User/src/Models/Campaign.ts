import { UserGet } from "./User";

  export interface CampaignsResult {
    items: CampaignGet[]
    page: Page
  }

  export interface CampaignsReponse {
    code: number;
    result: CampaignsResult
  }
  
  export type CampaignGet = {
    id: number
    title: string
    description: string
    targetAmount: number
    currentAmount: number
    createdAt: string
    startDate: string
    endDate: string
    status: string
    createdBy: number
    images: Image[]
    policies: PolicyGet[]
    donations: DonationGet[]
    campaignReports: CampaignReport[]
  }

  export interface DonationGet {
    id: number;
    amount: number;
    paymentMethod: string;
    donationDate: string;
    user: UserGet;
    transactions?: any;
  }

  export interface CampaignReport {
    id: number
    totalDonations: number
    totalRecipients: number
    resultsSummary: string
    lessonsLearned: string
    reportDate: string
    createdAt: string
    updatedAt: string
    campaign: number
    fundAllocations: FundAllocationGet[]
  }
  
  export interface FundAllocationGet {
    id: number
    category: string
    amount: number
    description: string
    createdAt: string
    report: number
    user: number
  }

export interface PolicyGet {
  id: number
  policyDescription: string
  eligibilityCriteria: string
  approvalRequired: string
  createdAt: string
  updatedAt: string
  campaign: number
  policyViolations: PolicyViolationGet[]
}

export interface PolicyViolationGet {
  id: number
  violationDescription: string
  violationDate: string
  status: string
  createdAt: string
  updatedAt: string
  policy: number
  violationActions: ViolationActionGet[]
}

export interface ViolationActionGet {
  id: number
  actionDescription: string
  actionDate: string
  status: string
  createdAt: string
  violation: number
}

  export interface CampaignReponse {
    code: number
    result: CampaignGet
  }
  
  export type CreatedBy = {
    id: number
    name: string
    email: string
    phone: string
    password: string
    role: string
  }

  export type CampaignFormRequest = {
    title: string
    description: string
    targetAmount: number
    createdBy: number
}
  
  export type Image = {
    id: number
    imagePath: string
    imageType: string
  }
  
  export type Page = {
    totalItem: number
    currentPage: number
    totalPages: number
    pageSize: number
  }

  export interface CampaignRequest {
      title: string;
      description: string;
      targetAmount: number;
      createdBy: number;
  }

  export interface CampaignRequestRes {
    code: number
    result: ResultCampaignRequest
  }
  
  export interface ResultCampaignRequest {
    id: number
    title: string
    description: string
    targetAmount: number
    currentAmount: any
    createdAt: string
    startDate: any
    endDate: any
    status: string
    createdBy: CreatedBy
    images: any[]
    policies: any[]
    donations: any[]
    campaignReports: any[]
  }
  
  export interface User {
    id: number
    name: string
    email: string
    phone: string
    password: string
    role: string
  }
  