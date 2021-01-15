package de.joshua.hatzinger.nico.maurer.jonas.domnick.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URISyntaxException;

public class RegelWindow extends JFrame {

    private final JEditorPane regeln;

    private final JScrollPane scrollPane;
    private final JScrollBar bar;

    public RegelWindow() {

        setLayout(new BorderLayout());
        setFocusable(true);
        setTitle("Black Jack - Regeln");
        setResizable(false);
        setIconImage(new ImageIcon(Main.class.getResource("/images/Icons/GameIcon/icon.png")).getImage());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        setBackground(Color.GREEN);
        setSize(640, 700);

        setLocationRelativeTo(null);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                new StartWindow();
            }
        });
        setVisible(true);
        getContentPane().setBackground(Color.WHITE);

        regeln = new JEditorPane();
        regeln.setEditable(false);


        try {

            regeln.setPage(getClass().getResource("regeln/regeln.html"));


        } catch (IOException e) {
            regeln.setContentType("text/html");
            regeln.setText("<html><body><h1>Die Regeln wurden nicht gefunden!</h1><p>Du kannst dir alternativ diese Regeln im Internet ansehen: <a href='https://www.besteonlinecasinos.co/blackjack/regeln/'>Link</a></p> </body></html>");
            regeln.addHyperlinkListener(l -> {

                Desktop desktop = java.awt.Desktop.getDesktop();
                if (l.getInputEvent().getID() == MouseEvent.MOUSE_CLICKED) {
                    try {
                        desktop.browse(l.getURL().toURI());
                    } catch (IOException | URISyntaxException ioException) {
                        ioException.printStackTrace();
                        regeln.setText("<html>Bitte starte die Regeln neu!</html>");
                    }
                }

            });
        }

        bar = new JScrollBar(JScrollBar.VERTICAL);
        bar.setPreferredSize(new Dimension(10, 0));
        bar.setUnitIncrement(20);

        scrollPane = new JScrollPane(regeln, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(0, 0, 620, 670);
        scrollPane.setVerticalScrollBar(bar);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        scrollPane.setWheelScrollingEnabled(true);
        add(scrollPane);


    }
}
