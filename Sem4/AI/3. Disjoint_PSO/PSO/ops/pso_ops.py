import random


def add_two_bin(position, velocity):
    new_pos = []
    for i in range(len(position)):
        if position[i] != velocity[i]:
            new_pos.append(1)
        else:
            new_pos.append(0)
    return new_pos


def diff_two_bin(position1, position2):
    for i in range(len(position2)):
        position2[i] = 1 - position2[i]
    return add_two_bin(position1, position2)


def multiply_scalar_particle(scalar, velocity):
    scalar = int(scalar)

    for i in range(scalar):
        if random.random() < 0.09:
            velocity.pop(0)
            velocity.append(1)
        else:
            velocity.append(velocity.pop(0))

    return velocity


def fitness_(particle, data):
    particle_pos = particle.position
    list1 = []
    list2 = []
    data_list = data.elems
    for i in range(len(data_list)):
        if particle_pos[i] == 1:
            list1.append(data_list[i])
        else:
            list2.append(data_list[i])
    l1_set = set(list1)

    l2_set = set(list2)
    print(list1)
    print(list2)
    nr_ap = 0
    for set_ in data.subsets:
        subset = set(set_)
        if subset <= l1_set:
            nr_ap += 1
        if subset <= l2_set:
            nr_ap += 1

    return nr_ap
