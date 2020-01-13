class Grammar:
    def __init__(self, start_symbol, terminals, non_terminals, productions):
        self.__start_symbol = start_symbol
        self.__terminals = terminals
        self.__non_terminals = non_terminals
        self.__productions = productions

    def get_start_symbol(self):
        return self.__start_symbol

    def get_terminals(self):
        return self.__terminals

    def get_non_terminals(self):
        return self.__non_terminals

    def get_productions(self):
        return self.__productions

    def get_productions_of(self, symbol):
        return list(filter(lambda x: x.get_left_term() == symbol, self.__productions))

    def set_start_symbol(self, new_sym):
        self.__start_symbol = new_sym

    def set_terminals(self, new_terminals):
        self.__terminals = new_terminals

    def set_non_terminals(self, new_non_term):
        self.__non_terminals = new_non_term

    def set_productions(self, new_productions):
        self.__productions = new_productions

    def is_terminal(self, symbol):
        return symbol in self.__terminals

    def is_non_terminal(self, symbol):
        return symbol in self.__non_terminals

    def __str__(self):

        string_prod = ""
        for prod in self.__productions:
            string_prod += str(prod) + "\n"
        string_term = ""
        for term in self.__terminals:
            string_term += term + " "
        string_non_term = ""
        for non_term in self.__non_terminals:
            string_non_term += non_term + " "

        return "Start symbol: " + self.__start_symbol + "\n" \
                                                        "Terminals: " + string_term + "\n" \
                                                                                      "Non-terminals: " + string_non_term + "\n" \
                                                                                                                            "Productions:\n" + string_prod
