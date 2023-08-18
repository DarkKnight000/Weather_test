package com.example.weather_test

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class WeatherForecastAdapter(private val forecastList: List<WeatherForecast>) :
    RecyclerView.Adapter<WeatherForecastAdapter.ViewHolder>()
{

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        private val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
        private val avgTempTextView: TextView = itemView.findViewById(R.id.avgTempTextView)
        private val speedTextView: TextView = itemView.findViewById(R.id.speedTextView)
        private val vlTextView: TextView = itemView.findViewById(R.id.vlTextView)
        private val descTextView: TextView = itemView.findViewById(R.id.descTempTextView)
        private val iconImageView: ImageView = itemView.findViewById(R.id.conditionIconImageView)

        fun bind(item: WeatherForecast)
        {
            dateTextView.text = item.date                       // Дата
            avgTempTextView.text = "${item.avgTempC}°C"         // Средняя температура
            speedTextView.text = "${item.maxWindKph} км/ч"      // Скорость ветра
            vlTextView.text = "${item.avgHumidity}%"            // Влажность
            descTextView.text = item.conditionText              // Описание
            Glide.with(itemView.context).load(item.conditionIcon).into(iconImageView)   // Установка изображения

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_weather_forecast, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.bind(forecastList[position])
    }

    override fun getItemCount(): Int = forecastList.size
}