package Visitor;

import Server.Server;
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

    @Override
    public void visit(UserProxy user)
    {
        throw new NotImplementedException();
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

    public void visit(Server server)
    {
        server.accept(this);
    }
}
