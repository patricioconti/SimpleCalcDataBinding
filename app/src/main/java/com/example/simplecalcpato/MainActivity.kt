package com.example.simplecalcpato

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.preference.PreferenceManager
import com.example.simplecalcpato.databinding.ActivityMainBinding
import com.example.simplecalcpato.viewmodel.CalcViewModel
import java.lang.IllegalArgumentException


//Tag used for Logcat
private const val TAG = "CalculatorActivity"


class MainActivity : AppCompatActivity() {

    //Initialize viewModel with delegate property
    private val viewModel: CalcViewModel by viewModels()


    //Initialize binding for using viewBinding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Define Binding an set content view
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_main)

        val view = binding.root
        setContentView(view)

        // Sets default values only once, first time this is called.
        // The third argument is a boolean that indicates whether
        // the default values should be set more than once. When false,
        // the system sets the default values only if this method has never
        // been called in the past.
        PreferenceManager.setDefaultValues(this, R.xml.root_preferences, false)

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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> {
                // Create an intent with a destination of SettingsActivity
                val intent = Intent(this, SettingsActivity::class.java)
                // Start an activity using destination from the Intent.
                startActivity(intent)
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}