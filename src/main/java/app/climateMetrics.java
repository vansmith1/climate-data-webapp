package app;


public class climateMetrics {
   // privates
   public String metric;
   public int location;
   public String date;

   public climateMetrics(){
   }

   //persona object
   public climateMetrics(String metric, String id, String date) {
       this.metric = metric;
       this.location = Integer.parseInt(id);
       this.date = date;
   }

  //getters
   public String getMetric() {
      return metric;
   }
   public int getLocation() {
      return location;
   }
   
   public String getDate(){
    return date;
   }

}
