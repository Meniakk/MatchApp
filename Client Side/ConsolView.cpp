//
// Created by noamc on 24/12/2019.
//


#include "ConsolView.h"

bool ConsolView::getLine(std::string& toline) {
    return !!std::getline(std::cin, toline);
}

void ConsolView::showOutput(std::string output) {
    std::cout << output;
}
