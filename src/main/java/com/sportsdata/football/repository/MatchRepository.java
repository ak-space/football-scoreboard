package com.sportsdata.football.repository;

import java.util.List;
import java.util.Optional;

public interface MatchRepository<T> {
    void add(T match);
    void update(T match);
    void remove(String homeTeamMame, String awayTeamName);
    List<T> getAll();
    Optional<T> find(String homeTeamMame, String awayTeamName);
}
