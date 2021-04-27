package fr.vallfeur.naturama.fragements

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import fr.vallfeur.naturama.MainActivity
import fr.vallfeur.naturama.PlantModel
import fr.vallfeur.naturama.PlantRepository
import fr.vallfeur.naturama.PlantRepository.Singleton.downloadUri
import fr.vallfeur.naturama.R
import java.util.*

class AddPlantFragment(private val context: MainActivity) : Fragment() {

    private var uploadImage:ImageView? = null
    private var file:Uri? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_add_plant, container, false)
        val imageButton = view.findViewById<Button>(R.id.upload_button)
        val uploadImageView = view.findViewById<ImageView>(R.id.preview_image)
        val confirmButton = view.findViewById<Button>(R.id.confirm_button)

        uploadImage = uploadImageView

        imageButton.setOnClickListener{
            pickupImage()
        }

        confirmButton.setOnClickListener { sendForm(view) }

        return view
    }

    private fun pickupImage(){
        val intent = Intent()

        intent.type = "image/"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 47)
    }

    private fun sendForm(view: View){
        val repo = PlantRepository()

        repo.uploadImage(file!!){
            val plantName = view.findViewById<EditText>(R.id.name_input).text.toString()
            val plantDescription = view.findViewById<EditText>(R.id.description_input).text.toString()
            val plantGrow = view.findViewById<Spinner>(R.id.grow_spinner).selectedItem.toString()
            val plantWater = view.findViewById<Spinner>(R.id.water_spinner).selectedItem.toString()
            val downloadImageUrl = downloadUri
            val plant = PlantModel(UUID.randomUUID().toString(), plantName, plantDescription, downloadImageUrl.toString(), plantGrow, plantWater)

            repo.addPlant(plant)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 47 && resultCode == Activity.RESULT_OK){
            if(data == null || data.data == null) return

            file = data.data
            uploadImage?.setImageURI(file)

        }
    }

}