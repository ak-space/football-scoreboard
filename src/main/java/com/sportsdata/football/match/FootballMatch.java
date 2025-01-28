package com.sportsdata.football.match;

import java.time.LocalDateTime;

public interface FootballMatch {
    LocalDateTime updatedTime();
    String homeTeamName();
    String awayTeamName();
    Score score();
}
