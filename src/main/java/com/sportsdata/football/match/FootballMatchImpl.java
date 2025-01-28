package com.sportsdata.football.match;

import java.time.LocalDateTime;

public record FootballMatchImpl(LocalDateTime updatedTime, String homeTeamName, String awayTeamName,
                                Score score) implements FootballMatch {
}
