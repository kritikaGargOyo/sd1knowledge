package com.example.twofragmentactivity

import android.content.Context
import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.list_item.view.*

class MoviesListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var name = view.textttt
    val image = view.image_movie

    fun updateData(movieName: String?, imageUrl: String?, context: Context?) {
        name.text = movieName
       var queue: RequestQueue = Volley.newRequestQueue(context)
        val imageRequest = ImageRequest(imageUrl,
            Response.Listener<Bitmap?> { response ->
                image.setImageBitmap(response)
            }, 0, 0, ImageView.ScaleType.CENTER_CROP, null,
            Response.ErrorListener { error ->
                error.printStackTrace()
            })
        queue.add(imageRequest)
    }
}