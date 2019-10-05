from utils import *

"""
a. Determine the greatest common divisors of elements from a list.
"""


def gcd(a, b):
    # if a == 1 or b == 1:
    #     return 1
    # if a == b:
    #     return a
    # if a > b:
    #     return gcd(a % b, b) if a % b != 0 else b
    # return gcd(a, b % a) if b % a != 0 else a
    return a if b == 0 else gcd(b, a % b)


def gcd_list(lst):
    if is_empty(get_rest(lst)):
        return get_first(lst)
    return gcd(get_first(lst), gcd_list(get_rest(lst)))


"""
b. Insert an element on the n-position in a list.
"""


def insert_el_pos(i, lst, v):
    if i == 1:
        return add_first(get_rest(lst), v)
    return add_first(insert_el_pos(i - 1, get_rest(lst), v), get_first(lst))
