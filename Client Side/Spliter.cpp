//
// Created by noamc on 24/12/2019.
//

#include "Spliter.h"
std::vector<std::string> Spliter::splitLine(const std::string &line, std::string sep) {
    std::vector<std::string> answer;
    std::string part;
    std::string c;
    for (int i = 0; i < line.size(); ++i) {
        c = line[i];
        if (c == sep) {
            if (!part.empty()) {
                answer.push_back(part);
            }
            part = "";
            continue;
        }
        part += line[i];
    }
    if (!part.empty()) {
        answer.push_back(part);
    }
    return answer;
}