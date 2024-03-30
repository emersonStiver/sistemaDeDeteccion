package view;

import javax.swing.*;
import java.awt.*;

public class CircleLayout implements LayoutManager {

    @Override
    public void layoutContainer(Container parent) {
        if (!(parent instanceof JPanel)) {
            throw new IllegalArgumentException("CircleLayout can only be used with JPanel");
        }

        JPanel panel = (JPanel) parent;
        Insets insets = panel.getInsets(); // Get insets (borders) of the panel
        int width = panel.getWidth() - insets.left - insets.right;
        int height = panel.getHeight() - insets.top - insets.bottom;

        int radius = Math.min(width / 2, height / 2); // Use the smaller dimension as radius
        int centerX = width / 2;
        int centerY = height / 2;

        int nButtons = panel.getComponentCount();
        double angleStep = 2 * Math.PI / nButtons;

        for (int i = 0; i < nButtons; i++) {
            JButton button = (JButton) panel.getComponent(i);
            int buttonWidth = button.getPreferredSize().width;
            int buttonHeight = button.getPreferredSize().height;

            // Adjust x and y to consider insets and button size
            int x = centerX + (int) (radius * Math.cos(i * angleStep)) - buttonWidth / 2 + insets.left ;
            int y = centerY + (int) (radius * Math.sin(i * angleStep)) - buttonHeight / 2 + insets.top;

            button.setBounds(x, y, buttonWidth, buttonHeight);
        }
    }

    // Implement other required methods of LayoutManager
    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return new Dimension(100, 100); // Set a minimum size
    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        return minimumLayoutSize(parent);
    }

    @Override
    public void removeLayoutComponent(Component comp) {
        // Handle component removal (optional)
    }

    @Override
    public void addLayoutComponent(String name, Component comp) {
        // Handle component addition (optional)
    }


}
