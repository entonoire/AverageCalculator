package fr.vallfeur.naturama.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.vallfeur.naturama.*

class PlantAdapter(val context: MainActivity, val plantlist: List<PlantModel>, private val layoutId: Int) : RecyclerView.Adapter<PlantAdapter.ViewHolder>(){

    //component box
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val plantImage = view.findViewById<ImageView>(R.id.image_item)
        val plantName:TextView? = view.findViewById<TextView>(R.id.name_item)
        val plantDescription:TextView? = view.findViewById<TextView>(R.id.description_item)
        val starIcon = view.findViewById<ImageView>(R.id.star_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = plantlist.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = plantlist[position]
        val repo = PlantRepository()

        //get image with glide
        Glide.with(context).load(Uri.parse(current.imageUrl)).into(holder.plantImage)
        holder.plantName?.text = current.name
        holder.plantDescription?.text = current.description

        if(current.liked){
            holder.starIcon.setImageResource(R.drawable.ic_star)
        }
        else{
            holder.starIcon.setImageResource(R.drawable.ic_unstar)
        }

        holder.starIcon.setOnClickListener{
            current.liked = !current.liked
            repo.updatePlant(current)
        }

        holder.itemView.setOnClickListener{
            PlantPopup(this, current).show()
        }

    }

}