package Matcher;

import HelpersClasses.EditDistanceRecursive;
import User.IUser;
import java.util.Comparator;
import java.util.List;

/**
 * A matcher using Levenshtein Distance by user short description
 */
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

    @Override
    public List<IUser> getKMatches(IUser userToMatch, List<IUser> usersList, int k)
    {
        List<IUser> possibleMatches = super.getPossibleMatches(userToMatch, usersList);

        if (possibleMatches.size() > k)
        {
            Comparator<IUser> byDesc = (Comparator.comparingInt(
                            (IUser user)
                                    -> EditDistanceRecursive.calculate
                                    (user.getShortDescription(), userToMatch.getShortDescription()))
            );

            possibleMatches.sort(byDesc);
            possibleMatches = possibleMatches.subList(0, k);
        }

        return possibleMatches;
    }
}
