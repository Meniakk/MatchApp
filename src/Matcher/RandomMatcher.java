package Matcher;
import User.IUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomMatcher extends ConsiderateMatcher implements IMatcher
{

    @Override
    public IUser getMatch(IUser userToMatch, List<IUser> usersList)
    {
        Random rng = new Random();
        List<IUser> possibleMatches = super.getPossibleMatches(userToMatch, usersList);

        int randomResult = rng.nextInt(possibleMatches.size());
        return possibleMatches.get(randomResult);
    }

    @Override
    public List<IUser> getKMatches(IUser userToMatch, List<IUser> usersList, int k)
    {
        List<IUser> possibleMatches = super.getPossibleMatches(userToMatch, usersList);

        if (possibleMatches.size() > k)
        {
            int usersToRemove = possibleMatches.size() - k;
            Random rng = new Random();
            for (int i = 0; i < usersToRemove; ++i)
            {
                int randomResult = rng.nextInt(possibleMatches.size());
                possibleMatches.remove(randomResult);
            }
        }

        return possibleMatches;
    }
}
