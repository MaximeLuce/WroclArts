export interface Event {
  eventId?: number;
  address: string;
  category: string;
  date: string; // "2000-05-21"
  numberOfPlaces: number;
  price: number;
  status: string;
  time: string;
  title: string;
  type: string;
  isRegistered?: boolean;
  organizationName?: string;
}

// ✅ payload used by forms (IMPORTANT FIX)
export type EventPayload = Omit<Event, 'eventId'>;
