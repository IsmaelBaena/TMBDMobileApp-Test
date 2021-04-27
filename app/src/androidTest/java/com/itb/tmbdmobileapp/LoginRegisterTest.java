package com.itb.tmbdmobileapp;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.itb.tmbdmobileapp.Activities.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class LoginRegisterTest {

    public static final String EJEMPLO = "ejemplo";

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void login() {
        onView(withId(R.id.search_textInputEditText)).perform(typeText(EJEMPLO)).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.login_textInputEditText_password)).perform(typeText(EJEMPLO)).perform(ViewActions.closeSoftKeyboard());

        onView(withId(R.id.register_button_login)).check(matches(isClickable()));
        onView(withId(R.id.register_button_login)).perform(click());

        onView(withId(R.id.tmdb_fragment)).check(matches(isDisplayed()));
    }

    @Test
    public void register() {
        onView(withId(R.id.register_button_register)).check(matches(isClickable()));
        onView(withId(R.id.register_button_register)).perform(click());

        onView(withId(R.id.register_fragment)).check(matches(isDisplayed()));

        onView(withId(R.id.register_textInputEditText_username)).perform(typeText(EJEMPLO)).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.register_textInputEditText_email)).perform(typeText(EJEMPLO)).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.register_textInputEditText_password)).perform(typeText(EJEMPLO)).perform(ViewActions.closeSoftKeyboard());

        onView(withId(R.id.register_button_register)).check(matches(isClickable()));
        onView(withId(R.id.register_button_register)).perform(click());

        onView(withId(R.id.login_fragment)).check(matches(isDisplayed()));
    }
}
