package Visitor;

import User.UserProxy;

public interface IVisitor {
    public void visit(UserProxy user);
    //todo public void visit(Server server);
}
