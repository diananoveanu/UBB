from state import State


class Config:
    def __init__(self, start_sym):
        self.state = State.NORMAL
        self.index = 0
        self.work_stack = []
        self.input_stack = [start_sym]

    def __str__(self):
        w_stack_str = ""
        for s in self.work_stack:
            w_stack_str += str(s) + " "
        in_stack_str = ""
        for s in self.input_stack:
            in_stack_str += str(s) + " "

        return "State: " + self.state + "\n" \
                                        "Index: " + str(self.index) + "\n" \
                                                                      "Work Stack: " + w_stack_str + "\n" \
                                                                                                     "Input Stack: " + in_stack_str
