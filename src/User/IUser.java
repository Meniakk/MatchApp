package User;

public interface IUser {
    short getId();
    short getAge();
    String getName();
    String getShortDescription();
    String getLongDescription();

    void setId(short id);
    void setAge(short age);
    void setName(String name);
    void setShortDescription(String shortDesc);
    void setLongDescription(String longDesc);
}
