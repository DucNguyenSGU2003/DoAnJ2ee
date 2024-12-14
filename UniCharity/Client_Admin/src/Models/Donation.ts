import { PageObject } from "./Paginate";

export interface DonationGet{
         id:number;
         amount:number;
         paymentMethod:string;
         donationDate:Date;
         campaign:CampaignSimple;
         user:UserSimple;
      transactions: TransactionSimple[]

    
    
}
export interface CampaignSimple {
    id: number;
    title: string;
    description: string;
    targetAmount: number;
    currentAmount: number;
    createdAt: Date;
    startDate: Date;
    endDate: Date;
    status: string;
  }

  export interface UserSimple {
    id:number;
    name:string;
    email:string;
    phone:string;
    password:string;
    role:string;
}

export interface TransactionSimple {
     id:number;
     transactionCode:string;
     paymentGateway:string;
     transactionDate:Date;
     transactionStatus:string;
     amount:number;
     responseCode:string;
     transactionDescription:string
    }



    //get by id
    export interface RootReportById {
      code: number
      result: ResultReportById
    }
    
    export interface ResultReportById {
      id: number
      totalDonations: number
      totalRecipients: number
      resultsSummary: string
      lessonsLearned: string
      reportDate: string
      createdAt: string
      updatedAt: string
      campaign: CampaignReportById
      fundAllocations: FundAllocationReportById[]
    }
    
    export interface CampaignReportById {
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
    
    export interface FundAllocationReportById {
      id: number
      category: string
      amount: number
      description: string
      createdAt: string
      report: Report
      user: UserReportById
    }
    
    export interface ReportReportById {
      id: number
      totalDonations: number
      totalRecipients: number
      resultsSummary: string
      lessonsLearned: string
      reportDate: string
      createdAt: string
      updatedAt: string
    }
    
    export interface UserReportById {
      id: number
      name: string
      email: string
      phone: string
      password: string
      role: string
    }
    

    //top donation
    export interface ResultTopDontaionByCampaignId {
      id: number;
      name: string;
      value: number;
    }

    export interface TopDontaionByCampaignId {
      data: ResultTopDontaionByCampaignId[];
    }
    
    export interface RootTopDontaionByCampaignId {
      code: number;
      result: TopDontaionByCampaignId;
    }
    
    


    //chart top
    export interface RootListDonationByCampaignId {
      code: number
      result: ResultListDonationByCampaignId
    }
    
    export interface ResultListDonationByCampaignId {
      items: ItemListDonationByCampaignId[]
      page: PageObject
    }
    
    export interface ItemListDonationByCampaignId {
      id: number
      amount: number
      paymentMethod: string
      donationDate: string
      campaign: CampaignListDonationByCampaignId
      user: UserListDonationByCampaignId
      transactions: TransactionListDonationByCampaignId[]
    }
    
    export interface CampaignListDonationByCampaignId {
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
    
    export interface UserListDonationByCampaignId {
      id: number
      name: string
      email: string
      phone: string
      password: string
      role: string
    }
    
    export interface TransactionListDonationByCampaignId {
      id: number
      transactionCode: any
      paymentGateway: string
      transactionDate: string
      transactionStatus: string
      amount: any
      responseCode: any
      transactionDescription: string
    }
