package com.neuralnetwork;
import java.util.ArrayList;

/**
 * The PerceptronLogical class is a concrete implementation of a perceptron for logical operations (e.g., AND, OR).
 * It provides methods for training the perceptron, making predictions, and calibrating weights.
 */
public class PerceptronLogical extends Perceptron implements PerceptronInterface {
    private final int ACTIVATION_FUNCTION = 0;

    private ArrayList<Node> input1Storage = new ArrayList<>();
    private ArrayList<Node> input2Storage = new ArrayList<>();
    private ArrayList<Node> outputStorage = new ArrayList<>();

    /**
     * Constructs a PerceptronLogical with the specified learning rate and bias value.
     *
     * @param learningRate the learning rate for the perceptron
     * @param valueBias the value for the bias node
     */
    public PerceptronLogical(double learningRate, int valueBias) {
        super(learningRate, valueBias);
    }

    /**
     * Loads a training example with two input values and the target output value.
     *
     * @param valueInp1 the first input value
     * @param valueInp2 the second input value
     * @param valueTarget the target output value
     */
    @Override
    public void load2(int valueInp1, int valueInp2, int valueTarget) {
        input1Storage.add(new Node(valueInp1));
        input2Storage.add(new Node(valueInp2));
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
                    (input1Storage.get(i).getValue() * weights.get(1)) +
                    (input2Storage.get(i).getValue() * weights.get(2))) * 1000.0) / 1000.0;

            System.out.println("\nWeighted sum: " + weightedSum);
            int predicted = activationFunction(weightedSum);
            System.out.println("Correct prediction is: " + outputStorage.get(i).getValue());
            System.out.println("Predicted is: " + predicted);

            if (predicted == outputStorage.get(i).getValue()) {
                System.out.println("Correct prediction");
                printInformation(i);
                i += 1;
            } else {
                changedWeights += 1;
                System.out.println("\n !!--Incorrect prediction--!!");
                weightCalibration(i, outputStorage.get(i).getValue(), (double) predicted);
                printInformation(i);
                i = 0;
            }
        }

        System.out.println("\n !!--Perceptron has been trained--!!\n");
        printFinalWeights();
    }

    /**
     * Applies the activation function to the weighted sum to produce the binary output.
     *
     * @param weightedSum the weighted sum of inputs and weights
     * @return the binary output (0 or 1)
     */
    public int activationFunction(double weightedSum) {
        if (weightedSum < ACTIVATION_FUNCTION) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * Predicts the output for given input values using the trained perceptron.
     *
     * @param input1 the first input value
     * @param input2 the second input value
     * @return the predicted output value
     */
    @Override
    public double predict2(int input1, int input2) {
        double weightedSum = Math.round(((biasNode.getValue() * weights.get(0)) +
                (input1 * weights.get(1)) +
                (input2 * weights.get(2))) * 1000.0) / 1000.0;
        return (double) activationFunction(weightedSum);
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
        weights.set(1, weights.get(1) + learningRate * error * input1Storage.get(i).getValue());
        weights.set(2, weights.get(2) + learningRate * error * input2Storage.get(i).getValue());
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
        System.out.printf("| %-7s | %9.5f | %6d |%n", "Input1", weights.get(1), input1Storage.get(i).getValue());
        System.out.printf("| %-7s | %9.5f | %6d |%n", "Input2", weights.get(2), input2Storage.get(i).getValue());
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
        System.out.printf("| %-7s | %9.5f |%n", "Input1", weights.get(1));
        System.out.printf("| %-7s | %9.5f |%n", "Input2", weights.get(2));
        System.out.println("+---------+-----------+");
    }

    /**
     * Throws UnsupportedOperationException as this perceptron does not support one input value.
     *
     * @param valueInp1 the input value
     * @param valueTarget the target output value
     * @throws UnsupportedOperationException as this method is not supported
     */
    @Override
    public void load1(int valueInp1, int valueTarget) {
        throw new UnsupportedOperationException("Not this perceptron");
    }

    /**
     * Throws UnsupportedOperationException as this perceptron does not support one input value.
     *
     * @param input1 the input value
     * @return none, as this method is not supported
     * @throws UnsupportedOperationException as this method is not supported
     */
    @Override
    public double predict1(int input1) {
        throw new UnsupportedOperationException("Not this perceptron");
    }
}
