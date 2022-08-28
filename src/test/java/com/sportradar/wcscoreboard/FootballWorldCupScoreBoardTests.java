package com.sportradar.wcscoreboard;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
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
	
	@Test
	public void testUpdateGame_with_invalid_matchId() {
		assertTrue(footballWorldCupScoreBoard.updateGame("Match100", 1, 0).equals("matchId not found"));

	}
	
	@Test
	public void testEndGame_with_null_matchId() {
		assertTrue(footballWorldCupScoreBoard.endGame(null).equals("matchId cannot be null"));

	}
	
	@Test
	public void testEndGame_with_valid_matchId() {
		assertTrue(footballWorldCupScoreBoard.endGame("Match1").equals("Match1 finished successfully"));
		ConcurrentHashMap<String, MatchDetails> scoreBoard = Whitebox.getInternalState(FootballWorldCupScoreBoard.class,
				"scoreBoard");
		assertFalse(scoreBoard.containsKey("Match1"));

	}
	
	@Test
	public void testEndGame_with_invalid_matchId() {
		assertTrue(footballWorldCupScoreBoard.endGame("Match100").equals("matchId not found"));

	}
	
	@Test
	public void testGetSummaryOfGames_with_no_score_update() {
		List<MatchDetails> summaryOfGames = footballWorldCupScoreBoard.getSummaryOfGames();
		assertTrue(summaryOfGames.get(0).getMatchNumber() == 4);
		assertTrue(summaryOfGames.get(1).getMatchNumber() == 3);
		assertTrue(summaryOfGames.get(2).getMatchNumber() == 2);
		assertTrue(summaryOfGames.get(3).getMatchNumber() == 1);

	}
	
	@Test
	public void testGetSummaryOfGames_with_updated_score() {
		footballWorldCupScoreBoard.updateGame("Match1", 1, 0);
		List<MatchDetails> summaryOfGames = footballWorldCupScoreBoard.getSummaryOfGames();
		assertTrue(summaryOfGames.get(0).getMatchNumber() == 1);
		assertTrue(summaryOfGames.get(1).getMatchNumber() == 4);
		assertTrue(summaryOfGames.get(2).getMatchNumber() == 3);
		assertTrue(summaryOfGames.get(3).getMatchNumber() == 2);

	}
	
	@Test
	public void testGetSummaryOfGames_when_there_are_no_games_played() {
		Whitebox.setInternalState(FootballWorldCupScoreBoard.class, "scoreBoard",
				new ConcurrentHashMap<String, MatchDetails>());
		assertTrue(footballWorldCupScoreBoard.getSummaryOfGames().size() == 0);

	}

}
