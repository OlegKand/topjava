package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.util.MealsUtil.*;

public class MealServlet extends HttpServlet {

    MealDao mealDao = new MealDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String action = request.getParameter("action");
        //request.setAttribute("meals", mealDao.getAllList());

        String id = request.getParameter("userId");
        switch (action == null ? "info" : action) {
            case "edit":
                request.setAttribute("editMeal", mealDao.getMealById(Integer.parseInt(id)));
                request.getRequestDispatcher("editMeals.jsp").forward(request, response);
                break;
            case "delete":
                mealDao.deleteMeal(Integer.parseInt(id));
                response.sendRedirect("MealServlet");
                request.setAttribute("meals", mealDao.getAllList());
                request.getRequestDispatcher("meals.jsp").forward(request, response);
                break;
            case "info":
            default:
                request.getRequestDispatcher("meals.jsp").forward(request, response);
                break;
        }
        request.getRequestDispatcher("meals.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException,ServletException{
        request.setCharacterEncoding("UTF-8");

        String id = request.getParameter("userId");
        LocalDateTime date = LocalDateTime.parse(request.getParameter("dateTime"));
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));
        boolean excess = Boolean.parseBoolean(request.getParameter("excess"));
        MealTo updateMeal = new MealTo(Integer.parseInt(id), date, description, calories, excess);

        mealDao.updateMeal(Integer.parseInt(id), updateMeal);
        request.setAttribute("meals", mealDao.getAllList());
        request.getRequestDispatcher("meals.jsp").forward(request, response);


    }



}
