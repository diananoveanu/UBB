import numpy as np


def sigmoid(x):
    eps = 1e-9
    sig = 1.0 / (1.0 + np.exp(-x))
    sig = np.minimum(sig, 1 - eps)  # Set upper bound
    sig = np.maximum(sig, eps)
    return sig


def loss(h, y):
    return (-y * np.log(h) - (1 - y) * np.log(1 - h)).mean()


def gradient_descent(features, results, learning_rate, num_iter, epoch_feedback):
    costs = []
    coeffs = np.zeros_like(features[0])

    for i in range(num_iter):
        predictions = np.dot(features, coeffs)
        sigomoids = sigmoid(predictions)
        cs = loss(sigomoids, results)
        costs.append(cs)

        gradient = np.dot(features.T, (sigomoids - results)) / results.size
        coeffs = coeffs - learning_rate * gradient

        if i % epoch_feedback == 0:
            print("Cost: " + str(cs) + " accuracy " + str(accuracy(coeffs, features, results)))

    return [costs, coeffs]


def accuracy(coeffs, features, results):
    num_corr = 0
    for i in range(len(results)):
        result = np.dot(features[i], coeffs)
        sigm = sigmoid(result)
        label = 0.0
        if sigm > .5:
            label = 1.0
        if label == results[i]:
            num_corr += 1

    return num_corr / len(results) * 100
