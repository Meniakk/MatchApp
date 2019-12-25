//
// Created by noamc on 24/12/2019.
//

#ifndef UTEMP_LEXER_H
#define UTEMP_LEXER_H

#include <vector>
#include <string>
#include "Spliter.h"

#define SEPARATOR " "

class Lexer {
public:
    std::vector<std::string> lexLine(const std::string &line);

};


#endif //UTEMP_LEXER_H
