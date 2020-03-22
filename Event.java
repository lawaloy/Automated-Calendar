import java.io.Serializable;
import java.sql.SQLOutput;
import java.util.Calendar;

/*
* Event information
*@author evank
*/

public class Event implements Comparable<Event>, Serializable{
    String title, dateEvent, start, end, dayOfWeek, monthName;
    int month, day, year;
    public static String mnames[] = { "Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec"};
    String[] daysOfTheWeek = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};

    /*
    * creates the event objects
    * @param title
    * @param date
    * @param startTime
    * @param endTime
    */
    public Event(String title, String date, String startTime, String endTime){
        this.title = title;
        this.dateEvent = date;

        String[] d = date.split("/");
        this.month = Integer.parseInt(d[0]);
        System.out.println(d[1]);
        this.day = Integer.parseInt(d[1]);
        this.year = Integer.parseInt(d[2]);
        this.start = startTime;
        this.end = endTime;
        this.monthName = mnames[month-1];
        this.dayOfWeek = daysOfTheWeek[this.getDayOfTheWeekValue()];
    }

    /*
    * return the title of the event
    * @return title
    */
    public String getTitle(){
        return title;
    }

    /*
    * return the date of the event
    * @return dateEvent
    */
    public String getDate(){
        return dateEvent;
    }

    /*
    * return the start time of the event
    * @return start
    */
    public String getStart(){
        return start;
    }

    /*
    * returns the end time of the event
    * @return end
    */
    public String getDayOfWeek(){
        return dayOfWeek;
    }

    /*
    * the name of the month in short hand
    * the name of the month in short hand
    * @return monthName
    */
    public String getMonthName(){
        return monthName;
    }

    /*
    * return the number of the month
    * @return month
    */
    public int getMonth(){
        return month;
    }

    /*
    * return the day number
    * @return day
    */
    public int getDay(){
        return day;
    }

    /*
    * return the year
    * @return year
    */
    public int getYear(){
        return year;
    }

    /*
    * return the value of the day of the week
    * @return intDayOf Week
    */
    public int getDayOfTheWeekValue(){
        Calendar c = Calendar.getInstance();
        String[] d = dateEvent.split("/");
        c.set(Integer.parseInt(d[2]), Integer.parseInt(d[0]), Integer.parseInt(d[1]));
        return c.get(Calendar.DAY_OF_WEEK);
    }

    /*
    * checks to see if two events are equal
    * @return boolean
    */
    public boolean equals(Event that){
        if(this.compareTo(that)==0){
            return true;
        }
        return false;
    }

    /*
    * compare to methods that compares one event to another
    */
    @Override
    public int compareTo(Event that){
        if (this.getYear() != that.getYear());
            return this.getYear() - that.getYear();
    }
    else if(this.getMonth() != that.getMonth()){
        return this.getMonth() - that.getMonth();
    }
    else if (this.getDay() != that.getDay()){
        return this.getDay() - that.getDay();
    }
    else{
        return this.getStart().compareTo(that.getStart());
    }

    /*
    * return the time and time of the event
    * @return TimeAndTitle
    */
    public String getEvent(){
        return start+"-"+end+"\n"+title;
    }
}
