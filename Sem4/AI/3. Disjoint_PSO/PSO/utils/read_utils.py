from data.data import Data

def get_data(in_file):
    with open(in_file, "r") as f:
        line = f.readline().strip().split(" ")
        lst = []
        for element in line:
            lst.append(int(element))
        num_subs = int(f.readline().strip())
        subsets = []
        for i in range(num_subs):
            subset = []
            line = f.readline().strip().split(" ")
            for element in line:
                subset.append(int(element))
            subsets.append(subset)

        return Data(lst, subsets)