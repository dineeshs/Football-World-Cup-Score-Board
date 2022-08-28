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
		
		FootballWorldCupScoreBoard scoreBoardMaker = new FootballWorldCupScoreBoard();
		scoreBoardMaker.createGame("Mexico", "Canada");
		scoreBoardMaker.createGame("Spain", "Brazil");
		scoreBoardMaker.createGame("Germany", "France");
		scoreBoardMaker.createGame("Uruguay", "Italy");
		scoreBoardMaker.createGame("Argentina", "Australia");
		List<MatchDetails> details1 = scoreBoardMaker.getSummaryOfGames();
		System.out.println("------------------------------------------");
		System.out.println("Score board of games - ");
		System.out.println("After creation - From recent to old");
		System.out.println("------------------------------------------");
		for (MatchDetails matchDetail : details1) {
			System.out.println(matchDetail.getMatchNumber() + ". " + matchDetail.getHomeTeam() + " "
					+ matchDetail.getHomeTeamScore() + " - " + matchDetail.getAwayTeam() + " "
					+ matchDetail.getAwayTeamScore());
		}
		System.out.println("------------------------------------------");
		System.out.println("Score board of games - ");
		System.out.println("After score updation - From recent to old");
		System.out.println("------------------------------------------");
		scoreBoardMaker.updateGame("Match1", 0, 5);
		scoreBoardMaker.updateGame("Match2", 10, 2);
		scoreBoardMaker.updateGame("Match3", 2, 2);
		scoreBoardMaker.updateGame("Match4", 6, 6);
		scoreBoardMaker.updateGame("Match5", 3, 1);
		List<MatchDetails> details2 = scoreBoardMaker.getSummaryOfGames();
		int i = 0;
		for (MatchDetails matchDetail : details2) {
			System.out.println(++i + ". " + matchDetail.getHomeTeam() + " " + matchDetail.getHomeTeamScore() + " - "
					+ matchDetail.getAwayTeam() + " " + matchDetail.getAwayTeamScore());
	}

	}

}
