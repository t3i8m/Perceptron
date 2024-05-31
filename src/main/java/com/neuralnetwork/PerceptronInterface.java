package com.neuralnetwork;
/**
 * The PerceptronInterface provides methods for training a perceptron, making predictions,
 * loading training data, and calibrating weights. Implementing classes should define
 * specific behavior for different types of perceptrons.
 */
public interface PerceptronInterface {
    
    /**
     * Trains the perceptron using the loaded training data.
     */
    void train();

    /**
     * Predicts the output for given input values using the trained perceptron.
     *
     * @param input1 the first input value
     * @param input2 the second input value
     * @return the predicted output value
     */
    double predict2(int input1, int input2);

    /**
     * Predicts the output for a given input value using the trained perceptron.
     *
     * @param input1 the input value
     * @return the predicted output value
     */
    double predict1(int input1);

    /**
     * Loads a training example with two input values and the target output value.
     *
     * @param valueInp1 the first input value
     * @param valueInp2 the second input value
     * @param valueTarget the target output value
     */
    void load2(int valueInp1, int valueInp2, int valueTarget);

    /**
     * Loads a training example with one input value and the target output value.
     *
     * @param valueInp1 the input value
     * @param valueTarget the target output value
     */
    void load1(int valueInp1, int valueTarget);

    /**
     * Adjusts the weights of the perceptron based on the prediction error.
     *
     * @param i the index of the training example
     * @param target the target output value
     * @param predicted the predicted output value
     */
    void weightCalibration(int i, int target, double predicted);
}
