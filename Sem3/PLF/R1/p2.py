from utils import *

"""
a. Substitute the i-th element from a list, with a value v. 
"""


def sub_i_el(i, lst, v):
    if i == 1:
        return add_first(get_rest(lst), v)
    return add_first(sub_i_el(i - 1, get_rest(lst), v), get_first(lst))


"""
b. Determine difference of two sets represented as lists.
"""


def diff_two_sets(set1, set2):
    if is_empty(set1):
        return Lista()
    if exists(set2, get_first(set1)):
        return diff_two_sets(get_rest(set1), set2)
    return add_first(diff_two_sets(get_rest(set1), set2), get_first(set1))
