from gradient_descent.grad_descent import gradient_descent, sigmoid, accuracy
import numpy as np
import matplotlib.pyplot as plt

class BinaryRegressor:

    def __init__(self, learning_rate, num_iter, epoch_feedback):
        self.__learning_rate = learning_rate
        self.__num_iter = num_iter
        self.__feedback = epoch_feedback

    def train(self, features, result,  pop_size=None, num_iter=None, interval=None, num_sel=None, prob=None, eps=None):
        return gradient_descent(features, result, self.__learning_rate, self.__num_iter,self.__feedback)

    def predict(self, features, result):
        return sigmoid(np.dot(features, result))

    def accuracy(self, coeffs, features, result):
        return accuracy(coeffs, features, result)

    def plot(self, coeffs, X, Y):
        x = [X[i][1] for i in range(len(X))]
        y = [X[i][2] for i in range(len(X))]

        x = np.asarray(x)
        y = np.asarray(y)
        colors = ['red', 'blue']

        for i in range(len(x)):
            if Y[i] == 0.0:
                clr = colors[0]
            else:
                clr = colors[1]
            plt.scatter(x[i], y[i], color=clr)

        X = np.asarray(X)
        minim = X.T[1].min()
        maxim = X.T[1].max()
        x_values = np.linspace(minim, maxim, 50)
        y_values = -(coeffs[0] + coeffs[1] * x_values) / coeffs[2]
        plt.plot(x_values, y_values, c='black')
        plt.show()
