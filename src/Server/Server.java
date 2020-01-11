package Server;

import DataBase.XMLDataBase;
import Logger.ILogger;
import Logger.Logger;
import Matcher.IMatcher;
import Matcher.LevenshteinDistanceMatcher;
import Matcher.RandomMatcher;
import User.IUser;
import User.UserProxy;
import Visitor.IVisitable;
import Visitor.IVisitor;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private List<IUser> m_usersList;
    private short m_nextID;

    public static final Server instance = new Server();

    public static Server getInstance(){
        return instance;
    }

    private Server()
    {
        m_usersList = XMLDataBase.getInstance().LoadAllUsers();
        m_nextID = XMLDataBase.getInstance().GetNextID();
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

    public List<IUser> getKMatches(IUser userToMatch, IMatcher matcher, int k)
    {
        Logger.getInstance().WriteToLog(ILogger.LogLevel.INFO, ILogger.LogSubject.SERVER,
                String.format("Looking for %d matches for %d", k, userToMatch.getId()));

        List<IUser> matches = matcher.getKMatches(userToMatch, m_usersList, k);
        if (matches == null)
        {
            Logger.getInstance().WriteToLog(ILogger.LogLevel.WARNING, ILogger.LogSubject.SERVER,
                    "Could not find any matches");
        }
        else
        {
            int countOfMatches = matches.size();
            Logger.getInstance().WriteToLog(ILogger.LogLevel.INFO, ILogger.LogSubject.SERVER,
                    String.format("Found %d matches out of %d", countOfMatches, k));
        }
        return matches;
    }

    public boolean createNewUser(short age, String name, String shortDescription, String longDescription, IUser.UserType userType, IUser.UserSex userSex, IUser.UserSex interestedIn)
    {
        boolean creationSucceeded = false;
        try
        {
            IUser newUser = new UserProxy(m_nextID, age, name, shortDescription, longDescription, userType, userSex, interestedIn);
            XMLDataBase.getInstance().SaveUser(newUser);
            ++m_nextID;
            m_usersList.add(newUser);
            creationSucceeded = true;
        }
        catch (Exception ignore)
        {}

        return creationSucceeded;
    }

    public void accept(IVisitor visitor)
    {
        for (IUser user: m_usersList)
        {
            user.accept(visitor);
        }
    }

    public boolean isUserExist(short id)
    {
        boolean isExist = false;

        for (IUser user : m_usersList)
        {
            if (user.getId() == id)
            {
                isExist = true;
                break;
            }
        }

        return isExist;
    }

    public static void main(String [] args)
    {
        Server server = Server.getInstance();
        System.out.println(server.getKMatches(server.m_usersList.get(0), new LevenshteinDistanceMatcher(), 2));
    }
}
