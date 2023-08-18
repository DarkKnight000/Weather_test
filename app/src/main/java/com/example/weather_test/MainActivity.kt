package com.example.weather_test

import android.app.PendingIntent.getActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.security.AccessController.getContext

class MainActivity : AppCompatActivity()
{
    val API_KEY = "61a9718efb334e05b5a142135231708"

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Вызов метода при запуске приложения
        getResult("Krasnodar")
    }

    private fun getResult(name: String)
    {
        val url = "https://api.weatherapi.com/v1/forecast.json?q=$name&days=5&key=$API_KEY"
        //  Список для хранения данных
        val forecastList = mutableListOf<WeatherForecast>()

        val queue = Volley.newRequestQueue(this)

        // Запрос на получение данных
        val stringRequest = StringRequest(Request.Method.GET,
            url,
            {
                    response ->

                val obj = JSONObject(response)
                val forecast = obj.getJSONObject("forecast")
                val forecastDays = forecast.getJSONArray("forecastday")
                Log.d("MyLog", "$forecast")

                // Добавление данных в список
                for (i in 0 until forecastDays.length())
                {
                    val forecastDay = forecastDays.getJSONObject(i)
                    val day = forecastDay.getJSONObject("day")
                    val date = forecastDay.getString("date")
                    val avgtempC = day.getDouble("avgtemp_c")
                    val maxwindKph = day.getDouble("maxwind_kph")
                    val avghumidity = day.getDouble("avghumidity")

                    val condition = day.getJSONObject("condition")
                    val conditionText = condition.getString("text")
                    val conditionIcon = "https:" + condition.getString("icon")

                    val weatherForecast = WeatherForecast(
                        date = date,
                        avgTempC = avgtempC,
                        maxWindKph = maxwindKph,
                        avgHumidity = avghumidity,
                        conditionText = conditionText,
                        conditionIcon = conditionIcon
                    )

                    forecastList.add(weatherForecast)
                }

                // Вывод данных в recyclerview
                val recyclerView: RecyclerView = findViewById(R.id.weatherRecyclerView)
                val layoutManager = LinearLayoutManager(this)
                recyclerView.layoutManager = layoutManager
                val adapter = WeatherForecastAdapter(forecastList)
                recyclerView.adapter = adapter

            },
            {
                Log.d("MyLog", "Volley error: $it")
            })

        queue.add(stringRequest)
    }

    // Обновить данные
    fun getRes(view: View)
    {
        getResult("Krasnodar")
    }

}