package billshare.com.activities;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class RegisterActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void registerActivityTest() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.register), withText("Register"),
                        withParent(allOf(withId(R.id.email_login_form),
                                withParent(withId(R.id.login_form))))));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction appCompatEditText = onView(
                withId(R.id.fullname));
        appCompatEditText.perform(scrollTo(), replaceText("Chad West"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                withId(R.id.phone_number));
        appCompatEditText2.perform(scrollTo(), replaceText("504-234-5678"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                withId(R.id.email));
        appCompatEditText3.perform(scrollTo(), replaceText("chad@chadmwest.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.email), withText("chad@chadmwest.com")));
        appCompatEditText4.perform(scrollTo(), click());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.email), withText("chad@chadmwest.com")));
        appCompatEditText5.perform(scrollTo(), replaceText("chad@chadmwest.cm"), closeSoftKeyboard());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.email), withText("chad@chadmwest.cm")));
        appCompatEditText6.perform(scrollTo(), click());

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.email), withText("chad@chadmwest.cm")));
        appCompatEditText7.perform(scrollTo(), click());

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.email), withText("chad@chadmwest.cm")));
        appCompatEditText8.perform(scrollTo(), replaceText("chad@chadmwest.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText9 = onView(
                withId(R.id.password));
        appCompatEditText9.perform(scrollTo(), replaceText("tfdr8"), closeSoftKeyboard());

        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.time_zone),
                        withParent(allOf(withId(R.id.email_login_form),
                                withParent(withId(R.id.register_form))))));
        appCompatSpinner.perform(scrollTo(), click());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(android.R.id.text1), withText("(GMT-6:00) America/Chicago"),
                        childAtPosition(
                                allOf(withClassName(is("com.android.internal.app.AlertController$RecycleListView")),
                                        withParent(withClassName(is("android.widget.FrameLayout")))),
                                93),
                        isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction appCompatSpinner2 = onView(
                allOf(withId(R.id.languages),
                        withParent(allOf(withId(R.id.email_login_form),
                                withParent(withId(R.id.register_form))))));
        appCompatSpinner2.perform(scrollTo(), click());

        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(android.R.id.text1), withText("bkm"),
                        childAtPosition(
                                allOf(withClassName(is("com.android.internal.app.AlertController$RecycleListView")),
                                        withParent(withClassName(is("android.widget.FrameLayout")))),
                                58),
                        isDisplayed()));
        appCompatTextView2.perform(click());

        ViewInteraction appCompatSpinner3 = onView(
                allOf(withId(R.id.languages),
                        withParent(allOf(withId(R.id.email_login_form),
                                withParent(withId(R.id.register_form))))));
        appCompatSpinner3.perform(scrollTo(), click());

        ViewInteraction appCompatTextView3 = onView(
                allOf(withId(android.R.id.text1), withText("en"),
                        childAtPosition(
                                allOf(withClassName(is("com.android.internal.app.AlertController$RecycleListView")),
                                        withParent(withClassName(is("android.widget.FrameLayout")))),
                                140),
                        isDisplayed()));
        appCompatTextView3.perform(click());

        ViewInteraction appCompatSpinner4 = onView(
                allOf(withId(R.id.currency),
                        withParent(allOf(withId(R.id.email_login_form),
                                withParent(withId(R.id.register_form))))));
        appCompatSpinner4.perform(scrollTo(), click());

        ViewInteraction appCompatTextView4 = onView(
                allOf(withId(android.R.id.text1), withText("USD"),
                        childAtPosition(
                                allOf(withClassName(is("com.android.internal.app.AlertController$RecycleListView")),
                                        withParent(withClassName(is("android.widget.FrameLayout")))),
                                92),
                        isDisplayed()));
        appCompatTextView4.perform(click());

        ViewInteraction appCompatEditText10 = onView(
                allOf(withId(R.id.password), withText("tfdr8")));
        appCompatEditText10.perform(pressImeActionButton());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.registerButton), withText("Register"),
                        withParent(allOf(withId(R.id.email_login_form),
                                withParent(withId(R.id.register_form))))));
        appCompatButton2.perform(scrollTo(), click());

        ViewInteraction editText = onView(
                allOf(withId(R.id.fullname), withText("Chad West"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.email_login_form),
                                        0),
                                0),
                        isDisplayed()));
        editText.check(matches(withText("Chad West")));

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.phone_number), withText("504-234-5678"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.email_login_form),
                                        1),
                                0),
                        isDisplayed()));
        editText2.check(matches(withText("504-234-5678")));

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.email), withText("chad@chadmwest.com"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.email_login_form),
                                        2),
                                0),
                        isDisplayed()));
        editText3.check(matches(withText("chad@chadmwest.com")));

        ViewInteraction editText4 = onView(
                allOf(withId(R.id.email), withText("chad@chadmwest.com"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.email_login_form),
                                        2),
                                0),
                        isDisplayed()));
        editText4.check(matches(withText("chad@chadmwest.com")));

        ViewInteraction editText5 = onView(
                allOf(withId(R.id.password),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.email_login_form),
                                        3),
                                0),
                        isDisplayed()));
        editText5.check(matches(withText("tfdr8")));

        ViewInteraction textView = onView(
                allOf(withId(android.R.id.text1), withText("(GMT-6:00) America/Chicago"),
                        childAtPosition(
                                allOf(withId(R.id.time_zone),
                                        childAtPosition(
                                                withId(R.id.email_login_form),
                                                4)),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("(GMT-6:00) America/Chicago")));

        ViewInteraction textView2 = onView(
                allOf(withId(android.R.id.text1), withText("USD"),
                        childAtPosition(
                                allOf(withId(R.id.currency),
                                        childAtPosition(
                                                withId(R.id.email_login_form),
                                                5)),
                                0),
                        isDisplayed()));
        textView2.check(matches(withText("USD")));

        ViewInteraction textView3 = onView(
                allOf(withId(android.R.id.text1), withText("en"),
                        childAtPosition(
                                allOf(withId(R.id.languages),
                                        childAtPosition(
                                                withId(R.id.email_login_form),
                                                6)),
                                0),
                        isDisplayed()));
        textView3.check(matches(withText("en")));

        ViewInteraction button = onView(
                allOf(withId(R.id.registerButton),
                        childAtPosition(
                                allOf(withId(R.id.email_login_form),
                                        childAtPosition(
                                                withId(R.id.register_form),
                                                0)),
                                7),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

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
