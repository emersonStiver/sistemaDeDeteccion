package view;

import javax.swing.*;
import java.awt.*;

public class AttractionLogPanel extends JPanel {
    private JTextArea textArea;
    private JScrollPane scrollPane;
    public AttractionLogPanel(Dimension dimension){
        setSize((int)dimension.getWidth()/2, 80);
        this.textArea = new JTextArea(10,25);
        this.scrollPane = new JScrollPane(textArea);
        add(scrollPane);
        setVisible(true);
    }

    public void logMessage(String message ){
        this.textArea.setText(this.textArea.getText() + message  );
    }
}
