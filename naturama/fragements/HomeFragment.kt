package fr.vallfeur.naturama.fragements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fr.vallfeur.naturama.MainActivity
import fr.vallfeur.naturama.PlantModel
import fr.vallfeur.naturama.PlantRepository.Singleton.plantList
import fr.vallfeur.naturama.R
import fr.vallfeur.naturama.adapter.PlantAdapter
import fr.vallfeur.naturama.adapter.PlantItemDecoration

class HomeFragment(private val context: MainActivity) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragement_home, container, false)


        val horizontalRecyclerView = view.findViewById<RecyclerView>(R.id.horinzontal_recycler_view)
        horizontalRecyclerView.adapter = PlantAdapter(context, plantList.filter { !it.liked }, R.layout.item_horizontal_plant)

        val verticalRecyclerView = view.findViewById<RecyclerView>(R.id.vertical_recycler_view)
        verticalRecyclerView.adapter = PlantAdapter(context, plantList, R.layout.item_vertical_plant)
        verticalRecyclerView.addItemDecoration(PlantItemDecoration())

        return view
    }

}