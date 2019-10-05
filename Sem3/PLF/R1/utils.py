from lista import *


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

