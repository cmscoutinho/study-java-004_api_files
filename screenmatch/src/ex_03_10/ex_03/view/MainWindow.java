package ex_03_10.ex_03.view;

import com.google.gson.Gson;
import ex_03_10.ex_03.exception.GitHubQueryErrorException;
import ex_03_10.ex_03.model.GitHubUser;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MainWindow extends JFrame {

    private final String TITLE = "Useless GitHub User Fetcher v1.0";

    private JPanel mainPanel;
    private JPanel queryPanel;
    private JPanel avatarPanel;
    private JPanel infoPanel;

    private JTextField queryField;

    private JButton queryButton;

    private JLabel avatarLabel;

    public MainWindow() {
        this.setTitle(TITLE);

        // Components initialization
        mainPanel = new JPanel();
        queryPanel = new JPanel();
        avatarPanel = new JPanel();
        infoPanel = new JPanel();

        queryField = new JTextField("Github username", 20);

        queryButton = new JButton("Search");

        avatarLabel = new JLabel();

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
                if (queryField.getText().equals("")) {
                    queryField.setText("Github username");
                    queryField.setForeground(Color.lightGray);
                }
            }
        });

        queryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = queryField.getText();

                final String baseUri = "https://api.github.com/users/";
                final String uri;
                try {
                    uri = baseUri + URLEncoder.encode(user, "UTF-8");

                    HttpClient client = HttpClient.newHttpClient();
                    HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).build();

                    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                    String json = response.body();

                    Gson gson = new Gson();
                    GitHubUser userObj = gson.fromJson(json, GitHubUser.class);
                    if (userObj.login() == null) {
                        throw new GitHubQueryErrorException("User not found!");
                    }
                    BufferedImage bi = ImageIO.read(new URL(userObj.avatar_url()));
                    avatarLabel.setIcon(new ImageIcon(bi));
                } catch (IOException | InterruptedException | GitHubQueryErrorException ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }

            }
        });

        avatarLabel.setVerticalAlignment(SwingConstants.TOP);
        avatarLabel.setVerticalTextPosition(SwingConstants.TOP);

        // Panel configuration
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        avatarPanel.setLayout(new BorderLayout());

        queryPanel.setPreferredSize(new Dimension(460 + 460 / 2, 50));
        avatarPanel.setPreferredSize(new Dimension(460, 460));
        infoPanel.setPreferredSize(new Dimension(460 / 2, 460));

//        avatarPanel.s

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

        avatarPanel.add(avatarLabel);

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
