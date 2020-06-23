package com.example.breakfast.ui.dashboard

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.breakfast.R
import com.example.breakfast.ui.home.HomeFragment
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray





class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var myProductName: LinearLayout


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {


        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        /*
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = "hello"
        })*/

        myProductName = root.findViewById(R.id.shopping_cart)

        /* get order here*/
        //val order = this.arguments?.getString(HomeFragment.ORDER) //real data
        val order = menu() // test data
        var totalPrice = 0

        if (order != null) {
            totalPrice = showOrder(order)
        }



        // create total
        val total = TextView(context)
        total.textSize = 20f
        total.text = "Total: $totalPrice"

        myProductName.addView(total)

        // create send button
        val send = Button(context)
        send.textSize = 20f
        send.text = "Send"

        myProductName.addView(send)

        return root
    }


    @SuppressLint("SetTextI18n")
    private fun showOrder(orderJson: String): Int{

        val products: JsonArray? = Gson().fromJson(orderJson, JsonArray::class.java)

        var total = 0
        if (products != null) {
            for(product in products){

                // create text view
                val productContext = TextView(context)
                productContext.textSize = 20f
                productContext.text =
                    "Name:       " + product.asJsonObject.get("name").asString + "\n" +
                            "Price:        " + product.asJsonObject.get("price").asString+ "\n" +
                            "Quantity:   " + product.asJsonObject.get("quantity").asString + "\n" +
                            "Picture:  " + product.asJsonObject.get("picture").asString

                // create card
                val card = context?.let { CardView(it) }

                // set card
                card?.setCardBackgroundColor(1)
                card?.minimumHeight = 50
                card?.minimumWidth = 50
                card?.radius = 20f
                card?.addView(productContext)

                myProductName.addView(card)

                // count total
                total += (product.asJsonObject.get("price").asInt) * (product.asJsonObject.get("quantity").asInt)
            }
        }

        return total
    }

    private fun menu(): String? {

        val products = ArrayList<HomeFragment.Product>()

        products.add(HomeFragment.Product("toast", 30, 1, "week"))
        products.add(HomeFragment.Product("ACB", 20, 2, "webdanbdfnek"))
        products.add(HomeFragment.Product("vdf", 70, 3, "dngdn"))
        products.add(HomeFragment.Product("hbbh", 60, 4, "weagndgnagfek"))

        val gson = GsonBuilder().setPrettyPrinting().create()

        return gson.toJson(products)
    }
}
