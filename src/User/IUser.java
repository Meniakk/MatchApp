package User;

import Visitor.CustomerCounterVisitor;
import Visitor.IVisitor;

import java.util.List;

public interface IUser {

    short getId();
    short getAge();
    String getName();
    String getShortDescription();
    String getLongDescription();
    UserType getUserType();
    UserSex getUserSex();
    UserSex getInterestedIn();

    void addLikedUser(IUser likedUser);
    void addLikedBy(IUser userThatLiked);
    List<IUser> getUsersThatLiked();
    List<IUser> getLikedUsers();


    void setId(short id);
    void setAge(short age);
    void setName(String name);
    void setShortDescription(String shortDesc);
    void setLongDescription(String longDesc);

    void accept(IVisitor visitor);
    IUser getUserThatLiked();
    boolean liked(short id);

    CustomerCounterVisitor generateUsersCounterReport();

    enum UserType {
        TRIAL,
        MEMBER,
        GOLD,
        ADMIN
    }
    static UserType StringToUserType(String userTypeStr)
    {
        IUser.UserType userType;
        switch (userTypeStr) {
            case "TRIAL":
                userType = IUser.UserType.TRIAL;
                break;
            case "MEMBER":
                userType = IUser.UserType.MEMBER;
                break;
            case "GOLD":
                userType = IUser.UserType.GOLD;
                break;
            case "ADMIN":
            default:
                userType = IUser.UserType.ADMIN;
                break;
        }
        return userType;
    }

    enum UserSex {
        MALE,
        FEMALE
    }
    static UserSex StringToUserSex(String userSexStr)
    {
        IUser.UserSex userSex;
        switch (userSexStr) {
            case "MALE":
                userSex = UserSex.MALE;
                break;
            default:
                userSex = UserSex.FEMALE;
                break;
        }
        return userSex;
    }

}
