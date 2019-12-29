package User;

import DataBase.XMLDataBase;
import Visitor.CustomerCounterVisitor;
import Visitor.IVisitor;
import Visitor.IVisitable;

public class UserProxy implements IUser, IVisitable {

    private IUser m_user;
    private UserType m_userType;
    private UserSex m_userSex;
    private UserSex m_interestedIn;



    public UserProxy(short id, short age, String name, String shortDescription, String longDescription, UserType userType, UserSex userSex, UserSex interestedIn)
    {
        m_userType = userType;
        m_user = new RealUser(id, age, name, shortDescription, longDescription);
        m_userSex = userSex;
        m_interestedIn = interestedIn;
    }

    public UserSex getUserSex() {
        return m_userSex;
    }

    @Override
    public short getId()
    {
        return m_user.getId();
    }

    @Override
    public short getAge()
    {
        return m_user.getAge();
    }

    @Override
    public String getName()
    {
        return m_user.getName();
    }

    @Override
    public String getShortDescription()
    {
        return m_user.getShortDescription();
    }

    @Override
    public String getLongDescription()
    {
        return m_user.getLongDescription();
    }

    public UserType getUserType() {
        return m_userType;
    }

    public UserSex getInterestedIn() {
        return m_interestedIn;
    }

    @Override
    public void setId(short id)
    {
        m_user.setId(id);
    }

    @Override
    public void setAge(short age)
    {
        m_user.setAge(age);
    }

    @Override
    public void setName(String name)
    {
        m_user.setName(name);
    }

    @Override
    public void setShortDescription(String shortDesc)
    {
        m_user.setShortDescription(shortDesc);
    }

    @Override
    public void setLongDescription(String longDesc)
    {
        m_user.setLongDescription(longDesc);
    }

    public void setUserType(UserType userType) {
        this.m_userType = userType;
    }

    public void setUserSex(UserSex userSex) {
        this.m_userSex = userSex;
    }

    public void setInterestedIn(UserSex interestedIn) {
        this.m_interestedIn = interestedIn;
    }

    @Override
    public CustomerCounterVisitor generateUsersCounterReport()
    {
        if (m_userType == UserType.ADMIN)
        {
            // todo returns to server, to send user the result.
            return m_user.generateUsersCounterReport();
        }
        else
        {
            //todo do something?
            return null;
        }
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}
