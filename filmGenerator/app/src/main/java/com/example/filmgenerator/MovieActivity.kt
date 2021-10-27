package com.example.filmgenerator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import java.io.InputStreamReader
import java.text.SimpleDateFormat

class MovieActivity : AppCompatActivity() {

    lateinit var moviesData: Movies
    private var movieIndex2Show: Int = 0
    private var proposedMovieNumber: Int = 0
    lateinit var showedMovies: MutableList<UByte>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        findViewById<Button>(R.id.get_movie_btn)
            .setOnClickListener{
                onGetRandomMovie()
            }

        loadData()
        onGetRandomMovie()
    }

    private fun loadData() {
        val moviesStream = resources.openRawResource(R.raw.movies)
        val gson = Gson()
        moviesData = gson.fromJson(InputStreamReader(moviesStream), Movies::class.java)

        val pref = getPreferences(Context.MODE_PRIVATE)
        showedMovies = pref.getString("showedMovies", "")?.splitToSequence(",")?.filter { it.isNotEmpty() }?.map { it.toUByte() }?.toMutableList()!!
        Log.d("showedMovies", showedMovies.joinToString(", "))

        if (showedMovies.size < moviesData.movies.size) {
            for (idx in showedMovies) {
                val lastMovieIndex = moviesData.movies.size - proposedMovieNumber - 1

                val movieIndex2Change = moviesData.movies.indexOfFirst { it.info.rank == idx }
                moviesData.movies[movieIndex2Change] = moviesData.movies[lastMovieIndex]
                    .also { moviesData.movies[lastMovieIndex] = moviesData.movies[movieIndex2Change] }

                proposedMovieNumber += 1
            }
        } else {
            showedMovies.clear()
        }
    }


    private fun updateMovie() {
        val movieImage = findViewById<ImageView>(R.id.movie_img)

        Glide.with(this)
            .load(moviesData.movies[movieIndex2Show].info.imageURL)
            .error(R.drawable.ic_launcher_foreground)
            .into(movieImage)

        showTitle()
        showRating()
        showDirectors()
        showGenres()
        showReleaseDate()
        showRunningTime()
        showActors()
        showPlot()
    }

    private fun showPlot() {
        findViewById<TextView>(R.id.movie_plot)
            .text = moviesData.movies[movieIndex2Show].info.plot
    }

    private fun showActors() {
        if (moviesData.movies[movieIndex2Show].info.actors.isNotEmpty()) {
            findViewById<TextView>(R.id.field_actors).text = resources.getString(R.string.actors)

            findViewById<TextView>(R.id.movie_actors)
                .text = moviesData.movies[movieIndex2Show].info.actors.joinToString()
        } else {
            findViewById<TextView>(R.id.field_actors).text = ""
        }
    }

    private fun showRunningTime() {
        if (moviesData.movies[movieIndex2Show].info.runningTimeSecs != 0u) {
            findViewById<TextView>(R.id.field_running_time).text = resources.getString(R.string.running_time)

            findViewById<TextView>(R.id.movie_running_time)
                .text = (moviesData.movies[movieIndex2Show].info.runningTimeSecs / 60u).toString() + " min"
        } else {
            findViewById<TextView>(R.id.field_running_time).text = ""
        }
    }

    private fun showReleaseDate() {
        findViewById<TextView>(R.id.movie_release_date)
            .text = SimpleDateFormat("MM-dd-yyyy")
            .format(moviesData.movies[movieIndex2Show].info.releaseDate)
    }

    private fun showGenres() {
        if (moviesData.movies[movieIndex2Show].info.genres.isNotEmpty()) {
            findViewById<TextView>(R.id.field_genres).text = resources.getString(R.string.movie_genres)

            findViewById<TextView>(R.id.movie_genres)
                .text = moviesData.movies[movieIndex2Show].info.genres.joinToString()
        } else {
            findViewById<TextView>(R.id.field_genres).text = ""
        }
    }

    private fun showTitle() {
        findViewById<TextView>(R.id.movie_title)
            .text = moviesData.movies[movieIndex2Show].title
    }

    private fun showRating() {
        val ratingField = findViewById<TextView>(R.id.movie_rating)
        if (moviesData.movies[movieIndex2Show].info.rating != 0.0f) {
            ratingField.text = moviesData.movies[movieIndex2Show].info.rating.toString()
        } else {
            ratingField.text = ""
        }
    }

    private fun showDirectors() {
        if (moviesData.movies[movieIndex2Show].info.directors.isNotEmpty()) {
            findViewById<TextView>(R.id.field_directors).text = resources.getString(R.string.movie_director)

            findViewById<TextView>(R.id.movie_directors)
                .text = moviesData.movies[movieIndex2Show].info.directors.joinToString()
        } else {
            findViewById<TextView>(R.id.field_directors).text = ""
        }
    }

    private fun onGetRandomMovie() {
        if (proposedMovieNumber >= moviesData.movies.size) {
            proposedMovieNumber = 0
        }

        val lastMovieIndex = moviesData.movies.size - proposedMovieNumber - 1

        movieIndex2Show = (0..lastMovieIndex).random()
        updateMovie()
        // add showed movie
        showedMovies.add(moviesData.movies[movieIndex2Show].info.rank)

        moviesData.movies[lastMovieIndex] = moviesData.movies[movieIndex2Show]
            .also { moviesData.movies[movieIndex2Show] = moviesData.movies[lastMovieIndex] }

        proposedMovieNumber += 1

        val pref = getPreferences(Context.MODE_PRIVATE)
        val editor = pref.edit()

        editor.putString("showedMovies", showedMovies.joinToString(","))
        editor.apply()
    }
}