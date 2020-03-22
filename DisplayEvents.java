import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DisplayEvents extends JPanel implements ChangeListener{

    private DataModel model;
    private JLabel date;
    private JTextArea agenda;
    private ArrayList<Event> schedule;
    private String day;

    /*
    * creates the object for the class
    * use a given data model
    * @param d
    */

    public DisplayEvents(DataModel d, ArrayList<Event> schedule){
        model=d;
        this.schedule = schedule;
        setBounds(550,10, 250, 100);
        date = new JLabel(model.getCurrentDate());
        agenda = new JTextArea();
        agenda.setEditable(false);
        agenda.setPreferredSize(new Dimension(100, 100));
        setLayout(new BorderLayout());
        add(date, BorderLayout.NORTH);
        add(agenda, BorderLayout.CENTER);
        setSize(new Dimension(300,350));
        setVisible(true);
    }

    public DisplayEvents(DataModel model) {
    }

    /*
    * Add the message to the test area
    */
    public void printToTextArea(){
        List<T> scheduale = null;
        Collections.sort(scheduale);
        agenda.setText("");
        for (Event s: scheduale){
            if (s.getDate().equals(day))
                agenda.append(s.getEvent()+"\n");
        }
        agenda.selectAll();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        scheduale = model.getDate();
        day = model.getCurrentDate();
        printToTextArea();
        date.setText(model.getCurrentDate());
    }
}
