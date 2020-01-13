import os
from utils import read_grammar_from_file
from recursive_descent import recursive_descent

GRAMMAR_PATH = os.path.join(os.getcwd(), "grammar.txt")
PIF_PATH = os.path.join(os.getcwd(), "pif.in")

if __name__ == "__main__":
    pif = open(PIF_PATH, 'r')
    pif_input = pif.readline().strip().split(" ")

    grammar = read_grammar_from_file(GRAMMAR_PATH)

    good, prods = recursive_descent(grammar, pif_input)

    print(grammar)
    print(pif_input)
    print(good, prods)
