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

        int randomResult = rng.nextInt((possibleMatches.size()) + 1);
        return possibleMatches.get(randomResult);
    }
}
