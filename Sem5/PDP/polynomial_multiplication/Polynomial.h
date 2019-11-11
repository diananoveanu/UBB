//
// Created by Diana  on 11/6/19.
//

#ifndef POLYNOMIAL_MULTIPLICATION_POLYNOMIAL_H
#define POLYNOMIAL_MULTIPLICATION_POLYNOMIAL_H


#include <vector>
#include "Utils.h"

class Polynomial {
private:
    std::vector<int> poly;
public:
    /*
     * Constructor for polynomial
     */
    explicit Polynomial(std::vector<int>& poly): poly{Utils::vectorToPoly(poly)}{}


    std::vector<int> getPoly() const{
        return this->poly;
    }

    friend std::ostream&operator<<(std::ofstream& os, const Polynomial& pol);

};


#endif //POLYNOMIAL_MULTIPLICATION_POLYNOMIAL_H
