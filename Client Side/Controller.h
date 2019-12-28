//
// Created by noamc on 24/12/2019.
//

#ifndef UTEMP_CONTROLLER_H
#define UTEMP_CONTROLLER_H

#include <string>
#include <vector>
#include "IController.h"
#include "Lexer.h"
#include "Parser.h"
#include "IState.h"
#include "OutputUpdate.h"
#include "ErrorMessages.h"
#include "IView.h"
#include "IModel.h"

/*
 * Used to control the program.
 * the "head" of the app
 * the c in mvc
 */
class Controller : public IController {
    Lexer lex;
    Parser par;
    IView *view;
    IModel *model;
    IState *currentState;
    bool run;
public:
    Controller(IView *view, IModel *model, IState *currentState);

    void sendServerCommand(std::string command) override;

    ~Controller() override;

    void stop() override;

    void outputEvent(std::string output) override;

    void exitEvent() override;

    void errorEvent() override;

    void newStateEvent(IState *newState) override;

};


#endif //UTEMP_CONTROLLER_H
