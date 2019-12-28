//
// Created by noamc on 24/12/2019.
//

#ifndef UTEMP_OUTPUTUPDATE_H
#define UTEMP_OUTPUTUPDATE_H


#include <string>
#include <stack>

class OutputUpdate {
    static OutputUpdate instance;
    std::stack<std::string> outputsStack;
    bool error;

    OutputUpdate() : error(false) {};


public:
    //TODO: make it thread safe
    static OutputUpdate &getInstance();

    bool isError();

    std::string getOutput();

    void logOutput(const std::string &output);

    void notifyError();

    bool isThereOutput();

    OutputUpdate(OutputUpdate const &) = delete;

    void operator=(OutputUpdate const &) = delete;


};

#endif //UTEMP_OUTPUTUPDATE_H
