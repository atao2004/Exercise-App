package ui.gui;

import ui.pages.*;
import ui.pages.ProgramPage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.*;

// The main screen which contains different pages
public class MainScreen extends JPanel {
    private Gui application;
    private ArrayList<Page> pages;
    private String buttonpanel = "Card with JButtons";
    private String textpanel = "Card with JTextField";
    private JPanel cards;

    // MODIFIES: this
    // EFFECTS:  main screen and its pages
    public MainScreen(Gui application) throws IOException {
        this.application = application;
        pages = new ArrayList<>();
        setLayout(new CardLayout());

//Where the components controlled by the CardLayout are initialized:
//Create the "cards".
        JPanel card1 = new JPanel();
        JPanel card2 = new JPanel();

//Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());
        cards.add(card1, buttonpanel);
        cards.add(card2, textpanel);

        // initializes and puts pages together
        WelcomePage homePage = new WelcomePage();
        SavePage savePage = new SavePage(application);
        ProgramPage myProgramPage = new ProgramPage(application);

        pages.add(savePage);
        pages.add(homePage);
        pages.add(myProgramPage);

        add(homePage, WelcomePage.TITLE);
        add(myProgramPage, ProgramPage.TITLE);
        add(savePage, SavePage.TITLE);

    }

    // MODIFIES: this
    // EFFECTS: creates and adds pages to page map
    private void initializePages() throws IOException {
        WelcomePage homePage = new WelcomePage();
        SavePage savePage = new SavePage(application);
        ProgramPage myProgramPage = new ProgramPage(application);

       // pages.put(SavePage.TITLE, savePage);
       // pages.put(WelcomePage.TITLE, homePage);
       // pages.put(ProgramPage.TITLE, myProgramPage);

        add(homePage, WelcomePage.TITLE);
        add(myProgramPage, ProgramPage.TITLE);
        add(savePage, SavePage.TITLE);
    }
}

