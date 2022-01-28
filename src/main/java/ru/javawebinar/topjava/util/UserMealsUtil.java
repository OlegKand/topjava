package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.javawebinar.topjava.util.TimeUtil.isBetweenHalfOpen;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        System.out.println("filter by cycles :");
        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);

        System.out.println("\nfilter by stream :");
        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with excess. Implement by cycles
        Map<LocalDate, Integer> caloriesForDay = new HashMap<>();
        for (UserMeal userMeal : meals){
            caloriesForDay.merge(userMeal.getDateTime().toLocalDate(), userMeal.getCalories(), Integer::sum);
        }

        List<UserMealWithExcess> filterList = new ArrayList<>();
        for (UserMeal userMeal : meals){
            if (isBetweenHalfOpen(userMeal.getDateTime().toLocalTime(), startTime, endTime)){
                if (caloriesForDay.get(userMeal.getDateTime().toLocalDate()) > caloriesPerDay){
                    filterList.add(new UserMealWithExcess(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), true));
                } else {
                    filterList.add(new UserMealWithExcess(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), false));
                }
            }
        }

        return filterList;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO Implement by streams
        Map<LocalDate, Integer> caloriesToday = meals.stream()
                .collect(Collectors.toMap(
                        k -> k.getDateTime().toLocalDate(),
                        UserMeal::getCalories,
                        Integer::sum
                        ));

        return meals.stream()
                .filter(userMeal -> isBetweenHalfOpen(userMeal.getDateTime().toLocalTime(),startTime,endTime))
                .map(elem -> new UserMealWithExcess(
                        elem.getDateTime(),
                        elem.getDescription(),
                        elem.getCalories(),
                        caloriesToday.get(elem.getDateTime().toLocalDate()) > caloriesPerDay
                )).collect(Collectors.toList());
    }
}
