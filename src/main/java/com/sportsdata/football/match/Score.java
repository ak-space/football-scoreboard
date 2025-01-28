package com.sportsdata.football.match;

public record Score(Integer homeTeamScore, Integer awayTeamScore){
    public int getTotalScore() {
        return homeTeamScore + awayTeamScore;
    }
}
