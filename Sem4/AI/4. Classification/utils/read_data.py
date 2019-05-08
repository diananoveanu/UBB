import pandas as pd
import numpy as np
import xlrd
from sklearn.preprocessing import normalize


def normalize_data(X):
    trans = X.T
    for i in range(len(trans)):
        trans[i] = normalize(trans[i].reshape(1, -1), norm="l2")

    return trans.T


def get_features_from_file(in_file):
    """
    Function to retrieve data from file
    :param in_file: input file
    :return: a matrix of features
    """
    df = pd.read_excel(in_file, sheet_name="Raw Data")
    LB = np.asarray(df['LB'])
    AC = np.asarray(df['AC'])
    FM = np.asarray(df['FM'])
    UC = np.asarray(df['UC'])
    DL = np.asarray(df['DL'])
    DS = np.asarray(df['DS'])
    DP = np.asarray(df['DP'])
    ASTV = np.asarray(df['ASTV'])
    MSTV = np.asarray(df['MSTV'])
    ALTV = np.asarray(df['LB'])
    MLTV = np.asarray(df['MLTV'])
    Width = np.asarray(df['Width'])
    Min = np.asarray(df['Min'])
    Max = np.asarray(df['Max'])
    Nmax = np.asarray(df['Nmax'])
    Nzeros = np.asarray(df['Nzeros'])
    Mode = np.asarray(df['Mode'])
    Mean = np.asarray(df['Mean'])
    Median = np.asarray(df['Median'])
    Variance = np.asarray(df['Variance'])
    Tendency = np.asarray(df['Tendency'])
    matrix = [
        [1, LB[i], AC[i], FM[i], UC[i], DL[i], DS[i], DP[i], ASTV[i], MSTV[i], ALTV[i], MLTV[i], Width[i], Min[i],
         Max[i], Nmax[i]
            , Nzeros[i], Mode[i], Mean[i], Median[i], Variance[i], Tendency[i]] for i in range(1, len(LB) - 3)]
    data = normalize_data(np.asarray(matrix))
    return data


def get_result_matrix(in_file):
    """
    Function to retrieve the results from the file
    :param in_file: in file
    :return: a matrix (1, n)
    """
    df = pd.read_excel(in_file, sheet_name="Raw Data")
    NSP = np.asarray(df['NSP'])

    return np.asarray([NSP[i] for i in range(1, len(NSP) - 3)])


def get_training_data(data):
    """

    :param data:
    :return:
    """
    num = len(data) * 0.8

    return data[:int(num)]


def get_test_data(data):
    """

    :param data:
    :return:
    """
    training = get_training_data(data)
    return data[len(training):]


def get_properties(prop_file):
    """
    Function to get the starting properties
    :param prop_file: path to file
    :return: a dictionary containing prop => value
    """
    props = {}
    with open(prop_file, "r") as f:
        num_props = int(f.readline().strip())
        for prop in range(num_props):
            line = f.readline().split(":")
            props[line[0]] = line[1]
    return props


"================================================="


def get_features_from_teacher(in_file):
    """
    Function to get features from teacher file
    :param in_file: in_file
    :return:
    """

    with open(in_file, "r") as f:

        n_features = int(f.readline().strip())
        num_rows = int(f.readline().strip())
        matrix = []
        for i in range(num_rows):
            line = f.readline().split(",")
            del line[len(line) - 1]
            matrix_row = [1]
            for row in line:
                matrix_row.append(float(row))
            matrix.append(matrix_row)

        data = normalize_data(np.asarray(matrix))

        return data


def get_result_matrix_from_teacher(in_file):
    """
     In file
     :param in_file:
     :return:
    """

    with open(in_file, "r") as f:
        n_features = int(f.readline().strip())
        num_rows = int(f.readline().strip())
        matrix = []
        for i in range(num_rows):
            line = f.readline().split(",")
            matrix.append(float(line[len(line) - 1]))

        return np.asarray(matrix)


def write_to_file(file, content):
    """

    :param content:
    :param file:
    :return:
    """
    fd = open(file, "w")
    fd.write(content + "\n")
    fd.close()


def get_data(fileName):
    file = open(fileName, "r")
    X = []
    Y = []

    file.readline()  # eliminate column name
    line = file.readline()

    while line:
        data = line.split("\n")[0].split(",")
        if data[0] == '"Male"':
            data[0] = 1
        else:
            data[0] = 0

        for i in range(1, len(data)):
            data[i] = float(data[i])

        X.append([1] + data[1:])
        Y.append(data[0])

        line = file.readline()

    file.close()
    return np.asarray(X), np.asarray(Y)
