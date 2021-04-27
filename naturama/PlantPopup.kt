package fr.vallfeur.naturama

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import fr.vallfeur.naturama.adapter.PlantAdapter

class PlantPopup(private val adapter: PlantAdapter, private val current: PlantModel) : Dialog(adapter.context){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.popup_plant_details)
        setupComponents()
        setupCloseButton()
        setupDeleteButton()
        setupStarButton()
    }

    private fun setupComponents(){
        val plantImage = findViewById<ImageView>(R.id.image_item)
        val plantName = findViewById<TextView>(R.id.popup_plant_name)
        val plantDescription = findViewById<TextView>(R.id.popup_plant_description_subtitle)
        val plantGrow = findViewById<TextView>(R.id.popup_plant_grow_subtitle)
        val plantWater = findViewById<TextView>(R.id.popup_plant_water_subtitle)

        plantName.text = current.name
        plantDescription.text = current.description
        plantGrow.text = current.grow
        plantWater.text = current.water
        Glide.with(adapter.context).load(Uri.parse(current.imageUrl)).into(plantImage)

    }

    private fun setupCloseButton(){
        val closeButton = findViewById<ImageView>(R.id.close_button)

        closeButton.setOnClickListener{
            dismiss()
        }
    }

    private fun setupDeleteButton(){
        val deleteButton = findViewById<ImageView>(R.id.delete_button)
        val repo = PlantRepository()

        deleteButton.setOnClickListener{
            repo.deletePlant(current)
            dismiss()
        }
    }

    private fun updateStar(button: ImageView){
        if(current.liked){
            button.setImageResource(R.drawable.ic_star)
        }
        else{
            button.setImageResource(R.drawable.ic_unstar)
        }
    }

    private fun setupStarButton(){
        val starButton = findViewById<ImageView>(R.id.star_button)
        updateStar(starButton)

        starButton.setOnClickListener{
            val repo = PlantRepository()
            current.liked = !current.liked
            repo.updatePlant(current)
            updateStar(starButton)
        }
    }

}