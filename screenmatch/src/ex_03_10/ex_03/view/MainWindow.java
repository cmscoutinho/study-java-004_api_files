package ex_03_10.ex_03.view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class MainWindow extends JFrame {

    private final String TITLE = "Useless GitHub User Fetcher v1.0";

    private JPanel mainPanel;
    private JPanel queryPanel;
    private JPanel avatarPanel;
    private JPanel infoPanel;

    private JTextField queryField;

    private JButton queryButton;

    public MainWindow() {
        this.setTitle(TITLE);

        // Components initialization
        mainPanel = new JPanel();
        queryPanel = new JPanel();
        avatarPanel = new JPanel();
        infoPanel = new JPanel();

        queryField = new JTextField("Github username", 20);

        queryButton = new JButton("Search");

        // Components configuration
        queryField.setForeground(Color.lightGray);
        queryField.setToolTipText("Github username");
        queryField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                queryField.setText("");
                queryField.setForeground(Color.black);
            }

            @Override
            public void focusLost(FocusEvent e) {
                queryField.setText("Github username");
                queryField.setForeground(Color.lightGray);
            }
        });


        // Panel configuration
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10,10,10,10));

        queryPanel.setPreferredSize(new Dimension(460 + 460 / 2, 50));
        avatarPanel.setPreferredSize(new Dimension(460, 460));
        infoPanel.setPreferredSize(new Dimension(460 / 2, 460));

        // Border configuration
        Border border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        queryPanel.setBorder(border);
        avatarPanel.setBorder(border);
        infoPanel.setBorder(border);

        mainPanel.add(queryPanel, BorderLayout.NORTH);
        mainPanel.add(avatarPanel, BorderLayout.WEST);
        mainPanel.add(infoPanel, BorderLayout.EAST);

        // Components inclusion
        queryPanel.add(queryField);
        queryPanel.add(queryButton);

        this.add(mainPanel);

        // Frame final settings
        this.pack();
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.requestFocus();
    }

    public static void main(String[] args) {
        new MainWindow();
    }
}
