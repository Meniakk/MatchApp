cmake_minimum_required(VERSION 3.15)
project(Client_Side)

set(CMAKE_CXX_STANDARD 14)

include_directories(.)
include_directories(Commands)

add_executable(Client_Side main.cpp IController.h Lexer.h Lexer.cpp Spliter.h Spliter.cpp Parser.h Parser.cpp IState.h Commands/ICommand.h OutputUpdate.h OutputUpdate.cpp Controller.h Controller.cpp TestState.h TestState.cpp ErrorMessages.h IView.h ConsolView.h ConsolView.cpp Commands/ExitCommand.h Commands/ExitCommand.cpp IModel.h Model.h Model.cpp)
