package model;

import java.util.ArrayList;
import java.util.List;
import model.FakeDataStorage;
import model.Food;
import model.IDataStorage;

/**
 * SBI-08 A helper class related to tasks of a food item It will hold methods
 * that technically are categories as the "view" aspect of a food item That will
 * help us keep the view separate from the model
 *
 * @author Ricky Zhao <riz5034@psu.edu>
 */
public class FoodHelper {

    /**
     * SBI-08 Return a list of food of a specific type
     *
     * @param type the type of food
     * @return the list of food
     * @author Ricky Zhao <riz5034@psu.edu>
     */
    public static List<Food> getFood(Food.FoodType type) {
        List<Food> foodList = new ArrayList<Food>();
        IDataStorage _foodStorage = new FakeDataStorage();

        for (Food food : _foodStorage.getFoods()) {
            if (food.getDescription() == type) {
                foodList.add(food);
            }
        }

        for (Food food : foodList) {
            System.out.print("Food: " + food.getDescription());
            System.out.print("\tPrice: $" + food.getPrice());
            System.out.print("\tSize: " + food.getSize());
            System.out.println("");
        }

        return foodList;
    }

    /**
     * SBI-08 Return a list of all the food
     *
     * @return a list of all food
     * @author Ricky Zhao <riz5034@psu.edu>
     */
    public static List<Food> getAll() {
        IDataStorage _foodStorage = new FakeDataStorage();
        for (Food food : _foodStorage.getFoods()) {
            System.out.print("Food: " + food.getDescription());
            System.out.print("\tPrice: $" + food.getPrice());
            System.out.print("\tSize: " + food.getSize());
            System.out.println("");
        }

        return _foodStorage.getFoods();
    }

    /**
     * SBI-20/21
     *
     * @author Subin Shaji
     */
    static List<Food> searchSize(String substring) {
        List<Food> foodList = new ArrayList<>();
        IDataStorage _foodStorage = new FakeDataStorage();
        for (Food food : _foodStorage.getFoods()) {
            if (food.getDescription().toString() == (substring).toUpperCase()) {
                foodList.add(food);
            }
        }

        for (Food food : foodList) {
            System.out.println("Search Food Size");
            System.out.print("Food: " + food.getDescription());
            System.out.print("\tPrice: $" + food.getPrice());
            System.out.print("\tSize: " + food.getSize());
            System.out.println("");

        }
        return foodList;
    }
   }
