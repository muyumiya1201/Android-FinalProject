package com.example.breakfast.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.breakfast.MainActivity
import com.example.breakfast.R
import com.example.breakfast.ui.dashboard.DashboardFragment
import com.google.gson.GsonBuilder

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    // product bean
    data class Product(var name: String, var price: Int, var quantity: Int, var picture: Int)

    companion object {
        const val ORDER: String = "ORDER"
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        /* you may send user order here */
        val bundle = Bundle()
        bundle.putString(ORDER, menu())

        val fragmentObj = DashboardFragment()
        fragmentObj.arguments = bundle

        return root
    }

    /* add user order here */
    private fun menu(): String? {

        val products = ArrayList<Product>()

        products.add(Product("toast", 30, 1, R.drawable.ic_home_black_24dp))
        products.add(Product("ACB", 20, 2, R.drawable.ic_home_black_24dp))
        products.add(Product("vdf", 70, 3, R.drawable.ic_home_black_24dp))
        products.add(Product("hbbh", 60, 4, R.drawable.ic_home_black_24dp))

        val gson = GsonBuilder().setPrettyPrinting().create()

        return gson.toJson(products)
    }
}
