export interface Organization {
  orgId?: number;
  email: string;
  nameOrga: string;
  krsNumber: string;
  subscriptionDate: string; // ex: "2026-04-10"
  status: string;
}

// ✅ payload used by forms (IMPORTANT FIX)
export type OrganizationPayload = Omit<Organization, 'orgId'>;
