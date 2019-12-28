#include <iostream>
#include "Controller.h"
#include "TestState.h"
#include "ConsolView.h"
#include "Model.h"
int main() {
    TestState s;
    Model m;
    ConsolView v;
    Controller c(&v,&m,&s);
    std::cout << "Hello, World!" << std::endl;
    return 0;
}