from utils import *

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
