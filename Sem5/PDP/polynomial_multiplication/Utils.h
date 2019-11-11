//
// Created by Diana  on 11/6/19.
//

#ifndef POLYNOMIAL_MULTIPLICATION_UTILS_H
#define POLYNOMIAL_MULTIPLICATION_UTILS_H


#include <vector>

class Utils {
public:
    static std::vector<int> vectorToPoly(std::vector<int>& poly){
        std::reverse(poly.begin(), poly.end());
        return poly;
    }
};


#endif //POLYNOMIAL_MULTIPLICATION_UTILS_H
