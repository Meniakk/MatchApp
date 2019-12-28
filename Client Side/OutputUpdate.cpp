//
// Created by noamc on 24/12/2019.
//

#include "OutputUpdate.h"

OutputUpdate &OutputUpdate::getInstance() {
    static OutputUpdate instance;
    return instance;
}

bool OutputUpdate::isThereOutput() {
    return !this->outputsStack.empty();
}

bool OutputUpdate::isError() {
    return error;
}

std::string OutputUpdate::getOutput() {
    if (this->outputsStack.empty()) {
        return "";
    }
    std::string output = this->outputsStack.top();
    this->outputsStack.pop();
    return output;
}

void OutputUpdate::logOutput(const std::string &output) {
    this->outputsStack.push(output);
}

void OutputUpdate::notifyError() {
    error = true;
}
