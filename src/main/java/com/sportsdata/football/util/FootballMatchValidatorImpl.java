package com.sportsdata.football.util;

import com.sportsdata.football.match.FootballMatch;
import org.apache.commons.lang3.StringUtils;

public class FootballMatchValidatorImpl implements FootballMatchValidator<FootballMatch> {
    @Override
    public Boolean validate(FootballMatch match) {
        if (match == null || match.score() == null) {
            return false;
        }
        return validate(match.homeTeamName(), match.awayTeamName(), match.score().homeTeamScore(), match.score().awayTeamScore());
    }

    @Override
    public Boolean validate(String homeTeamName, String awayTeamName) {
        return StringUtils.isNotBlank(homeTeamName) && StringUtils.isNotBlank(awayTeamName) && !homeTeamName.equals(awayTeamName);
    }

    @Override
    public Boolean validate(String homeTeamName, String awayTeamName, Integer homeTeamScore, Integer awayTeamScore) {
        if (!validate(homeTeamName, awayTeamName)) {
            return false;
        }
        return (homeTeamScore != null && awayTeamScore != null && homeTeamScore >= 0 && awayTeamScore >= 0);
    }
}
