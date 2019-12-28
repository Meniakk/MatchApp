//
// Created by noamc on 24/12/2019.
//

#ifndef UTEMP_TESTSTATE_H
#define UTEMP_TESTSTATE_H

#include "../../../../CLionProjects/utemp/IState.h"
#include "../../../../CLionProjects/utemp/Commands/ICommand.h"
#include "OutputUpdate.h"
class TestState: public IState {
    class TestCommand: public ICommand{
    public:
        ICommand::CommandReturn doCommand(std::vector<std::string> line) override;
    };
    TestCommand c;

public:
    bool isCommandInWindow(std::string symbol) override;

    ICommand *getCommand(std::string symbol) override;

};


#endif //UTEMP_TESTSTATE_H
