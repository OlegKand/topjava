package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

public class MealDao implements MealDaoImpl{

    public List<MealTo> mealToList = new LinkedList<>(MealsUtil
            .filteredByStreams(MealsUtil.meals,
                    LocalTime.of(0,0),
                    LocalTime.of(23,59),
                    MealsUtil.caloriesPerDay));

    @Override
    public void updateMeal(int id, MealTo mealTo) {
        MealsUtil.meals.set(id, new Meal(mealTo.getId(),
                mealTo.getDateTime(),
                mealTo.getDescription(),
                mealTo.getCalories()));
    }

    @Override
    public void deleteMeal(int id) {
        MealsUtil.meals.removeIf(meal -> meal.getId() == id);
    }

    @Override
    public List<MealTo> getAllList() {
        return MealsUtil.filteredByStreams(MealsUtil.meals,
                LocalTime.of(0,0),
                LocalTime.of(23,59),
                MealsUtil.caloriesPerDay);
    }

    @Override
    public MealTo getMealById(int id) {
        for (MealTo meal : getAllList()){
            if (meal.getId() == id) return meal;
        }
        return null;
    }
}
