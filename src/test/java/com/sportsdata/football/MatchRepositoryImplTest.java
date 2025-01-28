package com.sportsdata.football;

import com.sportsdata.football.match.FootballMatch;
import com.sportsdata.football.repository.MatchRepositoryImpl;
import com.sportsdata.football.match.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class MatchRepositoryImplTest extends BaseTest{

    private MatchRepositoryImpl repository;

    @BeforeEach
    public void setUp() {
        repository = new MatchRepositoryImpl();
    }

    @Test
    public void shouldAddMatch() {
        FootballMatch expectedMatch = createMatch("HomeTeam", "AwayTeam", new Score(0, 0));

        FootballMatch newMatch = createMatch("HomeTeam", "AwayTeam", new Score(0, 0));
        repository.add(newMatch);

        Optional<FootballMatch> retrievedMatch = repository.find("HomeTeam", "AwayTeam");
        assertTrue(retrievedMatch.isPresent());
        assertEquals(expectedMatch, retrievedMatch.get());
    }

    @Test
    public void shouldUpdateMatch() {
        FootballMatch expectedMatch = createMatch("HomeTeam", "AwayTeam", new Score(1, 2));

        FootballMatch newMatch = createMatch("HomeTeam", "AwayTeam", new Score(0, 0));
        repository.add(newMatch);

        FootballMatch updatedFootballMatch = createMatch("HomeTeam", "AwayTeam", new Score(1, 2));
        repository.update(updatedFootballMatch);

        Optional<FootballMatch> retrievedMatch = repository.find("HomeTeam", "AwayTeam");
        assertTrue(retrievedMatch.isPresent());
        assertEquals(expectedMatch, retrievedMatch.get());
    }

    @Test
    public void shouldRemoveMatch() {
        FootballMatch newMatch = createMatch("HomeTeam", "AwayTeam",
                new Score(0, 0));

        repository.add(newMatch);
        repository.remove("HomeTeam", "AwayTeam");

        Optional<FootballMatch> retrievedMatch = repository.find("HomeTeam", "AwayTeam");
        assertFalse(retrievedMatch.isPresent());
    }

    @Test
    public void shouldGetAllMatches() {
        FootballMatch newMatch1 = createMatch("HomeTeamA", "AwayTeamA",new Score(0, 0));
        FootballMatch newMatch2 = createMatch("HomeTeamB", "AwayTeamB",new Score(0, 0));
        repository.add(newMatch1);
        repository.add(newMatch2);

        List<FootballMatch> allMatches = repository.getAll();

        assertEquals(2, allMatches.size());
        assertTrue(allMatches.contains(newMatch1));
        assertTrue(allMatches.contains(newMatch2));
    }

    @Test
    public void shouldReturnEmptyOptionalIfMatchNotFound() {
        Optional<FootballMatch> retrievedMatch = repository.find("HomeTeam", "AwayTeam");
        assertFalse(retrievedMatch.isPresent());
    }
}