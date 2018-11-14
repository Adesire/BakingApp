package com.example.android.bakingapp;


import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;

import android.support.test.espresso.ViewAction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule =
    new ActivityTestRule<>(MainActivity.class);

    @Test
    public void recipeButtonClick(){
        //INSTRUMENTATION TESTING FOR CLICKING ON VIEW IN
        //RECYCLERVIEW
        //MY PLAN WAS TO TEST THE RECYCLER  VIEW, AND THE BUTTON CLICKS OF THE RECIPE DETAILS FRAGMENT
        //WHICH OPENS THE RECIPE INGREDIENTS.
        onView(withId(R.id.recipe_grid))
                .check(matches(isDisplayed()));
    }
}
