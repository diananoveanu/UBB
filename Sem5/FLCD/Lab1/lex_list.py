class Pair:
    def __init__(self, key, value):
        self.__key = key
        self.__value = value

    def get_key(self):
        return self.__key

    def get_value(self):
        return self.__value


class LexicographicallyList(object):

    def __init__(self):
        self.__lst = []

    def size(self):
        return len(self.__lst)

    def add(self, key, elem):
        if self.search(key) == -1:
            pair = Pair(key, elem)
            poz = 0
            if self.size() == 0:
                self.__lst.append(pair)
            else:
                while poz < self.size() and self.__lst[poz].get_key() < pair.get_key():
                    poz += 1

                self.__lst.insert(poz, pair)

    def search(self, key):
        l = 0
        r = self.size() - 1
        while l <= r:

            mid = l + (r - l) // 2

            if self.__lst[mid].get_key() == key:
                return self.__lst[mid]

            elif self.__lst[mid].get_key() < key:
                l = mid + 1
            else:
                r = mid - 1

        return -1

    def __repr__(self):
        tokens = []
        for pair in self.__lst:
            tokens.append("{0}: {1}".format(
                pair.get_key(), pair.get_value()
            ))

        return "{" + "\n".join(tokens) + "}"
