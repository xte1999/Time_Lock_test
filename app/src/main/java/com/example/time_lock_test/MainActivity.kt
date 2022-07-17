package com.example.time_lock_test

//import android.os.CountDownTimer

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.*
import java.util.*


class MainActivity : AppCompatActivity() {
//    设定的条件1：点击次数满100次
//    val set_seconds = 100        //
    companion object {
        //   全局变量
        var IF_time_reach = false
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var button_1 = findViewById<android.widget.TextView>(R.id.button1)
        button_1.setOnClickListener{showpass3()}

        var imageButton = findViewById<android.widget.ImageButton>(R.id.imageButton)
        imageButton.setOnClickListener{
            if(IF_time_reach) {
                gotoset()
            }
            else
            {
                Toast.makeText(this,"不管怎样，坚持下去，失败了也不怕",Toast.LENGTH_SHORT).show()
            }
        }

        var editText_1 = findViewById<android.widget.TextView>(R.id.editText_1)
        val androidId: String =
            Settings.System.getString(contentResolver, Settings.Secure.ANDROID_ID)
        editText_1.setText(androidId)
        if(androidId != "93aca999840c3a78")
            finish()
//        start_timer()
//        var timer = Timer()
//        var task = TimerTask() {
//            override fun run(){
//
//            }
//        }
//        timer.schedule()
    }

//    private fun show_password(set_seconds:Int){ //计时器、点击次数 两个条件 满足后显示密码
//        if(Companion.globali >= set_seconds && Companion.IF_time_reach==true)
//        {
//            var editText_1 = findViewById<android.widget.TextView>(R.id.editText_1)
//            editText_1.setText("9721003617230341")
//        }
//        else if(Companion.globali < set_seconds)
//        {   Companion.globali = Companion.globali +1
//            show_number(Companion.globali)
//        }
//    }


//    private fun showpass2(){ //通过单日时间判断是否需要展示密码
//        val rightnow = Calendar.getInstance()
//        var editText_1 = findViewById<android.widget.TextView>(R.id.editText_1)
//        val setTimeAarry = arrayOf(12,0,0)
//        val setTimeAarry2 = arrayOf(22,0,0)
//        val s1 = rightnow.get(Calendar.YEAR).toString() + "-" + rightnow.get(Calendar.MONTH).toString() + "-" + rightnow.get(Calendar.DAY_OF_MONTH).toString() + " " + rightnow.get(Calendar.HOUR_OF_DAY).toString() + ":" + rightnow.get(Calendar.MINUTE).toString() +":" + rightnow.get(Calendar.SECOND).toString() +"." + rightnow.get(Calendar.MILLISECOND).toString()
//        if(rightnow.get(Calendar.HOUR_OF_DAY)>= setTimeAarry[0] && rightnow.get(Calendar.HOUR_OF_DAY)<= setTimeAarry2[0])
//        {
//            editText_1.setText("9721003617230341")
//        }
//    }
    private fun gotoset(){
        val intent = Intent(this, InputTime_Activity::class.java)
        startActivity(intent)
    }

    private fun fileIsExists(filePath: String): Boolean {
        try {
            val f = File(filePath)
            if (!f.exists()) {
                return false
            }
        } catch (e: Exception) {
            return false
        }
        return true
    }



    private fun get_saved(filename:String): List<Int> {
        val content = StringBuffer()

        try{
            val input = openFileInput(filename)
            val reader = BufferedReader(InputStreamReader(input))
            reader.use { reader.forEachLine {content.append(it) }  }
        }
        catch (e:IOException){e.printStackTrace()}

        val aray_split = content.toString().split('/')
        val result = aray_split.map { it.toInt() }
        return result
    }

    private fun showpass3(){
        val filename = "/data/data/com.example.time_lock_test/files/data_set.txt"
        if(fileIsExists(filename))
        {
            val DeadlineAarry = get_saved("data_set.txt")
            val rightnow = Calendar.getInstance()
            rightnow.setTimeZone(TimeZone.getTimeZone("GMT+8:00"))
            val nowAarry = arrayOf(rightnow.get(Calendar.YEAR), rightnow.get(Calendar.MONTH)+1,rightnow.get(Calendar.DAY_OF_MONTH),rightnow.get(Calendar.HOUR_OF_DAY),rightnow.get(Calendar.MINUTE))
            for(index in DeadlineAarry.indices)
            {
                if(DeadlineAarry[index]!=nowAarry[index])
                {
                    if(DeadlineAarry[index]<nowAarry[index])
                    {
                        var editText_1 = findViewById<android.widget.TextView>(R.id.editText_1)
                        editText_1.setText("1")
                        IF_time_reach = true
                        break
                    }
                    else
                    {
//                    var editText_1 = findViewById<android.widget.TextView>(R.id.editText_1)
//                    val s1 = nowAarry[0].toString() + '/'+nowAarry[1].toString()+"/"+nowAarry[2].toString()+' '+nowAarry[3].toString()+':'+nowAarry[4].toString()
//
//                    editText_1.setText("现在时间为 "+ s1 )
                        val intent = Intent(this, PictureActivity::class.java)
                        startActivity(intent)
                        break
                    }
                }

            }
        }
        else
            gotoset()

    }

//    private fun showpass4(){
//
//    }
//    private fun show_number(seconds:Int){
//        var Textview_1 = findViewById<android.widget.TextView>(R.id.Textview_1)
//        Textview_1.setText(seconds.toString())
//    }

//    private fun start_timer(){
//        val countDownTimer: CountDownTimer by lazy  {
//            object : CountDownTimer(600000,1000){
//            override fun onFinish() {
//                Companion.IF_time_reach = true
//            }
//
//            override fun onTick(millisUntilFinished: Long) {
//                val text = millisUntilFinished.toString() + "毫秒"
//                var Textview_time = findViewById<android.widget.TextView>(R.id.Timer_show)
//                Textview_time.setText(text)
//            }
//        }
//        }
//        countDownTimer.start()
//    }
}
