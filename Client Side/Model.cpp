//
// Created by noamc on 25/12/2019.
//

#include "Model.h"

void Model::proccesLine(std::string line, IState *currentState) {
    std::vector<std::string> tokens = this->lex.lexLine(line);
    ICommand *command = this->par.parse(tokens, currentState);
    if(command == nullptr){
        this->notifyError();
        return;
    }
    ICommand::CommandReturn out = command->doCommand(tokens);
    if (out.isError) {
        this->notifyError();
    }
    if (out.isExit) {
        this->notifyExit();
    }
    if (!out.output.empty()) {
        notifyOutput(out.output);
    }

}

std::string Model::output() {
    return OutputUpdate::getInstance().getOutput();
}

void Model::addObserver(IModel::ModelObserver *observer) {
    this->observers.push_back(observer);
}

void Model::notifyExit() {
    for (ModelObserver *ob: this->observers) {
        ob->exitEvent();
    }
}

void Model::notifyOutput(std::string output) {
    for (ModelObserver *ob: this->observers) {
        ob->outputEvent(output);
    }
}

void Model::notifyNewState(IState *newState) {
    for (ModelObserver *ob: this->observers) {
        ob->newStateEvent(newState);
    }
}

void Model::notifyError() {
    for (ModelObserver *ob: this->observers) {
        ob->errorEvent();
    }
}

void Model::notify(std::function<void(IModel::ModelObserver *)> apply) {
    for (ModelObserver *ob: this->observers) {
        apply(ob);
    }
}
