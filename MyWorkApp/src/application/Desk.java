package application;

public class Desk {
   String Date;
   String Title;
   Desk(String date, String title){
	   this.Date = date;
	   this.Title = title;
   }
   public String getDate() {
	   return this.Date;
   }
   public void setDate(String date) {
	   this.Date = date;
   }
   public String getTitle() {
	   return this.Title;
   }
   public void setTitle(String title) {
	   this.Title = title;
   }
}
