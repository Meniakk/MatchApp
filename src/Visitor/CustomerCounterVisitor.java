package Visitor;

import Server.Server;
import User.IUser;
import User.RealUser;
import User.UserProxy;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class CustomerCounterVisitor implements IVisitor
{

    private int m_trialUsers;
    private int m_memberUsers;
    private int m_goldUsers;

    public int getTrialUsers() {
        return m_trialUsers;
    }

    public int getMemberUsers() {
        return m_memberUsers;
    }

    public int getGoldUsers() {
        return m_goldUsers;
    }

    public CustomerCounterVisitor()
    {
        m_trialUsers = 0;
        m_memberUsers = 0;
        m_goldUsers = 0;
    }

    public void visit(RealUser user)
    {
        switch (user.getUserType())
        {
            case TRIAL:
                ++m_trialUsers;
                break;
            case MEMBER:
                ++m_memberUsers;
                break;
            case GOLD:
                ++m_goldUsers;
                break;
            case ADMIN:
                break;
        }
    }

    @Override
    public void visit(IUser user)
    {
        user.accept(this);
    }

    @Override
    public void visit(UserProxy user)
    {
        user.accept(this);
    }

    @Override
    public String toString() {

        return "Customer Counter Results: " +
                "Trial Users = " + m_trialUsers +
                " | Member Users = " + m_memberUsers +
                " | Gold Users = " + m_goldUsers;
    }
}
