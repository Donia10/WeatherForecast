package com.example.weatherforecast.model.local

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import com.google.gson.annotations.Expose

data class NameTuple (@NonNull@ColumnInfo(name = "lat") @Expose var lat: Double,
                     @NonNull @ColumnInfo(name = "lon") @Expose var lon: Double)
