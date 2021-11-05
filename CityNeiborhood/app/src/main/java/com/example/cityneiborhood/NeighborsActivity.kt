package com.example.cityneiborhood

import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.cityneighborhood.R
import com.google.gson.Gson
import java.io.InputStreamReader

class NeighborsActivity : AppCompatActivity() {
    lateinit var neighbors: Cities
    private var selectedCityIndex: Int = 0
    private var cityRadius: Float = 0.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_neighbors)

        loadData()

        val neighborsInRadius = neighborsInRadius()
        findViewById<TextView>(R.id.city_neighbors).text = neighbors.cities
            .filterIndexed { _, city -> city.id in neighborsInRadius }
            .joinToString { it.name }
    }

    private fun neighborsInRadius(): List<Int> {
        val neighborsInRadius = mutableListOf<Int>()

        for (i in neighbors.cities.indices) {
            if (i != selectedCityIndex) {
                val dist = FloatArray(1)
                Location.distanceBetween(
                    neighbors.cities[i].coord.lat, neighbors.cities[i].coord.lon,
                    neighbors.cities[selectedCityIndex].coord.lat,
                    neighbors.cities[selectedCityIndex].coord.lon,
                    dist
                )
                if (dist.first() <= cityRadius) {
                    neighborsInRadius.add(neighbors.cities[i].id)
                }
            }
        }
        return neighborsInRadius
    }

    private fun loadData() {
        val moviesStream = resources.openRawResource(R.raw.city_list)
        val gson = Gson()
        neighbors = gson.fromJson(InputStreamReader(moviesStream), Cities::class.java)

        selectedCityIndex = intent.getIntExtra("selectedCityIndex",0)
        cityRadius = intent.getFloatExtra("cityRadius", 100.0f)
    }
}