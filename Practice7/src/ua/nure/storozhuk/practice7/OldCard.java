package ua.nure.storozhuk.practice7;

import java.util.Locale;

public class OldCard {
   private String thema;
   Type type;
   Valuable valuable;
   private String country;
   private int year;
   private String author;
   public void setThema(String thema) {
	   this.thema = thema;
   }
   public void setType(Type type) {
	   this.type = type;
   }
   public void setValuable(Valuable value) {
	   valuable = value;
   }
   public void setCountry(String country) {
	   this.country = country;
   }
   public void setYear(int year) {
	   this.year = year;
   }
   public void setAuthor(String author) {
	   this.author = author;
   }
   public String getThema() {
	   return thema;
   }
   public String getType() {
	   StringBuilder res = new StringBuilder();
	   res.append(this.type.toString());
	   res.replace(1, res.length(), res.substring(1, res.length()).toLowerCase(Locale.getDefault()));
	   return res.toString();
   }
   public String getValuable() {
	   StringBuilder ret = new StringBuilder();
	   ret.append(this.valuable.toString());
	   ret.replace(1, ret.length(), ret.substring(1, ret.length()).toLowerCase(Locale.getDefault()));
	   return ret.toString();
   }
   public String getCountry() {
	   return country;
   }
   public int getYear() {
	   return year;
   }
   public String getAuthor() {
	   return author;
   }
   public String toString() {
	   if(author == null) {
		   return "Thema: " + getThema() + "\nType: " + getType() + 
				   "\nValuable: " + getValuable() + "\nCountry: " + getCountry() +
				   "\nYear: " + getYear();
	   }
	   return "Thema: " + getThema() + "\nType: " + getType() + 
			   "\nValuable: " + getValuable() + "\nCountry: " + getCountry() +
			   "\nYear: " + getYear() + "\nAuthor: " + getAuthor();
   }
}
