import { DonationGet } from "./Donation";
import { PageObject } from "./Paginate";

export  interface ResponseListDonationAPI
{
    code:number;
    result:
    {
     items:DonationGet[],
     page:PageObject
        
    }
}