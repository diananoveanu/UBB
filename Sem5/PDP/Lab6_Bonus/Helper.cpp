//
// Created by Diana on 11/13/2019.
//

#include "Helper.h"
#include <fstream>
#include <algorithm>

std::vector<int> Helper::readBigFromFile(std::string &file) {
    /**
     * read big number from file and store it backwards
     */
    std::ifstream in(file);
    std::vector<int> rez;
    int x;
    while (in >> x) {
        rez.push_back(x);
    }
    in.close();
    std::reverse(rez.begin(), rez.end());
    return rez;
}
