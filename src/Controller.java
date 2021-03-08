import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Function;

public class Controller implements Initializable {

    @FXML
    private LineChart<Double, Double> lineGraph;

    @FXML
    private AreaChart<Double, Double> areaGraph;

    @FXML
    private Button lineGraphButton;

    @FXML
    private Button areaGraphButton;

    @FXML
    private Button directButton;

    @FXML
    private Button lagrangeButton;

    @FXML
    private Button newtonButton;

    @FXML
    private Button clearButton;

    private MyGraph mathsGraph;
    private MyGraph areaMathsGraph;

    @Override
    public void initialize(final URL url, final ResourceBundle rb) {
        mathsGraph = new MyGraph(lineGraph, 50);
        areaMathsGraph = new MyGraph(areaGraph, 50);
    }

    @FXML
    private void handleLineGraphButtonAction(final ActionEvent event) {
        lineGraph.setVisible(true);
        areaGraph.setVisible(false);
    }

    @FXML
    private void handleAreaGraphButtonAction(final ActionEvent event) {
        areaGraph.setVisible(true);
        lineGraph.setVisible(false);
    }

    private void plotLine(Function<Double, Double> function) {
        if (lineGraph.isVisible()) {
            mathsGraph.plotLine(function);
        } else {
            areaMathsGraph.plotLine(function);
        }
    }

    @FXML
    private void handleDirectButtonAction(final ActionEvent event) {
        double[] x={3, 7, 11, 14, 17, 20, 25, 30, 35, 40, 42, 43};
        double[] y={1,47,670,1529,3629,9217,20921,38226,61049,82329,90980,95591};
        double[] xi={37};
        DirectMethod.calculateLinear(x,y,xi);
        plotLine(DirectMethod.calculateFunction(x,y));
    }

    @FXML
    private void handleLagrangeButtonAction(final ActionEvent event) {
        Lagrange.Data f[]={new Lagrange.Data(3, 1), new Lagrange.Data(7, 47), new Lagrange.Data(11, 670),
                new Lagrange.Data(14, 1529), new Lagrange.Data(17, 3629), new Lagrange.Data(20, 9217),
                new Lagrange.Data(25, 20921), new Lagrange.Data(30, 38226), new Lagrange.Data(35, 61049),
                new Lagrange.Data(40, 82329), new Lagrange.Data(42, 90980), new Lagrange.Data(43, 95591)};
        Lagrange.calculateLagrange(f,37,12);
        plotLine(Lagrange.calculateFunction(f));
    }

    @FXML
    private void handleNewtonButtonAction(final ActionEvent event) {
        int n = 4;
        double value=754.8;
        double x[] = {1101, 911.3, 636, 451.1};
        double y[][]=new double[x.length][x.length];

        y[0][0]=25.113;
        y[1][0]=30.131;
        y[2][0]=40.120;
        y[3][0]=50.128;

        DividedDifferences.calculateDD(x,y,n,value);

        DividedDifferences.dividedDiffTable(x, y, n);

       plotLine(DividedDifferences.calculateFunction(x,y,n));
    }

    @FXML
    private void handleClearButtonAction(final ActionEvent event) {
        clear();
    }

    private void clear() {
        if (lineGraph.isVisible()) {
            mathsGraph.clear();
        } else {
            areaMathsGraph.clear();
        }
    }
}
