package Methods;

import ArrayStack.BidimensionalArrayStack;
import ArrayStack.Stack;
import java.util.Arrays;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author pa426
 */
public class CalculusBisection {

    private static Stack S;
    private static Stack Xlower;
    private static Stack Xupper;
    private static double[][] data;
    private static final DefaultXYDataset datasetPoints = new DefaultXYDataset();

    public static void bisection1(double xlower, double xupper, double decPoint) {//method used calculate root point acording the paramethers that enter the method and store the data in a global Stack

        S = new BidimensionalArrayStack();//Declaring a new Stack beeing sure is clear before using it
        Xlower = new BidimensionalArrayStack();
        Xupper = new BidimensionalArrayStack();
        double xnew, fxlower, fxupper, fxnew, diff;
        int iteration;

        fxlower = xlower - Math.pow(xlower, 2);
        fxupper = xupper - Math.pow(xupper, 2);
        iteration = 0;

        do {
            iteration += 1;
            // determine xnew and f (xnew)
            xnew = (xlower + xupper) / 2.0;
            fxnew = xnew - Math.pow(xnew, 2);
            System.out.println("Approx for iteration{}" + iteration + " is " + xnew);
            diff = Math.abs(xupper - xlower) / 2.0;
            Xlower.push(xlower, fxlower);//Inserting data in the Stack
            Xupper.push(xupper, fxupper);

            if (fxlower * fxnew > 0) {
                xlower = xnew;
                fxlower = fxnew;
            } else if (fxupper * fxnew > 0) {
                xupper = xnew;
                fxupper = fxnew;
            }
            S.push(xnew, fxnew);
        } while (diff > decPoint);
        System.out.println("root to six decimal places is " + xnew);
    }

    public static void bisection2(double xlower, double xupper, double decPoint) {//method used calculate root point acording the paramethers that enter the method and store the data in a global Stack

        S = new BidimensionalArrayStack();//Declaring a new Stack beeing sure is clear before using it
        Xlower = new BidimensionalArrayStack();
        Xupper = new BidimensionalArrayStack();
        double xnew, fxlower, fxupper, fxnew, diff;
        int iteration;

        fxlower = (Math.log(Math.abs(xlower + 1.0))) + 1.0;
        fxupper = (Math.log(xupper + 1.0)) + 1.0;
        iteration = 0;

        do {
            iteration += 1;
            // determine xnew and f (xnew)
            xnew = (xlower + xupper) / 2.0;
            fxnew = (Math.log(Math.abs(xnew + 1.0))) + 1.0;
            System.out.println("Approx for iteration{}" + iteration + " is " + xnew);
            diff = Math.abs(xupper - xlower) / 2;
            Xlower.push(xlower, fxlower);//Inserting data in the Stack
            Xupper.push(xupper, fxupper);

            if (fxlower * fxnew > 0) {
                xlower = xnew;
                fxlower = fxnew;
            } else if (fxupper * fxnew > 0) {
                xupper = xnew;
                fxupper = fxnew;
            }
            S.push(xnew, fxnew);
        } while (diff > decPoint);
        System.out.println("root to six decimal places is " + xnew);
    }

    public static void bisection3(double xlower, double xupper, double decPoint) {//method used calculate root point acording the paramethers that enter the method and store the data in a global Stack

        S = new BidimensionalArrayStack();//Declaring a new Stack beeing sure is clear before using it
        Xlower = new BidimensionalArrayStack();
        Xupper = new BidimensionalArrayStack();
        double xnew, fxlower, fxupper, fxnew, diff;
        int iteration;

        fxlower = (Math.abs(xlower)) - (3.0 * xlower);
        fxupper = (Math.abs(xupper)) - (3.0 * xupper);
        iteration = 0;

        do {
            iteration += 1;
            // determine xnew and f (xnew)
            xnew = (xlower + xupper) / 2.0;
            fxnew = (Math.abs(xnew)) - (3.0 * xnew);
            System.out.println("Approx for iteration{}" + iteration + " is " + xnew);
            diff = Math.abs(xupper - xlower) / 2.0;
            Xlower.push(xlower, fxlower);//Inserting data in the Stack
            Xupper.push(xupper, fxupper);

            if (fxlower * fxnew > 0) {
                xlower = xnew;
                fxlower = fxnew;
            } else if (fxupper * fxnew > 0) {
                xupper = xnew;
                fxupper = fxnew;
            }
            S.push(xnew, fxnew);
        } while (diff > decPoint);
        System.out.println("root to six decimal places is " + xnew);
    }

    private static void datasetPointsBisection() {// Method used to retrieve data from stack and copy it in an array uset for the dataset

        data = new double[2][(S.size())];

        int i = (S.size() - 1);

        while (!S.isEmpty()) {//While used to retrieve data from stack
            data[0][i] = Double.parseDouble(S.popElement1().toString());
            data[1][i] = Double.parseDouble(S.popElement2().toString());
            i--;
        }

        System.out.println("TEST DEEP ARRAY BISECTION: " + Arrays.deepToString(data));
        datasetPoints.addSeries("Iteration points", data);

    }

    public static ChartPanel createChartBisection(XYDataset datasetFunction) {// Method that populates and returns a Chart Pannel object, uses an entering paramether for the equation dataset and a global variable for the iteration points data set 

        datasetPointsBisection();

        JFreeChart chart = ChartFactory.createXYLineChart("Equation Chart",
                "X Axys", "Y Axys", datasetFunction, PlotOrientation.VERTICAL, true, true,
                false);

        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();//declaring a renderer used to plot more than one datatset on the table


        plot.setDataset(1, datasetPoints);
        renderer.setSeriesLinesVisible(1, false);
        renderer.setSeriesShapesVisible(1, false);
        renderer.setSeriesLinesVisible(0, true);
        renderer.setSeriesShapesVisible(0, true);
        plot.setRenderer(1, renderer);

        return new ChartPanel(chart);

    }

    public static String textDataBisection(int x) {// Method used to populate a string with data from iteration which will be used to populate a textArea
        String txtArea = "";

        for (int i = 0; i < data[0].length; i++) {//for used to retrieve data from stack and store it in a string
            txtArea += "Approx for iteration{}" + (i + 1) + " is " + data[0][i] + "\n ";
        }
        
        txtArea += "Root to " + x + " decimal places is " + data[0][data[0].length - 1];
        return txtArea;
    }
    
        public static DefaultTableModel getTableData() {// Method used to retrieve data from Stack and transform it in an array and populate a model table 

        Object[][] tableData = new Object[data[0].length][7];
        int i = (Xlower.size()-1);
        
        while (!Xlower.isEmpty()) {//while statement used to retrieve data from stack and copy it in an 2d arrray
            tableData[i][1] = Double.parseDouble(Xlower.popElement1().toString());
            tableData[i][2] = Double.parseDouble(Xlower.popElement2().toString());
            tableData[i][3] = Double.parseDouble(Xupper.popElement1().toString());
            tableData[i][4] = Double.parseDouble(Xupper.popElement2().toString());
            i--;
        }
        
        for( i = 0; i < data[0].length; i++){
            tableData[i][0] = i+1;
            tableData[i][5] = data[0][i];
            tableData[i][6] = data[1][i];
        }
        
        String[] columnNames = {"Iteration", "Xlower", "f(Xlower)", "Xupper", "f(Xupper)", "Xnew", "f(Xnew)"};
        
        DefaultTableModel model = new DefaultTableModel(tableData, columnNames);
        
        return model;
    }

}
