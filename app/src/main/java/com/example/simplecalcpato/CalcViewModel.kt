package com.example.simplecalcpato

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

enum class Operation {
    ADD,
    SUB,
    MUL,
    DIV
}

class CalcViewModel : ViewModel() {


    // The internal MutableLiveData that stores the status of the most recent request
    private val _operation = MutableLiveData<Operation>()

    // The external immutable LiveData for the request status
    val operation: LiveData<Operation> = _operation

    //Result number of the operation as LiveData and encapsulated
    private val _result = MutableLiveData<Double>()
    val result: LiveData<Double>
        get() = _result

     /**
     * Addition operation
     */
    fun add(firstOperand: Double, secondOperand: Double) {
         _operation.value = Operation.ADD
        _result.value = firstOperand + secondOperand
    }

    /**
     * Subtract operation
     */
    fun sub(firstOperand: Double, secondOperand: Double) {
        _operation.value = Operation.SUB
        _result.value = firstOperand - secondOperand
    }

    /**
     * Multiplication operation
     */
    fun mul(firstOperand: Double, secondOperand: Double) {
        _operation.value = Operation.MUL
        _result.value = firstOperand * secondOperand
    }


    /**
     * Divide operation
     */
    fun div(firstOperand: Double, secondOperand: Double) {
        _operation.value = Operation.DIV
        _result.value = firstOperand / secondOperand
    }


}

