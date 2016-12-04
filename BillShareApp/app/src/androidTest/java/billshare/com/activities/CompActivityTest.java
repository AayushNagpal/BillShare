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
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class CompActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void compActivityTest() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(android.R.id.button1), withText("OK")));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.register), withText("Register"),
                        withParent(allOf(withId(R.id.email_login_form),
                                withParent(withId(R.id.login_form))))));
        appCompatButton2.perform(scrollTo(), click());

        ViewInteraction appCompatEditText = onView(
                withId(R.id.fullname));
        appCompatEditText.perform(scrollTo(), replaceText("user1"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                withId(R.id.phone_number));
        appCompatEditText2.perform(scrollTo(), replaceText("5041234567"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                withId(R.id.email));
        appCompatEditText3.perform(scrollTo(), replaceText("user1@test.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                withId(R.id.password));
        appCompatEditText4.perform(scrollTo(), replaceText("12345"), closeSoftKeyboard());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.uploadButton), withText("Browse")));
        appCompatButton3.perform(scrollTo(), click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.fileNameView), withText("IMG_20161203_172929.jpg"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.email_login_form),
                                        7),
                                1),
                        isDisplayed()));
        textView.check(matches(withText("IMG_20161203_172929.jpg")));

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.registerButton), withText("Register"),
                        withParent(allOf(withId(R.id.email_login_form),
                                withParent(withId(R.id.register_form))))));
        appCompatButton4.perform(scrollTo(), click());

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.register), withText("Register"),
                        withParent(allOf(withId(R.id.email_login_form),
                                withParent(withId(R.id.login_form))))));
        appCompatButton5.perform(scrollTo(), click());

        ViewInteraction appCompatEditText5 = onView(
                withId(R.id.fullname));
        appCompatEditText5.perform(scrollTo(), replaceText("user2"), closeSoftKeyboard());

        ViewInteraction appCompatEditText6 = onView(
                withId(R.id.phone_number));
        appCompatEditText6.perform(scrollTo(), replaceText("5047894561"), closeSoftKeyboard());

        ViewInteraction appCompatEditText7 = onView(
                withId(R.id.email));
        appCompatEditText7.perform(scrollTo(), replaceText("user2@test.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText8 = onView(
                withId(R.id.password));
        appCompatEditText8.perform(scrollTo(), replaceText("12345"), closeSoftKeyboard());

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(R.id.uploadButton), withText("Browse")));
        appCompatButton6.perform(scrollTo(), click());

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.fileNameView), withText("IMG_20161203_172929.jpg"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.email_login_form),
                                        7),
                                1),
                        isDisplayed()));
        textView2.check(matches(withText("IMG_20161203_172929.jpg")));

        ViewInteraction appCompatButton7 = onView(
                allOf(withId(R.id.registerButton), withText("Register"),
                        withParent(allOf(withId(R.id.email_login_form),
                                withParent(withId(R.id.register_form))))));
        appCompatButton7.perform(scrollTo(), click());

        ViewInteraction appCompatAutoCompleteTextView = onView(
                withId(R.id.email));
        appCompatAutoCompleteTextView.perform(scrollTo(), replaceText("user1@test.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText9 = onView(
                withId(R.id.password));
        appCompatEditText9.perform(scrollTo(), replaceText("12345"), closeSoftKeyboard());

        ViewInteraction appCompatButton8 = onView(
                allOf(withId(R.id.email_sign_in_button), withText("Sign in"),
                        withParent(allOf(withId(R.id.email_login_form),
                                withParent(withId(R.id.login_form))))));
        appCompatButton8.perform(scrollTo(), click());

        ViewInteraction appCompatTextView = onView(
                allOf(withText("You"), isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.userNameView), withText("user1"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        textView3.check(matches(withText("user1")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.emailView), withText("user1@test.com"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                2),
                        isDisplayed()));
        textView4.check(matches(withText("user1@test.com")));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.mobileNumberView), withText("5041234567"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                3),
                        isDisplayed()));
        textView5.check(matches(withText("5041234567")));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.currencyView), withText("CNY"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                4),
                        isDisplayed()));
        textView6.check(matches(withText("CNY")));

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.timeZoneView), withText("(GMT0:00) Africa/Abidjan"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                5),
                        isDisplayed()));
        textView7.check(matches(withText("(GMT0:00) Africa/Abidjan")));

        ViewInteraction imageView = onView(
                allOf(withId(R.id.profile_image),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));

        ViewInteraction appCompatTextView2 = onView(
                allOf(withText("Groups"), isDisplayed()));
        appCompatTextView2.perform(click());

        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.fab), isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction appCompatEditText10 = onView(
                withId(R.id.group_name));
        appCompatEditText10.perform(scrollTo(), replaceText("testgroup"), closeSoftKeyboard());

        ViewInteraction appCompatEditText11 = onView(
                withId(R.id.amountEditText));
        appCompatEditText11.perform(scrollTo(), replaceText("500"), closeSoftKeyboard());

        ViewInteraction appCompatEditText12 = onView(
                withId(R.id.limit));
        appCompatEditText12.perform(scrollTo(), replaceText("500"), closeSoftKeyboard());

        ViewInteraction appCompatAutoCompleteTextView2 = onView(
                withId(R.id.searchFriends));
        appCompatAutoCompleteTextView2.perform(scrollTo(), replaceText("user"), closeSoftKeyboard());

        ViewInteraction linearLayout = onView(
                allOf(withClassName(is("android.widget.LinearLayout")), isDisplayed()));
        linearLayout.perform(click());

        ViewInteraction appCompatButton9 = onView(
                allOf(withId(R.id.uploadBill), withText("Upload Bill")));
        appCompatButton9.perform(scrollTo(), click());

        ViewInteraction textView8 = onView(
                allOf(withId(R.id.name), withText("user2"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        textView8.check(matches(withText("user2")));

        ViewInteraction textView9 = onView(
                allOf(withId(R.id.email), withText("user2@test.com"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        textView9.check(matches(withText("user2@test.com")));

        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.save), withContentDescription("Logout"), isDisplayed()));
        actionMenuItemView.perform(click());

        ViewInteraction textView10 = onView(
                allOf(withId(R.id.name), withText("testgroup"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        textView10.check(matches(withText("testgroup")));

        ViewInteraction textView11 = onView(
                allOf(withId(R.id.adminTextView), withText("Admin user1"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        textView11.check(matches(withText("Admin user1")));

        ViewInteraction textView12 = onView(
                allOf(withId(R.id.amountTextView), withText("CNY 500.0"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                0),
                        isDisplayed()));
        textView12.check(matches(withText("CNY 500.0")));

        ViewInteraction textView13 = onView(
                allOf(withId(R.id.groupSize), withText("2 Members"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                1),
                        isDisplayed()));
        textView13.check(matches(withText("2 Members")));

        ViewInteraction textView14 = onView(
                allOf(withId(R.id.debit), withText("250.0"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                1),
                        isDisplayed()));
        textView14.check(matches(withText("250.0")));

        ViewInteraction textView15 = onView(
                allOf(withId(R.id.credit), withText("0.0"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        textView15.check(matches(withText("0.0")));

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
