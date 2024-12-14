import { UserGet } from "./User"

export interface AuthenRes {
    code: number
    result: Result
  }
  
export interface Result {
    token: string
    authenticated: boolean
    user: UserGet
}
