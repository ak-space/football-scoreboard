package com.sportsdata.football.scoreboard;

import com.sportsdata.football.repository.MatchRepositoryImpl;
import com.sportsdata.football.util.FootballMatchValidatorImpl;

import java.time.Clock;

public class DefaultFootballScoreboardFactory {
    public static FootballScoreboard createDefault() {
        return new FootballScoreboardImpl(
                new MatchRepositoryImpl(),
                new FootballMatchValidatorImpl(),
                new FootballMatchComparator(),
                Clock.systemDefaultZone()
        );
    }
}