import { PageObject } from "./Paginate";

export class User {
     id: number | null;
     name: string;
     email: string;
     phone: string;
     password: string;
     role: string;

    constructor(id: number | null, name: string, email: string, phone: string, password: string, role: string) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = role;
    }
    
}


//get list 
export interface RootGetListUser {
    code: number
    result: ResultGetListUser
  }
  
  export interface ResultGetListUser {
    items: ItemGetListUser[]
    page: PageObject
  }
  
  export interface ItemGetListUser {
    id: number
    name: string
    email: string
    phone: string
    password: string
    role: string
    fundAllocations: FundAllocationGetListUser[]
    campaigns: CampaignGetListUser[]
    donations: DonationGetListUser[]
  }
  
  export interface FundAllocationGetListUser {
    id: number
    category: string
    amount: number
    description: string
    createdAt: string
    report: ReportGetListUser
    user: User
  }
  
  export interface ReportGetListUser {
    id: number
    totalDonations: number
    totalRecipients: number
    resultsSummary: string
    lessonsLearned: string
    reportDate: string
    createdAt: string
    updatedAt: string
  }
  
  export interface UserGetListUser {
    id: number
    name: string
    email: string
    phone: string
    password: string
    role: string
  }
  
  export interface CampaignGetListUser {
    id: number
    title: string
    description: string
    targetAmount: number
    currentAmount: number
    createdAt: string
    startDate: string
    endDate: string
    status: string
    createdBy: CreatedByGetListUser
    images: ImageGetListUser[]
    policies: PolicyGetListUser[]
    donations: DonationGetListUser[]
    campaignReports: CampaignReportGetListUser[]
  }
  
  export interface CreatedByGetListUser {
    id: number
    name: string
    email: string
    phone: string
    password: string
    role: string
  }
  
  export interface ImageGetListUser {
    id: number
    imagePath: string
    imageType: string
    campaign: CampaignGetListUser
  }
  
  
  export interface PolicyGetListUser {
    id: number
    policyDescription: string
    eligibilityCriteria: string
    approvalRequired: string
    createdAt: string
    updatedAt: string
    campaign: CampaignGetListUser
    policyViolations: PolicyViolationGetListUser[]
  }
  
  export interface PolicyViolationGetListUser {
    id: number
    violationDescription: string
    violationDate: string
    status: string
    createdAt: string
    updatedAt: string
    policy: PolicyGetListUser
    violationActions: ViolationActionGetListUser[]
  }
  
  
  
  export interface ViolationActionGetListUser {
    id: number
    actionDescription: string
    actionDate: string
    status: string
    createdAt: string
    violation: ViolationGetListUser
  }
  
  export interface ViolationGetListUser {
    id: number
    violationDescription: string
    violationDate: string
    status: string
    createdAt: string
    updatedAt: string
  }
  
  export interface DonationGetListUser {
    id: number
    amount: number
    paymentMethod: string
    donationDate: string
    campaign: CampaignGetListUser
    user: User
    transactions: TransactionGetListUser[]
  }
  
  
  export interface TransactionGetListUser {
    id: number
    transactionCode: any
    paymentGateway: string
    transactionDate: string
    transactionStatus: string
    amount: any
    responseCode: any
    transactionDescription: string
  }
  
  export interface CampaignReportGetListUser {
    id: number
    totalDonations: number
    totalRecipients: number
    resultsSummary: string
    lessonsLearned: string
    reportDate: string
    createdAt: string
    updatedAt: string
    campaign: CampaignGetListUser
    fundAllocations: FundAllocationGetListUser[]
  }

