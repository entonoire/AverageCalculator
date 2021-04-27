package fr.vallfeur.naturama

import android.net.Uri
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import fr.vallfeur.naturama.PlantRepository.Singleton.databaseRef
import fr.vallfeur.naturama.PlantRepository.Singleton.downloadUri
import fr.vallfeur.naturama.PlantRepository.Singleton.plantList
import fr.vallfeur.naturama.PlantRepository.Singleton.storageRef
import java.util.*

class PlantRepository {

    object Singleton{
        private val BUCKET_URL: String = "gs://naturama-740ab.appspot.com"

        val storageRef = FirebaseStorage.getInstance().getReferenceFromUrl(BUCKET_URL)
        val databaseRef = FirebaseDatabase.getInstance("https://naturama-740ab-default-rtdb.firebaseio.com/").getReference("plants")
        val plantList = arrayListOf<PlantModel>()

        var downloadUri: Uri? = null
    }

    fun updateData(callback: () -> Unit){
        databaseRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                plantList.clear()
                for(ds in snapshot.children){

                    val plant = ds.getValue(PlantModel::class.java)

                    if(plant != null){
                        plantList.add(plant)
                    }
                }
                callback()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    fun uploadImage(file: Uri, callback: () -> Unit){
        if(file != null){
            val name = UUID.randomUUID().toString()+".jpg"
            val ref = storageRef.child(name)
            val uploadTask = ref.putFile(file)

            uploadTask.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>>{ task ->
                if(!task.isSuccessful){
                    task.exception?.let{throw it}
                }
                return@Continuation ref.downloadUrl
            }).addOnCompleteListener { task ->
                if(task.isSuccessful){
                    downloadUri = task.result
                    callback()
                }
            }
        }
    }

    fun updatePlant(plant: PlantModel) = databaseRef.child(plant.id).setValue(plant)

    fun addPlant(plant: PlantModel) = databaseRef.child(plant.id).setValue(plant)

    fun deletePlant(plant: PlantModel) = databaseRef.child(plant.id).removeValue()

}