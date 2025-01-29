package com.sportsdata.football.match;

public record Score(Integer homeTeamScore, Integer awayTeamScore){
    public Integer getTotalScore() {
        return homeTeamScore + awayTeamScore;
    }
}
