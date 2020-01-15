from Lista import *

"""
a. Test the inclusion of two sets, represented as lists.
"""


def testInclusion(set1, set2):
    if is_empty(set1):
        return True
    elif exists(set2, get_first(set1)):
        return testInclusion(get_rest(set1), set2)
    return False


"""
b. Eliminate all occurrences of an element from a list.
"""


def eliminateAllOccur(lst, elem):
    if is_empty(lst):
        return Lista()
    if get_first(lst) != elem:
        return add_first(eliminateAllOccur(get_rest(lst), elem), get_first(lst))
    return eliminateAllOccur(get_rest(lst), elem)


### Utils

def get_first(lst):
    return lst.prim.e


def get_rest(lst):
    new_lst = Lista()
    new_lst.prim = lst.prim.urm
    return new_lst


def add_first(lst, el):
    nod = Nod(el)
    nod.urm = lst.prim
    lst.prim = nod
    return lst


def is_empty(lst):
    if lst.prim is None:
        return True
    return False


def exists(lst, e):
    if is_empty(lst):
        return False
    if get_first(lst) == e:
        return True
    return exists(get_rest(lst), e)
