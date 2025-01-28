package com.sportsdata.football.util;

public interface FootballMatchValidator<T> {
    Boolean validate(T match);
    Boolean validate(String homeTeamName, String awayTeamName);
    Boolean validate(String homeTeamName, String awayTeamName, Integer homeTeamScore, Integer awayTeamScore);
}
