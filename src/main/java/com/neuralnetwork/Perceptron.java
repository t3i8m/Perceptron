package com.neuralnetwork;
import java.util.Random;
import java.util.ArrayList;

/**
 * The Perceptron class is an abstract base class for creating perceptron models.
 * It provides methods to initialize weights, load training data, train the model,
 * and make predictions. Subclasses must implement the abstract methods to provide
 * specific functionality for different types of perceptron models.
 */
public abstract class Perceptron {

    protected Random random = new Random();
    protected ArrayList<Double> weights = new ArrayList<>(); // {bias, input1, input2}
    protected Node biasNode;
    protected double learningRate;
    protected int iterationN = 0;
    protected int changedWeights = 0;

    /**
     * Constructs a Perceptron with a specified learning rate and bias value.
     *
     * @param learningRate the learning rate for the perceptron
     * @param valueBias the value for the bias node
     */
    public Perceptron(double learningRate, int valueBias) {
        this.learningRate = learningRate;
        this.biasNode = new Node(valueBias);
        initialWeights();
    }

    /**
     * Initializes the weights for the perceptron with random values between -1 and 1.
     */
    private void initialWeights() {
        weights.add(2 * random.nextDouble() - 1);
        weights.add(2 * random.nextDouble() - 1);
        weights.add(2 * random.nextDouble() - 1);
    }

    /**
     * Loads a training example with one input value and the target output value.
     *
     * @param valueInp1 the input value
     * @param valueTarget the target output value
     */
    public abstract void load1(int valueInp1, int valueTarget);

    /**
     * Loads a training example with two input values and the target output value.
     *
     * @param valueInp1 the first input value
     * @param valueInp2 the second input value
     * @param valueTarget the target output value
     */
    public abstract void load2(int valueInp1, int valueInp2, int valueTarget);

    /**
     * Trains the perceptron using the loaded training data.
     */
    public abstract void train();

    /**
     * Predicts the output for given input values using the trained perceptron.
     *
     * @param input1 the first input value
     * @param input2 the second input value
     * @return the predicted output value
     */
    public abstract double predict2(int input1, int input2);

    /**
     * Predicts the output for a given input value using the trained perceptron.
     *
     * @param input1 the input value
     * @return the predicted output value
     */
    public abstract double predict1(int input1);

    /**
     * Adjusts the weights of the perceptron based on the prediction error.
     *
     * @param i the index of the training example
     * @param target the target output value
     * @param predicted the predicted output value
     */
    public abstract void weightCalibration(int i, int target, double predicted);

    /**
     * Prints information about the training process for a specific training example.
     *
     * @param i the index of the training example
     */
    public abstract void printInformation(int i);

    /**
     * Prints the final weights of the perceptron after training.
     */
    public abstract void printFinalWeights();
}
