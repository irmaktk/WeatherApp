package com.example.weatherapp.data

import com.example.weatherapp.data.database.ForecastDB
import com.example.weatherapp.domain.ForecastDataSource
import com.example.weatherapp.domain.ForecastList

class ForecastServer(private val dataMapper: ServerDataMapper = ServerDataMapper(),
                     private val forecastDb: ForecastDB = ForecastDB()) : ForecastDataSource {

    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? {
        val result = ForecastByZipCodeRequest(zipCode).execute()
        val converted = dataMapper.convertToDomain(zipCode, result)
        forecastDb.saveForecast(converted)
        return forecastDb.requestForecastByZipCode(zipCode, date)
    }

    override fun requestDayForecast(id: Long) = throw UnsupportedOperationException()
}