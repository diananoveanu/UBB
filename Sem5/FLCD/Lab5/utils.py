from grammar import Grammar
from production import Production


def read_grammar_from_file(in_file):
    with open(in_file, "r") as f_d:
        line = f_d.readline().strip()
        productions = []
        terminals = line.split(' ')
        line = f_d.readline().strip()
        non_terminals = line.split(' ')
        line = f_d.readline().strip()
        start_sym = line
        line = f_d.readline().strip()
        while line != "":
            #dc
            segments = line.split('->')
            productions_on_line = segments[1].split('|')
            for prod in productions_on_line:
                production = Production(segments[0].replace(" ", ""), prod.split())
                productions.append(production)
            line = f_d.readline().strip()
        return Grammar(start_sym, terminals, non_terminals, productions)


