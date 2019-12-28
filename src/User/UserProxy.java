package User;

public class UserProxy implements IUser {

    private IUser m_user;
    private UserType m_userType;

    public UserProxy(short id, short age, String name, String shortDescription, String longDescription, UserType userType)
    {
        m_userType = userType;
        m_user = new RealUser(id, age, name, shortDescription, longDescription);
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

    public void setUserType(UserType userType) {
        this.m_userType = userType;
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
}
