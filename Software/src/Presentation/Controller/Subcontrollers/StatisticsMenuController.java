package Presentation.Controller.Subcontrollers;

import Presentation.View.MainView.MainView;
import Presentation.View.Subviews.PanelFeatures.ColorLibrary;
import Presentation.View.Subviews.StatisticsGraphs.BarFeatures.BarChart;
import Presentation.View.Subviews.StatisticsGraphs.BarFeatures.ModelChart;
import Presentation.View.Subviews.StatisticsViewBar;
import Presentation.View.Subviews.StatisticsViewPie;
import jdk.swing.interop.SwingInterOpUtils;

import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;

/**
 * Used to control user input in the statistics menu
 *
 * @author ICE 9
 */

public class StatisticsMenuController implements ActionListener {
    private MainView view;

    private StatisticsViewBar statsViewBar;
    private StatisticsViewPie statsViewPie;
    private ColorLibrary colorLibrary;


    /**
     * Constructor used in main
     */
    public StatisticsMenuController() {
            colorLibrary=new ColorLibrary();
    }

    /**
     * Used to determine the user has interacted
     * @param e interaction
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "GO BACK":
               showGame("1");
               break;
            case "SEE PLAYER VICTORIES":
                statsViewPie.getPie().setSlice(0,statsViewPie.getController().getStatsPiechart().get(0),statsViewPie.getColorLibrary().getColor(1));
                statsViewPie.getPie().setSlice(1,statsViewPie.getController().getStatsPiechart().get(1),statsViewPie.getColorLibrary().getColor(5));
                showGame("11");
                break;
            case "SEE TOTAL ATTACKS":
                statsViewBar.setChartWithCurrentData();
               // statsViewBar.setChart(updateChart(statsViewBar.getBar()));
                showGame("12");
                break;
        }
    }

    /**
     * Used to determine what to show
     * @param caseShow a string with what to show
     */
    public void showGame(String caseShow){
        view.getCl().show(view.getContentPane(),caseShow);
    }


    /**
     * A setter for the visuals for stats
     * @param statsViewBar Bar visuals
     * @param statsViewPie Pie visuals
     */
    public void setView(StatisticsViewBar statsViewBar, StatisticsViewPie statsViewPie) {
        this.statsViewBar = statsViewBar;
        this.statsViewPie = statsViewPie;
    }

    /**
     * A setter for the view
     * @param view MainView
     */
    public void registerView(MainView view) {
        this.view = view;
    }
}
