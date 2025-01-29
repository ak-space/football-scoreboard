package com.sportsdata;

import com.sportsdata.football.match.FootballMatch;
import com.sportsdata.football.scoreboard.DefaultFootballScoreboardFactory;
import com.sportsdata.football.scoreboard.FootballScoreboard;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class Main {
    public static void main(String[] args) {
        FootballScoreboard scoreboard = DefaultFootballScoreboardFactory.createDefault();

        log.info("Start of the Football World Cup");

        scoreboard.startMatch("Argentina", "Australia");
        scoreboard.startMatch("Germany", "France");
        scoreboard.startMatch("Mexico", "Canada");
        scoreboard.startMatch("Spain", "Brazil");
        scoreboard.startMatch("Uruguay", "Italy");

        log.info("The matches have started.");
        printSummary(scoreboard.getSummary());

        scoreboard.updateScore("Mexico", "Canada", 0, 5);
        scoreboard.updateScore("Spain", "Brazil", 10, 2);
        scoreboard.updateScore("Germany", "France", 2, 2);
        scoreboard.updateScore("Uruguay", "Italy", 6, 6);
        scoreboard.updateScore("Argentina", "Australia", 3, 1);

        log.info("Match scores have been updated.");
        printSummary(scoreboard.getSummary());
    }

    private static void printSummary(List<FootballMatch> summary) {
        StringBuilder sb = new StringBuilder();
        summary.forEach(m -> sb.append(System.lineSeparator())
                .append(String.format("%-15s %-2d - %-15s %-2d",
                        m.homeTeamName(),
                        m.score().homeTeamScore(),
                        m.awayTeamName(),
                        m.score().awayTeamScore())));

        log.info("Summary:{}", sb);
    }
}