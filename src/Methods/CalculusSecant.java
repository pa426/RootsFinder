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
public class CalculusSecant {

    private static Stack S;
    private static Stack Xold1;
    private static Stack Xold2;
    private static double[][] data;
    public static DefaultXYDataset datasetPoints = new DefaultXYDataset();

    public static void secant1(double xold1, double xold2, double decPoint) {//method used calculate root point acording the paramethers that enter the method and store the data in a global Stack

        double xnew, fxold1, fxold2, fxnew, diff;
        int iteration = 0;
        S = new BidimensionalArrayStack();//Declaring a new Stack beeing sure is clear before using it
        Xold1 = new BidimensionalArrayStack();
        Xold2 = new BidimensionalArrayStack();
        
        do {
            
            iteration += 1;
            // determine f(xold1) and f(xold2)
            fxold1 = xold1 - Math.pow(xold1, 2);
            fxold2 = xold2 - Math.pow(xold2, 2);
            Xold1.push(xold1, fxold1);//Inserting data in the Stack
            Xold2.push(xold2, fxold2);
            xnew = xold1 - (fxold1 * (xold1 - xold2)) / (fxold1 - fxold2);
            System.out.println("Approx for iteration{}" + iteration + " is " + xnew);
            diff = Math.abs(xnew - xold1);
            xold2 = xold1;
            xold1 = xnew;
            fxnew = xnew - Math.pow(xnew, 2);
            S.push(xnew, fxnew);
        } while (diff > decPoint);

        System.out.println("root to six decimal places is " + xnew);
      

    }

    public static void secant2(double xold1, double xold2, double decPoint) {//method used calculate root point acording the paramethers that enter the method and store the data in a global Stack

        double xnew, fxold1, fxold2, fxnew, diff;
        int iteration = 0;
        S = new BidimensionalArrayStack();//Declaring a new Stack beeing sure is clear before using it
        Xold1 = new BidimensionalArrayStack();
        Xold2 = new BidimensionalArrayStack();

        do {
            iteration += 1;
            // determine f(xold1) and f(xold2)
            fxold1 = (Math.log(xold1 + 1.0)) + 1.0;
            fxold2 = (Math.log(xold2 + 1.0)) + 1.0;
            Xold1.push(xold1, fxold1);//Inserting data in the Stack
            Xold2.push(xold2, fxold2);
            xnew = xold1 - (fxold1 * (xold1 - xold2)) / (fxold1 - fxold2);
            System.out.println("Approx for iteration{}" + iteration + " is " + xnew);
            diff = Math.abs(xnew - xold1);
            xold2 = xold1;
            xold1 = xnew;
            fxnew = (Math.log(xnew + 1.0)) + 1.0;
            S.push(xnew, fxnew);
        } while (diff > decPoint);
        System.out.println("root to six decimal places is " + xnew);
       

    }

    public static void secant3(double xold1, double xold2, double decPoint) {//method used calculate root point acording the paramethers that enter the method and store the data in a global Stack

        double xnew, fxold1, fxold2, fxnew, diff;
        int iteration = 0;
        S = new BidimensionalArrayStack();//Declaring a new Stack beeing sure is clear before using it
        Xold1 = new BidimensionalArrayStack();
        Xold2 = new BidimensionalArrayStack();

        do {
            iteration += 1;
            // determine f(xold1) and f(xold2)
            fxold1 = (Math.abs(xold1)) - (3.0 * xold1);
            fxold2 = (Math.abs(xold2)) - (3.0 * xold2);
            Xold1.push(xold1, fxold1);//Inserting data in the Stack
            Xold2.push(xold2, fxold2);
            xnew = xold1 - (fxold1 * (xold1 - xold2)) / (fxold1 - fxold2);
            System.out.println("Approx for iteration{}" + iteration + " is " + xnew);
            diff = Math.abs(xnew - xold1);
            xold2 = xold1;
            xold1 = xnew;
            fxnew = (Math.abs(xnew)) - (3 * xnew);
            S.push(xnew, fxnew);
        } while (diff > decPoint);
        System.out.println("root to six decimal places is " + xnew);
       

    }

    private static void datasetPointsSecant() {// Method used to retrieve data from stack and copy it in an array uset for the dataset

        data = new double[2][(S.size())];

        int i = (S.size()-1);
        
        while (!S.isEmpty()) {//While used to retrieve data from stack
            data[0][i] = Double.parseDouble(S.popElement1().toString());
            data[1][i] = Double.parseDouble(S.popElement2().toString());
            i--;
        }
        
        System.out.println("TEST DEEP ARRAY SECANT: " + Arrays.deepToString(data));
        datasetPoints.addSeries("Iteration points", data);


    }
    
    public static ChartPanel createChartSecant(XYDataset datasetFunction ) {// Method that populates and returns a Chart Pannel object, uses an entering paramether for the equation dataset and a global variable for the iteration points data set 
        
        datasetPointsSecant();
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

    public static String textDataSecant(int x) {// Method used to populate a string with data from iteration which will be used to populate a textArea
        
        String txtArea = "";

        for (int i = 0; i < data[0].length; i++) {//for used to retrieve data from stack and store it in a string
            txtArea += "Approx for iteration{}" + (i + 1) + " is " + data[0][i] + "\n ";
        }
        
        txtArea += "Root to " + x + " decimal places is " + data[0][data[0].length - 1];
        return txtArea;
    }
    
    public static DefaultTableModel getTableData() {// Method used to retrieve data from Stack and transform it in an array and populate a model table 

        Object[][] tableData = new Object[data[0].length][7];
        int i = (Xold1.size()-1);
        
        while (!Xold1.isEmpty()) {//while statement used to retrieve data from stack and copy it in an 2d arrray
            tableData[i][1] = Double.parseDouble(Xold1.popElement1().toString());
            tableData[i][2] = Double.parseDouble(Xold1.popElement2().toString());
            tableData[i][3] = Double.parseDouble(Xold2.popElement1().toString());
            tableData[i][4] = Double.parseDouble(Xold2.popElement2().toString());
            i--;
        }
        
        for( i = 0; i < data[0].length; i++){
            tableData[i][0] = i+1;
            tableData[i][5] = data[0][i];
            tableData[i][6] = data[1][i];
        }
        
        String[] columnNames = {"Iteration", "Xold1", "f(Xold1)", "Xold2", "f(Xold2)", "Xnew", "f(Xnew)"};
        
        DefaultTableModel model = new DefaultTableModel(tableData, columnNames);
        
        return model;
    }
}
