from utils import *

"""
a. Check if a list is a set.
"""


def is_set(lst):
    if is_empty(lst):
        return True
    if exists(get_rest(lst), get_first(lst)):
        return False
    return is_set(get_rest(lst))


def is_set_one_liner(lst):
    return True if is_empty(lst) else False if exists(get_rest(lst), get_first(lst)) else is_set(get_rest(lst))


"""
b. Determine the number of distinct elements from a list.
"""


def distinct(lst):
    if is_empty(lst):
        return 0
    if exists(get_rest(lst), get_first(lst)):
        return distinct(get_rest(lst))
    return 1 + distinct(get_rest(lst))
