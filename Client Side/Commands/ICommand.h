//
// Created by noamc on 24/12/2019.
//

#ifndef UTEMP_ICOMMAND_H
#define UTEMP_ICOMMAND_H

#include <vector>
#include <string>
#include <functional>

class ICommand {
public:
//    class CommandObserver {
//    public:
//        virtual void outputEvent() = 0;
//
//        virtual void exitEvent() = 0;
//
//        virtual void errorEvent() = 0;
//
//        virtual void newStateEvent() = 0;
//    };

//    virtual void addObserver(CommandObserver *observer) = 0;

    typedef struct CommandReturn {
        std::string output;
        bool isError = false;
        bool isExit = false;

        CommandReturn() = default;
    } CommandReturn;

    virtual CommandReturn doCommand(std::vector<std::string> line) = 0;
};


#endif //UTEMP_ICOMMAND_H
