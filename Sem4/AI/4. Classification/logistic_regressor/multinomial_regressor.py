from gradient_descent.grad_descent import sigmoid
import numpy as np
import copy
import matplotlib.pyplot as plt


def class_set(result):
    return set(result)


def modify_result(results_copy, cl):
    for i in range(0, len(results_copy)):
        if results_copy[i] != cl:
            results_copy[i] = 0
        else:
            results_copy[i] = 1
    return results_copy


class MultinomialRegressor:

    def __init__(self, regressor):
        self.__bin_regressor = regressor

    def train(self, features, result, pop_size=None, num_iter=None, interval=None, num_sel=None, prob=None, eps=None):
        classes = class_set(result)
        coeff_matrix = []
        for cl in classes:
            print("Training for class " + str(cl))
            results_copy = copy.deepcopy(result)
            results_copy = modify_result(results_copy, cl)
            coeffs = \
            self.__bin_regressor.train(features, results_copy, pop_size, num_iter, interval, num_sel, prob, eps)[1]
            coeff_matrix.append(coeffs)
        print(coeff_matrix)
        return np.asarray(coeff_matrix)

    def predict(self, features, result):
        predict_matrix = []
        for cl in set(result):
            results_copy = copy.deepcopy(result)
            results_copy = modify_result(results_copy, cl)
            predict_matrix.append(sigmoid(np.dot(features, results_copy)))
        return np.asarray(predict_matrix)

    def __get_label(self, coeffs, feature, results):
        cls_set = set(results)
        cls_set = list(cls_set)

        label = -1
        best_sgm = -1
        for i in range(len(cls_set)):
            result = np.dot(feature, coeffs[i])
            sigm = sigmoid(result)
            if sigm > best_sgm:
                best_sgm = sigm
                label = cls_set[i]

        return label

    def accuracy(self, coeffs, features, results):
        num_corr = 0
        for i in range(len(results)):
            label = self.__get_label(coeffs, features[i], results)
            # print("Label: " + str(label) + " Result: " + str(results[i]))
            if label == results[i]:
                num_corr += 1

        return num_corr / len(results) * 100

    def plot(self, coeffs, X, Y):
        cls_set = set(Y)
        XX = []
        YY = []
        colors = ['red', 'green', 'blue']
        for cl in cls_set:
            xcl = []
            ycl = []
            for i in range(len(X)):
                if Y[i] == cl:
                    xcl.append(X[i][1])
                    ycl.append(X[i][2])
            XX.append(xcl)
            YY.append(ycl)

        for set_classes in range(len(XX)):
            X_set = np.asarray(XX[set_classes])
            Y_set = np.asarray(YY[set_classes])
            plt.scatter(X_set, Y_set, color=colors[set_classes])

        X = np.asarray(X)
        minim = X.T[1].min()
        maxim = X.T[1].max()
        x_values = np.linspace(minim, maxim, 50)
        for i in range(len(coeffs)):
            y_values = -(coeffs[i][0] + coeffs[i][1] * x_values) / coeffs[i][2]
            plt.plot(x_values, y_values, c='black')

        plt.show()
