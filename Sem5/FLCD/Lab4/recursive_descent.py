from config import Config
from production import Production
from state import State


def get_next_prod(prod, prods):
    for i in range(len(prods)):
        if prod == prods[i] and i < len(prods) - 1:
            return prods[i + 1]
    return None


def recursive_descent(grammar, sequence):
    # get config
    # s := q; i := 1; a :=e; B :=S : {initial config}
    config = Config(grammar.get_start_symbol())

    # while (s != t) and (s != e) do
    while config.state != State.FINAL and config.state != State.ERROR:
        # if s = q then
        if config.state == State.NORMAL:
            # if (B=e) and (i = n + 1) then
            if len(config.input_stack) == 0 and config.index == len(sequence):
                # s := t
                config.state = State.FINAL
            elif len(config.input_stack) == 0:
                config.state = State.BACK
            else:
                # if tip(B) = A then
                if config.input_stack[0] in grammar.get_non_terminals():
                    non_term = config.input_stack[0]
                    first_prod = grammar.get_productions_of(non_term)[0]
                    # push (a, A1); {fie A1 -> gamma prima productie a lui A}
                    config.work_stack.append((first_prod.get_left_term(), first_prod.get_right_term()))
                    # push (B, gamma)  pop (B, A);
                    config.input_stack = first_prod.get_right_term() + config.input_stack[1:]
                else:
                    # backtrack if index is max (input stack inca are chestii in el)
                    if config.index == len(sequence):
                        config.state = State.BACK
                    elif config.input_stack[0] == 'e':
                        config.work_stack.append('e')
                        config.input_stack = config.input_stack[1:]
                    # if tip(B = xi) then
                    elif config.input_stack[0] == sequence[config.index]:
                        # i:= i + 1
                        config.index += 1
                        # push (a, a)
                        config.work_stack.append(config.input_stack[0])
                        config.input_stack = config.input_stack[1:]
                    else:
                        config.state = State.BACK
        else:
            if config.state == State.BACK:
                if config.work_stack[-1] in grammar.get_terminals():
                    if config.work_stack[-1] == 'e':
                        config.work_stack.pop(-1)
                    else:
                        config.index -= 1
                        terminal = config.work_stack.pop(-1)
                        config.input_stack = [terminal] + config.input_stack
                else:
                    last_production = config.work_stack[-1]
                    productions = grammar.get_productions_of(last_production[0])
                    productions = [(prod.get_left_term(), prod.get_right_term()) for prod in productions]
                    next_prod = get_next_prod(last_production, productions)
                    if next_prod:
                        config.state = State.NORMAL
                        config.work_stack.pop(-1)
                        config.work_stack.append((next_prod[0], next_prod[1]))
                        config.input_stack = config.input_stack[len(last_production[1]):]
                        config.input_stack = next_prod[1] + config.input_stack
                    elif config.index == 0 and last_production[0] == grammar.get_start_symbol():
                        config.state = State.ERROR
                    else:
                        config.work_stack.pop(-1)
                        if last_production[1] == ['e']:
                            config.input_stack = [last_production[0]] + config.input_stack
                        else:
                            config.input_stack = [last_production[0]] + config.input_stack[
                                                                        len(last_production[1]):]
    prod_rules = []
    if config.state == State.ERROR:
        return False, []
    else:
        for prod in config.work_stack:
            if len(prod) > 1:
                if Production(prod[0], prod[1]) in grammar.get_productions():
                    prod_rules.append(prod)

    return True, prod_rules
