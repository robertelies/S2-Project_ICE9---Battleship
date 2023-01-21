package Presentation.View.MainView;

import Business.manager.BoardManager;
import Business.manager.FormManager;
import Business.manager.GameManager;
import Business.manager.UserManager;
import Presentation.Controller.Subcontrollers.*;
import Presentation.View.Subviews.*;
import Presentation.View.Subviews.PlayerScreenElements.JBackgroundPanel;

import javax.swing.*;
import java.awt.*;

/**
 * The class the brings the entire view together, being able to direct the user at the beginning of the program
 *
 * @author ICE 9
 */

public class MainView extends JFrame{
    private NewGameView newGameView;
    private CardLayout cl;
    private StatisticsViewBar statsViewBar;
    private StatisticsViewPie statsViewPie;
    private MainMenuView mainMenuView;
    private ContinueGameView contGameView;
    private BattleView battleView;
    private ConfigBoardView confView;
    private LoginView logView;
    private LogoutView logoutView;
    private RegisterView regView;
    private StatisticsMenu statsMenu;
    private BattleViewController battleViewController;
    private FormManager formManager;
    private UserManager userManager;
    private GameManager gameManager;
    private BoardManager boardManager;

    /**
     * Constructor used at start
     */
    public MainView() {

    }

    /**
     * getter for new game
     * @return NewGameView
     */
    public NewGameView getNewGame() {
        return newGameView;
    }

    /**
     * getter for a battle
     * @return BattleView
     */
    public BattleView getBattleView() {
        return battleView;
    }

    /**
     * getter for signup view
     * @return RegisterView
     */
    public RegisterView getRegView(){return regView;}

    /**
     * getter for login
     * @return LoginView
     */
    public LoginView getLogView(){return logView;}

    /**
     * getter for logout
     * @return LogoutView
     */
    public LogoutView getLogoutView() {return logoutView;}

    private void configureWindow() {
        setTitle("BATTLESHIPS");
        setSize(1350, 900);
        Image img = Toolkit.getDefaultToolkit().getImage("Images/background.png");
        setIconImage(img);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
    }


    /**
     * A getter for the layout
     * @return CardLayout
     */
    public CardLayout getCl(){
        return cl;
    }

    /**
     * A getter for the view pertaining to the player setting ships
     * @return the ConfigBoardView
     */
    public ConfigBoardView getConfView(){
        return confView;
    }

    /**
     * a getter for the battleviewcontroller
     * @return the BattleViewController
     */
    public BattleViewController getBattleViewController() {
         return battleViewController;
    }

    /**
     * A getter for the boardmanager
     * @return BoardManager
     */
    public BoardManager getBoardManager(){
        return boardManager;
    }

    /**
     * a getter for the continue game visuals
     * @return ContinueGameView
     */
    public ContinueGameView getContGameView(){
        return contGameView;
    }




    /**
     * USed to clear a board when necessary
     */
    public void resetGameElements() {
        confView.resetView();
        battleViewController.resetBoards();
        newGameView.clearElements();
        newGameView.getController().setColors();
    }

    /**
     * A setter for the panes
     * @param panel the JBackgroundPanel to be added
     * @param constraint a String
     */
    public void setContentPanes(JBackgroundPanel panel, String constraint){
        this.getContentPane().add(panel,constraint);
    }

    private void putPanes(){
        setContentPanes(mainMenuView.getPanel(),"1");
        setContentPanes(regView.getPanel(),"2");
        setContentPanes((logView.getPanel()),"3");
        newGameView.setView();
        setContentPanes(newGameView.getPanel(),"4");
        setContentPanes(contGameView.getPanel(),"5");
        setContentPanes(confView.getPanel(),"6");
        battleView = new BattleView(2, this, battleViewController,newGameView,formManager,userManager,gameManager);
        setContentPanes(battleView.getPanel(),"7");
        battleView = new BattleView(3,this, battleViewController,newGameView,formManager,userManager,gameManager);
        setContentPanes(battleView.getPanel(),"8");
        battleView = new BattleView(4,this, battleViewController,newGameView,formManager,userManager,gameManager);
        setContentPanes(battleView.getPanel(),"9");
        setContentPanes(statsMenu.getPanel(),"10");
        setContentPanes(statsViewPie.getPanel(),"11");
        setContentPanes(statsViewBar.getPanel(),"12");
        setContentPanes(logoutView.getPanel(),"13");
    }

    /**
     * Used to set all the view
     * @param statsViewBar statistics bar
     * @param statsViewPie statistics pie
     * @param newGameView new game
     * @param contGameView continue game
     * @param confView configure board
     * @param logoutView logout
     * @param logView log in
     * @param regView sign up
     * @param mainMenuView main menu
     * @param statsMenu statistics
     */
    public void setViews(StatisticsViewBar statsViewBar, StatisticsViewPie statsViewPie, NewGameView newGameView, ContinueGameView contGameView, ConfigBoardView confView, LogoutView logoutView, LoginView logView, RegisterView regView, MainMenuView mainMenuView, StatisticsMenu statsMenu) {
        this.confView = confView;
        this.contGameView = contGameView;
        this.statsViewBar = statsViewBar;
        this.statsViewPie = statsViewPie;
        this.newGameView = newGameView;
        this.logoutView = logoutView;
        this.logView = logView;
        this.regView = regView;
        this.mainMenuView =mainMenuView;
        this.statsMenu = statsMenu;
    }

    /**
     * A setter for all of the managers
     * @param formManager checks the user input everything
     * @param userManager used for logic related to user
     * @param gameManager used to manage a game
     * @param boardManager used for the board
     */
    public void setManagers(FormManager formManager, UserManager userManager, GameManager gameManager, BoardManager boardManager){
        this.formManager = formManager;
        this.userManager = userManager;
        this.gameManager = gameManager;
        this.boardManager = boardManager;
    }

    /**
     * A setter for a controller
     * @param battleViewController to be set too
     */
    public void setBattleViewController(BattleViewController battleViewController) {
        this.battleViewController = battleViewController;
    }

    /**
     * Used to begin the menu
     */
    public void start() {
        configureWindow();
        cl= new CardLayout();

        this.setLayout(cl);
        JBackgroundPanel viewImagePanel = new JBackgroundPanel("Images/background.png");
        viewImagePanel.setSize(1350, 600);
        viewImagePanel.setLayout(new BorderLayout());
        putPanes();
        this.setVisible(true);
    }
}
