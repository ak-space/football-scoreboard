package com.sportsdata.football;

import com.sportsdata.football.match.FootballMatch;
import com.sportsdata.football.match.Score;
import com.sportsdata.football.util.FootballMatchValidatorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FootballMatchValidatorImplTest extends BaseTest{
    private FootballMatchValidatorImpl validator;

    @BeforeEach
    public void setUp() {
        validator = new FootballMatchValidatorImpl();
    }

    @Test
    void validateMatchWithValidData() {
        FootballMatch match = createMatch("HomeTeam", "AwayTeam", new Score(0, 0));
        assertTrue(validator.validate(match));
    }

    @Test
    void validateMatchWithNullMatch() {
        assertFalse(validator.validate(null));
    }

    @Test
    void validateMatchWithNullScore() {
        FootballMatch match = createMatch("HomeTeam", "AwayTeam", null);
        assertFalse(validator.validate(match));
    }

    @Test
    void validateMatchWithNullHomeTeam() {
        FootballMatch match = createMatch(null, "AwayTeam", new Score(0, 0));
        assertFalse(validator.validate(match));
    }

    @Test
    void validateMatchWithNullAwayTeam() {
        FootballMatch match = createMatch("HomeTeam", null, new Score(0, 0));
        assertFalse(validator.validate(match));
    }

    @Test
    void validateMatchWithNullTeamNames() {
        FootballMatch match = createMatch(null, null, new Score(0, 0));
        assertFalse(validator.validate(match));
    }

    @Test
    void validateMatchWithBlankTeamNames() {
        FootballMatch match = createMatch(" ", " ", new Score(0, 0));
        assertFalse(validator.validate(match));
    }

    @Test
    void validateTeamNamesWithValidNames() {
        assertTrue(validator.validate("HomeTeam", "AwayTeam"));
    }

    @Test
    void validateTeamNamesWithBlankHomeTeam() {
        assertFalse(validator.validate(" ", "AwayTeam"));
    }

    @Test
    void validateTeamNamesWithBlankAwayTeam() {
        assertFalse(validator.validate("HomeTeam", "  "));
    }

    @Test
    void validateTeamNamesWithSameNames() {
        assertFalse(validator.validate("HomeTeam", "HomeTeam"));
    }

    @Test
    void validateTeamsAndScoresWithValidData() {
        assertTrue(validator.validate("Team A", "Team B", 1, 2));
    }

    @Test
    void validateTeamsAndScoresWithNullScores() {
        assertFalse(validator.validate("HomeTeam", "AwayTeam", null, 2));
        assertFalse(validator.validate("HomeTeam", "AwayTeam", 1, null));
        assertFalse(validator.validate("HomeTeam", "AwayTeam", null, null));
    }

    @Test
    void validateTeamsAndScoresWithNegativeScores() {
        assertFalse(validator.validate("HomeTeam", "AwayTeam", -1, 2));
        assertFalse(validator.validate("HomeTeam", "AwayTeam", 1, -2));
    }

    @Test
    void validateTeamsAndScoresWithInvalidTeamNames() {
        assertFalse(validator.validate(" ", "AwayTeam", 1, 2));
        assertFalse(validator.validate("HomeTeam", " ", 1, 2));
    }
}
