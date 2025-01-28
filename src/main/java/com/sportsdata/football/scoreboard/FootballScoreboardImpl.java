package com.sportsdata.football.scoreboard;

import com.sportsdata.football.match.FootballMatch;
import com.sportsdata.football.match.FootballMatchImpl;
import com.sportsdata.football.repository.MatchRepository;
import com.sportsdata.football.match.Score;
import com.sportsdata.football.util.FootballMatchValidator;
import lombok.extern.log4j.Log4j2;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Log4j2
public class FootballScoreboardImpl implements FootballScoreboard {

    private final MatchRepository<FootballMatch> matchRepository;
    private final FootballMatchValidator<FootballMatch> matchValidator;
    private final Comparator<FootballMatch> comparator;
    private final Clock clock;

    public FootballScoreboardImpl(MatchRepository<FootballMatch> matchRepository,
                                  FootballMatchValidator<FootballMatch> matchValidator,
                                  Comparator<FootballMatch> comparator,
                                  Clock clock) {
        this.matchRepository = matchRepository;
        this.matchValidator = matchValidator;
        this.comparator = comparator;
        this.clock = clock;
    }

    @Override
    public void startMatch(String homeTeamName, String awayTeamName) {
        matchValidator.validate(homeTeamName, awayTeamName);
        FootballMatch newFootballMatch = new FootballMatchImpl(LocalDateTime.now(clock), homeTeamName, awayTeamName, new Score(0, 0));
        matchRepository.find(homeTeamName, awayTeamName).ifPresent(match -> {
            throw new IllegalArgumentException("Match already exists.");
        });
        matchRepository.add(newFootballMatch);
        log.debug("Match: {} - {} started.", homeTeamName, awayTeamName);
    }

    @Override
    public void updateScore(String homeTeamName, String awayTeamName, int newHomeScore, int newAwayScore) {
        matchValidator.validate(homeTeamName, awayTeamName, newHomeScore, newAwayScore);
        matchRepository.find(homeTeamName, awayTeamName).orElseThrow(() ->
                new IllegalArgumentException("Match not found.")
        );
        matchRepository.update(new FootballMatchImpl(LocalDateTime.now(clock), homeTeamName, awayTeamName, new Score(newHomeScore, newAwayScore)));
        log.debug("Match: {} {} - {} {} score updated.", homeTeamName, newHomeScore, awayTeamName, newAwayScore);
    }

    @Override
    public void finishMatch(String homeTeamName, String awayTeamName) {
        matchValidator.validate(homeTeamName, awayTeamName);
        matchRepository.find(homeTeamName, awayTeamName).orElseThrow(() ->
                new IllegalArgumentException("Match not found.")
        );
        matchRepository.remove(homeTeamName, awayTeamName);
        log.debug("Match: {} - {} finished.", homeTeamName, awayTeamName);
    }

    @Override
    public List<FootballMatch> getSummary() {
        List<FootballMatch> matches = new ArrayList<>(matchRepository.getAll());
        matches.sort(comparator.reversed());
        log.debug("Returning summary list: {}", matches);
        return matches;
    }
}
