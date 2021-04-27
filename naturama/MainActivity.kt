package fr.vallfeur.naturama

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import fr.vallfeur.naturama.fragements.AddPlantFragment
import fr.vallfeur.naturama.fragements.CollectionFragment
import fr.vallfeur.naturama.fragements.HomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(HomeFragment(this), R.string.home_page_title)

        val navigationView = findViewById<BottomNavigationView>(R.id.navigation_view)
        navigationView.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.home_page -> {
                    loadFragment(HomeFragment(this), R.string.home_page_title)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.collection_page -> {
                    loadFragment(CollectionFragment(this),  R.string.hcollection_page_title)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.add_plant_page -> {
                    loadFragment(AddPlantFragment(this),  R.string.add_plant_page_title)
                    return@setOnNavigationItemSelectedListener true
                }
                else -> false
            }
        }



    }

    private fun loadFragment(fragment: Fragment, string: Int) {
        val repo = PlantRepository()

        findViewById<TextView>(R.id.page_title).text = resources.getString(string)

        repo.updateData{
            //inject fragment in fragment_container
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragement_container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}