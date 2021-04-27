package com.itb.tmbdmobileapp;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.itb.tmbdmobileapp.Activities.MainActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
public class UseCaseTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    private void executeLogin() {
        LoginRegisterTest loginRegisterTest = new LoginRegisterTest();
        loginRegisterTest.login();
    }

    @Test
    public void see_popular_film() {
        executeLogin();

        ViewInteraction materialToolbar = onView(
                allOf(withId(R.id.topAppBar),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.appBarLayout),
                                        0),
                                0),
                        isDisplayed()));
        materialToolbar.perform(click());

        ViewInteraction navigationMenuItemView = onView(
                allOf(withId(R.id.item_peliculas),
                        childAtPosition(
                                allOf(withId(R.id.design_navigation_view),
                                        childAtPosition(
                                                withId(R.id.navigationView),
                                                0)),
                                1),
                        isDisplayed()));
        navigationMenuItemView.perform(click());

        ViewInteraction materialToolbar2 = onView(
                allOf(withId(R.id.topAppBar),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.appBarLayout),
                                        0),
                                0),
                        isDisplayed()));
        materialToolbar2.perform(click());

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.recyclerView_1),
                        childAtPosition(
                                withClassName(is("android.widget.RelativeLayout")),
                                1)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));
    }

    @Test
    public void recomendations_to_search_and_test_editText() {
        executeLogin();

        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.search), withContentDescription("Search"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.topAppBar),
                                        2),
                                0),
                        isDisplayed()));
        actionMenuItemView.perform(click());

        onView(withId(R.id.search_textInputEditText)).perform(typeText("ejemplo")).perform(ViewActions.closeSoftKeyboard());
    }

    @Test
    public void  see_actor_in_a_film() {
        executeLogin();

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.recyclerView_1),
                        childAtPosition(
                                withClassName(is("android.widget.RelativeLayout")),
                                1)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.recyclerViewActors),
                        childAtPosition(
                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                4)));
        recyclerView2.perform(actionOnItemAtPosition(0, click()));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
