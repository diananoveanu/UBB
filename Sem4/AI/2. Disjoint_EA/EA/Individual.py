from Utils import partition_distance, num_of_subsets_included, get_same_pos, random_num


class Genotype:

    def __init__(self, first_subset, second_subset):
        self.first_list = first_subset
        self.second_list = second_subset


class Individual:

    def __init__(self, size, genotype):
        self.size = size
        self.genotype = genotype
        self.fitness = None

    def calc_fitness(self, problem):
        first_list = self.genotype.first_list
        second_list = self.genotype.second_list

        first_partition = []
        second_partition = []
        for i in range(len(first_list)):
            if first_list[i] == 1:
                first_partition.append(problem.data.set[i])
        for i in range(len(second_list)):
            if second_list[i] == 1:
                second_partition.append(problem.data.set[i])

        return partition_distance(first_partition, second_partition, problem.data.set) + num_of_subsets_included(
            first_partition,
            second_partition,
            problem.data.subsets) + get_same_pos(first_list, second_list)

    def mutate(self, probability):
        first_list = self.genotype.first_list
        second_list = self.genotype.second_list

        for i in range(0, len(first_list)):
            if random_num() < probability:
                first_list[i] = 1 - first_list[i]

        for i in range(0, len(second_list)):
            if random_num() < probability:
                second_list[i] = 1 - second_list[i]
        self.genotype.first_lst = first_list
        self.genotype.second_lst = second_list

    def crossover(self, mother, father, probability):
        off_first_list = []
        off_second_list = []

        for i in range(len(mother.genotype.first_list)):
            if random_num() < probability:
                off_first_list.append(mother.genotype.first_list[i])
            else:
                off_first_list.append(father.genotype.first_list[i])

        for i in range(len(mother.genotype.first_list)):
            if random_num() < probability:
                off_second_list.append(mother.genotype.second_list[i])
            else:
                off_second_list.append(father.genotype.second_list[i])

        return Individual(len(off_first_list), Genotype(off_first_list, off_second_list))


def generate_random_individual(size):
    first_list = []
    second_list = []
    for i in range(size):
        if random_num() < 0.5:
            first_list.append(1)
        else:
            first_list.append(0)
    for i in range(size):
        if random_num() < 0.5:
            second_list.append(1)
        else:
            second_list.append(0)
    return Individual(size, Genotype(first_list, second_list))
