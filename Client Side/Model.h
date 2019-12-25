//
// Created by noamc on 25/12/2019.
//

#ifndef UTEMP_MODEL_H
#define UTEMP_MODEL_H

#include <vector>
#include <functional>
#include <queue>
#include "IModel.h"
#include "IState.h"
#include "Lexer.h"
#include "Parser.h"

class Model : public IModel {
    Lexer lex;
    Parser par;
    std::vector<IModel::ModelObserver *> observers;
    std::queue<std::string> outputQueue;
public:
    void addObserver(ModelObserver *observer) override;

    void proccesLine(std::string line, IState *currentState) override;

    std::string output() override;


private:
    void notify(std::function<void(IModel::ModelObserver *)> apply);

    void notifyExit();

    void notifyError();

    void notifyOutput(std::string output);

    void notifyNewState(IState *newState);

};


#endif //UTEMP_MODEL_H
