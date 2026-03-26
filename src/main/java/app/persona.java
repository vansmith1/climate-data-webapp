package app;

public class persona {
   // privates
   private String name;
   private String level;
   private String age;
   private String gender;
   private String ethnicity;
   private String background;
   private String needs;
   private String goals;
   private String skills;
   private String painPoints;
   private String image;

   //persona object
   public persona(String name, String level, String age, String gender, String ethnicity, String background, String needs, String goals, String skills, String painPoints, String image) {
       this.name = name;
       this.level = level;
       this.age = age;
       this.gender = gender;
       this.ethnicity = ethnicity;
       this.background = background;
       this.needs = needs;
       this.goals = goals;
       this.skills = skills;
       this.painPoints = painPoints;
       this.image = image;
   }

  //getters
   public String getName() {
      return name;
   }
   public String getLevel() {
      return level;
   }
   public String getAge() {
      return age;
   }
   public String getGender(){
    return gender;
   }
   public String getEthnicity(){
    return ethnicity;
   }
   public String getBackground(){
    return background;
   }
   public String getNeeds(){
    return needs;
   }
   public String getGoals(){
    return goals;
   }
   public String getSkills(){
    return skills;
   }
   public String getPainPoints(){
    return painPoints;
   }
   public String getImage(){
      return image;
   }


}