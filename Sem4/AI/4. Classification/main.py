from logistic_regressor.binary_regressor import BinaryRegressor
from logistic_regressor.multinomial_regressor import MultinomialRegressor
from utils.read_data import get_features_from_file, get_result_matrix, get_training_data, get_test_data, get_properties

"""====================PROPS============================"""
props = get_properties("config/config.txt")
num_iter = int(props['num_iter'])
learning_rate = float(props['learning_rate'])
epoch_feedback = int(props['epoch_feedback'])

"""====================PROPS============================"""

"""====================ALL DATA========================="""
all_data_features = get_features_from_file("data/CTG.xls")
all_data_results = get_result_matrix("data/CTG.xls")
"""====================ALL DATA========================="""

"""====================TRAIN DATA FEATURES=============="""
training_data_features = get_training_data(all_data_features)
training_data_results = get_training_data(all_data_results)
"""====================TRAIN DATA FEATURES=============="""

"""====================TEST DATA FEATURES==============="""
test_data_features = get_test_data(all_data_features)
test_data_results = get_test_data(all_data_results)
"""====================TEST DATA FEATURES==============="""

bin_reg = BinaryRegressor(learning_rate, num_iter, epoch_feedback)

multi_reg = MultinomialRegressor(bin_reg)

coeffs = multi_reg.train(training_data_features, training_data_results)

test_acc_lin_reg = str(multi_reg.accuracy(coeffs, test_data_features, test_data_results))
print("===================LIN_REG================")
print("Accuracy on train: " + str(multi_reg.accuracy(coeffs, training_data_features, training_data_results)) + "%")
print("Accuracy on test: " + test_acc_lin_reg + "%")
print("\n")
