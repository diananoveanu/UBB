from utils import *

"""
a. Test the equality of two lists.
"""


def lists_equal(l1, l2):
    if is_empty(l1) and is_empty(l2):
        return True
    if get_first(l2) == get_first(l1):
        return lists_equal(get_rest(l1), get_rest(l2))
    return False


"""
b. Determine the intersection of two sets represented as lists.
"""


def inters_two_sets(s1, s2):
    if is_empty(s1):
        return Lista()
    if exists(s2, get_first(s1)):
        return add_first(inters_two_sets(get_rest(s1), s2), get_first(s1))
    return inters_two_sets(get_rest(s1), s2)
