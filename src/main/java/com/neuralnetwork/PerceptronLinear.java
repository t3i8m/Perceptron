package com.neuralnetwork;
import java.util.ArrayList;

/**
 * The PerceptronLinear class is a concrete implementation of a perceptron for linear regression tasks.
 * It provides methods for training the perceptron, making predictions, and calibrating weights.
 */
public class PerceptronLinear extends Perceptron implements PerceptronInterface {

    private ArrayList<Node> inputStorage = new ArrayList<>();
    private ArrayList<Node> outputStorage = new ArrayList<>();

    /**
     * Constructs a PerceptronLinear with the specified learning rate and bias value.
     *
     * @param learningRate the learning rate for the perceptron
     * @param valueBias the value for the bias node
     */
    public PerceptronLinear(double learningRate, int valueBias) {
        super(learningRate, valueBias);
    }

    /**
     * Loads a training example with one input value and the target output value.
     *
     * @param valueInp1 the input value
     * @param valueTarget the target output value
     */
    @Override
    public void load1(int valueInp1, int valueTarget) {
        inputStorage.add(new Node(valueInp1));
        outputStorage.add(new Node(valueTarget));

        System.out.println("\n !!--Perceptron is ready to learn--!!\n");
    }

    /**
     * Trains the perceptron using the loaded training data.
     * Iterates through the training examples and adjusts the weights based on prediction errors.
     */
    @Override
    public void train() {
        int i = 0;
        while (i < outputStorage.size()) {
            iterationN += 1;
            double weightedSum = Math.round(((biasNode.getValue() * weights.get(0)) + 
                                              (inputStorage.get(i).getValue() * weights.get(1))) * 1000.0) / 1000.0;

            System.out.println("\nWeighted sum: " + weightedSum);
            System.out.println("Correct prediction is: " + outputStorage.get(i).getValue());
            System.out.println("Predicted is: " + weightedSum);

            if (weightedSum == outputStorage.get(i).getValue()) {
                System.out.println("Correct prediction");
                printInformation(i);
                i += 1;
            } else {
                changedWeights += 1;
                System.out.println("\n !!--Incorrect prediction--!!");
                weightCalibration(i, outputStorage.get(i).getValue(), weightedSum);
                printInformation(i);
                i = 0;
            }
        }

        System.out.println("\n !!--Perceptron has been trained--!!\n");
        printFinalWeights();
    }

    /**
     * Predicts the output for a given input value using the trained perceptron.
     *
     * @param input1 the input value
     * @return the predicted output value
     */
    @Override
    public double predict1(int input1) {
        return Math.round(((biasNode.getValue() * weights.get(0)) + (input1 * weights.get(1))) * 1000.0) / 1000.0;
    }

    /**
     * Adjusts the weights of the perceptron based on the prediction error.
     *
     * @param i the index of the training example
     * @param target the target output value
     * @param predicted the predicted output value
     */
    @Override
    public void weightCalibration(int i, int target, double predicted) {
        System.out.println("Calibrating weights...");
        double error = target - predicted;
        weights.set(0, weights.get(0) + learningRate * error * biasNode.getValue());
        weights.set(1, weights.get(1) + learningRate * error * inputStorage.get(i).getValue());
    }

    /**
     * Prints the training information after each iteration, including the current weights and values.
     *
     * @param i the index of the training example
     */
    public void printInformation(int i) {
        System.out.println("Weights and values after iteration " + iterationN);
        System.out.println("Number of changed weights: " + changedWeights);
        System.out.println("Current weights and values:");
        System.out.println("+---------+-----------+--------+");
        System.out.printf("| %-7s | %9s | %6s |%n", "Node", "Weight", "Value");
        System.out.println("+---------+-----------+--------+");
        System.out.printf("| %-7s | %9.5f | %6d |%n", "Bias", weights.get(0), biasNode.getValue());
        System.out.printf("| %-7s | %9.5f | %6d |%n", "Input", weights.get(1), inputStorage.get(i).getValue());
        System.out.printf("| %-7s | %9s | %6d |%n", "Output", "N/A", outputStorage.get(i).getValue());
        System.out.println("+---------+-----------+--------+");
    }

    /**
     * Prints the final weights of the perceptron after training.
     */
    public void printFinalWeights() {
        System.out.println("FINAL WEIGHTS:");
        System.out.println("Number of iterations: " + iterationN);
        System.out.println("Number of changed weights: " + changedWeights);
        System.out.println("+---------+-----------+");
        System.out.printf("| %-7s | %9s |%n", "Node", "Weight");
        System.out.println("+---------+-----------+");
        System.out.printf("| %-7s | %9.5f |%n", "Bias", weights.get(0));
        System.out.printf("| %-7s | %9.5f |%n", "Input", weights.get(1));
        System.out.println("+---------+-----------+");
    }

    /**
     * Throws UnsupportedOperationException as this perceptron does not support two input values.
     *
     * @param valueInp1 the first input value
     * @param valueInp2 the second input value
     * @param valueTarget the target output value
     * @throws UnsupportedOperationException as this method is not supported
     */
    @Override
    public void load2(int valueInp1, int valueInp2, int valueTarget) {
        throw new UnsupportedOperationException("Not this perceptron");
    }

    /**
     * Throws UnsupportedOperationException as this perceptron does not support two input values.
     *
     * @param input1 the first input value
     * @param input2 the second input value
     * @return none, as this method is not supported
     * @throws UnsupportedOperationException as this method is not supported
     */
    @Override
    public double predict2(int input1, int input2) {
        throw new UnsupportedOperationException("Not this perceptron");
    }
}
