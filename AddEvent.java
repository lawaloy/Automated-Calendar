import javax.swing.*;
import javax.swing.table.*;
import org.omg.CORBA.BAD_INV_ORDER;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.lang.String.*;



public class AddEvent extends JFrame {
    private DataModel model;
    private JButton save;
    private JTextField title, startTime, endTime;
    private JLabel date;
    private JPanel panel;

    /**
     * create a constructor object for the class
     * @param d dataModel
     */

    public AddEvent(DataModel d){
        model = d;
        setLayout(new BorderLayout());
        setSize(300, 100);

        save = new JButton("Save");
        title = new JTextField();
        title.setPreferredSize(new Dimension(100,25));

        startTime = new JTextField();
        startTime.setPreferredSize(new Dimension(50,25));

        endTime = new JTextField();
        endTime.setPreferredSize(new Dimension(50,25));

        date = new JLabel(model.getCurrentDate());
        date.setPreferredSize(new Dimension(100, 25));
        panel = new JPanel();

        panel.setLayout(new FlowLayout());
        panel.add(date);
        panel.add(startTime);
        panel.add(endTime);
        panel.add(save);

        save.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {

                Event et = null;
                boolean flag = true;
                if (!(title.getText().isEmpty() && startTime.getText().isEmpty() && endTime.getText().isEmpty())) {

                    et = new Event(title.getText(), date.getText(), startTime.getText(), endTime.getText());

                    for (Event s : model.getData()){
                        if (et.equals(s)){
                            flag = false;
                        }
                    }
                }
                if (flag) {
                    model.update(et);
                    dispose();

                } else {
                    JFrame f = new JFrame("Error");
                    f.setSize(300,100);
                    JLabel lbl = new JLabel ("Please enter another event with Time Conflict");
                    f.add(lbl);
                    f.setDefaultCloseOperation(Jframe.EXT_ON_CLOSE);
                    f.setResizable(false);
                    f.setVisible(true);
                }
            }
        });

        add(title, BorderLayout.NORTH);
        add(panel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
}
