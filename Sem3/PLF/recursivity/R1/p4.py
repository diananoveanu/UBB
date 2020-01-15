from utils import *

"""
a. Determine if a list has even number of elements, without computing the length of the list.
"""


def has_even_nb_el(lst):
    if is_empty(lst):
        return True
    if has_even_nb_el(get_rest(lst)) is True:
        return False
    return True


"""
b. Delete all occurrences of an element e from a list.
"""


def delete_all_occur(lst, e):
    if is_empty(lst):
        return Lista()
    if get_first(lst) == e:
        return delete_all_occur(get_rest(lst), e)
    return add_first(delete_all_occur(get_rest(lst), e), get_first(lst))
