package com.sportsdata.football.scoreboard;

import com.sportsdata.football.match.FootballMatch;
import java.util.List;

public interface FootballScoreboard {
    void startMatch(String homeTeamName, String awayTeamName);
    void updateScore(String homeTeamName, String awayTeamName, Integer homeScore, Integer awayScore);
    void finishMatch(String homeTeam, String awayTeam);
    List<FootballMatch> getSummary();
}
