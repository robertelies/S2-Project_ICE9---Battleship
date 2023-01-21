package Presentation.View.Subviews;

import Business.manager.FormManager;
import Business.manager.GameManager;
import Business.manager.UserManager;
import Presentation.Controller.Subcontrollers.BattleViewController;
import Presentation.View.MainView.MainView;
import Presentation.View.Subviews.PanelFeatures.ButtonShape;
import Presentation.View.Subviews.PanelFeatures.ColorLibrary;
import Presentation.View.Subviews.PlayerScreenElements.BoardFeatures.BoardGrid;
import Presentation.View.Subviews.PlayerScreenElements.JBackgroundPanel;
import Presentation.View.Subviews.PlayerScreenElements.LogoutButton;
import Presentation.View.Subviews.PlayerScreenElements.RulesPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * A class used to visually demonstrate to the user what occurs during a battle
 *
 * @author ICE 9
 */

public class BattleView {

    private final JBackgroundPanel viewImagePanel;
    private final FormManager formManager;
    private final UserManager userManager;
    private final MainView view;
    private JLabel time;
    private final int numPlayers;
    private final BattleViewController controller;
    private JButton exit;
    private JButton logout;
    private final Box boards;
    private final NewGameView newGameView;
    private JPanel userBoard;
    private JLabel username;
    private final GameManager gameManager;
    private JTable table;

    /**
     *  Constructor used to show game
     * @param numPlayers the number of players in the battle
     * @param view the mainview
     * @param controller controller designed for this class
     * @param newGameView the view for a new game
     * @param formManager used to manage user input
     * @param userManager used to manage user data
     * @param gameManager used to manage game data
     */
    public BattleView(int numPlayers, MainView view, BattleViewController controller, NewGameView newGameView, FormManager formManager, UserManager userManager, GameManager gameManager){
       // userBoard=new BoardGrid(newGameView.getController(), view.getConfView().getController(), gameManager.getPlayerBoard());
        this.view = view;
        this.formManager=formManager;
        this.userManager=userManager;
        this.numPlayers=numPlayers;
        this.newGameView=newGameView;
        this.gameManager = gameManager;
        this.controller = controller;
        JPanel gameBoards=new JPanel();
        gameBoards.setOpaque(false);
        viewImagePanel = new JBackgroundPanel("Images/background.png");
        viewImagePanel.setLayout(new BoxLayout(viewImagePanel,BoxLayout.Y_AXIS));
        boards = Box.createVerticalBox();
        Box.createRigidArea(new Dimension(20,20));
        gameBoards.setLayout(new GridLayout(2,2));
        gameBoards.setBorder(new EmptyBorder(0,50,0,0));
        gameBoards.setOpaque(false);
        boards.add(setBoards(numPlayers,gameBoards));
        boards.setOpaque(false);
        viewImagePanel.add(boards);
        viewImagePanel.add(buttonsPanel());
        viewImagePanel.add(new JLabel("  "));
        viewImagePanel.add(new RulesPanel());
    }

    /**
     * Used to set up the button options for the user
     * @return JPanel with buttons
     */
    public JPanel buttonsPanel(){
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setOpaque(false);
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel,BoxLayout.X_AXIS));
        ButtonShape buttonShape= new ButtonShape();
        ColorLibrary colorLibrary=new ColorLibrary();
        exit = new JButton("EXIT");
        exit.addActionListener(controller);
        exit.setActionCommand("EXIT");
        exit=buttonShape.makeFancyButton(exit,Color.white,colorLibrary.getColor(0));
        buttonsPanel.add(exit);
        LogoutButton logout;
        switch (numPlayers) {
            case 3 -> logout = new LogoutButton(view, "8",formManager,userManager);
            case 4 -> logout = new LogoutButton(view, "9",formManager,userManager);
            default -> logout = new LogoutButton(view,"7",formManager,userManager);
        }
        buttonsPanel.add(logout.getLogout());
        return buttonsPanel;
    }

    /**
     * A setter for the boards depending onthe number of players
     * @param numPlayers number of players in the game
     * @param board the board to be set
     * @return a JPanel to be shown to user
     */
    public JPanel setBoards(int numPlayers,JPanel board) {
        for(int i=0;i<numPlayers;i++){
            JPanel userSection= new JPanel();
            userSection.setOpaque(false);
            userSection.setLayout(new GridLayout(1,2));
            BoardGrid gridBattle = new BoardGrid(newGameView.getController(), view.getConfView().getController(),gameManager);
            userSection.add(gridBattle);
            controller.addBoard(gridBattle);

            userSection.add(setDataUser());
            controller.addTable(table);
            board.add(userSection);
        }
        board.setOpaque(false);
        return board;
    }


    private JPanel setDataUser() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        username= new JLabel("username");
        username.setFont(new Font("Verdana",Font.PLAIN,14));
        username.setForeground(Color.white);
        controller.addName(username);
        setClock();
        panel.add(username);
        panel.add(setTableStatus());
        panel.add(time);
        panel.setOpaque(false);
        return panel;
    }

    /**
     * A getter for the username
     * @return JLabel
     */
    public JLabel getUsername(){
        return username;
    }


    private void setClock() {
        time = new JLabel("00:00:00");
        time.setForeground(Color.white);
        time.setFont(new Font("Verdana",Font.BOLD,20));
        controller.addTimer(time);
    }

    private JPanel setTableStatus() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        String[] columns = {"Name", "Status"};
        //data for JTable in a 2D table
        Object[][] data = {
                {"Aircraft C. (1)","sailing"},
                {"Destroyer (1)","sailing"},
                {"Speed Boat (1)","sailing"},
                {"Submarine (2)","sailing"}
        };
        DefaultTableModel model = new DefaultTableModel(data, columns);
        table = new JTable(model);
        table.setRowHeight(40);
        table.getColumn("Name").setMaxWidth(150);
        table.getColumn("Status").setMaxWidth(150);
        panel.add(table.getTableHeader());
        panel.add(table);
        return panel;
    }




    /**
     * A getter for the Panel
     * @return JBackgroundPanel
     */
    public JBackgroundPanel getPanel(){
        return viewImagePanel;
    }





    /**
     * Used to set the board related to user
     * @param userBoard JPanel of userboard
     */
    public void setUserBoard(JPanel userBoard){
        this.userBoard=userBoard;
    }


}
