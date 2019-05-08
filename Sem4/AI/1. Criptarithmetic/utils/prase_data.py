from data_representation.data import Data


def get_data(in_file):
    """
    Function to retrieve data from file
    :param in_file: input_file
    :return: a Data object containing the list of words and the result word
    """

    with open(in_file, "r") as f:

        word_lst = []
        line = f.readline().strip()

        word_lst.append(line[:len(line) - 1].strip())
        op_type = line[-1]
        while True:
            line = f.readline().strip()
            if line == "":
                break
            word_lst.append(line)

        result = word_lst[-1]
        return Data(word_lst[:len(word_lst) - 1], result, op_type)
