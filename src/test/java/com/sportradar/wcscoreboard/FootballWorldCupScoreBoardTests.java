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

}
