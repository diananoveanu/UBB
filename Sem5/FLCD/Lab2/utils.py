from finite_automata import FiniteAutomata


class Neighbour:

    def __init__(self, node, char):
        self.__node = node
        self.__char = char

    def get_node(self):
        return self.__node

    def get_char(self):
        return self.__char


def read_fa_from_file(path):
    """
    Function which reads a finite automata from file
    :param path: path to the file where de FA is stored
    :return: the FA contained in the file
    """

    with open(path, "r") as f:
        line = f.readline().strip()
        transitions = {}
        states = set()
        final_states = set()
        start_state = None
        alphabet = line.split(" ")
        while True:
            line = f.read().strip()
            trans = line.split(" ")
            if len(trans) == 1:
                start_state = trans[0]
                break
            elem = transitions.get(trans[0], [])
            if trans[3] == 1:
                final_states.add(trans[0])
            states.add(trans[0])
            if not elem:
                transitions[trans[0]] = [Neighbour(trans[1], trans[2])]
            else:
                transitions[trans[0]].append(Neighbour(trans[1], trans[2]))

        return FiniteAutomata(alphabet, states, transitions, start_state, final_states)
