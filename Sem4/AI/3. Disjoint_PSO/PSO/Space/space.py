import random
import numpy as np

from ops.pso_ops import fitness_, multiply_scalar_particle, diff_two_bin


class Space:
    def __init__(self, n_particles, data, W, c1, c2):
        self.W = W
        self.c1 = c1
        self.c2 = c2
        self.n_particles = n_particles
        self.particles = []
        self.gbest_value = float('inf')
        self.gbest_position = np.random.randint(2, size=len(data.elems))
        self.data = data

    def print_particles(self):
        for particle in self.particles:
            print(particle)

    def fitness(self, particle):
        return fitness_(particle, self.data)

    def set_pbest(self):
        for particle in self.particles:
            fitness_cadidate = self.fitness(particle)
            if particle.pbest_value > fitness_cadidate:
                particle.pbest_value = fitness_cadidate
                particle.pbest_position = particle.position

    def set_gbest(self):
        for particle in self.particles:
            best_fitness_cadidate = self.fitness(particle)
            if self.gbest_value > best_fitness_cadidate:
                self.gbest_value = best_fitness_cadidate
                self.gbest_position = particle.position

    def move_particles(self):
        for particle in self.particles:

            new_velocity = (multiply_scalar_particle(self.W, particle.velocity)) + multiply_scalar_particle((self.c1 * random.random()), (
                    diff_two_bin(particle.pbest_position, particle.position))) + \
                           multiply_scalar_particle((random.random() * self.c2), (diff_two_bin(self.gbest_position, particle.position)))
            particle.velocity = new_velocity
            particle.move()
