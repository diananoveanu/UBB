from Space.space import Space
from ops.pso_ops import fitness_
from particle.particle import Particle
from utils.read_utils import get_data

data = get_data("data/in_file")

W = 0.5
c1 = 0.8
c2 = 0.9
particles = 10
dimensions = len(data.elems)
n_iterations = 10000

search_space = Space(particles, data, W, c1, c2)
particles_vector = [Particle(dimensions) for _ in range(search_space.n_particles)]
search_space.particles = particles_vector
search_space.print_particles()
search_space.print_particles()

iteration = 0
while iteration < n_iterations:
    search_space.set_pbest()
    search_space.set_gbest()

    best_particle = Particle(dimensions)
    best_particle.position = search_space.gbest_position
    if fitness_(best_particle, data) == 0:
        break

    search_space.move_particles()
    iteration += 1

print("The best solution is: ", search_space.gbest_position, " in n_iterations: ", iteration)