package com.sportsdata;

import com.sportsdata.football.match.FootballMatch;
import com.sportsdata.football.scoreboard.DefaultFootballScoreboardFactory;
import com.sportsdata.football.scoreboard.FootballScoreboard;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        FootballScoreboard scoreboard = DefaultFootballScoreboardFactory.createDefault();

        scoreboard.startMatch("Argentina", "Australia");
        scoreboard.startMatch("Germany", "France");
        scoreboard.startMatch("Mexico", "Canada");
        scoreboard.startMatch("Spain", "Brazil");
        scoreboard.startMatch("Uruguay", "Italy");

        scoreboard.updateScore("Mexico", "Canada", 0, 5);
        scoreboard.updateScore("Spain", "Brazil", 10, 2);
        scoreboard.updateScore("Germany", "France", 2, 2);
        scoreboard.updateScore("Uruguay", "Italy", 6, 6);
        scoreboard.updateScore("Argentina", "Australia", 3, 1);

        List<FootballMatch> summary = scoreboard.getSummary();

        summary.forEach(m ->
                System.out.printf("%s %d - %s %d%n", m.homeTeamName(), m.score().homeTeamScore(), m.awayTeamName(), m.score().awayTeamScore()));
    }
}