package com.example.simplecalcpato

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.simplecalcpato.databinding.ActivityMainBinding
import java.lang.IllegalArgumentException



private const val TAG = "CalculatorActivity"


class MainActivity : AppCompatActivity() {

    //Initialize viewModel with delegate property
    private val viewModel: CalcViewModel by viewModels()


    //Initialize binding for using viewBinding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Define Binding an set content view
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main)

        val view = binding.root
        setContentView(view)


        //Bind viewModel with calcViewModel on the XML file
        binding.calcViewModel = viewModel

        // Specify the current activity as the lifecycle owner.
        binding.lifecycleOwner = this

        //Set onClick listeners for each button
        binding.operationAddBtn.setOnClickListener {
            viewModel.add(
                getOperandOne(binding.operandOneEditText),
                getOperandTwo(binding.operandTwoEditText)
            )
        }

        binding.operationSubBtn.setOnClickListener {
            viewModel.sub(
                getOperandOne(binding.operandOneEditText),
                getOperandTwo(binding.operandTwoEditText)
            )

        }
        binding.operationMulBtn.setOnClickListener {
            viewModel.mul(
                getOperandOne(binding.operandOneEditText),
                getOperandTwo(binding.operandTwoEditText)
            )
        }


        //Use try and catch for division by zero
        binding.operationDivBtn.setOnClickListener {
            try {
                viewModel.div(
                    getOperandOne(binding.operandOneEditText),
                    getOperandTwo(binding.operandTwoEditText)
                )
            } catch (iae: IllegalArgumentException) {
                Log.e(TAG, "IllegalArgumentException", iae)
                binding.operationResultTextView.text = getString(R.string.computationError)
            }

        }

    }

    /**
     * @return the operandOne value entered in an EditText as double.
     */
    private fun getOperandOne(operandEditText: EditText?): Double {
        //I fix the bug for NumberFormatException using to Double Or Null.
        // If OperandEditText.text is empty then is null
        //And if it is null with elvis operator (?:) return 0.0
        val operandOne = operandEditText?.text?.toString()?.toDoubleOrNull() ?: 0.0
        //Set text to 0.0 on operand edit text if it is empty
        if (operandOne == 0.0) {
            binding.operandOneEditText.setText("0")
        }
        return operandOne
    }

    /**
     * @return the operand value entered in an EditText as double.
     */
    private fun getOperandTwo(operandEditText: EditText?): Double {
        //I fix the bug for NumberFormatException using to Double Or Null.
        // If OperandEditText.text is empty then is null
        //And if it is null with elvis operator (?:) return 0.0
        val operandTwo = operandEditText?.text?.toString()?.toDoubleOrNull() ?: 0.0
        //Set text to 0.0 on operand edit text if it is empty
        if (operandTwo == 0.0) {
            binding.operandTwoEditText.setText("0")
        }
        return operandTwo

    }

}