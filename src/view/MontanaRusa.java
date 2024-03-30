package view;

import Controller.EventController;

import javax.swing.*;
import java.awt.*;

public class MontanaRusa extends JPanel {
    private EventController events;

    public MontanaRusa(Dimension dimension, EventController events) {
        this.events = events;
        setVisible(true);
        setSize((int) dimension.getWidth() / 2, (int) dimension.getHeight() / 2);
        // Add glue at the beginning and end for vertical centering
        setLayout(new BorderLayout());

        JPanel panel1 = new JPanel();
        panel1.add(new JLabel("Roll coaster"));

        JPanel panel2 = new JPanel();
        for (int i = 0; i < 4; i++) {
            final int errorCode = i;
            JButton button = new JButton("Vagon " + i);
            button.addActionListener(e -> events.reportError("Montana",errorCode));
            panel2.add(button);
        }

        JPanel panel3 = new JPanel();
        JButton start = new JButton("Start");
        start.addActionListener(e ->  events.startAttraction(1));
        JButton stop = new JButton("Stop");
        stop.addActionListener(e -> events.stopAttraction(1));
        JButton repair = new JButton("Repair");
        repair.addActionListener(e -> events.repairAttraction(1));
        panel3.add(start);
        panel3.add(stop);
        panel3.add(repair);

        add(panel1,BorderLayout.NORTH);
        add(panel2, BorderLayout.CENTER);
        add(panel3, BorderLayout.SOUTH);


    }
}
