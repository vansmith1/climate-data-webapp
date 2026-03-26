package app;


public class percentageClimateMetrics {
   // privates
   public String timeRange;
   public double metricTotal;
   public String stateRange;
   public double numberStore;
   public double MaxTemp;
   public double Precipitation;
   public double RainDaysNum;
   public double RainDaysMeasure;
   public double Evaporation;
   public double EvapDaysNum;
   public double MaxTempDays;
   public double MinTemp;
   public double MinTempDays;
   public double Humid;
   public double Okta;
   public double Sunshine;

   public percentageClimateMetrics(){
   }

   //climate object
   public percentageClimateMetrics(String timeRange, double numberStore, double metricTotal, String stateRange, double MaxTemp, double Precipitation, double RainDaysNum, double RainDaysMeasure, double Evaporation, double EvapDaysNum, double MaxTempDays, double MinTemp, double MinTempDays, double Humid, double Okta, double Sunshine){
       this.timeRange = timeRange;
       this.stateRange = stateRange;
       this.metricTotal = metricTotal;
       this.numberStore = numberStore;
       this.MaxTemp = MaxTemp;
       this.Precipitation = Precipitation;
       this.RainDaysNum = RainDaysNum;
       this.RainDaysMeasure = RainDaysMeasure;
       this.Evaporation = Evaporation;
       this.EvapDaysNum = EvapDaysNum;
       this.MaxTempDays = MaxTempDays;
       this.MinTemp = MinTemp;
       this.MinTempDays = MinTempDays;
       this.Humid = Humid;
       this.Okta = Okta;
       this.Sunshine = Sunshine;
   }

  //getters
   public String getTimeRange() {
      return timeRange;
   }
   public double getMetricTotal(){
    return metricTotal;
   }
   public String getStageRange(){
    return stateRange;
   }
   public double getNumberStore(){
    return numberStore;
   }
   public double getPrecipitation(){
    return Precipitation;
   }
   public double getRainDaysNum(){
    return RainDaysNum;
   }
   public double getRainDaysMeasure(){
    return RainDaysMeasure;
   }
   public double getEvaporation(){
    return Evaporation;
   }
   public double getEvapDaysNum(){
    return EvapDaysNum;
   }
   public double getMaxTemp(){
    return MaxTemp;
   }
   public double getMaxTempDays(){
    return MaxTempDays;
   }
   public double getMinTemp(){
    return MinTemp;
   }
   public double getMinTempDays(){
    return MinTempDays;
   }
   public double getHumid(){
    return Humid;
   }
   public double getSunshine(){
    return Sunshine;
   }
   public double getOkta(){
    return Okta;
   }

}
