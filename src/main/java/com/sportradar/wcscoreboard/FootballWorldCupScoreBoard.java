package com.sportradar.wcscoreboard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

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
		if(matchDetail == null) return "matchId not found";
		matchDetail.setHomeTeamScore(homeTeamScore); 
		matchDetail.setAwayTeamScore(awayTeamScore);
		return matchId + " updated successfully";
	}
	
	public String endGame(String matchId) {
		if (matchId == null)
			return "matchId cannot be null";
		if (FootballWorldCupScoreBoard.scoreBoard.containsKey(matchId))
			FootballWorldCupScoreBoard.scoreBoard.remove(matchId);
		else
			return "matchId not found";
		return matchId + " finished successfully";
	}
	
	public List<MatchDetails> getSummaryOfGames() {
		List<MatchDetails> matchDetails = new ArrayList<MatchDetails>(FootballWorldCupScoreBoard.scoreBoard.values());
		Comparator<MatchDetails> scoreComparator = (a, b) -> {
			return Integer.compare(a.getHomeTeamScore() + a.getAwayTeamScore(),
					b.getHomeTeamScore() + b.getAwayTeamScore());
		};
		Comparator<MatchDetails> matchNumberComparator = (a, b) -> {
			return Integer.compare(a.getMatchNumber(), b.getMatchNumber());
		};
		return matchDetails.stream().sorted(scoreComparator.reversed().thenComparing(matchNumberComparator.reversed()))
				.collect(Collectors.toList());

	}

	public static void main(String[] args) {
		
		

	}

}
