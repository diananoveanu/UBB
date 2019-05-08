import os


def set_difference(set_a, set_b):
    a = set(set_a)
    b = set(set_b)
    c = a & b
    return len(list(a - c))


def check_partition(set_a, set_b, set_c):
    a = set(set_a)
    b = set(set_b)
    c = set(set_c)
    return a | b == c and a & b == set()


def partition_distance(set_a, set_b, set_c):
    a = set(set_a)
    b = set(set_b)
    c = set(set_c)

    return max(len((a | b) - c), len(c - (a | b)))


def num_of_subsets_included(set_a, set_b, subs_lst):
    a = set(set_a)
    b = set(set_b)
    num_ap = 0
    for subset in subs_lst:
        c = set(subset)

        if c <= a or c <= b:
            num_ap += 1

    return num_ap


def random_num():
    return int.from_bytes(os.urandom(8), byteorder='big') / ((1 << 64) - 1)


def get_same_pos(set_a, set_b):
    nr_ap = 0
    for i in range(len(set_a)):
        if set_a[i]*set_b[i] != 0:
            nr_ap += 1
    return nr_ap


def get_best(sample):
    best_fit = sample[0].fitness
    best_ind = sample[0]
    for i in range(1, len(sample)):
        if sample[i].fitness < best_fit:
            best_fit = sample[i].fitness
            best_ind = sample[i]
    return best_ind


def get_best_from_pop(population):
    best_fit = population.pop_list[0].fitness
    best_ind = population.pop_list[0]
    for i in range(1, len(population.pop_list)):
        if population.pop_list[i].fitness < best_fit:
            best_fit = population.pop_list[i].fitness
            best_ind = population.pop_list[i]
    return best_ind


def get_worst_from_pop(sample):
    worst_fit = sample.pop_list[0].fitness
    worst_ind = sample.pop_list[0]
    for i in range(1, len(sample.pop_list)):
        if sample.pop_list[i].fitness > worst_fit:
            worst_fit = sample.pop_list[i].fitness
            worst_ind = sample.pop_list[i]
    return worst_ind


def get_worst(sample):
    worst_fit = sample[0].fitness
    worst_ind = sample[0]
    for i in range(1, len(sample)):
        if sample[i].fitness > worst_fit:
            worst_fit = sample[i].fitness
            worst_ind = sample[i]
    return worst_ind


def replace_worst(population, individual):
    worst = get_worst_from_pop(population)

    if individual.fitness > worst.fitness:
        return population

    for i in range(0, len(population.pop_list)):
        if population.pop_list[i].genotype.first_list == worst.genotype.first_list and \
                population.pop_list[i].genotype.second_list == worst.genotype.second_list and \
                population.pop_list[i].fitness == worst.fitness:
            population.pop_list[i] = individual
            break
    return population


def print_sol(individual, initial_set):
    first_set = individual.genotype.first_lst
    second_set = individual.genotype.second_lst

    first_list = []
    second_list = []
    for i in range(len(first_set)):
        if first_set[i] == 1:
            first_list.append(initial_set[i])
        if second_set[i] == 1:
            second_list.append(initial_set[i])

    print("Set 1: ")
    print(first_list)

    print("Set 2: ")
    print(second_list)
