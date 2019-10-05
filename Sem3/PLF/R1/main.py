from utils import *
from p1 import list_to_set, union
from p2 import sub_i_el, diff_two_sets
from p3 import is_set, is_set_one_liner, distinct
from p4 import has_even_nb_el, delete_all_occur
from p5 import insert_el_pos, gcd_list
from p6 import add_elem_end, add_two_lists
from p7 import lists_equal, inters_two_sets


def main():
    # 1a
    # tipar(list_to_set(creareLista()))
    # 1b
    # tipar(union(creareLista(), creareLista()))
    # 2a
    # tipar(sub_i_el(2, creareLista(), 4))
    # 2b
    # tipar(diff_two_sets(list_to_set(creareLista()), list_to_set(creareLista())))
    # 3a
    # lst = creareLista()
    # print(is_set(lst))
    # print(is_set_one_liner(lst))
    # 3b
    # print(distinct(creareLista()))
    # 4a
    # print(has_even_nb_el(creareLista()))
    # 4b
    # tipar(delete_all_occur(creareLista(),2))
    # 5a
    # print(gcd_list(creareLista()))
    # 5b
    # tipar(insert_el_pos(2, creareLista(), 7))
    # 6a
    # tipar(add_elem_end(creareLista(), 3))
    # 7a
    # print(lists_equal(creareLista(), creareLista()))
    # 7b
    tipar(inters_two_sets(list_to_set(creareLista()), list_to_set(creareLista())))


main()
