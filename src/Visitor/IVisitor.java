package Visitor;

import User.IUser;

public interface IVisitor {
    public void visit(IUser user);
    //todo public void visit(Server server);
}
