package com.sportradar.wcscoreboard;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.ConcurrentHashMap;

import org.junit.Before;
import org.junit.Test;
import org.powermock.reflect.Whitebox;

import com.sportradar.wcscoreboard.model.MatchDetails;


public class FootballWorldCupScoreBoardTests {

	public static FootballWorldCupScoreBoard footballWorldCupScoreBoard;

	@Before
	public void setUp() throws Exception {

		FootballWorldCupScoreBoardTests.footballWorldCupScoreBoard = new FootballWorldCupScoreBoard();
		Whitebox.setInternalState(FootballWorldCupScoreBoard.class, "matchOrder", 0);
		Whitebox.setInternalState(FootballWorldCupScoreBoard.class, "scoreBoard",
				new ConcurrentHashMap<String, MatchDetails>());
		footballWorldCupScoreBoard.createGame("Mexico", "Canada");
		footballWorldCupScoreBoard.createGame("Spain", "Brazil");
		footballWorldCupScoreBoard.createGame("Germany", "France");
		footballWorldCupScoreBoard.createGame("Uruguay", "Italy");
	}

	@Test
	public void testCreateGame_with_parameters_as_null() {

		assertTrue(footballWorldCupScoreBoard.createGame(null, null).equals("Team names cannot be null"));
	}

	@Test
	public void testCreateGame_add_new_game() {

		footballWorldCupScoreBoard.createGame("Argentina", "Australia");
		ConcurrentHashMap<String, MatchDetails> scoreBoard = Whitebox.getInternalState(FootballWorldCupScoreBoard.class,
				"scoreBoard");
		assertTrue(scoreBoard.size() == 5);
	}
	
	@Test
	public void testUpdateGame_with_null_matchId() {

		assertTrue(footballWorldCupScoreBoard.updateGame(null, 1, 0).equals("matchId cannot be null"));
	}

	@Test
	public void testUpdateGame_with_negative_scores() {

		assertTrue(footballWorldCupScoreBoard.updateGame("Match1", -1, 0).equals("team scores cannot be negative values"));
	}

	@Test
	public void testUpdateGame_with_valid_scores_and_matchId() {
		assertTrue(footballWorldCupScoreBoard.updateGame("Match1", 1, 0).equals("Match1 updated successfully"));
		ConcurrentHashMap<String, MatchDetails> scoreBoard = Whitebox.getInternalState(FootballWorldCupScoreBoard.class,
				"scoreBoard");
		assertTrue(scoreBoard.get("Match1").getHomeTeamScore().equals(1));

	}

}
