export interface SystemStatistics {
  // Record<string, number> to type an oject that has string for keys and values numbers
  apiUsagePercentages: Record<string, number>;
  highestEventPriceRetrieved: number;
}
