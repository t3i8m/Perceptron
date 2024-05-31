package com.neuralnetwork;
/**
 * The Node class represents a simple node with a value and a weight.
 * It provides methods to set and get the weight, and to get the value of the node.
 */
public class Node {

    private int value;
    private double weight;

    /**
     * Constructs a Node with a specified value.
     *
     * @param value the value of the node
     */
    public Node(int value) {
        this.value = value;
    }

    /**
     * Sets the weight of the node.
     *
     * @param weight the weight to be assigned to the node
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Returns the weight of the node.
     *
     * @return the weight of the node
     */
    public double getWeight() {
        return this.weight;
    }

    /**
     * Returns the value of the node.
     *
     * @return the value of the node
     */
    public int getValue() {
        return this.value;
    }
}
