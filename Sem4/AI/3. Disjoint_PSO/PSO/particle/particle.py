import numpy as np
from ops.pso_ops import add_two_bin

class Particle:
    def __init__(self, dimensions):
        self.position = np.random.randint(2, size=dimensions)
        self.pbest_position = self.position
        self.pbest_value = float('inf')
        self.velocity = np.array([0] * dimensions)

    def __str__(self):
        return "I am at " + str(self.position) + " meu pbest is " + str(self.pbest_position)

    def move(self):
        self.position = add_two_bin(self.position, self.velocity)



