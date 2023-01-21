package Presentation.View.Subviews;

import Business.manager.FormManager;
import Business.manager.GameManager;
import Business.manager.UserManager;
import Presentation.Controller.Subcontrollers.ConfigBoardController;
import Presentation.View.MainView.MainView;
import Presentation.View.Subviews.PanelFeatures.ButtonShape;
import Presentation.View.Subviews.PanelFeatures.ColorLibrary;
import Presentation.View.Subviews.PanelFeatures.ImageFeatures;
import Presentation.View.Subviews.PlayerScreenElements.BoardFeatures.BoardGrid;
import Presentation.View.Subviews.PlayerScreenElements.JBackgroundPanel;
import Presentation.View.Subviews.PlayerScreenElements.LogoutButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * A class to be used when a user is determining the layout of their board
 *
 * @author ICE 9
 */

public class ConfigBoardView {
    private JBackgroundPanel viewImagePanel;
    private ConfigBoardController controller;
    private final JLabel spaces = new JLabel("  ");
    private FormManager formManager;
    private UserManager userManager;
    private ImageFeatures imageFeatures;
    private  NewGameView newGameView;
    private  MainView mainView;
    private JButton rotate;
    private JButton logoutButton;
    private JTable table;
    private BoardGrid board;
    private GameManager gameManager;

    /**
     * Constructor
     */
    public ConfigBoardView() {

    }

    /**
     * Used to define the area the user interacts
     */
    public void setView(){
        viewImagePanel = new JBackgroundPanel("Images/background.png");
        viewImagePanel.setBorder(new EmptyBorder(190, 150, 300, 170));
        viewImagePanel.setLayout(new BoxLayout(viewImagePanel, BoxLayout.Y_AXIS));

        this.imageFeatures = new ImageFeatures();
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        panel.setBackground(Color.white);
        Box panelLeft = setPanelLeft();
        panelLeft.setBorder(new EmptyBorder(50, 0, 50, 0));
        Box panelRight = setPanelRight();
        panelRight.setBorder(new EmptyBorder(70, 0, 50, 0));
        panel.add(panelLeft);
        panel.add(panelRight);
        JPanel panelInstructions = new JPanel();
        panelInstructions.setLayout(new BoxLayout(panelInstructions, BoxLayout.Y_AXIS));
        JLabel title2 = new JLabel("INSTRUCTIONS");
        title2.setFont(new Font("Verdana", Font.BOLD, 15));
        panelInstructions.add(title2);
        panelInstructions.add(new JLabel("  "));
        panelInstructions.add(new JLabel("1. Arrange your ships on the grid according to FLEET table Ships cannot touch or be contiguously         ")).setFont(new Font("Verdana", Font.PLAIN, 12));
        panelInstructions.add(new JLabel("\nNOTE: to arrange a ship, click the row in FLEET table of the ship and select the cell in board to place it")).setFont(new Font("Verdana", Font.PLAIN, 12));
        JLabel title = new JLabel("GAME CONFIGURATION");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Verdana", Font.BOLD, 30));
        viewImagePanel.add(title);
        viewImagePanel.add(panel);
        viewImagePanel.add(panelInstructions);
    }

    private Box setPanelRight() {
        ButtonShape buttonShape = new ButtonShape();
        ColorLibrary colorLibrary = new ColorLibrary();
        Box box = Box.createVerticalBox();
        Box.createRigidArea(new Dimension(180, 200));
        box.setBorder(new EmptyBorder(20, 0, 20, 20));
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        JButton submitConfig = new JButton("SUBMIT CONFIGURATION");
        submitConfig.addActionListener(controller);
        submitConfig = buttonShape.makeFancyButton(submitConfig, colorLibrary.getColor(0), Color.white);
        submitConfig.setActionCommand("SUBMIT BOARD CONFIGURATION");
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(setTableWithImages());
        panel.add(spaces);
        panel.add(submitConfig);
        panel.add(spaces);
        LogoutButton logout = new LogoutButton(mainView, "6", formManager, userManager);
        logoutButton = logout.getLogout();
        panel.add(logoutButton);
        box.add(panel);
        return box;
    }

    private JPanel setTableWithImages() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        ImageIcon aircraftIcon = new ImageIcon(imageFeatures.resizeImage("Images/aircraftCarrier.jpg", 50, 30));
        ImageIcon destroyerIcon = new ImageIcon(imageFeatures.resizeImage("Images/destroyer.jpg", 50, 30));
        ImageIcon speedBoatIcon = new ImageIcon(imageFeatures.resizeImage("Images/speedBoat.jpg", 50, 30));
        ImageIcon submarineIcon = new ImageIcon(imageFeatures.resizeImage("Images/submarine.jpg", 50, 30));

        String[] columns = {"Name", "Ship Image", "Size"};
        //data for JTable in a 2D table
        Object[][] data = {
                {"Aircraft C. (1)", aircraftIcon, "5 squares"},
                {"Destroyer (1)", destroyerIcon, "4 squares"},
                {"Speed Boat (1)", speedBoatIcon, "2 squares"},
                {"Submarine (2)", submarineIcon, "3 squares"}
        };
        DefaultTableModel model = new DefaultTableModel(data, columns);

        table = new JTable(model) {
            public Class getColumnClass(int column) {
                return (column == 1) ? Icon.class : Object.class;
            }
        };
        table.setRowHeight(40);
        table.getColumn("Name").setMaxWidth(150);
        table.getColumn("Ship Image").setMaxWidth(150);
        table.getColumn("Size").setMaxWidth(150);

        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
        rotate = new JButton();
        rotate.setIcon(new ImageIcon(imageFeatures.resizeImage("Images/rotate.png", 20, 20)));
        rotate.setBackground(Color.white);
        rotate.setBorder(new LineBorder(Color.black, 1, true));
        rotate.setSize(new Dimension(20, 20));
        rotate.addActionListener(controller);
        rotate.setActionCommand("ROTATE");
        panel.add(table.getTableHeader());
        panel.add(table);
        buttons.add(rotate);
        panel.add(new JLabel("\n"));
        panel.add(buttons);
        panel.add(new JLabel("\n"));
        return panel;
    }

    private Box setPanelLeft() {
        Box box = Box.createVerticalBox();
        Box.createRigidArea(new Dimension(180, 200));
        box.setBorder(new EmptyBorder(0, 0, 0, 20));
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel label = new JLabel("Current Board: ");
        panel.add(label);
        panel.add(spaces);
        board = new BoardGrid(newGameView.getController(), controller, gameManager);
        panel.add(board);
        box.add(panel);
        return box;
    }

    /**
     * A getter for the panel
     *
     * @return JBackgroundPanel
     */
    public JBackgroundPanel getPanel() {
        return viewImagePanel;
    }

    /**
     * A getter the for the table
     *
     * @return JTable
     */
    public JTable getTable() {
        return table;
    }

    /**
     * a getter for the board
     *
     * @return a JPanel
     */
    public JPanel getBoard() {
        return board;
    }

    /**
     * A getter for the controller
     *
     * @return ConfigBoardController
     */
    public ConfigBoardController getController() {
        return controller;
    }

    /**
     * Used to reset visuals for the user
     */
    public void resetView() {
        controller.resetGame();
        board.resetBoard();

    }

    /**
     * A setter for the internal views
     * @param view the mainview
     * @param newGameView used when a user does a newgame
     */
    public void registerViews(MainView view, NewGameView newGameView) {
        this.mainView = view;
        this.newGameView = newGameView;
    }

    /**
     * Used to set the parameters as the attributes
     * @param formManager to manage the user input
     * @param userManager to manage user logic
     * @param gameManager to manage game info
     */
    public void registerManagers(FormManager formManager, UserManager userManager, GameManager gameManager) {
        this.formManager = formManager;
        this.userManager = userManager;
        this.gameManager = gameManager;
    }

    /**
     * A setter for the controller
     * @param configBoardController the controller designed for this view
     */
    public void registerController(ConfigBoardController configBoardController) {
        this.controller = configBoardController;
    }
}
