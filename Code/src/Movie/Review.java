package Movie;

import java.io.Serializable;

/**
 * class that holds information of a user's review
 */
public class Review implements Serializable {
	private String reviewerName;
	private double rating;
	private String content;

	/**
	 * returns numerical rating of the review
	 * @return rating
	 */
	public double getRating() {
		return rating;
	}

	/**
	 * return name of the reviewer
	 * @return name
	 */
	public String getReviewerName() {
		return reviewerName;
	}

	/**
	 * returns the text content of the review
	 * @return content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * set name of the reviewer
	 * @param s name of the reviewer
	 */
	public void setName(String s) { reviewerName = s; }

	/**
	 * set numerical rating of the review
	 * @param r rating of the review
	 */
	public void setRating(double r) { rating = r; }

	/**
	 * set content of the review
	 * @param c content of review
	 */
	public void setContent(String c) { content = c; }

}
