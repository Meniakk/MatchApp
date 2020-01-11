package Matcher;

import User.IUser;
import java.util.List;

public class LevenshteinDistanceMatcher extends ConsiderateMatcher implements IMatcher
{
    @Override
    public IUser getMatch(IUser userToMatch, List<IUser> usersList)
    {
        List<IUser> possibleMatches = super.getPossibleMatches(userToMatch, usersList);

        int minDistance  = Integer.MAX_VALUE;
        IUser match = null;

        for (IUser user : possibleMatches)
        {
            int levenshteinDistance = HelpersClasses.EditDistanceRecursive.calculate(user.getShortDescription(),
                            userToMatch.getShortDescription());

            if (levenshteinDistance < minDistance)
            {
                minDistance = levenshteinDistance;
                match = user;
            }
        }

        return match;
    }
}
