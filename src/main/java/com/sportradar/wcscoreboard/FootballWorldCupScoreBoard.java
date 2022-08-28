package com.sportradar.wcscoreboard;

import java.util.concurrent.ConcurrentHashMap;

import com.sportradar.wcscoreboard.model.MatchDetails;

public class FootballWorldCupScoreBoard {

	private static ConcurrentHashMap<String, MatchDetails> scoreBoard = new ConcurrentHashMap<String, MatchDetails>();;
	private static int matchOrder = 0;
	private static String MATCH = "Match";

	public synchronized String createGame(String homeTeam, String awayTeam) {
		return null;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
