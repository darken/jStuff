package darken.jstuff.game.rating;

/**
 * From <a href='http://en.wikipedia.org/wiki/Elo_rating_system'>
 * http://en.wikipedia.org/wiki/Elo_rating_system</a>
 * <p>
 * If Player A has true strength Ra and Player B has true strength Rb, the exact
 * formula (using the logistic curve) for the expected score of Player A is
 * </p>
 * 
 * <blockquote>
 * 
 * <pre>
 * Ea = 1 / (1 + 10 &circ; ((Rb - Ra) / 400))
 * </pre>
 * 
 * </blockquote> Similarly the expected score for Player B is <blockquote>
 * 
 * <pre>
 * Eb = 1 / (1 + 10 &circ; ((Ra - Rb) / 400))
 * </pre>
 * 
 * </blockquote>
 * 
 * This could also be expressed by <blockquote>
 * 
 * <pre>
 * Ea = Qa / Qa + Qb
 * </pre>
 * 
 * </blockquote> and <blockquote>
 * 
 * <pre>
 * Ea = Qb / Qa + Qb
 * </pre>
 * 
 * </blockquote> where <blockquote>
 * 
 * <pre>
 * Qa = 10 &circ; (Ra/400) and Qb = 10 &circ; (Rb/400)
 * </pre>
 * 
 * </blockquote>
 */
public class EloRating {

	/**
	 * Supposing Player A was expected to score Ea points but actually scored Sa
	 * points. The formula for updating his rating is <blockquote>
	 * 
	 * <pre>
	 * Ra' = Ra + K(Sa - Ea)
	 * </pre>
	 * 
	 * </blockquote> The maximum possible adjustment per game (sometimes called
	 * the K-value) was set at K = 16 for masters and K = 32 for weaker players.
	 * 
	 * <p>
	 * This update can be performed after each game or each tournament, or after
	 * any suitable rating period.
	 * </p>
	 * 
	 * <p>
	 * New players are assigned a rating of 1500, with the best humans and bots
	 * rating over 2000.
	 * </p>
	 * 
	 * @param rating
	 * @param kValue
	 * @param score
	 * @param expectedScore
	 * @return The updated score
	 */
	public Double updateScore(Double rating, Integer kValue, Double score,
			Double expectedScore) {
		return rating + kValue * (score - expectedScore);
	}

	/**
	 * The expected score of Player A is
	 * 
	 * <blockquote>
	 * 
	 * <pre>
	 * Ea = 1 / (1 + 10 &circ; ((Ra - Rb) / 400))
	 * </pre>
	 * 
	 * </blockquote>
	 * <p>
	 * New players are assigned a rating of 1500, with the best humans and bots
	 * rating over 2000.
	 * </p>
	 * 
	 * @param ratingA
	 * @param ratingB
	 * @return
	 */
	public Double calculateExpectedScore(Double ratingA, Double ratingB) {
		Double qA = calculateQ(ratingA);
		Double qB = calculateQ(ratingB);

		Double eA = qA / (qA + qB);

		return eA;
	}

	private Double calculateQ(Double rating) {
		return Math.pow(10d, (rating / 400));
	}

}
