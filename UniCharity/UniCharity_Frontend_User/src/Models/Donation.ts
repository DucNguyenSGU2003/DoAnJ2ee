export interface DonationPost {
  campaign: string
  amount: string
  email: string
  name: string
}

export interface DonationRes {
  code: number
  result: string
}

export interface CheckoutRes {
  code: number
  result: Result
}

export interface Result {
  id: number
  transactionCode: string
  paymentGateway: string
  transactionDate: string
  transactionStatus: string
  amount: number
  responseCode: string
  transactionDescription: string
  donation: Donation
}

export interface Donation {
  id: number
  amount: number
  paymentMethod: string
  donationDate: string
}
