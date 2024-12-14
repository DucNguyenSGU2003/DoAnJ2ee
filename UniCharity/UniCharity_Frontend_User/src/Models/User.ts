export interface UserGet {
    id: number;
    name: string;
    email: string;
    phone: string;
    role: string;
  }

  export interface RegisterRes {
    code: number
    result: Result
  }
  
  export interface Result {
    userResponse: UserGet
    token: string
  }
  
