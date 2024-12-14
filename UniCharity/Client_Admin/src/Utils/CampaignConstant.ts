export const STATUS_PENDING = "Pending";
export const STATUS_ACTIVE = "Active";
export const STATUS_COMPLETE = "Completed";
export const STATUS_CANCLE = "Cancelled";

export const TAB_CREATE_CAMPAIGN = "Campaign";
export const TAB_CREATE_POLICIES = "Policies";

export const CAMPAIGN_STATUS = [
  { id: STATUS_PENDING, status: STATUS_PENDING },
  { id: STATUS_ACTIVE, status: STATUS_ACTIVE },
  { id: STATUS_COMPLETE, status: STATUS_COMPLETE },
  { id: STATUS_CANCLE, status: STATUS_CANCLE },
];

export const PAGE_CAMPAIGN = 0;
export const LIMIT_CAMPAIGN = 5;

export const TABS_CREATE_CAMPAIGN = [
  { id: TAB_CREATE_CAMPAIGN, title: TAB_CREATE_CAMPAIGN },
  { id: TAB_CREATE_POLICIES, title: TAB_CREATE_POLICIES }
];
