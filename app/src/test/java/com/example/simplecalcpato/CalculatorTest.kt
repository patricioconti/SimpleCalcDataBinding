package com.example.simplecalcpato

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.simplecalcpato.viewmodel.CalcViewModel
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class CalculatorTest {

    //To specify that LiveData objects should not call the main thread
    // we need to provide a specific test rule any time we are testing a LiveData object
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    //viewModel lateinit to use it outside setup().
    private lateinit var viewModel: CalcViewModel

    //Before annotation to use CalcViewModel on every test and avoid code repetition
    @Before
    fun setup() {
        //Call viewModel
        viewModel = CalcViewModel()
        //when testing the values of a LiveData object,
        // the objects need to be observed in order for changes to be emitted
        viewModel.result.observeForever {}
    }

    @Test
    fun add_two_numbers() {
        viewModel.add(1.0,1.0)
        //Use result.value because of LiveData
        assertEquals(2.0, viewModel.result.value)
        }

    @Test
    fun add_two_numbers_negative() {
        viewModel.add(-2.0,-3.0)
        assertEquals(-5.00, viewModel.result.value)
    }

    @Test
    fun add_two_numbers_float() {
        viewModel.add(1.111,1.111)
        assertEquals(2.222, viewModel.result.value)
           }

    @Test
    fun sub_two_numbers() {
        viewModel.sub(8.0,5.0)
        assertEquals(3.0, viewModel.result.value)
    }

    @Test
    fun sub_work_with_negative_result() {
        viewModel.sub(6.0,10.0)
        assertEquals(-4.0, viewModel.result.value)
    }

    @Test
    fun mul_two_numbers() {
        viewModel.mul(5.2,6.3)
        assertEquals(32.76, viewModel.result.value)
    }

    @Test
    fun mul_two_numbers_zero() {
        viewModel.mul(5.5,0.0)
        assertEquals(0.0, viewModel.result.value)
    }

    @Test
    fun div_two_numbers() {
        viewModel.div(10.0,5.0)
        assertEquals(2.0, viewModel.result.value)
    }

    @Test
    fun div_work_with_float_result() {
        viewModel.div(5.8,2.2)
        assertEquals(2.6363, viewModel.result.value!!, 0.0001)
    }

    @Test
    fun div_two_numbers_zero() {
        viewModel.div(32.0,0.0)
        assertEquals(Double.POSITIVE_INFINITY, viewModel.result.value)
    }

}