//
// Created by Diana on 11/13/2019.
//

#include "numberDiff.h"

void numberDiff(std::vector<int> *a, std::vector<int> *b) {
    int carry = 0;
    for (int i = 0; i < a->size(); i++) {
        if (i >= b->size()) {
            if (carry == 0) {
                return;
            } else {
                if ((*a)[i] == 0) {
                    carry = -1;
                    continue;
                } else {
                    (*a)[i] = (*a)[i] + carry;
                    break;
                }
            }
        } else {
            (*a)[i] = (*a)[i] + carry;
            carry = 0;
            if ((*a)[i] >= (*b)[i]) {
                (*a)[i] = (*a)[i] - (*b)[i];
            } else {
                (*a)[i] = (*a)[i] - (*b)[i] + 10;
                carry = -1;
            }
        }
    }
}
