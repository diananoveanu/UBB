from utils import *

"""
a. Add an element at the end of a list. 
"""


def add_elem_end(lst, v):
    if is_empty(lst):
        ls = Lista()
        ls.prim = v
        return add_first(Lista(), v)
    return add_first(add_elem_end(get_rest(lst), v), get_first(lst))


"""
b. Concatenate two lists.
"""


def add_two_lists(s1, s2):
    if is_empty(s2):
        return s1
    return add_two_lists(add_first(s1, get_first(s2)), get_rest(s2))
