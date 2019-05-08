
class Number:

    def __init__(self, num_lst):
        self.num_lst = num_lst

    def add_number(self, num):

        rez_lst = []
        num1 = num.num_lst
        num2 = self.num_lst
        diff_len = len(num1) - len(num2)
        if diff_len > 0:
            num2 = [0]*diff_len + num2
        else:
            num1 = [0]*(-diff_len) + num1

        rem = 0
        for i in range(len(num1) - 1, -1, -1):
            rez = num1[i] + num2[i] + rem
            dig = rez % 16
            rem = rez // 16
            rez_lst.append(dig)

        if rem > 0:
            rez_lst.append(rem)

        return Number(rez_lst[::-1])

    def sub_number(self, num):
        """

        :param num:
        :return:
        """
        if self == num:
            return Number([0])
        rez_lst = []
        num2 = num.num_lst
        num1 = self.num_lst
        diff_len = len(num1) - len(num2)
        if diff_len > 0:
            num2 = [0] * diff_len + num2
        else:
            num1 = [0] * (-diff_len) + num1

        rem = 0
        for i in range(len(num1) - 1, -1, -1):
            rez = num1[i] - (num2[i] + rem)
            if rez < 0:
                rem = 1
            else:
                rem = 0
            if rem != 0:
                rez += 16
            rez_lst.append(rez)

        while rez_lst[len(rez_lst) - 1] == 0:
            rez_lst = rez_lst[:len(rez_lst) -1]

        return Number(rez_lst[::-1])

    def __eq__(self, other):
        return self.num_lst == other.num_lst

    def __ge__(self, other):
        for i in range(len(self.num_lst)):
            if self.num_lst[i] > other.num_lst[i]:
                return True
            else:
                return False
        return True
