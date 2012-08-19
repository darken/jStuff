package darken.jstuff.game;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import darken.jstuff.game.rating.EloRating;

public class EloRatingTest {

	private static final double SCORE_DELTA = 0.005;

	private static final double RATING_DELTA = 0.5;

	public static EloRating rating;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		rating = new EloRating();
	}

	@Test
	public void calculateExpectedScoreTest() {
		calculateAndAssertExpectedScore(1500d, 1500d, 0.5d);
	}

	/**
	 * Suppose Player A has a rating of 1613, and plays in a five-round
	 * tournament. He loses to a player rated 1609, draws with a player rated
	 * 1477, defeats a player rated 1388, defeats a player rated 1586, and loses
	 * to a player rated 1720. His actual score is (0 + 0.5 + 1 + 1 + 0) = 2.5.
	 * His expected score, calculated according to the formula above, was (0.506
	 * + 0.686 + 0.785 + 0.539 + 0.351) = 2.867. Therefore his new rating is
	 * (1613 + 32· (2.5 − 2.867)) = 1601, assuming that a K factor of 32 is
	 * used.
	 * 
	 * If he had scored two wins, one loss, and two draws, for a total score of
	 * three points, that would have been slightly better than expected, and his
	 * new rating would have been (1613 + 32· (3 − 2.867)) = 1617.
	 */
	@Test
	public void updateScoreTest() {
		Double ratingA = 1613d;

		Double expectedScoreA = 0d;
		expectedScoreA += calculateAndAssertExpectedScore(ratingA, 1609d, 0.506);
		expectedScoreA += calculateAndAssertExpectedScore(ratingA, 1477d, 0.686);
		expectedScoreA += calculateAndAssertExpectedScore(ratingA, 1388d, 0.785);
		expectedScoreA += calculateAndAssertExpectedScore(ratingA, 1586d, 0.539);
		expectedScoreA += calculateAndAssertExpectedScore(ratingA, 1720d, 0.351);

		assertScore(2.867d, expectedScoreA);

		Integer kValue = 32;

		Double newRatingA = rating.updateScore(ratingA, kValue, 2.5,
				expectedScoreA);
		assertRating(1601, newRatingA);

		newRatingA = rating.updateScore(ratingA, kValue, 3d, expectedScoreA);
		assertRating(1617, newRatingA);
	}

	private Double calculateAndAssertExpectedScore(Double ratingA,
			Double ratingB, Double expectedScore) {
		Double actualExpectedScore = rating.calculateExpectedScore(ratingA,
				ratingB);
		assertScore(expectedScore, actualExpectedScore);
		return actualExpectedScore;
	}

	private void assertScore(double expectedScore, double actualScore) {
		Assert.assertEquals(expectedScore, actualScore, SCORE_DELTA);
	}

	private void assertRating(double expectedRating, double actualRating) {
		Assert.assertEquals(expectedRating, actualRating, RATING_DELTA);
	}
}
