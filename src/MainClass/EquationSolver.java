package MainClass;

import Methods.CalculusSecant;
import Methods.CalculusBisection;
import Methods.CalculusNewtonRaphson;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.DatasetUtilities;
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
public class EquationSolver extends JFrame
        implements ActionListener {

    private final String[] equationsStrings = {"x-x^2", "ln(x+1)+1", "e^x-3x"};
    private final String[] methodsStrings = {"Select a method:", "Newton-Raphson", "Secant", "Bisection"};
    String[] columnNames = {"Iteration", "X", "f(X)"};
    private double decpoint;
    private double limity = -50;
    private double limitx = 50;
    private static XYDataset datasetFunction;

    private final JLabel eqLbl = new JLabel("Select a equation:");
    private final JLabel spLbl = new JLabel("Starting point(s):");
    private final JLabel decLbl = new JLabel("Decimals");
    private final JComboBox equationComboBox = new JComboBox(equationsStrings);
    private final JComboBox methodComboBox = new JComboBox(methodsStrings);
    private final JTextField sp1TextField = new JTextField(4);
    private final JTextField sp2TextField = new JTextField(4);
    private final JTextField decTextField = new JTextField(6);
    private final JButton calculateBtn = new JButton("Calculate");
    private final JTextArea textArea = new JTextArea(26, 30);
    private final JScrollPane scrollPane = new JScrollPane(textArea);
    private final JTable table = new JTable();
    private final JPanel panel = new JPanel();
    private final JPanel start = new JPanel();
    private final JPanel center = new JPanel();
    private final JPanel down = new JPanel();
    private final JFreeChart chart = ChartFactory.createXYLineChart("Equation Chart",
            "X Axys", "Y Axys", datasetFunction, PlotOrientation.VERTICAL, true, true,
            false);
    private final ChartPanel chartPanel = new ChartPanel(chart);

    public static void main(String[] args) {//Constructor for the JFrame
        new EquationSolver();
    }

    public EquationSolver() {

        panel.setLayout(new BorderLayout());
        panel.add(scrollPane);
        setSize(1200, 700);
        setResizable(true);
        setTitle("Equation Solver");

        setLocationRelativeTo(null);

        start.add(eqLbl);
        start.add(equationComboBox);
        equationComboBox.addActionListener(this);
        start.add(methodComboBox);
        methodComboBox.addActionListener(this);
        start.add(spLbl);
        start.add(sp1TextField);
        start.add(sp2TextField);
        sp2TextField.setVisible(false);
        start.add(decLbl);
        start.add(decTextField);
        start.add(calculateBtn);
        calculateBtn.addActionListener(this);
        add(start, BorderLayout.PAGE_START);

        center.add(chartPanel);
        chartPanel.setMouseWheelEnabled(true);
        center.add(scrollPane);

        add(center, BorderLayout.CENTER);

        JScrollPane scrollpane = new JScrollPane(table);
        scrollpane.setPreferredSize(new Dimension(700, 150));
        down.add(scrollpane);
        add(down, BorderLayout.SOUTH);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        this.setVisible(true);
        textArea.setText(null);//command to clear the textArea

        String decString = "0.";//block used to set the decimal point 

        if (decTextField.getText().hashCode() != 0) {
            for (int i = 0; i < (Integer.parseInt(decTextField.getText())); i++) {
                decString += "0";
            }
            decString += "1";
            decpoint = Double.parseDouble(decString);
        } else {
            decTextField.setText("0");
        }

    

        switch (methodComboBox.getSelectedItem().toString()) {//block used to show or hide the second textField coresponding the mothod selected
            case "Secant":
                sp2TextField.setVisible(true);
                this.setVisible(true);
                break;
            case "Bisection":
                sp2TextField.setVisible(true);
                this.setVisible(true);
                break;
            default:
                sp2TextField.setVisible(false);
                this.setVisible(true);
                break;
        }

        if (e.getSource() == calculateBtn) {

            try {//Try used to test if the inserted starting point is not a numerical value
                switch (methodComboBox.getSelectedItem().toString()) {//Switch used to implement the limits of the function ploted on the graph accordingly the method selected
                    
                    case "Secant":
                    case "Bisection":
                        if (sp1TextField.getText().hashCode() == 0 || sp2TextField.getText().hashCode() == 0) {
                            JOptionPane.showMessageDialog(null, "Please insert two starting points");
                        }
                        if (Double.parseDouble(sp2TextField.getText()) > Double.parseDouble(sp1TextField.getText())) {
                            limitx = Math.abs(Double.parseDouble(sp2TextField.getText()));
                            limity = limitx * (-1);
                        } else {
                            limitx = Math.abs(Double.parseDouble(sp1TextField.getText()));
                            limity = limitx * (-1);
                        }
                        break;
                    case "Newton-Raphson":
                        limitx = Math.abs(Double.parseDouble(sp1TextField.getText()));
                        limity = limitx * (-1);
                        break;
                    case "Select a method:":
                        JOptionPane.showMessageDialog(null, "Please select a method!");
                        break;
                    default:
                        limitx = 50;
                        limity = -50;
                        break;
                }

                if (limitx == 0) {
                    limitx = 50;
                    limity = -50;
                } else if ("ln(x+1)+1".equals(equationComboBox.getSelectedItem().toString()) || "e^x-3x".equals(equationComboBox.getSelectedItem().toString()) || "x-x^2".equals(equationComboBox.getSelectedItem().toString())) {
                    limitx *= 10;
                    limity *= 10;
                }

                if (null != equationComboBox.getSelectedItem().toString()) {//Test if the comboBox was pressed 
                    switch (equationComboBox.getSelectedItem().toString()) {//Swith implementing in a global variable a dataset accordingly the function selected
                        case "x-x^2":
                            datasetFunction = DatasetUtilities.sampleFunction2D(v -> v - Math.pow(v, 2.0), limity, limitx, 100, "Function x-x^2");
                            break;
                        case "ln(x+1)+1":
                            datasetFunction = DatasetUtilities.sampleFunction2D(v -> Math.log(v + 1.0) + 1.0, limity, limitx, 100, "Function ln(x+1)+1");
                            break;
                        default:
                            datasetFunction = DatasetUtilities.sampleFunction2D(v -> (Math.exp(v)) - (3 * v), limity, limitx, 100, "Function e^x-3x");
                            break;
                    }
                }

                switch (equationComboBox.getSelectedItem().toString()) {// Switch used to verify what function and method was selected
                    case "x-x^2":
                        if (null != methodComboBox.getSelectedItem().toString()) {
                            switch (methodComboBox.getSelectedItem().toString()) {
                                case "Newton-Raphson":
                                    CalculusNewtonRaphson.newtonRaphson1(Double.parseDouble(sp1TextField.getText()), decpoint);
                                    refreshTable(CalculusNewtonRaphson.createChartNewtonRapson(datasetFunction));
                                    textArea.append(CalculusNewtonRaphson.textDataNewtonRapson(Integer.parseInt(decTextField.getText())));
                                    table.setModel(CalculusNewtonRaphson.getTableData());
                                    break;
                                case "Secant":
                                    CalculusSecant.secant1(Double.parseDouble(sp1TextField.getText()), Double.parseDouble(sp2TextField.getText()), decpoint);
                                    refreshTable(CalculusSecant.createChartSecant(datasetFunction));
                                    textArea.append(CalculusSecant.textDataSecant(Integer.parseInt(decTextField.getText())));
                                    table.setModel(CalculusSecant.getTableData());
                                    break;
                                case "Bisection":
                                    CalculusBisection.bisection1(Double.parseDouble(sp1TextField.getText()), Double.parseDouble(sp2TextField.getText()), decpoint);
                                    refreshTable(CalculusBisection.createChartBisection(datasetFunction));
                                    textArea.append(CalculusBisection.textDataBisection(Integer.parseInt(decTextField.getText())));
                                    table.setModel(CalculusBisection.getTableData());
                                    break;
                            }
                        }
                        break;
                    case "ln(x+1)+1":
                        if (null != methodComboBox.getSelectedItem().toString()) {
                            switch (methodComboBox.getSelectedItem().toString()) {
                                case "Newton-Raphson":
                                    CalculusNewtonRaphson.newtonRaphson2(Double.parseDouble(sp1TextField.getText()), decpoint);
                                    refreshTable(CalculusNewtonRaphson.createChartNewtonRapson(datasetFunction));
                                    textArea.append(CalculusNewtonRaphson.textDataNewtonRapson(Integer.parseInt(decTextField.getText())));
                                    table.setModel(CalculusNewtonRaphson.getTableData());
                                    break;
                                case "Secant":
                                    CalculusSecant.secant2(Double.parseDouble(sp1TextField.getText()), Double.parseDouble(sp2TextField.getText()), decpoint);
                                    refreshTable(CalculusSecant.createChartSecant(datasetFunction));
                                    textArea.append(CalculusSecant.textDataSecant(Integer.parseInt(decTextField.getText())));
                                    table.setModel(CalculusSecant.getTableData());
                                    break;
                                case "Bisection":
                                    CalculusBisection.bisection2(Double.parseDouble(sp1TextField.getText()), Double.parseDouble(sp2TextField.getText()), decpoint);
                                    refreshTable(CalculusBisection.createChartBisection(datasetFunction));
                                    textArea.append(CalculusBisection.textDataBisection(Integer.parseInt(decTextField.getText())));
                                    table.setModel(CalculusBisection.getTableData());
                                    break;
                            }
                        }
                        break;
                    case "e^x-3x":
                        if (null != methodComboBox.getSelectedItem().toString()) {
                            switch (methodComboBox.getSelectedItem().toString()) {
                                case "Newton-Raphson":
                                    CalculusNewtonRaphson.newtonRaphson3(Double.parseDouble(sp1TextField.getText()), decpoint);
                                    refreshTable(CalculusNewtonRaphson.createChartNewtonRapson(datasetFunction));
                                    textArea.append(CalculusNewtonRaphson.textDataNewtonRapson(Integer.parseInt(decTextField.getText())));
                                    table.setModel(CalculusNewtonRaphson.getTableData());
                                    break;
                                case "Secant":
                                    CalculusSecant.secant3(Double.parseDouble(sp1TextField.getText()), Double.parseDouble(sp2TextField.getText()), decpoint);
                                    refreshTable(CalculusSecant.createChartSecant(datasetFunction));
                                    textArea.append(CalculusSecant.textDataSecant(Integer.parseInt(decTextField.getText())));
                                    table.setModel(CalculusSecant.getTableData());
                                    break;
                                case "Bisection":
                                    CalculusBisection.bisection3(Double.parseDouble(sp1TextField.getText()), Double.parseDouble(sp2TextField.getText()), decpoint);
                                    refreshTable(CalculusBisection.createChartBisection(datasetFunction));
                                    textArea.append(CalculusBisection.textDataBisection(Integer.parseInt(decTextField.getText())));
                                    table.setModel(CalculusBisection.getTableData());
                                    break;
                            }
                        }
                        break;
                }
            } catch (Exception x) {
                JOptionPane.showMessageDialog(null, "Please insert a numerical value!");
            }
        }
    }

    public void refreshTable(ChartPanel CP) {//method used to refresh a table after inserting new data sets
        center.removeAll();
        center.revalidate();
        center.add(CP);
        CP.setMouseWheelEnabled(true);
        center.add(scrollPane);
        center.repaint();
    }

}
