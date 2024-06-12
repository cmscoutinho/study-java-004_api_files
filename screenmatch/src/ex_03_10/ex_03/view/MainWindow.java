package ex_03_10.ex_03.view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class MainWindow extends JFrame {

    private final String TITLE = "Useless GitHub User Fetcher v1.0";

    private JPanel mainPanel;
    private JPanel queryPanel;
    private JPanel avatarPanel;
    private JPanel infoPanel;

    public MainWindow() {
        this.setTitle(TITLE);
//        this.setLayout(new BorderLayout(10, 10));

        mainPanel = new JPanel();
        queryPanel = new JPanel();
        avatarPanel = new JPanel();
        infoPanel = new JPanel();

        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10,10,10,10));

        queryPanel.setPreferredSize(new Dimension(460 + 460 / 2, 50));
        avatarPanel.setPreferredSize(new Dimension(460, 460));
        infoPanel.setPreferredSize(new Dimension(460 / 2, 460));

        Border border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        queryPanel.setBorder(border);
        avatarPanel.setBorder(border);
        infoPanel.setBorder(border);

        mainPanel.add(queryPanel, BorderLayout.NORTH);
        mainPanel.add(avatarPanel, BorderLayout.WEST);
        mainPanel.add(infoPanel, BorderLayout.EAST);

        this.add(mainPanel);

        this.pack();
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    public static void main(String[] args) {
        new MainWindow();
    }
}
