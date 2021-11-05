package com.example.cityneighborhood

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.cityneiborhood.Cities
import com.example.cityneiborhood.NeighborsActivity
import com.google.gson.Gson
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    lateinit var neighbors: Cities
    lateinit var citiesName: MutableList<String>
    private var selectedCityIndex: Int = 0
    private var cityRadius: Float = 0.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.neighbors_btn).setOnClickListener {
            onNeighborsBtnClick()
        }

        loadData()

        val spinner = findViewById<Spinner>(R.id.spinner_city)
        val adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, citiesName)
        spinner.adapter = adapter
    }

    private fun loadData() {
        val moviesStream = resources.openRawResource(R.raw.city_list)
        val gson = Gson()
        neighbors = gson.fromJson(InputStreamReader(moviesStream), Cities::class.java)

        val pref = getPreferences(Context.MODE_PRIVATE)
        selectedCityIndex = pref.getInt("selectedCityIndex", 0)
        cityRadius = pref.getFloat("cityRadius", 0.0f)

        citiesName = ArrayList()
        for (i in neighbors.cities.indices) {
            citiesName.add(neighbors.cities[i].name)
        }
    }

    private fun onNeighborsBtnClick() {
        val city = findViewById<Spinner>(R.id.spinner_city)
        val cityRadiusView = findViewById<EditText>(R.id.city_radius)
        val btn = findViewById<Button>(R.id.neighbors_btn)

        selectedCityIndex = neighbors.cities.indexOfFirst { it.name == city.selectedItem }

        val pref = getPreferences(Context.MODE_PRIVATE)
        val editor = pref.edit()

        editor.putInt("selectedCityIndex", selectedCityIndex)
        editor.putFloat("cityRadius", cityRadius)

        if (cityRadiusView.text.isNullOrEmpty()) {
            findViewById<TextView>(R.id.error_field).text = "Enter city radius"
            return
        } else {
            findViewById<TextView>(R.id.error_field).text = ""
            cityRadius = cityRadiusView.text.toString().toFloat()
        }

        val neighborActivity = Intent(this, NeighborsActivity::class.java)
        neighborActivity.putExtra("selectedCityIndex", selectedCityIndex)
        neighborActivity.putExtra("cityRadius", cityRadius)

        startActivity(neighborActivity)
    }
}