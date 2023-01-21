import Business.Entities.AI;
import Business.Entities.Board_;
import Business.Entities.Point;
import Business.manager.BoardManager;
import Business.manager.FormManager;
import Business.manager.GameManager;
import Business.manager.UserManager;
import Presentation.Controller.Subcontrollers.*;
import Presentation.View.MainView.MainView;
import Presentation.View.Subviews.*;

import java.io.IOException;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) {
        //VIEWS
        MainView view =new MainView();
        StatisticsViewBar statsViewBar = new StatisticsViewBar();
        MainMenuView mainMenuView = new MainMenuView();
        StatisticsViewPie statsViewPie = new StatisticsViewPie();
        ContinueGameView contGameView = new ContinueGameView();
        NewGameView newGameView = new NewGameView();
        ConfigBoardView confView = new ConfigBoardView();
        LoginView logView = new LoginView();
        LogoutView logoutView = new LogoutView();
        RegisterView regView = new RegisterView();
        StatisticsMenu statsMenu = new StatisticsMenu();

        //CONTROLLERS
        MainMenuController mainMenuController = new MainMenuController();
        StatisticsController statisticsController = new StatisticsController();
        BattleViewController battleViewController = new BattleViewController();
        ConfigBoardController configBoardController = new ConfigBoardController();
        ContinueGameController continueGameController = new ContinueGameController();
        RegisterController registerController= new RegisterController();
        LogoutController logoutController = new LogoutController();
        LoginController loginController =  new LoginController();
        NewGameController newGameController = new NewGameController();
        StatisticsMenuController statisticsMenuController = new StatisticsMenuController();

        // MANAGERS
        UserManager userManager = new UserManager();
        GameManager gameManager = new GameManager();
        FormManager formManager = new FormManager();
        BoardManager boardManager = new BoardManager();

        // INTERCONNECTIONS
        //views
        statsViewBar.registerController(statisticsController);
        statsViewBar.registerManagers(formManager,userManager);
        statsViewBar.registerView(view);

        statsViewPie.registerController(statisticsController);
        statsViewPie.registerManagers(formManager,userManager);
        statsViewPie.registerView(view);

        mainMenuView.registerController(mainMenuController);

        contGameView.registerController(continueGameController);
        contGameView.registerManagers(formManager,userManager);
        contGameView.registerView(view);

        newGameView.registerView(view);
        newGameView.registerManagers(formManager,userManager);
        newGameView.registerController(newGameController);

        confView.registerViews(view,newGameView);
        confView.registerManagers(formManager,userManager,gameManager);
        confView.registerController(configBoardController);

        logView.registerController(loginController);
        logView.registerView(view);

        logoutView.registerController(logoutController);

        regView.registerController(registerController);
        regView.registerView(view);

        statsMenu.registerView(view);
        statsMenu.registerController(statisticsMenuController);
        statsMenu.registerManagers(formManager,userManager);

        //controllers
        mainMenuController.registerView(view);
        statisticsController.registerView(view);
        statisticsController.registerManager(userManager);
        battleViewController.registerView(view);
        battleViewController.registerManagers(userManager,gameManager);
        configBoardController.registerView(view);
        configBoardController.registerManagers(userManager,boardManager,gameManager);
        continueGameController.registerView(view);
        continueGameController.registerManagers(gameManager,userManager,configBoardController);
        registerController.registerView(view);
        registerController.registerManager(userManager,formManager,gameManager,logoutController);
        logoutController.registerView(view);
        logoutController.registerManagers(formManager,userManager);
        loginController.registerView(view);
        loginController.registerManagers(formManager,userManager,gameManager,logoutController);
        newGameController.registerview(view);
        newGameController.registerManagers(formManager,userManager,gameManager);
        statisticsMenuController.registerView(view);
        //managers
        gameManager.registerListener(battleViewController);
        statisticsMenuController.setView(statsViewBar,statsViewPie);
        formManager.setView(view);
        formManager.setManager(userManager);


        view.setViews(statsViewBar,statsViewPie,newGameView,contGameView,confView,logoutView,logView,regView,mainMenuView,statsMenu);

        statsViewBar.setBar();
        statsViewBar.updateChart();
        statsViewPie.setPie();
        mainMenuView.updateView();
        contGameView.updateView();
        newGameView.setView();
        confView.setView();
        logView.setView();
        logoutView.setView();
        regView.setView();
        statsMenu.setView();

        view.setBattleViewController(battleViewController);
        view.setManagers(formManager,userManager,gameManager,boardManager);
        view.start();
    }
}