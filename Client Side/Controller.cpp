//
// Created by noamc on 24/12/2019.
//

#include "Controller.h"


void Controller::exitEvent() {
    this->run = false;
}

void Controller::errorEvent() {
    view->showOutput(ERROR_MESSAGES_START + model->output() + "\n");
}

void Controller::sendServerCommand(std::string command) {

}

Controller::~Controller() {}

Controller::Controller(IView *view, IModel *model, IState *currentState) : view(view), model(model),
                                                                           currentState(currentState), run(true) {
    this->run = true;
    std::string line;
    int lineCounter = 0;
    model->addObserver(this);
    while (run) {
        ++lineCounter;
        view->showOutput(">>");
        view->getLine(line);
        model->proccesLine(line, this->currentState);
    }
}


void Controller::stop() {
    this->run = false;
}


void Controller::newStateEvent(IState *newState) {
    delete this->currentState;
    this->currentState = newState;
}

void Controller::outputEvent(std::string output) {
    view->showOutput(output + "\n");
}

