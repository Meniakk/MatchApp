package Visitor;

import User.IUser;
import User.RealUser;
import User.UserProxy;

public interface IVisitor
{
    public void visit(IUser user);
    public void visit(UserProxy user);
    public void visit(RealUser user);
}
