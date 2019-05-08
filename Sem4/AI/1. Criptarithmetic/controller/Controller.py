from utils.number import Number
class Controller:

    def __init__(self):
        self.name = "contoller"

    def solve_gbfs(self, distinct_letters, data):
        queue = []
        starting_node = []
        queue.append(starting_node)

        while len(queue) > 0:
            top = queue.pop()
            if len(top) > len(distinct_letters):
                break

            if len(top) == len(distinct_letters) and self.solution(top, distinct_letters, data) is True:
                return self.construct_solution(distinct_letters, top)

            children = self.getChildren(top, distinct_letters)
            if children:
                queue = queue + children

    def solve_dfs(self, distinct_letters, data):
        stack = []
        starting_node = []
        stack.append(starting_node)

        while len(stack) > 0:
            top = stack.pop(0)
            if len(top) > len(distinct_letters):
                continue

            if len(top) == len(distinct_letters) and self.solution(top, distinct_letters, data) is True:
                return self.construct_solution(distinct_letters, top)

            children = self.getChildren(top, distinct_letters)
            if children:
                stack = stack + children

    def getChildren(self, top, dist_letters):
        if len(top) >= len(dist_letters):
            return []

        if not top:
            return [[i] for i in range(0, 16)]

        children = []
        for i in range(16):
            if i not in top:
                child = top + [i]
                children.append(child)
        return children

    def solution(self, top, distinct_letters, data):
        mapping = {}
        for i in range(len(top)):
            mapping[distinct_letters[i]] = top[i]
        num_lst = []
        for word in data.word_lst:
            dig_lst = []
            if mapping[word[0]] == 0:
                return False
            for letter in word:
                dig_lst.append(mapping[letter])
            num_lst.append(Number(dig_lst))

        dig_lst = []
        for letter in data.result:
            dig_lst.append(mapping[letter])
        num_lst.append(Number(dig_lst))

        rez = num_lst[0]

        for i in range(1, len(num_lst) - 1):
            if data.op_type == "+":
                rez = rez.add_number(num_lst[i])
            else:
                if not rez >= num_lst[i]:
                    return False
                rez = rez.sub_number(num_lst[i])

        return rez == num_lst[-1]

    def construct_solution(self, distinct_letters, top):
        mapping = {}
        for i in range(len(top)):
            mapping[distinct_letters[i]] = top[i]
        return mapping