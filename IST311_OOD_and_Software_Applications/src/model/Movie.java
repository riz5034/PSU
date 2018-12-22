package model;

import java.util.Date;

/**
 * SBI-02
 * Movie will be used to represent a single movie
 *
 * @author Phil O'Connell <pxo4@psu.edu>
 */
public class Movie {

  // Title of the movie
  private String _title;

  // The wide-release date
  private Date _releaseDate;

  /**
   * Movie constructor
   *
   * @param title the title of the movie
   * @param releaseDate the wide-release date
   * @author Phil O'Connell <pxo4@psu.edu>
   */
  public Movie(String title, Date releaseDate) {
    this._title = title;
    this._releaseDate = releaseDate;
  }

  /**
   * Get the title of the movie
   * @return the title of the Movie
   * @author Phil O'Connell <pxo4@psu.edu>
   */
  public String getTitle() {
    return _title;
  }

  /**
   * Set the title of the movie
   * @param title the title of the Movie
   * @author Phil O'Connell <pxo4@psu.edu>
   */
  public void setTitle(String title) {
    this._title = title;
  }

  /**
   * Get the release date
   * @return the release date
   * @author Phil O'Connell <pxo4@psu.edu>
   */
  public Date getReleaseDate() {
    return _releaseDate;
  }

  /**
   * Set the release date
   * @param releaseDate the release date of the movie
   * @author Phil O'Connell <pxo4@psu.edu>
   */
  public void setReleaseDate(Date releaseDate) {
    this._releaseDate = releaseDate;
  }
}
