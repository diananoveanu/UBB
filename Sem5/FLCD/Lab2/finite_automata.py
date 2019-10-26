class FiniteAutomata:
    """
    CLass for representing a finite automaa
    """

    def __init__(self, alphabet, states, transitions, start_state, final_states):
        """
        Constructor for FiniteAutomata class
        :param alphabet: alphabet accepted by the FA
        :param states: set with all states of the FA
        :param transitions: list with transitions (q1 -> q2, s) meaning q1 goes into q2 with s
        :param start_state: start state of the FA
        :param final_states: set containing all final states of the FA
        """
        self.__alphabet = alphabet
        self.__states = states
        self.__transitions = transitions
        self.__start_state = start_state
        self.__final_states = final_states

    """
    Getters
    """
    def get_alphabet(self):
        return self.__alphabet

    def get_states(self):
        return self.__states

    def get_final_states(self):
        return self.__final_states

    def get_transitions(self):
        return self.__transitions

    def get_start_state(self):
        return self.__start_state
