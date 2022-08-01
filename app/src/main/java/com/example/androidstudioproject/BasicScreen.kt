package com.example.androidstudioproject

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class BasicScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.basic_screen)
        if(getSharedPreferences("sharedPref", 0)!=null) {
            val pref = getSharedPreferences("sharedPref", 0)
            val hour = pref.getInt("hour", 0)
            val minute = pref.getInt("minute", 0)
            var conversionTime = "0"+hour.toString() + minute.toString() + "00"
            countDown(conversionTime)

            val userInfo = findViewById<TextView>(R.id.userInfo) as TextView
            val grade = pref.getInt("grade", 0)
            val usergrade = grade.toString() + "학년"
            userInfo.setText(usergrade)

            val userInfo2 = findViewById<TextView>(R.id.userInfo2) as TextView
            val difficulty = pref.getString("difficulty", "0")

        }
    }

    fun countDown(time : String){
        val lefttime = findViewById<TextView>(R.id.leftTime) as TextView
        var conversionTime: Long = 0

        // 1000 단위가 1초
        // 60000 단위가 1분
        // 60000 * 3600 = 1시간

        var getHour = time.substring(0, 2)
        var getMin = time.substring(2, 4)
        var getSecond = time.substring(4, 6)

        // "00"이 아니고, 첫번째 자리가 0 이면 제거
        if (getHour.substring(0, 1) == "0") {
            getHour = getHour.substring(1, 2)
        }

        if (getMin.substring(0, 1) == "0") {
            getMin = getMin.substring(1, 2)
        }

        if (getSecond.substring(0, 1) == "0") {
            getSecond = getSecond.substring(1, 2)
        }

        // 변환시간
        conversionTime = getHour.toLong() * 1000 * 3600 + getMin.toLong() * 60 * 1000 + getSecond.toLong() * 1000

        // 첫번쨰 인자 : 원하는 시간 (예를들어 30초면 30 x 1000(주기))
        // 두번쨰 인자 : 주기( 1000 = 1초)
        object : CountDownTimer(conversionTime, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                // 시간단위
                var hour = (millisUntilFinished / (60 * 60 * 1000)).toString()

                // 분단위
                val getMin :Long = millisUntilFinished - (millisUntilFinished / (60 * 60 * 1000))
                var min = (getMin / (60 * 1000)).toString() // 몫

                // 초단위
                var second = ((getMin % (60 * 1000)) / 1000).toString() // 나머지

                // 시간이 한자리면 0을 붙인다
                if (hour.length == 1) {
                    hour = "0$hour"
                }

                // 분이 한자리면 0을 붙인다
                if (min.length == 1) {
                    min = "0$min"
                }

                // 초가 한자리면 0을 붙인다
                if (second.length == 1) {
                    second = "0$second"
                }

                lefttime.setText("$hour:$min:$second")
            }

            override fun onFinish() {
                lefttime.setText("시간종료!")
            }

        }.start()
    }

    fun retryProblemClicked(v : View){
        startActivity(Intent(this, RetryProblemScreen::class.java))
    }

    fun settingClicked(v : View){
        startActivity(Intent(this, SettingScreen::class.java))
    }

    fun helpClicked(v : View){
        startActivity(Intent(this, HelpScreen::class.java))
    }

    fun problemSolveClicked(v : View){
        startActivity(Intent(this, ProblemSolveScreen::class.java))
    }

    fun todaySolveClicked(v : View){
        startActivity(Intent(this, TodaySolveScreen::class.java))
    }

    fun wrongProblemClicked(v : View){
        startActivity(Intent(this, WrongProblemScreen::class.java))
    }
}