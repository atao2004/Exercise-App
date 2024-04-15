package ui.pages;

import ui.gui.Styling;

import javax.swing.*;
import java.awt.*;

// Creates a Homepage
public class WelcomePage extends JPanel implements Page {
    public static final String TITLE = "Home";

    // MODIFIES: this
    // EFFECTS: instantiates the Welcome page with images.
    public WelcomePage() {
        setLayout(new BorderLayout());

        doEverything();

    }

    // EFFECTS: creates the panels with images.
    @SuppressWarnings("methodlength")
    public void doEverything() {
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Styling.BACKGROUND_COLOR);
        mainPanel.setLayout(new

                BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel okGraphic = new JLabel();
        okGraphic.setIcon(new

                ImageIcon("./data/IMG_3194.jpg"));
        float centerAlignment = CENTER_ALIGNMENT - 0.18f;
        okGraphic.setAlignmentX((centerAlignment));
        okGraphic.setIcon(new

                ImageIcon(new javax.swing.ImageIcon(("./data/IMG_3194.jpg"))
                .getImage().getScaledInstance(350, 300, Image.SCALE_SMOOTH)));

        JLabel workoutGraphic = new JLabel();
        //workoutGraphic.setIcon(new ImageIcon("./data/Fitness.png"));
        workoutGraphic.setAlignmentX((centerAlignment));
        workoutGraphic.setIcon(new

                ImageIcon(new javax.swing.ImageIcon(("./data/workout.jpeg")).getImage()
                .getScaledInstance(300, 300, Image.SCALE_SMOOTH)));
        workoutGraphic.setAlignmentY(0.6f);
        workoutGraphic.setAlignmentX(0.1f);

        JLabel healthGraphic = new JLabel();
        healthGraphic.setIcon(new

                ImageIcon("./data/health.png"));
        // float centerAlignment = CENTER_ALIGNMENT - 0.9f;
        healthGraphic.setAlignmentX((centerAlignment));
        healthGraphic.setIcon(new

                ImageIcon(new javax.swing.ImageIcon(("./data/health.png"))
                .getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH)));


        add(mainPanel, BorderLayout.CENTER);

        JPanel bufferPanel = new JPanel();
        bufferPanel.setBackground(Color.decode("#bfe3b4"));
        bufferPanel.setPreferredSize(new

                Dimension(0, 320));

        add(bufferPanel, BorderLayout.NORTH);
        bufferPanel.add(workoutGraphic);


        JLabel title = new JLabel("Welcome To Your Workout!!!", SwingConstants.CENTER);
        title.setFont(Styling.TITLE_FONT);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(title);

        mainPanel.add(okGraphic);
        bufferPanel.add(healthGraphic);
        bufferPanel.add(workoutGraphic);
    }
}
