package com.neuralnetwork;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * The Main class demonstrates the usage of the Perceptron interface to perform logical AND operations
 * and linear regression tasks. It creates instances of Perceptron for logical operations and linear regression,
 * loads training data, trains the models, tests them on sample inputs, and displays the results in charts.
 */
public class Main {
    /**
     * Logical AND/OR input arrays.
     */
    public static final int[] INPUT1 = {0, 0, 1, 1};
    public static final int[] INPUT2 = {0, 1, 0, 1};
    
    /**
     * Learning rate for the Perceptron training.
     */
    public static final double LEARNING_RATE = 0.1;
    
    /**
     * Expected output for the logical AND operation.
     */
    public static final int[] OUTPUT = {0, 0, 0, 1}; // AND
    
    /**
     * Target outputs for the linear function y = 2x + 1.
     */
    public static final int[] TARGETS = {1, 3, 5, 7, 9, 11};

    /**
     * Main method to run the Perceptron training and testing for both logical AND and linear regression.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        PerceptronFactory factory = new PerceptronFactory();

        // Logical AND Perceptron
        PerceptronInterface perceptronLogic = factory.getPerceptron("Logical", LEARNING_RATE, 1);
        createLogicalPerceptron(perceptronLogic, INPUT1, INPUT2, OUTPUT);

        // Linear regression Perceptron
        PerceptronInterface perceptronLinear = factory.getPerceptron("Linear", LEARNING_RATE, 1);
        int[] inputs = {0, 1, 2, 3, 4, 5};  // This should match the length of TARGETS
        DefaultCategoryDataset linearDataset = createLinearDataset(perceptronLinear, inputs, TARGETS);
        createAndShowLineChart("Linear Regression Perceptron", linearDataset);

        // Test linear regression on new inputs
        System.out.println("\n*** Testing on new inputs ***");
        int[] newInputs = {10, 20, -1};
        for (int input : newInputs) {
            double prediction = perceptronLinear.predict1(input);
            System.out.println("Input: " + input + " -> Prediction: " + prediction);
        }
    }

    /**
     * Creates and displays a line chart with the given title and dataset.
     * 
     * @param title the title of the chart
     * @param dataset the dataset to be displayed in the chart
     */
    public static void createAndShowLineChart(String title, DefaultCategoryDataset dataset) {
        JFreeChart lineChart = ChartFactory.createLineChart(
                title,
                "Input",
                "Output",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));

        ApplicationFrame chartFrame = new ApplicationFrame(title);
        chartFrame.setContentPane(chartPanel);
        chartFrame.pack();
        RefineryUtilities.centerFrameOnScreen(chartFrame);
        chartFrame.setVisible(true);
    }

    /**
     * Trains the logical AND Perceptron.
     * 
     * @param perceptron the Perceptron to be trained
     * @param inputs1 the first set of input values
     * @param inputs2 the second set of input values
     * @param outputs the expected output values
     */
    public static void createLogicalPerceptron(PerceptronInterface perceptron, int[] inputs1, int[] inputs2, int[] outputs) {
        for (int i = 0; i < outputs.length; i++) {
            perceptron.load2(inputs1[i], inputs2[i], outputs[i]);
        }
        perceptron.train();

        for (int i = 0; i < outputs.length; i++) {
            double prediction = perceptron.predict2(inputs1[i], inputs2[i]);
            System.out.printf("Input: (%d, %d), Target: %d, Prediction: %.1f\n", inputs1[i], inputs2[i], outputs[i], prediction);
        }
    }

    /**
     * Creates a dataset for the linear regression Perceptron, trains it, and returns the dataset.
     * 
     * @param perceptron the Perceptron to be trained
     * @param inputs the input values
     * @param targets the target output values
     * @return a dataset containing the predicted and actual output values
     */
    public static DefaultCategoryDataset createLinearDataset(PerceptronInterface perceptron, int[] inputs, int[] targets) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < targets.length; i++) {
            perceptron.load1(inputs[i], targets[i]);
        }
        perceptron.train();

        for (int i = 0; i < targets.length; i++) {
            double prediction = perceptron.predict1(inputs[i]);
            dataset.addValue(prediction, "Predicted", Integer.toString(inputs[i]));
            dataset.addValue(targets[i], "Actual", Integer.toString(inputs[i]));
        }

        return dataset;
    }
}
