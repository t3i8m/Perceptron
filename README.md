
# Implementation and Analysis of a Simple Perceptron

This project demonstrates the implementation of a simple perceptron to solve logical functions (e.g., AND) and perform linear regression tasks (e.g., fitting `y = 2x + 1`). The work evaluates the perceptron's performance by analyzing the impact of initial weights, learning rates, and target value variations.

---

## Overview

The perceptron, inspired by Frank Rosenblatt's 1958 foundational work, is a simple machine learning model used for binary classification and regression tasks. This project explores its ability to adapt to different datasets and parameter configurations.

---

## Objectives

1. **Logical Classification**:
   - Solve the logical AND function using a perceptron with two inputs and a bias node.
2. **Linear Regression**:
   - Train the perceptron to fit the equation `y = 2x + 1`.
3. **Parameter Analysis**:
   - Evaluate the effects of:
     - Initial weights.
     - Learning rates.
     - Adding more target values.

---

## Methodology

### 1. Initialization
- Randomly initialize weights, including the bias.
- Example formula for weighted sum:
  ```python
  def weighted_sum(inputs, weights, bias):
      return sum(i * w for i, w in zip(inputs, weights)) + bias
