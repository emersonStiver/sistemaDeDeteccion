package view;

import javax.swing.*;
import java.awt.*;

public class Portal extends JFrame {
    public Portal(){
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth() / 2;
        int height = (int) dimension.getHeight() / 2;
        int x = (int) dimension.getWidth() / 2 - width / 2;
        int y = (int) dimension.getHeight() / 2 - height / 2;
        setBounds(x, y, 650, 600);
        setResizable(false);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new PortalController(this));
        this.setVisible(true);
    }


}
