//
// Created by noamc on 24/12/2019.
//

#ifndef UTEMP_ISTATE_H
#define UTEMP_ISTATE_H

#include <string>
#include "Commands/ICommand.h"

class IState {
public:
    virtual bool isCommandInWindow(std::string symbol) = 0;

    virtual ICommand *getCommand(std::string symbol) = 0;

    virtual ~IState() {}

};


#endif //UTEMP_ISTATE_H
