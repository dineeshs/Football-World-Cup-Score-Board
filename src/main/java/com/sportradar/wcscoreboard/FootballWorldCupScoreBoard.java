package com.sportradar.wcscoreboard;

import java.util.concurrent.ConcurrentHashMap;

import com.sportradar.wcscoreboard.model.MatchDetails;

public class FootballWorldCupScoreBoard {

	private static ConcurrentHashMap<String, MatchDetails> scoreBoard = new ConcurrentHashMap<String, MatchDetails>();;
	private static int matchOrder = 0;
	private static String MATCH = "Match";

	public synchronized String createGame(String homeTeam, String awayTeam) {
		if (homeTeam == null || awayTeam == null)
			return "Team names cannot be null";
		String matchId = FootballWorldCupScoreBoard.MATCH + ++matchOrder;
		FootballWorldCupScoreBoard.scoreBoard.put(matchId, new MatchDetails(homeTeam, awayTeam, 0, 0, matchOrder));
		return matchId;
	}
	
	public String updateGame(String matchId, Integer homeTeamScore, Integer awayTeamScore) {
		if (matchId == null)
			return "matchId cannot be null";
		if (homeTeamScore < 0 || awayTeamScore < 0)
			return "team scores cannot be negative values";
		MatchDetails matchDetail = FootballWorldCupScoreBoard.scoreBoard.get(matchId);
		matchDetail.setHomeTeamScore(homeTeamScore);
		matchDetail.setAwayTeamScore(awayTeamScore);
		return matchId + " updated successfully";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
