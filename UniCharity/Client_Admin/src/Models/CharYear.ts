import { PageObject } from "./Paginate"

export interface RootChartYear {
    code: number
    result: ResultChartYear
  }
  
  export interface ResultChartYear {
    "1": number
    "2": number
    "3": number
    "4": number
    "5": number
    "6": number
    "7": number
    "8": number
    "9": number
    "10": number
    "11": number
    "12": number
  }


  //table

  export interface RootTableYear {
    code: number
    result: Result
  }
  
  export interface Result {
    items: ItemTableYear[]
    page: PageObject
  }
  
  export interface ItemTableYear {
    id: number
    title: string
    description: string
    targetAmount: number
    currentAmount: number
    createdAt: string
    startDate: string
    endDate: string
    status: string
    createdBy: CreatedByTableYear
    images: ImageTableYear[]
    policies: PolicyTableYear[]
    donations: DonationTableYear[]
    campaignReports: CampaignReportTableYear[]
  }
  
  export interface CreatedByTableYear {
    id: number
    name: string
    email: string
    phone: string
    password: string
    role: string
  }
  
  export interface ImageTableYear {
    id: number
    imagePath: string
    imageType: string
    campaign: CampaignTableYear
  }
  
  export interface CampaignTableYear {
    id: number
    title: string
    description: string
    targetAmount: number
    currentAmount: number
    createdAt: string
    startDate: string
    endDate: string
    status: string
  }
  
  export interface PolicyTableYear {
    id: number
    policyDescription: string
    eligibilityCriteria: string
    approvalRequired: string
    createdAt: string
    updatedAt: string
    campaign: CampaignTableYear
    policyViolations: PolicyViolationTableYear[]
  }
  

  export interface PolicyViolationTableYear {
    id: number
    violationDescription: string
    violationDate: string
    status: string
    createdAt: string
    updatedAt: string
    policy: PolicyTableYear
    violationActions: ViolationActionTableYear[]
  }
  
 
  
  export interface ViolationActionTableYear {
    id: number
    actionDescription: string
    actionDate: string
    status: string
    createdAt: string
    violation: ViolationTableYear
  }
  
  export interface ViolationTableYear {
    id: number
    violationDescription: string
    violationDate: string
    status: string
    createdAt: string
    updatedAt: string
  }
  
  export interface DonationTableYear {
    id: number
    amount: number
    paymentMethod: string
    donationDate: string
    campaign: CampaignTableYear
    user: UserTableYear
    transactions: TransactionTableYear[]
  }
  
 
  
  export interface UserTableYear {
    id: number
    name: string
    email: string
    phone: string
    password: string
    role: string
  }
  
  export interface TransactionTableYear {
    id: number
    transactionCode: any
    paymentGateway: string
    transactionDate: string
    transactionStatus: string
    amount: any
    responseCode: any
    transactionDescription: string
  }
  
  export interface CampaignReportTableYear {
    id: number
    totalDonations: number
    totalRecipients: number
    resultsSummary: string
    lessonsLearned: string
    reportDate: string
    createdAt: string
    updatedAt: string
    campaign: CampaignTableYear
    fundAllocations: FundAllocationTableYear[]
  }

  export interface FundAllocationTableYear {
    id: number
    category: string
    amount: number
    description: string
    createdAt: string
    report: Report
    user: UserTableYear
  }
  
  export interface ReportTableYear {
    id: number
    totalDonations: number
    totalRecipients: number
    resultsSummary: string
    lessonsLearned: string
    reportDate: string
    createdAt: string
    updatedAt: string
  }
  
 
  
 
  