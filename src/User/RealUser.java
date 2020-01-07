package User;

import Server.Server;
import Visitor.CustomerCounterVisitor;
import Visitor.IVisitor;

final public class RealUser implements IUser {
    private short m_id;
    private short m_age;
    private String m_name;
    private String m_shortDescription;
    private String m_longDescription;
    private UserType m_userType;
    private UserSex m_userSex;
    private UserSex m_interestedIn;

    public RealUser(short id, short age, String name, String shortDescription, String longDescription, UserType userType, UserSex userSex, UserSex interestedIn)
    {
        this.m_id = id;
        this.m_age = age;
        this.m_name = name;
        this.m_shortDescription = shortDescription;
        this.m_longDescription = longDescription;
        m_userType = userType;
        m_userSex = userSex;
        m_interestedIn = interestedIn;
    }

    @Override
    public String toString() {
        return String.format("id=\"%d\", name=\"%s\", age=\"%d\"", m_id, m_name, m_age);
    }


    @Override
    public short getId() {
        return m_id;
    }

    @Override
    public short getAge() {
        return m_age;
    }

    @Override
    public String getName() {
        return m_name;
    }

    @Override
    public String getShortDescription() {
        return m_shortDescription;
    }

    @Override
    public String getLongDescription() {
        return m_longDescription;
    }

    @Override
    public UserType getUserType() {
        return m_userType;
    }

    @Override
    public UserSex getUserSex() {
        return m_userSex;
    }

    @Override
    public UserSex getInterestedIn() {
        return m_interestedIn;
    }

    @Override
    public void setId(short id) {
        m_id = id;
    }

    @Override
    public void setAge(short age) {
        m_age = age;
    }

    @Override
    public void setName(String name) {
        m_name = name;
    }

    @Override
    public void setShortDescription(String shortDesc) {
        m_shortDescription = shortDesc;
    }

    @Override
    public void setLongDescription(String longDesc) {
        m_longDescription = longDesc;
    }

    @Override
    public CustomerCounterVisitor generateUsersCounterReport()
    {
        CustomerCounterVisitor visitor = new CustomerCounterVisitor();
        //todo Server need to be a singleton
        visitor.visit(Server.getInstance());
        return visitor;
    }

}
