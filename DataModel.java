import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class DataModel implements Serializable{

    ArrayList<ChangeListener> listeners;
    ArrayList<Event> data;
    private String currentDate;

    /**
     * constructs the model object
     */

    public DataModel(){
        this.listeners = new ArrayList<>();
        this.data = new ArrayList<>();
    }

    /**
     * sets the current date of the calendar
     */
    public void setCurrentDate(String d) {
        currentDate = d;
    }

    /*
    * Attach a listener to the Model
    * @param c
    */
    public void attach(ChangeListener c){
        listeners.add(c);
    }

    /*
    * add a message to the string ArrayList
    */
    public void update(Event message){
        data.add(message);
        for (ChangeListener l: listeners){
            l.stateChanged(new ChangeEvent(this));
        }
    }

    /*
    update the current date for views
     */
    public void updateDate() {
        for (ChangeListener l : listeners) {
            l.stateChanged(new ChangeEvent(this));
        }
    }

    public String getCurrentDate() {
        return currentDate;
    }
}