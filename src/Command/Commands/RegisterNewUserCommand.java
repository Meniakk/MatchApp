package Command.Commands;

import Command.ICommand;
import Logger.ILogger;
import Logger.Logger;
import User.IUser;

import java.util.List;

public class RegisterNewUserCommand implements ICommand<String> {
    //types of user types
    private static final String TRIAL = "Trial";
    private static final String GOLD = "Gold";
    private static final String MEMBER = "Member";
    private static final String ADMIN = "Admin";

    //types of user sex
    private static final String MALE = "Male";
    private static final String FEMALE = "Female";

    @Override
    public String doCommand(List<String> line) {
        short age = Short.parseShort(line.get(1));
        String name = line.get(2);
        String shortDescription = line.get(3);
        String longDescription = line.get(4);
        String userTypeString = line.get(5);
        String userSexString = line.get(6);
        String interestedInString = line.get(7);

        IUser.UserType userType;
        switch (userTypeString) {
            case (TRIAL):
                userType = IUser.UserType.TRIAL;
                break;
            case (MEMBER):
                userType = IUser.UserType.MEMBER;
                break;
            case (GOLD):
                userType = IUser.UserType.GOLD;
                break;
            case (ADMIN):
                userType = IUser.UserType.ADMIN;
                break;
            default:
                userType = IUser.UserType.TRIAL;
                Logger.getInstance().WriteToLog(ILogger.LogLevel.WARNING, ILogger.LogSubject.COMMAND,
                        "RegisterNewUserCommand: userType is in the wrong format. Line received:\n"
                                + line.toString());
                return "0";
        }

        IUser.UserSex userSex;
        switch (userSexString) {
            case (MALE):
                userSex = IUser.UserSex.MALE;
                break;
            case (FEMALE):
                userSex = IUser.UserSex.FEMALE;
                break;
            default:
                Logger.getInstance().WriteToLog(ILogger.LogLevel.WARNING, ILogger.LogSubject.COMMAND,
                        "RegisterNewUserCommand: userSex is in the wrong format. Line received:\n"
                                + line.toString());
                return "0";
        }

        IUser.UserSex interestedIn;
        switch (interestedInString) {
            case (MALE):
                interestedIn = IUser.UserSex.MALE;
                break;
            case (FEMALE):
                interestedIn = IUser.UserSex.FEMALE;
                break;
            default:
                Logger.getInstance().WriteToLog(ILogger.LogLevel.WARNING, ILogger.LogSubject.COMMAND,
                        "RegisterNewUserCommand: interestedIn is in the wrong format. Line received:\n"
                                + line.toString());
                return "0";
        }

        int id = Server.Server.getInstance().createNewUser(age, name, shortDescription, longDescription, userType, userSex, interestedIn);
        Logger.getInstance().WriteToLog(ILogger.LogLevel.INFO, ILogger.LogSubject.COMMAND,
                "RegisterNewUserCommand: Registered a new user");
        if (id > 0 )
            return " "+id;
        else
            return "0";
    }
}
