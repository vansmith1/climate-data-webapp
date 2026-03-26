package app;


public class groupedClimateMetrics {
   // privates
   public String timeRange;
   public String metricTotal;
   public String stateRange;

   public groupedClimateMetrics(){
   }

   //climate object
   public groupedClimateMetrics(String timeRange, String groupBy, String metricTotal, String stateRange) {
       this.timeRange = timeRange;
       this.stateRange = stateRange;
       this.metricTotal = metricTotal;
   }

  //getters
   public String getTimeRange() {
      return timeRange;
   }
   public String getMetricTotal(){
    return metricTotal;
   }
   public String getStageRange(){
    return stateRange;
   }

}
