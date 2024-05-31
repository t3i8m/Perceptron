package com.neuralnetwork;
/**
 * The PerceptronFactory class is responsible for creating instances of different types of perceptrons.
 * It provides a method to get a perceptron instance based on the specified type.
 */
public class PerceptronFactory {

    /**
     * Creates and returns an instance of a perceptron based on the specified type.
     *
     * @param type the type of perceptron to create ("Logical" or "Linear")
     * @param learningRate the learning rate for the perceptron
     * @param valueBias the value for the bias node
     * @return a PerceptronInterface implementation based on the specified type, or null if the type is unknown
     * @throws IllegalArgumentException if the specified type is null or empty
     */
    public PerceptronInterface getPerceptron(String type, double learningRate, int valueBias) {
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("Type cannot be null or empty");
        }

        if (type.equals("Logical")) {
            return new PerceptronLogical(learningRate, valueBias);
        } else if (type.equals("Linear")) {
            return new PerceptronLinear(learningRate, valueBias);
        } else {
            return null;
        }
    }
}
