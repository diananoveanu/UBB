from Utils import replace_worst, get_best_from_pop
from Individual import generate_random_individual
from Population import Population


class Algorithm:
    def __init__(self, problem, file_name="data.txt"):
        with open(file_name, "r") as f:
            self.pop_size = int(f.readline())
            self.iterations = int(f.readline())
            self.cross_probability = float(f.readline())
            self.mutation_probability = float(f.readline())
            self.num_selected = int(f.readline())
        pop_lst = []
        for i in range(self.pop_size):
            pop_lst.append(generate_random_individual(len(problem.data.set)))
        self.population = Population(self.pop_size, pop_lst)
        self.problem = problem
        self.data = problem.data

    def iteration(self, k, cross_prob, mutate_prob):
        mother = self.population.selection(k)
        father = self.population.selection(k)
        offspring = mother.crossover(mother, father, cross_prob)
        offspring.mutate(mutate_prob)
        offspring.fitness = offspring.calc_fitness(self.problem)
        self.population = replace_worst(self.population, offspring)

    def run(self):
        self.population.evaluate(self.problem)
        best = get_best_from_pop(self.population)
        for i in range(self.iterations):
            self.iteration(self.num_selected, self.cross_probability, self.mutation_probability)
            iter_best = get_best_from_pop(self.population)
            if iter_best.fitness < best.fitness:
                best = iter_best
        return best

    def run_plot(self):
        best_fitness = []
        self.population.evaluate(self.problem)
        best = get_best_from_pop(self.population)
        for i in range(self.iterations):
            self.iteration(self.num_selected, self.cross_probability, self.mutation_probability)
            iter_best = get_best_from_pop(self.population)
            best_fitness.append(iter_best.fitness)
            if iter_best.fitness < best.fitness:
                best = iter_best
        return best_fitness
