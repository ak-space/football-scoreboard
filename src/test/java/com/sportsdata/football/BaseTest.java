package com.sportsdata.football;

import com.sportsdata.football.match.FootballMatch;
import com.sportsdata.football.match.FootballMatchImpl;
import com.sportsdata.football.match.Score;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class BaseTest {

    protected static final Instant FIXED_INSTANT = Instant.parse("2025-01-01T00:00:00Z");
    protected static final Clock FIXED_CLOCK = Clock.fixed(FIXED_INSTANT, ZoneId.systemDefault());

    protected FootballMatch createMatch(String homeTeam, String awayTeam, Score score) {
        return new FootballMatchImpl(LocalDateTime.now(FIXED_CLOCK), homeTeam, awayTeam, score);
    }
}
