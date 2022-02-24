package com.example.room_yehudamelvin_32

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.room_yehudamelvin_32.room.Constant
import com.example.room_yehudamelvin_32.room.MovieDb
import com.example.room_yehudamelvin_32.room.movie
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddActivity : AppCompatActivity() {

    val db by lazy { MovieDb(this) }
    private var movieId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        setupView()
        setupListener()


    }

    fun setupView(){
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val intentType = intent.getIntExtra("intent_type", 0)
        when(intentType) {
            Constant.TYPE_CREATE -> {
                btn_update.visibility = View.GONE
            }
            Constant.TYPE_READ -> {
                btn_save.visibility = View.GONE
                btn_update.visibility = View.GONE
                getMovie()
            }
            Constant.TYPE_UPDATE ->{
                btn_save.visibility = View.GONE
                getMovie()
            }
        }
    }

    fun setupListener(){
        btn_save.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.movieDao().addMovie(
                    movie(0, et_tittle.text.toString(),
                    et_description.text.toString())
                )

                finish()
            }
        }
        btn_update.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.movieDao().updateMovie(
                    movie( movieId, et_tittle.text.toString(),
                        et_description.text.toString())
                )

                finish()
            }
        }
    }

    fun getMovie() {
        movieId = intent.getIntExtra("intent_id", 0)
            CoroutineScope(Dispatchers.IO).launch {
                val movies = db.movieDao().getMovie(movieId)[0]
                et_tittle.setText(movies.tittle)
                et_description.setText(movies.desc)
            }
        }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}