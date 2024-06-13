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
    private JLabel userNameLabel;
    private JLabel nameLabel;
    private JLabel bioLabel;
    private JLabel companyLabel;
    private JLabel locationLabel;
    private JLabel publicReposLabel;


    public MainWindow() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            System.out.println("Look and feel error: " + e.getMessage());
        }

        this.setTitle(TITLE);

        // Components initialization
        mainPanel = new JPanel();
        queryPanel = new JPanel();
        avatarPanel = new JPanel();
        infoPanel = new JPanel();

        queryField = new JTextField("Github username", 20);

        queryButton = new JButton("Search");

        avatarLabel = new JLabel();
        userNameLabel = new JLabel();
        nameLabel = new JLabel();
        bioLabel = new JLabel();
        companyLabel = new JLabel();
        locationLabel = new JLabel();
        publicReposLabel = new JLabel();

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
        queryField.setHorizontalAlignment(SwingConstants.CENTER);

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
                    userNameLabel.setText(userObj.login());
                    nameLabel.setText(userObj.name());
                    bioLabel.setText("<html>" + userObj.bio() + "</html>");
                    companyLabel.setText("<html>" + userObj.company() + "</html>");
                    locationLabel.setText("<html>" + userObj.location() + "</html>");
                    publicReposLabel.setText(String.valueOf(userObj.public_repos()));
                } catch (IOException | InterruptedException | GitHubQueryErrorException ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }

            }
        });

        // Panel configuration
        mainPanel.setLayout(new BorderLayout(10, 10));

        avatarPanel.setLayout(new BorderLayout());
        infoPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        queryPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));


        queryPanel.setPreferredSize(new Dimension(465 + 460 / 2, 60));
        avatarPanel.setPreferredSize(new Dimension(465, 465));
        infoPanel.setPreferredSize(new Dimension(460 / 2, 465));

        // Border configuration
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        Border border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        queryPanel.setBorder(border);
        avatarPanel.setBorder(border);
        infoPanel.setBorder(border);

        userNameLabel.setBorder(BorderFactory.createTitledBorder("Username"));
        nameLabel.setBorder(BorderFactory.createTitledBorder("Name"));
        bioLabel.setBorder(BorderFactory.createTitledBorder("Bio"));
        companyLabel.setBorder(BorderFactory.createTitledBorder("Company"));
        locationLabel.setBorder(BorderFactory.createTitledBorder("Location"));
        publicReposLabel.setBorder(BorderFactory.createTitledBorder("Public repos"));

        // Components inclusion
        queryPanel.add(queryField);
        queryPanel.add(queryButton);

        avatarPanel.add(avatarLabel);

        infoPanel.add(userNameLabel);
        userNameLabel.setPreferredSize(new Dimension(userNameLabel.getParent().getPreferredSize().width-30, 50));
        userNameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        infoPanel.add(nameLabel);
        nameLabel.setPreferredSize(new Dimension(nameLabel.getParent().getPreferredSize().width-30, 50));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        infoPanel.add(bioLabel);
        bioLabel.setPreferredSize(new Dimension(bioLabel.getParent().getPreferredSize().width-30, 100));
        bioLabel.setHorizontalAlignment(SwingConstants.CENTER);

        infoPanel.add(companyLabel);
        companyLabel.setPreferredSize(new Dimension(companyLabel.getParent().getPreferredSize().width-30, 50));
        companyLabel.setHorizontalAlignment(SwingConstants.CENTER);

        infoPanel.add(locationLabel);
        locationLabel.setPreferredSize(new Dimension(locationLabel.getParent().getPreferredSize().width-30, 50));
        locationLabel.setHorizontalAlignment(SwingConstants.CENTER);

        infoPanel.add(publicReposLabel);
        publicReposLabel.setPreferredSize(new Dimension(publicReposLabel.getParent().getPreferredSize().width-30, 50));
        publicReposLabel.setHorizontalAlignment(SwingConstants.CENTER);

        mainPanel.add(queryPanel, BorderLayout.NORTH);
        mainPanel.add(avatarPanel, BorderLayout.WEST);
        mainPanel.add(infoPanel, BorderLayout.EAST);

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
