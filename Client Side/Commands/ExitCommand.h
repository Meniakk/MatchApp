//
// Created by noamc on 24/12/2019.
//

#ifndef UTEMP_EXITCOMMAND_H
#define UTEMP_EXITCOMMAND_H

#include "ICommand.h"

class ExitCommand: public ICommand {
public:
    ICommand::CommandReturn doCommand(std::vector<std::string> line) override;

};


#endif //UTEMP_EXITCOMMAND_H
