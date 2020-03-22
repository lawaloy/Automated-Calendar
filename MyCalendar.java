import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.GregorianCalendar;
import java.util.Calendar;

public class MyCalendar extends JFrame{
    private DataModel model;
    private JPanel gridDates;
    private JButton forward, backwards, create, quite;
    private JLabel lbMonth, ibYear;
    private int realYear, realMonth, realDay, currentYear, currentMonth, currentDay;
    private GridLayout layoutOfCalendar;
    private int nod, som; //Numer of Days, Start of Month
    private String date;

    public MyCalendar(DataModel m){
        model = m;
        setLayout(null);

        gridDates = new JPanel();
        forward = new JButton(">>");
        forward.setBounds(260, 25, 50, 25);
        forward.addActionListener(new ActionListener() {

            @Overridepublic
            void actionPerformed(ActionEvent e) {
                if (currentMonth == 11 && currentDay == 31) { //Back by one year
                    currentMonth = 0;
                    currentDay = 1;
                    currentYear++;
                } else { //back by one month
                    currentDay++;
                    if (currentDay > nod) {
                        currentMonth++;
                        currentDay = 1;
                    }
                }
                refreshCalendar(currentMonth, currentYear, currentDay);
            }
        });

        backwards = new JButton(",,");
        backwards.setBounds(10, 25, 50, 25);
        backwards.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentMonth == 0 && currentDay == 1) {
                    //back by one year
                    currentMonth = 11;
                    currentDay = 31;
                    currentYear--;
                } else {
                    //back by one month
                    currentDay--;
                    if (currentDay == 0) {
                        currentMonth--;
                        GregorianCalendar cal = new GregorianCalendar(currentYear, currentMonth, 1);
                        currentDay = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
                    }
                }
                refreshCalendar(currentMonth, currentYear, currentDay);
            }
        });
        create = new JButton("Create Event");
        create.setBounds(10, 310, 150, 20);
        create.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e){
                new AddEventPopUp(model);

            }
        });
        quite = new JButton("Quite");
        quite.setBounds(200, 310, 100, 20);
        quite.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e){
                try{FileOutputStream fileOut =
                        new FileOutputStream("Events.ser");
                        out.writeObject(model);
                        out.close();
                        fileOut.close();
            }
            catch (IOException i){
                    i.printStackTrace();
            }
             dispose();
        });

        layoutOfCalendar = new GridLayout(6, 7);
        gridDates.setLayout(layoutOfCalendar);
        gridDates.setBounds(10, 50, 500, 250);

        lbYear = new JLabel("");
        lbMonth.setBounds(100 - lbMonth.getPreferredSize().width / 2, 55, 100, 25);

        // Get real month/year
        GregorianCalendar cal = (GregorianCalnedar) Calendar.getInstance(); //Create

        realDay = cal.get(GregorianCalendar.DAY_OF_MONTH); //Get day
        realMonth = cal.get(GregorianCalendar.MONTH); //Get month
        realYear = cal.get(GregorianCalendar.YEAR); //gET YEAR

        currentMonth = realMonth; //Match month and year
        currentDay = realDay;
        currentYear = realYear;

        DisplayEvents display = new DisplayEvents(model);

        model.attach(display);
        this.add(ibYear);
        this.add(ibMonth);
        this.add(create);
        this.add(backwards);
        this.add(forward);
        this.add(gridDates);
        this.add(quite);
        this.refreshCalndar(currentMonth, currentYear, currentDay);
        this.add(display);

        setResizable(false);
        setSize(new Dimension(1000, 400));
        setVisible(true);
    }

    public void refreshCalendar(int month;  int year; int Day){
            //Variable
            String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
            lbMonth.setText(months[month]); //Refresh the month label (at the top)
            lbMonth.setText(String.valueOf(year));

            GregorianCalendar cal = new GregorianCalendar(year, month, 1);

            nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
            som = cal.get(GregorianCalendar.DAY_OF_WEEK);
            gridDates.removeAll();

            String[] headers = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

            if (som == 7) {
                layoutOfCalendar.setRows(7);
            } else {
                layoutOfCalendar.setRows(6);
            }
            for (int i = 0; i < 7; i++) {
                gridDates.add(new JLabel(headers[i]));
            }
            //Draw Calendar
            for (int i = 1; i < som; i++) {
                gridDates.add(new JLabel(""));
            }
            for (int i = 1; i <= nod; i++) {
                if (i == day) {
                    gridDates.add(new JLabel("[" + Integer.toString(i) + "]"));
                } else {
                    gridDates.add(new JLabel(Integer.toString(i)));
                }
                gridDates.validate();
                gridDates.repaint();
                date = (currentMonth + 1) + "/" + currentDay + "/" + currentYear;
                model.setCurrentDate(date);
                model.updateDate();
            }
        }
    }
}


