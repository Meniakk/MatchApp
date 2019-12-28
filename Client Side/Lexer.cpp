//
// Created by noamc on 24/12/2019.
//

#include "Lexer.h"


std::vector<std::string> Lexer::lexLine(const std::string &line) {
    std::vector<std::string> out;
    std::vector<std::string> parts = Spliter::splitLine(line, SEPARATOR);
    for (const std::string &part: parts) {
        if (part != "" && part != " ") {
            out.push_back(part);
        }
    }
    return out;
}
