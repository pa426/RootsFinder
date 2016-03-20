package Methods;

import LinkedList.DLinkedList;
import LinkedList.DoubleNode;
import java.util.Arrays;
import javax.swing.*;
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
public class CalculusNewtonRaphson {

    private static final DLinkedList xNewLinkedList = new DLinkedList();
    private static final DLinkedList xLinkedList = new DLinkedList();
    private static DoubleNode xNewHead = new DoubleNode();
    private static DoubleNode xHead = new DoubleNode();
    private static final DefaultXYDataset datasetPoints = new DefaultXYDataset();

    public static void newtonRaphson1(double xold, double decPoint) {//method used calculate root point acording the paramethers that enter the method and store the data in a global paramether linked list

        double xnew, fxold, fxnew, fdashxold, diff;
        int iteration = 0;
        xNewLinkedList.head = null;//Clearing the linked list before using it
        xLinkedList.head = null;

        do {
            iteration += 1;
            fxold = xold - Math.pow(xold, 2.0);
            fdashxold = 1.0 - (2.0 * xold);
            xnew = xold - (fxold / fdashxold);
            fxnew = xnew - Math.pow(xnew, 2.0);
            System.out.println("Approx for iteration{}" + iteration + " is " + xnew);

            if (iteration == 1) {//Block used to insert data in the linked list
                xNewLinkedList.addFirst(xold, fxold, iteration);
                xLinkedList.addFirst(xnew, fxnew, iteration);
            } else {
                xNewLinkedList.addMid(xold, fxold, iteration, iteration - 1);
                xLinkedList.addMid(xnew, fxnew, iteration, iteration - 1);
            }

            diff = Math.abs(xnew - xold);
            xold = xnew;

        } while (diff > decPoint);
        xNewLinkedList.addMid(xnew, 0.0, iteration + 1, iteration);//Block used to insert data in the linked list
        xLinkedList.addMid(xnew, 0.0, iteration + 1, iteration);
        System.out.println("root to six decimal places is " + xnew);

    }

    public static void newtonRaphson2(double xold, double decPoint) {//method used calculate root point acording the paramethers that enter the method and store the data in a global paramether linked list

        double xnew, fxold, fxnew, fdashxold, diff;
        int iteration;
        iteration = 0;
        xNewLinkedList.head = null;//Clearing the linked list before using it
        xLinkedList.head = null;

        do {
            iteration += 1;
            fxold = (Math.log(Math.abs(xold + 1.0))) + 1.0;
            fdashxold = (1.0 / (xold + 1.0));
            xnew = xold - (fxold / fdashxold);
            fxnew = xnew - Math.pow(xnew, 2.0);
            System.out.println("Approx for iteration{}" + iteration + " is " + xnew);

            if (iteration == 1) {//Block used to insert data in the linked list
                xNewLinkedList.addFirst(xold, fxold, iteration);
                xLinkedList.addFirst(xnew, fxnew, iteration);
            } else {
                xNewLinkedList.addMid(xold, fxold, iteration, iteration - 1);
                xLinkedList.addMid(xnew, fxnew, iteration, iteration - 1);
            }

            diff = Math.abs(xnew - xold);
            xold = xnew;
        } while (diff > decPoint);
        xNewLinkedList.addMid(xnew, 0.0, iteration + 1, iteration);//Block used to insert data in the linked list
        xLinkedList.addMid(xnew, 0.0, iteration + 1, iteration);
        System.out.println("root to six decimal places is " + xnew);

    }

    public static void newtonRaphson3(double xold, double decPoint) {//method used calculate root point acording the paramethers that enter the method and store the data in a global paramether linked list

        double xnew, fxold, fxnew, fdashxold, diff;
        int iteration;
        iteration = 0;
        xNewLinkedList.head = null;//Clearing the linked list before using it
        xLinkedList.head = null;

        do {
            iteration += 1;
            // determine f(xold) and f’(xold)
            fxold = (Math.exp(xold)) - (3.0 * xold);
            fdashxold = (Math.exp(xold) - 3.0);
            xnew = xold - (fxold / fdashxold);
            fxnew = xnew - Math.pow(xnew, 2.0);
            System.out.println("Approx for iteration{}" + iteration + " is " + xnew);

            if (iteration == 1) {//Block used to insert data in the linked list
                xNewLinkedList.addFirst(xold, fxold, iteration);
                xLinkedList.addFirst(xnew, fxnew, iteration);
            } else {
                xNewLinkedList.addMid(xold, fxold, iteration, iteration - 1);
                xLinkedList.addMid(xnew, fxnew, iteration, iteration - 1);
            }

            diff = Math.abs(xnew - xold);
            xold = xnew;
        } while (diff > decPoint);
        xNewLinkedList.addMid(xnew, 0.0, iteration + 1, iteration);//Block used to insert data in the linked list
        xLinkedList.addMid(xnew, 0.0, iteration + 1, iteration);
        System.out.println("root to six decimal places is " + xnew);

    }

    private static void datasetPointsNewtonRapson() {// Method used to retrieve the stored values copy them in a array and store them in a global dataset

        double[] data1 = new double[xNewLinkedList.size()];
        double[] data2 = new double[xNewLinkedList.size()];
        int i = 0;

        xNewHead = xNewLinkedList.head;
        xNewHead = xNewHead.getNext();
        while (xNewHead != null) {//while used to store data from linked list to an array
            data1[i] = xNewHead.getElement1();
            data2[i] = xNewHead.getElement2();
            i++;
            xNewHead = xNewHead.getNext();
        }

        double[][] data = {data1, data2};
        System.out.println("TEST DEEP ARRAY NR: " + Arrays.deepToString(data));
        datasetPoints.addSeries("Iteration points", data);

    }

    public static ChartPanel createChartNewtonRapson(XYDataset datasetFunction) {// Method that populates and returns a Chart Pannel object, uses an entering paramether for the equation dataset and a global variable for the iteration points data set 

        datasetPointsNewtonRapson();
        JFreeChart chart = ChartFactory.createXYLineChart("Equation Chart",
                "X Axys", "Y Axys", datasetFunction, PlotOrientation.VERTICAL, true, true,
                false);//creating a object table wich takes the methods arguments as a dataset

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

    public static String textDataNewtonRapson(int x) {// Method used to populate a string with data from iteration which will be used to populate a textArea

        double xnew = 0;
        String txtArea = "";
        xNewHead = xNewLinkedList.head;
        xNewHead = xNewHead.getNext();

        while (xNewHead != null) {//while used to retrieve data from linked list and store it in a string
            txtArea += "Approx for iteration{}" + (xNewHead.getCounter() - 1) + " is " + xNewHead.getElement1() + "\n ";
            xnew = xNewHead.getElement1();
            xNewHead = xNewHead.getNext();
        }
        txtArea += "Root to " + x + " decimal places is " + xnew;
        return txtArea;
    }

    public static DefaultTableModel getTableData() {// Method used to retrieve data from the linked lists transform it in an array and populate a model table 

        Object[][] tableData = new Object[xNewLinkedList.size()][5];
        int i = 0;

        xNewHead = xNewLinkedList.head;
        xHead = xLinkedList.head;
        xNewHead = xNewHead.getNext();
        xHead = xHead.getNext();
        while (xNewHead != null) {//while statement used to retrieve data from linked list and copy it in an 2d arrray
            tableData[i][0] = i + 1;
            tableData[i][1] = xNewHead.getElement1();
            tableData[i][2] = xNewHead.getElement2();
            tableData[i][3] = xHead.getElement1();
            tableData[i][4] = xHead.getElement2();

            i++;
            xNewHead = xNewHead.getNext();
            xHead = xHead.getNext();
        }

        String[] columnNames = {"Iteration", "Xold", "f(Xold)", "Xnew", "f(Xnew)"};

        DefaultTableModel model = new DefaultTableModel(tableData, columnNames);

        return model;
    }

}
