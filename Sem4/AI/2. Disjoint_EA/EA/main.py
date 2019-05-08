from Algorithm import Algorithm
from Problem import Problem
from Utils import print_sol
import matplotlib.pyplot
import statistics

class Application:
    def __init__(self):
        self.problem = Problem()
        self.algorithm = Algorithm(self.problem)

    def run(self):
        print_sol(self.algorithm.run(), self.problem.data.set)

    def run_plot(self):
        results = list(map(lambda x: x, self.algorithm.run_plot()))
        matplotlib.pyplot.plot(range(1, len(results) + 1), results)
        matplotlib.pyplot.show()

    def run_statistics(self):
        results = []
        for _ in range(30):
            algorithm = Algorithm(self.problem)
            results.append(algorithm.run().fitness)
        print("Average fitness: {}".format(statistics.mean(results)))
        print("Standard deviation of fitness: {}".format(statistics.stdev(results)))

if __name__ == '__main__':
    app = Application()
    app.run()
    app.run_plot()
