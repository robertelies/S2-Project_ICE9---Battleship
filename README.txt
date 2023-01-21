# Battleship

by ICE Group 9
on Sunday 29th May 2022

The following explanation is in conjunction with OOPD Second-Semester Project

## System requirements

* [IntelliJ IDEA 11.0.13](https://www.jetbrains.com/idea/download) 
* Java 11 with openJDK-17

It was tested using IntelliJ IDEA Ultimate Edition v2021.3 

## How to execute the code

By opening the project in IntelliJ the user will be able to compile the code using the platform. 

## Game Functionality

* Before entering the game the player must either log in or register (must put a valid username, email and password with confirmation)

* To begin the player will place their ships and choose their desired number of opponents. After this is completed in the Board Configuration stage, the user will indicate through interaction that they are prepared to begin the game. 

* Once the user is ready to begin the game the program will display the user and opponents board with the clock. The user will be informed of the AI's attacks with the changing the colored cells within the player board. The user will be able to track what cells they have already shot based on the distinct colors. If a ship is contained within a cell or a cell conatins a sunk ship, this will be noticiable to the user through color status changes in the boards cell. 

* There exists a timer for the user and AI's shots. This a value that will be checked when the user goes to attack another cell. If the user pressed too soon, a display message will appear to inform them. 

* The game will continue until only one participant is remaining. In the case that the user is eliminated the AI will remain playing until only one remains. 

## Game Statistics

* The game has a menu for displaying the statistics regarding a player. This menu allows access to view two different charts, a time per game chart and a winning percentage chart. These are represented as a bar and a pie chart

* It is also possible for the selection of viewing one particular players statistics from the list of user we have registered. This will change the display in real time to show the one desired by the user. 

## Considerations

* The entire project utilizes the Three Layer Architecture approach to programming while simultaneously integrating the Model-View-Controller (MVC) design pattern. The views and controllers of the views will be placed in the presentation while the business layers will act as the model. This goal is to decouple the views from the model so it can work autonomously. 

* The project connect to the database by reading a configuration file named config.json that provides the relevent information regarding the database for connection. This informaiton includes the connection port, IP address, name, username, and password of the database.
* The database that this project uses is provided through the remoteMySql.com service. This is an online database, becuase of the nature of this service, it does not require you to create any tables, however sometimes there are connectivity issues where the service has time out issues. This can be solved by restarting the intellij project and building the project once again.

## Available Persistence

* User related persistence
    * We must save information about the users that play our game, we save the typical information that any game would require such as username, email and password. This will allow a user to sign into our program. We ensure that the user will have access their save games and files because they have unique identifiers we have checked throughout the process.

* Game related persistence
    * Our group adapted the Game.java class to be modified and stored. GameObject.java grabs the information from the game and arranges it for saving. The game recieves an ID and a name. These are stored along with the Player.java and the number of AI that are with an arraylist of AI. A duration variable in string format keeps track of the time for the game. 

## About The Architecure

* Persistence/Config Package:
    * ConfigDAO
    * ConfigJSONDAO
    * ConfigObject

* Persistence/Database Package:
    * GameDao
    * SQLConnector
    * SQLGameDao
    * SQLUserDAO
    * UserDao

* Persistence/Game Package:
    * BoardDAO
    * GameJSONDAO
    * GameObject

* Business/Entities Package: 
    * AI
    * Aircraft
    * Board_
    * Cell
    * Cell_
    * Destroyer
    * Game
    * MyClock
    * Player
    * Point
    * Ship
    * Speedboat
    * Submarine
    * User
    * UserShotTimer

* Business/Manager Package:
    * BoardManager
    * FormManager
    * GameManager
    * StatisticsManager
    * UserManager

* Presentation/Controller/Board Package:
    * CellController

* Presentation/Controller/Listeners Package:
    * GameListener

* Presentation/Controller/Subcontrollers Package:
    * BattleViewController
    * ConfigBoardController
    * ContinueGameController
    * LoginController
    * LogoutController
    * MainMenuController
    * NewGameController
    * RegisterController
    * StatisticsController
    * StatisticsMenuController

* Presentation/View/MainView Package:
    * MainView

* Presentation/View/Subviews/PanelFeatures Package:
    * ButtonShape
    * ColorLibrary
    * ImageFeatures

* Presentation/View/Subviews/PlayerScreenElements Package:
    * JBackgroundPanel
    * LogoutButton
    * RulesPanel

* Presentation/View/Subviews/PlayerScreenElements/BoardFeatures Package:
    * BoardGrid

* Presentation/View/Subviews/StatisticsGraphs/BarFeatures Package:
    * BarChart
    * BlankPlotChart
    * BlankPlotChatRender
    * LabelColor
    * LegendItem
    * ModelChart
    * ModelLegend
    * NiceScale
    * SeriesSize

* Presentation/View/Subviews/StatisticsGraphs/PieFeatures Package:
    * Part
    * PieChart

* Presentation/View/Subviews Package:
    * BattleView
    * ConfigBoardView
    * ContinueGameView
    * LoginView
    * LogoutView
    * MainMenuView
    * NewGameView
    * RegisterView
    * StatisticsView
    * StatisticsViewBar
    * StatisticsViewBar
    
