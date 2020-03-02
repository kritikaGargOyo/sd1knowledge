package com.example.twofragmentactivity

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ImageView.ScaleType
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.list_item.view.*

class FragmentTwoAdapter (val Box: ArrayList<String>?, val image:ArrayList<String> ,var context:Context) : RecyclerView.Adapter<ViewHolder>()
{
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int):ViewHolder {
        val viewHolder: RecyclerView.ViewHolder
        val inflater = LayoutInflater.from(viewGroup.context)
        val v1 = inflater.inflate(R.layout.list_item, viewGroup, false)
        viewHolder = ViewHolder(v1)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return Box?.size as Int
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text=Box?.get(position)
        var queue: RequestQueue = Volley.newRequestQueue(context)
        val imageRequest =
            ImageRequest(image.get(position),
                Response.Listener<Bitmap?> {response->
                    holder.image.setImageBitmap(response)
                },0,0, ScaleType.CENTER_CROP,null,
                Response.ErrorListener{ error -> error.printStackTrace()
                })
        queue.add(imageRequest)
    }



}
class ViewHolder(view:View) : RecyclerView.ViewHolder(view)
{
    var name = view.textttt
    val image = view.image_movie

}
