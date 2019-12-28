//
// Created by noamc on 24/12/2019.
//

#ifndef UTEMP_IVIEW_H
#define UTEMP_IVIEW_H

#include <string>


class IView {
public:
    virtual bool getLine(std::string& toline) = 0;

    virtual void showOutput(std::string output) = 0;
};


#endif //UTEMP_IVIEW_H
