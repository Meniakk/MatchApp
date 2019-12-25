//
// Created by noamc on 24/12/2019.
//

#include "TestState.h"

bool TestState::isCommandInWindow(std::string symbol) {
    return symbol == "print";
}

ICommand *TestState::getCommand(std::string symbol) {
    return &(this->c);
}

ICommand::CommandReturn TestState::TestCommand::doCommand(std::vector<std::string> line) {
    ICommand::CommandReturn out;
    out.isExit = false;
    if (line.size() < 2) {
        out.isError = true;
    } else {
        out.output = line[1];
    }
    return out;
}
