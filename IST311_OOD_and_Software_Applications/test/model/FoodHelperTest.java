/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Food.FoodType;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Subin Shaji
 */
public class FoodHelperTest {

    public FoodHelperTest() {
    }

    /**
     * SBI-20 As a developer, I want to test that if I search for popcorn, that
     * I get a list containing the 3 popcorn objects (small, medium and large),
     * so I know the code works
     *
     * @author Subin Shaji <sxs5879@psu.edu>
     */
    @Test
    public void test_searchSize_POPCORN_ReturnsValue() {
        List<Food> actual = new ArrayList<>();
        List<Food> expected = new ArrayList<>();
        IDataStorage _database = new FakeDataStorage();
        String substring = "POPCORN";
        actual = FoodHelper.searchSize(substring);

        expected.add(_database.getFoods().get(0));
        expected.add(_database.getFoods().get(1));
        expected.add(_database.getFoods().get(2));

        assertEquals("Size should be equal to expected", expected.size(), actual.size());

        for (int i = 0; i < expected.size(); i++) {
            assertEquals("Size of Popcorn matches expected", expected.get(i).getSize(), actual.get(i).getSize());
            assertEquals("Type of Food matches expected", expected.get(i).getDescription(), actual.get(i).getDescription());
        }
    }

    /**
     * SBI-21 As a developer, I want to test that if I search for popcorn, that
     * I get a list containing the 3 popcorn objects (small, medium and large),
     * so I know the code works
     *
     * @author Subin Shaji <sxs5879@psu.edu>
     */

    @Test
    public void test_searchSize_SODA_ReturnsValue() {
        List<Food> actual = new ArrayList<>();
        List<Food> expected = new ArrayList<>();
        IDataStorage _database = new FakeDataStorage();
        String substring = "SODA";
        actual = FoodHelper.searchSize(substring);

        expected.add(_database.getFoods().get(3));
        expected.add(_database.getFoods().get(4));
        expected.add(_database.getFoods().get(5));

        assertEquals("Size should be equal to expected", expected.size(), actual.size());

        for (int i = 0; i < expected.size(); i++) {
            assertEquals("Size of Soda matches expected", expected.get(i).getSize(), actual.get(i).getSize());
            assertEquals("Type of Food matches expected", expected.get(i).getDescription(), actual.get(i).getDescription());

        }
    }
}
