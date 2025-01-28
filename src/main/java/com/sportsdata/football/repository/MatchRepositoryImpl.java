package com.sportsdata.football.repository;

import com.sportsdata.football.match.FootballMatch;
import java.util.*;

public class MatchRepositoryImpl implements MatchRepository<FootballMatch> {

    private final Map<String, FootballMatch> matchStorage;

    public MatchRepositoryImpl() {
        this(new HashMap<>());
    }

    public MatchRepositoryImpl(Map<String, FootballMatch> teamMatchRelations) {
        this.matchStorage = new HashMap<>();
    }

    @Override
    public void add(FootballMatch match) {
        String matchKey = getMatchKey(match.homeTeamName(), match.awayTeamName());
        matchStorage.put(matchKey,match);
    }

    @Override
    public void update(FootballMatch match) {
        String matchKey = getMatchKey(match.homeTeamName(), match.awayTeamName());
        matchStorage.replace(matchKey, match);
    }

    @Override
    public void remove(String homeTeamMame, String awayTeamName) {
        String matchKey = getMatchKey(homeTeamMame, awayTeamName);
        matchStorage.remove(matchKey);
    }

    @Override
    public List<FootballMatch> getAll() {
        return new ArrayList<>(matchStorage.values());
    }

    @Override
    public Optional<FootballMatch> find(String homeTeamMame, String awayTeamName) {
        String matchKey = getMatchKey(homeTeamMame, awayTeamName);
        return Optional.ofNullable(matchStorage.get(matchKey));
    }

    private String getMatchKey(String homeTeamName, String awayTeamName) {
        return homeTeamName + "_" + awayTeamName;
    }
}
