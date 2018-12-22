/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import java.util.ArrayList;
import static org.hamcrest.core.Is.is;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ricky Zhao
 */
public class MovieHelperTest {

    public MovieHelperTest() {
    }

    /**
     * SBI-14 As a developer, I want to test that if I search showings for "the", 
     * then all showings containing that word are returned, so I know the code works
     *
     * @author Ricky Zhao <riz5034@psu.edu>
     */
    @Test
    public void test_searchShowing_GetsValidShowing() {
        List<Showing> actList = new ArrayList<Showing>();
        List<Showing> expList = new ArrayList<Showing>();
        String substring = "the";

        expList.add(new FakeDataStorage().getShowings().get(2));
        actList = MovieHelper.searchShowing(substring);

        assertEquals("Size should be same", expList.size(), actList.size());

        // Compare fields: time, movie, theater, cost
        for (int i = 0; i < expList.size(); i++) {
            assertEquals("Time should be same", expList.get(i).getTime(), actList.get(i).getTime());
            assertEquals("Mobie should be same", expList.get(i).getMovie().getTitle(), actList.get(i).getMovie().getTitle());
            assertEquals("Theater should be same", expList.get(i).getTheater().getName(), actList.get(i).getTheater().getName());
            assertEquals("Cost should be same", expList.get(i).getCost(), actList.get(i).getCost());
        }
    }

    /**
     * SBI-15 As a developer, I want to test that if I search showings for "asdfasdf", 
     * then no showings are returned, so I know the code works
     * 
     * @author Ricky Zhao <riz5034@psu.edu>
     */
    @Test
    public void test_searchShowing_GetsNonValidShowing() {
        List<Showing> actList = new ArrayList<>();
        String substring = "asdfasdf";

        actList = MovieHelper.searchShowing(substring);

        assertTrue("List should be empty", actList.isEmpty());
    }

    /**
     * SBI-12, SBI-13 If valid input string is given, there should be a list
     *
     * @author Zachary Sigamony <zvs5039@psu.edu>
     */
    @Test
    public void test_searchTitle_GetsValidMovie() {
        List<Movie> actList = new ArrayList<Movie>();//Setup the list for movie type object
        List<Movie> expList = new ArrayList<Movie>();//Setup the list for movie type object
        String substring = "the";//The find keyword

        expList.add(new FakeDataStorage().getMovies().get(2));//Get data from the database that contain the keyword
        actList = MovieHelper.searchTitle(substring);//Test the method

        assertEquals("Size should be same", expList.size(), actList.size());//test the size of both list

        for (int i = 0; i < expList.size(); i++) {
            assertEquals("Movie should be same", expList.get(i).getTitle(), actList.get(i).getTitle());//check contain data match
        }
    }

    @Test
    public void test_searchTitle_GetsNonValidMovie() {
        List<Movie> actList = new ArrayList<>();//Set the return list 
        String substring = "asdfasdf";//Keywordd to test

        actList = MovieHelper.searchTitle(substring);//method to test

        assertTrue("List should be empty", actList.isEmpty());//the keyword should return empty 
    }
    
}
