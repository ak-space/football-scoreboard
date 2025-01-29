package com.sportsdata.football;

import com.sportsdata.football.scoreboard.DefaultFootballScoreboardFactory;
import com.sportsdata.football.scoreboard.FootballScoreboard;
import com.sportsdata.football.scoreboard.FootballScoreboardImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DefaultFootballScoreboardFactoryTest {

    @Test
    void createDefaultShouldReturnValidFootballScoreboard() {
        FootballScoreboard scoreboard = DefaultFootballScoreboardFactory.createDefault();
        assertNotNull(scoreboard);
        assertInstanceOf(FootballScoreboardImpl.class, scoreboard);
    }
}