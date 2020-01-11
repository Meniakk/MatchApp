package Matcher;

import User.IUser;
import java.util.ArrayList;
import java.util.List;

class ConsiderateMatcher implements IMatcher
{
    List<IUser> getPossibleMatches(IUser userToMatch, List<IUser> usersList)
    {
        List<IUser> possibleMatches = new ArrayList<>();
        for (IUser user : usersList)
        {
            if ((userToMatch.getInterestedIn().equals(user.getUserSex())) &&
                    (userToMatch.getUserSex().equals(user.getInterestedIn())) &&
                    (user.getUserType() != IUser.UserType.ADMIN))
            {
                possibleMatches.add(user);
            }
        }

        return possibleMatches;
    }

    @Override
    public IUser getMatch(IUser userToMatch, List<IUser> usersList) {
        return null;
    }
}
