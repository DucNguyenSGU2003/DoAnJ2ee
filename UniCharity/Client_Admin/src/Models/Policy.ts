import { PageObject } from "./Paginate"

export interface ResponseAllPolicy {
    code: number
    result: ResultPolicy
  }
  
  export interface ResultPolicy {
    items: ItemPolicy[]
    page: PageObject
  }
  
  export interface ItemPolicy {
    id: number
    policyDescription: string
    eligibilityCriteria: string
    approvalRequired: string
    createdAt: string
    updatedAt: string
    campaign: Campaign
    policyViolations: PolicyViolation[]
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
  
  export interface PolicyViolation {
    id: number
    violationDescription: string
    violationDate: string
    status: string
    createdAt: string
    updatedAt: string
    policy: Policy
    violationActions: ViolationAction[]
  }
  
  export interface Policy {
    id: number
    policyDescription: string
    eligibilityCriteria: string
    approvalRequired: string
    createdAt: string
    updatedAt: string
  }
  
  export interface ViolationAction {
    id: number
    actionDescription: string
    actionDate: string
    status: string
    createdAt: string
    violation: Violation
  }
  
  export interface Violation {
    id: number
    violationDescription: string
    violationDate: string
    status: string
    createdAt: string
    updatedAt: string
  }