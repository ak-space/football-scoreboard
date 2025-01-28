package com.sportsdata.football.scoreboard;

import com.sportsdata.football.match.FootballMatch;

import java.util.Comparator;

public class FootballMatchComparator implements Comparator<FootballMatch> {
    @Override
    public int compare(FootballMatch footballMatch1, FootballMatch footballMatch2) {
        int scoreComparison = Integer.compare(
                footballMatch1.score().getTotalScore(),
                footballMatch2.score().getTotalScore());
        if (scoreComparison != 0) {
            return scoreComparison;
        }
        return footballMatch1.updatedTime().compareTo(footballMatch2.updatedTime());
    }
}
