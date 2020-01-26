package Command.Commands;

import Command.ICommand;
import Logger.ILogger;
import Logger.Logger;
import User.IUser;

import java.util.List;

public class RegisterNewUserCommand implements ICommand<String> {
    //types of user types
    private static final String TRIAL = "TRIAL";
    private static final String GOLD = "GOLD";
    private static final String MEMBER = "MEMBER";
    private static final String ADMIN = "ADMIN";

    //types of user sex
    private static final String MALE = "MALE";
    private static final String FEMALE = "FEMALE";

    @Override
    public String doCommand(List<String> line) {
        short age = 0;
        String name = "";
        String shortDescription = "";
        String longDescription = "";
        String userTypeString = "";
        String userSexString = "";
        String interestedInString = "";
        try {
            age = Short.parseShort(line.get(1));
            name = line.get(2);
            shortDescription = line.get(3);
            longDescription = line.get(4);
            userTypeString = line.get(5).toUpperCase();
            userSexString = line.get(6).toUpperCase();
            interestedInString = line.get(7).toUpperCase();
        } catch (Exception e) {
            return "0";
        }

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
        if (id > 0)
            return " " + id;
        else
            return "0";
    }
}
