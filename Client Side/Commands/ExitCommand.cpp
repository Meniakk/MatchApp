//
// Created by noamc on 24/12/2019.
//

#include "ExitCommand.h"

ICommand::CommandReturn ExitCommand::doCommand(std::vector<std::string> line) {
    ICommand::CommandReturn out;
    out.isExit = true;
    return out;
}
