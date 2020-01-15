from utils import *
from p6 import add_two_lists

"""
a. Transform a list in a set.
"""


def list_to_set(lst):
    if is_empty(lst):
        return Lista()
    if exists(get_rest(lst), get_first(lst)):
        return list_to_set(get_rest(lst))
    return add_first(list_to_set(get_rest(lst)), get_first(lst))


"""
b. Determine the union of two sets. The sets are represented as lists.
"""


def union(set1, set2):
    return list_to_set(add_two_lists(set1, set2))
