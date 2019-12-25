//
// Created by noamc on 24/12/2019.
//

#ifndef UTEMP_CONSOLVIEW_H
#define UTEMP_CONSOLVIEW_H

#include "IView.h"
#include <iostream>
#include <string>
class ConsolView: public IView {
public:
    bool getLine(std::string& toline) override;

    void showOutput(std::string output) override;

};


#endif //UTEMP_CONSOLVIEW_H
