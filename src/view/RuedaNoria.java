package view;

import Controller.EventController;

import javax.swing.*;
import java.awt.*;

public class RuedaNoria extends JPanel {
    EventController events;
    public RuedaNoria(Dimension dimension, EventController events){
        this.events = events;
        setVisible(true);
        //setSize((int)dimension.getWidth() / 2, (int)dimension.getHeight() / 2);
        setSize(50,50);
        setLayout(new BorderLayout());

        JPanel panel1 = new JPanel();
        panel1.add(new JLabel("Rueda Noria"));

        JPanel panel2 = new JPanel();
        panel2.setLayout(new CircleLayout());
        panel2.setSize((int)dimension.getWidth() / 2, (int)dimension.getHeight() / 2);
        // Add buttons to the panel
        for (int i = 0; i < 6; i++) { // Add desired number of buttons
            JButton button = new JButton("Vehicle " + (i + 1));
            final int vehicleId = i;
            button.addActionListener(e -> events.reportError("Rueda", vehicleId));
            panel2.add(button);
        }

        JPanel panel3 = new JPanel();
        JButton start = new JButton("Start");
        start.addActionListener(e ->  events.startAttraction(2));
        JButton stop = new JButton("Stop");
        stop.addActionListener(e -> events.stopAttraction(2));
        JButton repair = new JButton("Repair");
        repair.addActionListener(e -> events.repairAttraction(2));
        panel3.add(start);
        panel3.add(stop);
        panel3.add(repair);

        add(panel1, BorderLayout.NORTH);
        add(panel2, BorderLayout.CENTER);
        add(panel3, BorderLayout.SOUTH);
    }
}
