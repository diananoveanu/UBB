class Data:

    def __init__(self, input_set, subsets):
        self.set = input_set
        self.subsets = subsets


class Problem:

    def __init__(self, file_name="input.txt"):
        self.data = self.load_data(file_name)

    @staticmethod
    def load_data(file_name):
        with open(file_name, "r") as f:
            input_set = f.readline().split(",")
            lst = []
            for element in input_set:
                lst.append(int(element))

            nr_subsets = int(f.readline())
            subsets = []
            for i in range(nr_subsets):
                line = f.readline().split(",")
                subset = []
                for element in line:
                    subset.append(int(element))
                subsets.append(subset)
        return Data(lst, subsets)
