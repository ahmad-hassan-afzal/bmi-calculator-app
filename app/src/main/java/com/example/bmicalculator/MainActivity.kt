package com.example.bmicalculator

import android.app.Dialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import kotlin.math.roundToLong

class MainActivity : AppCompatActivity() {
    var height = 100f
    var weight = 40f
    var age = 15
    var gender = "male"

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getSupportActionBar()?.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar()?.setCustomView(R.layout.custom_appbar);

        val seekBar = findViewById<SeekBar>(R.id.seekbar_height)
        seekBar.min = 100
        seekBar.max = 250

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                height = progress.toFloat()
                findViewById<TextView>(R.id.txt_height).text = height.toInt().toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}

        })
    }

    fun modifyAge(view: View) {
        when (view.id){
            R.id.btn_age_minus -> if ( age > 0 )    age = age - 1
            R.id.btn_age_plus -> age = age + 1
        }
        findViewById<TextView>(R.id.txt_age).text = age.toString()
    }
    fun modifyWeight(view: View) {
        when (view.id){
            R.id.btn_weight_minus -> if ( weight > 0 )   weight = weight - 1
            R.id.btn_weight_plus -> weight = weight + 1
        }
        findViewById<TextView>(R.id.txt_weight).text = weight.toInt().toString()
    }

    fun modifyGender(view: View) {
        when (view.id){
            R.id.imgBtn_male -> {
                findViewById<ImageButton>(R.id.imgBtn_male).alpha = 1f
                gender = "male"
                findViewById<ImageButton>(R.id.imgBtn_female).alpha = 0.5f
            }
            R.id.imgBtn_female -> {
                findViewById<ImageButton>(R.id.imgBtn_female).alpha = 1f
                gender = "female"
                findViewById<ImageButton>(R.id.imgBtn_male).alpha = 0.5f
            }
        }
    }

    fun calculateBMI(view: View) {
        var bmi:Float = ((weight / height / height)*10000)

        var title = ""
        var color = R.color.green
        var msg = ""

        val dialog:Dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_bmi)
        dialog.findViewById<TextView>(R.id.txt_bmi).text = String.format("%.1f", bmi)

        if ( bmi < 10f ) {
            title = "Underweight"
            color = R.color.pink
            msg = "You are dangerously Underweight, Go Eat Something!!"
        } else if ( bmi >= 10f && bmi < 18.5f ) {
            title = "Underweight"
            color = R.color.yellow
            msg = "You are Underweight, Go Eat Something!"
        } else if( bmi >= 18.5f && bmi < 25f ) {
            title = "Normal"
            color = R.color.green
            msg = "You have Normal body weight (: Good Job!"
        } else if( bmi >= 25f && bmi < 30f ) {
            title = "Overweight"
            color = R.color.yellow
            msg = "You are Overweight, You should start burning calories!"
        } else {
            title = "Obese"
            color = R.color.pink
            msg = "Sorry to say but you should do something!"
        }

        dialog.findViewById<TextView>(R.id.txt_alert_title).text = title
        dialog.findViewById<TextView>(R.id.txt_alert_title).setTextColor(resources.getColor(color))
        dialog.findViewById<TextView>(R.id.txt_bmi).setTextColor(resources.getColor(color))
        dialog.findViewById<TextView>(R.id.txt_alert_msg).text = msg
        dialog.show()
    }
}