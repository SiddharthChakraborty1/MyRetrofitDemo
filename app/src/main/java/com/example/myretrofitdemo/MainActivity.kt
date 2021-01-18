package com.example.myretrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retService = RetrofitInstance.getInstance().create(AlbumService::class.java)
        val responseLiveData : LiveData<Response<Albums>> = liveData {
            val result = retService.getSortedAlbums(4)
            emit(result)
        }

        responseLiveData.observe(this, Observer {
            val albumsList = it.body()?.listIterator()
            if(albumsList != null){

                while (albumsList.hasNext()){
                    val albumsItem = albumsList.next()
                    val result = " " + "Album title: ${albumsItem.title}"+"\n"+
                            " " + "Album id: ${albumsItem.id}"+"\n"+
                            " " + "Album userId: ${albumsItem.userId}"+"\n\n\n"
                    tv_title.append(result)
                }
            }

        })

        val pathResponse: LiveData<Response<AlbumsItem>> = liveData {
            val response = retService.getSpecificAlbum(3)
            emit(response)
        }

        pathResponse.observe(this, Observer {
            val title = it.body()?.title
            Toast.makeText(this," The album with id 3 is $title", Toast.LENGTH_SHORT).show()
        })

    }
}