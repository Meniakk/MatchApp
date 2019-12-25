//
// Created by noamc on 24/12/2019.
//

#ifndef UTEMP_IMODEL_H
#define UTEMP_IMODEL_H

#include <string>
#include "IState.h"

class IModel {
public:

    class ModelObserver {
    public:
        virtual void outputEvent(std::string output) = 0;

        virtual void exitEvent() = 0;

        virtual void errorEvent() = 0;

        virtual void newStateEvent(IState *newState) = 0;
    };

    virtual void addObserver(ModelObserver *observer) = 0;

    virtual void proccesLine(std::string line, IState *currentState) = 0;

    virtual std::string output() = 0;


};


#endif //UTEMP_IMODEL_H
