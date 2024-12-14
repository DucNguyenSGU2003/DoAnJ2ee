import { User } from "./Campaign"

export interface HistoryResponse {
    code: number
    result: Result
  }
  
  export interface Result {
    items: HistoryDonation[]
    page: Page
  }
  
  export type HistoryDonation = {
    id: number
    amount: number
    paymentMethod: string
    donationDate: string
    campaign: Campaign
    user: User
    transactions: Transaction[]
  }
  
  export interface Campaign {
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
  
  export interface Transaction {
    id: number
    transactionCode?: string
    paymentGateway?: string
    transactionDate?: string
    transactionStatus: string
    amount?: number
    responseCode?: string
    transactionDescription?: string
  }
  
  export interface Page {
    totalItem: number
    currentPage: number
    totalPages: number
    pageSize: number
  }
  