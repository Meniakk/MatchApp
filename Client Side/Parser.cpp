//
// Created by noamc on 24/12/2019.
//

#include "Parser.h"

ICommand* Parser::parse(std::vector<std::string> line, IState *currentState) {
    if (line.empty()) {
        return nullptr;
    }
    std::string commandSymbol = line[0];
    if (!currentState->isCommandInWindow(commandSymbol)) {
        OutputUpdate::getInstance().notifyError();
        OutputUpdate::getInstance().logOutput("invalid command\ngot: " + line[0]);
        return nullptr;
    }
    return currentState->getCommand(commandSymbol);

}
