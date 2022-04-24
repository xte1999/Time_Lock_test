package com.example.time_lock_test

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.loper7.date_time_picker.DateTimeConfig
import com.loper7.date_time_picker.DateTimePicker
import java.time.Instant
//import com.loper7.date_time_picker.number_picker.NumberPicker
import java.io.*
import java.util.*

class  InputTime_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
//        val YEAR_ = 0
//        val MONTH_ = 1
//        val DAY = 2
//        val HOUR = 3
//        val MIN = 4
//        val SECOND = 5
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_time)
        val dateTimePicker = findViewById<DateTimePicker>(R.id.dateTimePicker)
        dateTimePicker.setGlobal(DateTimeConfig.GLOBAL_CHINA)


        val nowmillis = System.currentTimeMillis()+518400000
        dateTimePicker.setMinMillisecond(nowmillis)
        dateTimePicker.setDefaultMillisecond(nowmillis)


        val buttonok = findViewById<android.widget.TextView>(R.id.buttonok)
        buttonok.setOnClickListener{save_time()}

        val buttonno = findViewById<android.widget.TextView>(R.id.buttonno)
        buttonno.setOnClickListener{finish()}

    }



    private fun save_time(){
        var dateTimePicker = findViewById<DateTimePicker>(R.id.dateTimePicker)
//        val year_value = dateTimePicker.getPicker(0)?.getValue()
//        val MONTH_value = dateTimePicker.getPicker(1)?.getValue()
//        val DAY_value = dateTimePicker.getPicker(2)?.getValue()
//        val HOUR_value = dateTimePicker.getPicker(3)?.getValue()
        // datastring为设置的日期字符串
        var datastring=""
        for(i in 0..4){
            datastring += dateTimePicker.getPicker(i)?.getValue().toString()
            if(i!=4) datastring += '/'
        }

        try{
            val output = openFileOutput("data_set.txt", Context.MODE_PRIVATE)
            val writer = BufferedWriter(OutputStreamWriter(output))
            writer.use { it.write(datastring)}
            finish()
            }
        catch(e:IOException) {e.printStackTrace()}

    }


}