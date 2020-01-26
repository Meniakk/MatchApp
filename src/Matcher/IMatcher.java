package Matcher;

import User.IUser;

import java.util.List;

public interface IMatcher
{
    List<IUser> getKMatches(IUser userToMatch, List<IUser> usersList, int k);
}
