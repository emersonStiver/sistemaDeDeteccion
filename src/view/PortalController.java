package view;

import Controller.EventController;
import Controller.ParkController;

import javax.swing.*;
import java.awt.*;

public class PortalController extends JPanel {
    private EventController events;
    private MontanaRusa montanaRusa;
    private RuedaNoria ruedaNoria;
    private JFrame portal;
    private AttractionLogPanel logsMontana, logsRueda;
    public PortalController(JFrame portal){
        var operatorsCenterPanel = new OperatorsLogCenter();

        setVisible(true);
        this.portal = portal;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


        //Initialization of objects
        JPanel logPanels = new JPanel(new GridLayout(1, 2));
        this.logsMontana = new AttractionLogPanel(getSize());
        this.logsRueda = new AttractionLogPanel(getSize());
        logPanels.add(logsMontana);
        logPanels.add(logsRueda);
        this.events = new ParkController(operatorsCenterPanel,logsMontana, logsRueda);

        JPanel attractionPanel = new JPanel(new GridLayout(1, 2));
        attractionPanel.add(new MontanaRusa(portal.getSize(), events));
        attractionPanel.add(new RuedaNoria(portal.getSize(), events));



        add(attractionPanel);
        add(logPanels);
        add(operatorsCenterPanel);

    }
}
