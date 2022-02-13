package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.MealTo;

import java.util.List;

public interface MealDaoImpl {

    public void updateMeal(int id, MealTo mealTo);
    public void deleteMeal(int id);
    public List<MealTo> getAllList();
    public MealTo getMealById(int id);
}
