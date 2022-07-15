package com.example.simplecalcpato

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.simplecalcpato.DrawableMatcher.withDrawable
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class CalculatorInstrumentedTest {
    //Rule that executes main activity before test
    @get:Rule
    val activity = ActivityScenarioRule(MainActivity::class.java)

    // Add two numbers
    @Test
    fun add_two_numbers() {
        //Type 20 on operand one edit text
        Espresso.onView(ViewMatchers.withId(R.id.operand_one_edit_text))
            .perform(ViewActions.typeText("20"))

        //Type 53 on operand two edit text
        Espresso.onView(ViewMatchers.withId(R.id.operand_two_edit_text))
            .perform(ViewActions.typeText("53"))

            //Close soft keyboard to reach operator button button
            .perform(ViewActions.closeSoftKeyboard())

        //Click Add Operation button
        Espresso.onView(ViewMatchers.withId(R.id.operation_add_btn))
            .perform(ViewActions.click())

        //Check Operation result view matches expected value.
        Espresso.onView(ViewMatchers.withId(R.id.operation_result_text_view))
            .check(ViewAssertions.matches(ViewMatchers.withText(Matchers.containsString("73.0"))))

        //Check OperationImageView matches the expected drawable resource
        Espresso.onView(withId(R.id.operation_image)).check(
            ViewAssertions.matches(withDrawable(R.drawable.ic_baseline_add_24))
        )

    }

    // Sub two numbers
    @Test
    fun sub_two_numbers() {
        //Type 12.5 on operand one edit text
        Espresso.onView(ViewMatchers.withId(R.id.operand_one_edit_text))
            .perform(ViewActions.typeText("12.5"))

        //Type 14.5 on operand two edit text
        Espresso.onView(ViewMatchers.withId(R.id.operand_two_edit_text))
            .perform(ViewActions.typeText("14.5"))

            //Close soft keyboard to reach operator button button
            .perform(ViewActions.closeSoftKeyboard())

        //Click Sub Operation button
        Espresso.onView(ViewMatchers.withId(R.id.operation_sub_btn))
            .perform(ViewActions.click())

        //Check Operation result view matches expected value.
        Espresso.onView(ViewMatchers.withId(R.id.operation_result_text_view))
            .check(ViewAssertions.matches(ViewMatchers.withText(Matchers.containsString("-2.0"))))

        //Check OperationImageView matches the expected drawable resource
        Espresso.onView(withId(R.id.operation_image)).check(
            ViewAssertions.matches(withDrawable(R.drawable.ic_baseline_sub_24))
        )

    }

    // mul two numbers
    @Test
    fun mul_two_numbers() {
        //Type 20.2 on operand one edit text
        Espresso.onView(ViewMatchers.withId(R.id.operand_one_edit_text))
            .perform(ViewActions.typeText("20.2"))

        //Type 14.5 on operand two edit text
        Espresso.onView(ViewMatchers.withId(R.id.operand_two_edit_text))
            .perform(ViewActions.typeText("8.5"))

            //Close soft keyboard to reach operator button button
            .perform(ViewActions.closeSoftKeyboard())

        //Click Mul Operation button
        Espresso.onView(ViewMatchers.withId(R.id.operation_mul_btn))
            .perform(ViewActions.click())

        //Check Operation result view matches expected value.
        Espresso.onView(ViewMatchers.withId(R.id.operation_result_text_view))
            .check(ViewAssertions.matches(ViewMatchers.withText(Matchers.containsString("171.7"))))

        //Check OperationImageView matches the expected drawable resource
        Espresso.onView(withId(R.id.operation_image)).check(
            ViewAssertions.matches(withDrawable(R.drawable.ic_baseline_mul_24))
        )

    }






    // Div two numbers
    @Test
    fun divide_two_numbers() {
        //Type 100 on operand one edit text
        Espresso.onView(ViewMatchers.withId(R.id.operand_one_edit_text))
            .perform(ViewActions.typeText("100"))

        //Type 50 on operand two edit text
        Espresso.onView(ViewMatchers.withId(R.id.operand_two_edit_text))
            .perform(ViewActions.typeText("50"))

            //Close soft keyboard to reach operator button button
            .perform(ViewActions.closeSoftKeyboard())

        //Click Sub Operation button
        Espresso.onView(ViewMatchers.withId(R.id.operation_div_btn))
            .perform(ViewActions.click())

        //Check Operation result view matches expected value.
        Espresso.onView(ViewMatchers.withId(R.id.operation_result_text_view))
            .check(ViewAssertions.matches(ViewMatchers.withText(Matchers.containsString("2.0"))))

        //Check OperationImageView matches the expected drawable resource
        Espresso.onView(withId(R.id.operation_image)).check(
            ViewAssertions.matches(withDrawable(R.drawable.ic_baseline_div_24))
        )

    }



}

/**
 * Custom matcher to find drawable.
 */
object DrawableMatcher {

    fun withDrawable(@DrawableRes resourceId: Int): Matcher<View> {
        return object : BoundedMatcher<View, ImageView>(ImageView::class.java) {
            override fun describeTo(description: Description?) {
                description!!.appendText("has drawable resource $resourceId")
            }

            override fun matchesSafely(imageView: ImageView): Boolean {
                return isSameBitmap(imageView, imageView.drawable, resourceId)
            }
        }
    }

    private fun isSameBitmap(item: View, drawable: Drawable?, expectedResId: Int): Boolean {
        val image = item as ImageView
        if (expectedResId < 0) {
            return image.drawable == null
        }
        val expectedDrawable: Drawable? = ContextCompat.getDrawable(item.context, expectedResId)
        if (drawable == null || expectedDrawable == null) {
            return false
        }
        // Make tint consistent just in case they differ
        val bitmap = getBitmap(drawable)
        val expectedBitmap = getBitmap(expectedDrawable)
        return bitmap.sameAs(expectedBitmap)
    }

    /**
     * Convert vector drawable to bitmap
     * @param drawable vector drawable
     */
    private fun getBitmap(drawable: Drawable): Bitmap {
        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }
}
