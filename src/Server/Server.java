package Server;

import DataBase.XMLDataBase;
import Logger.ILogger;
import Logger.Logger;
import Matcher.IMatcher;
import User.IUser;
import User.UserProxy;
import Visitor.IVisitable;
import Visitor.IVisitor;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class Server implements IVisitable {

    private XMLDataBase m_dataBase;
    private List<IUser> m_usersList;
    private short m_nextID;

    public static final Server instance = new Server();

    public static Server getInstance(){
        return instance;
    }

    private Server()
    {
        m_usersList = new ArrayList<>();
        m_dataBase = XMLDataBase.getInstance();
        m_nextID = m_dataBase.GetNextID();
    }

    public IUser getMatch(IUser userToMatch, IMatcher matcher)
    {
        Logger.getInstance().WriteToLog(ILogger.LogLevel.INFO, ILogger.LogSubject.SERVER, String.format("Looking for a match for %d", userToMatch.getId()));
        IUser match = matcher.getMatch(userToMatch, m_usersList);
        if (match == null)
        {
            Logger.getInstance().WriteToLog(ILogger.LogLevel.WARNING, ILogger.LogSubject.SERVER, "Could not find a match");
        }
        else
        {
            Logger.getInstance().WriteToLog(ILogger.LogLevel.INFO, ILogger.LogSubject.SERVER, String.format("Matched %d", match.getId()));
        }
        return match;
    }

    public boolean createNewUser(short age, String name, String shortDescription, String longDescription, IUser.UserType userType, IUser.UserSex userSex, IUser.UserSex interestedIn)
    {
        boolean creationSucceeded = false;
        try
        {
            IUser newUser = new UserProxy(m_nextID, age, name, shortDescription, longDescription, userType, userSex, interestedIn);
            m_dataBase.SaveUser(newUser);
            ++m_nextID;
            m_usersList.add(newUser);
            creationSucceeded = true;
        }
        catch (Exception ignore)
        {}

        return creationSucceeded;
    }


    @Override
    public void accept(IVisitor visitor) {
        for (IUser user: m_usersList)
        {

        }
    }
}
