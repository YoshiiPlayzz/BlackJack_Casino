package de.joshua.hatzinger.nico.maurer.jonas.domnick.ui;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {

    private final Image img;
    private Image scaled;

    public ImagePanel(Image img) {
        this.img = img;
    }

    @Override
    public void invalidate() {
        super.invalidate();
        int width = getWidth();
        int height = getHeight();

        if (width > 0 && height > 0) {
            scaled = img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return img == null ? new Dimension(450, 298) : new Dimension(
                img.getWidth(this), img.getHeight(this));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(scaled, 0, 0, getWidth(), getHeight(), null);
        //System.out.println(getWidth() + "     " + getHeight());
    }


}
