package Matcher;

import User.IUser;

import java.util.List;

public interface IMatcher
{
    IUser getMatch(IUser userToMatch, List<IUser> usersList);
}
