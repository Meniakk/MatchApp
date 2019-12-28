//
// Created by noamc on 24/12/2019.
//

#ifndef UTEMP_SPLITER_H
#define UTEMP_SPLITER_H

#include <vector>
#include <string>

class Spliter {
public:
    static std::vector<std::string> splitLine(const std::string &line, std::string sep);

};


#endif //UTEMP_SPLITER_H
