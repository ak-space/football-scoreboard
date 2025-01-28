package com.sportsdata.football;

import com.sportsdata.football.match.FootballMatch;
import com.sportsdata.football.match.Score;
import com.sportsdata.football.repository.MatchRepositoryImpl;
import com.sportsdata.football.scoreboard.FootballMatchComparator;
import com.sportsdata.football.scoreboard.FootballScoreboard;
import com.sportsdata.football.scoreboard.FootballScoreboardImpl;
import com.sportsdata.football.util.FootballMatchValidatorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class FootballMatchImplTest extends BaseTest {

    private FootballScoreboard scoreboard;

    @BeforeEach
    public void setUp() {
        scoreboard = new FootballScoreboardImpl(
                new MatchRepositoryImpl(),
                new FootballMatchValidatorImpl(),
                new FootballMatchComparator(),
                FIXED_CLOCK);
    }

    @Test
    public void shouldInitializeNewMatchWithZeroScores() {
        scoreboard.startMatch("HomeTeam", "AwayTeam");
        List<FootballMatch> summary = scoreboard.getSummary();

        assertEquals(1, summary.size());

        FootballMatch footballMatch = summary.getFirst();

        assertEquals("HomeTeam", footballMatch.homeTeamName());
        assertEquals("AwayTeam", footballMatch.awayTeamName());
        assertEquals(0, footballMatch.score().homeTeamScore());
        assertEquals(0, footballMatch.score().awayTeamScore());
    }

    @Test
    public void shouldIncreaseMatchScoreForHomeTeam() {
        scoreboard.startMatch("HomeTeam", "AwayTeam");
        scoreboard.updateScore("HomeTeam", "AwayTeam", 1, 0);
        List<FootballMatch> summary = scoreboard.getSummary();

        assertEquals(1, summary.size());

        FootballMatch footballMatch = summary.getFirst();

        assertEquals("HomeTeam", footballMatch.homeTeamName());
        assertEquals("AwayTeam", footballMatch.awayTeamName());
        assertEquals(1, footballMatch.score().homeTeamScore());
        assertEquals(0, footballMatch.score().awayTeamScore());
    }

    @Test
    public void shouldIncreaseMatchScoreForAwayTeam() {
        scoreboard.startMatch("HomeTeam", "AwayTeam");
        scoreboard.updateScore("HomeTeam", "AwayTeam", 0, 1);
        List<FootballMatch> summary = scoreboard.getSummary();

        assertEquals(1, summary.size());

        FootballMatch footballMatch = summary.getFirst();

        assertEquals("HomeTeam", footballMatch.homeTeamName());
        assertEquals("AwayTeam", footballMatch.awayTeamName());
        assertEquals(0, footballMatch.score().homeTeamScore());
        assertEquals(1, footballMatch.score().awayTeamScore());
    }

    @Test
    public void shouldRemoveMatchFromScoreboard() {
        scoreboard.startMatch("HomeTeam", "AwayTeam");
        scoreboard.updateScore("HomeTeam", "AwayTeam", 0, 1);
        scoreboard.finishMatch("HomeTeam", "AwayTeam");
        List<FootballMatch> summary = scoreboard.getSummary();

        assertEquals(0, summary.size());
    }

    @Test
    public void shouldReturnSorterByScore() {
        scoreboard.startMatch("HomeTeam-Argentina", "AwayTeam-Australia");
        scoreboard.startMatch("HomeTeam-Germany", "AwayTeam-France");
        scoreboard.startMatch("HomeTeam-Mexico", "AwayTeam-Canada");
        scoreboard.startMatch("HomeTeam-Spain", "AwayTeam-Brazil");
        scoreboard.startMatch("HomeTeam-Uruguay", "AwayTeam-Italy");

        scoreboard.updateScore("HomeTeam-Mexico", "AwayTeam-Canada", 0, 5);
        scoreboard.updateScore("HomeTeam-Spain", "AwayTeam-Brazil", 10, 2);
        scoreboard.updateScore("HomeTeam-Germany", "AwayTeam-France", 2, 2);
        scoreboard.updateScore("HomeTeam-Uruguay", "AwayTeam-Italy", 6, 6);
        scoreboard.updateScore("HomeTeam-Argentina", "AwayTeam-Australia", 3, 1);

        List<FootballMatch> summary = scoreboard.getSummary();

        assertEquals(5, summary.size());

        List<FootballMatch> expected = List.of(
                createMatch("HomeTeam-Uruguay", "AwayTeam-Italy", new Score(6, 6)),
                createMatch("HomeTeam-Spain", "AwayTeam-Brazil", new Score(10, 2)),
                createMatch("HomeTeam-Mexico", "AwayTeam-Canada", new Score(0, 5)),
                createMatch("HomeTeam-Argentina", "AwayTeam-Australia", new Score(3, 1)),
                createMatch("HomeTeam-Germany", "AwayTeam-France", new Score(2, 2))
        );

        assertIterableEquals(expected, summary);
    }
}
