//
// Created by noamc on 24/12/2019.
//

#ifndef UTEMP_PARSER_H
#define UTEMP_PARSER_H

#include "../../../../CLionProjects/utemp/Commands/ICommand.h"
#include <vector>
#include <string>
#include "../../../../CLionProjects/utemp/IState.h"
#include "OutputUpdate.h"

class Parser {
public:
    ICommand* parse(std::vector<std::string> line, IState *currentState);
};


#endif //UTEMP_PARSER_H
