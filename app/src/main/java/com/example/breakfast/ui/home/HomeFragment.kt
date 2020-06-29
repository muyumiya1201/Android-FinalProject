package com.example.breakfast.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.breakfast.R
import com.example.breakfast.ui.dashboard.DashboardFragment
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonElement

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var ProductView: LinearLayout
    // product bean
    data class Product(var name: String, var price: Int, var quantity: Int, var picture: Int)
    data class ProductDisplay(var name: String, var price: Int, var picture: Int)

    private val orderProduct: ArrayList<Product> = ArrayList()

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

        ProductView = root.findViewById(R.id.menu_layout)

        val btnBurger: Button = root.findViewById(R.id.hamburger_btn)
        btnBurger.setOnClickListener { burgerMenu()?.let { it1 -> showMenu(it1) } }
        val btnToast: Button = root.findViewById(R.id.toast_btn)
        btnToast.setOnClickListener { toastMenu()?.let { it1 -> showMenu(it1) } }
        val btnOmelette: Button = root.findViewById(R.id.omelette_btn)
        btnOmelette.setOnClickListener { omeletteMenu()?.let { it1 -> showMenu(it1) } }
        val btnDrink: Button = root.findViewById(R.id.drink_btn)
        btnDrink.setOnClickListener { drinkMenu()?.let { it1 -> showMenu(it1) } }


        /* you may send user order here */
        /*val bundle = Bundle()
        bundle.putString(ORDER, shoppingCart())

        val fragmentObj = DashboardFragment()
        fragmentObj.arguments = bundle*/

        return root
    }

    override fun onStop() {
        super.onStop()
        Log.d("Fragment 1", "onStop")

        val bundle = Bundle()
        bundle.putString(ORDER, shoppingCart())

        val fragmentObj = DashboardFragment()
        fragmentObj.arguments = bundle
    }

    /* add user order here */
    private fun shoppingCart(): String? {

        val products = ArrayList<Product>()

        products.add(Product("toast", 30, 1, R.drawable.ic_home_black_24dp))
        products.add(Product("ACB", 20, 2, R.drawable.ic_home_black_24dp))
        products.add(Product("vdf", 70, 3, R.drawable.ic_home_black_24dp))
        products.add(Product("hbbh", 60, 4, R.drawable.ic_home_black_24dp))

        //val gson = GsonBuilder().setPrettyPrinting().create()
        Log.v("ORDER", orderProduct.toString())
        return orderProduct.toString()
        //return gson.toJson(products)
    }

    @SuppressLint("SetTextI18n")
    private fun showMenu(menuJson: String): Int{

        val productDisplay: JsonArray? = Gson().fromJson(menuJson, JsonArray::class.java)

        if (productDisplay != null) {
            ProductView.removeAllViews()
            for(product in productDisplay){

                // create text view
                val productContext = TextView(context)
                productContext.textSize = 20f
                productContext.text =
                    "Name:       " + product.asJsonObject.get("name").asString + "\n" +
                    "Price:        " + product.asJsonObject.get("price").asString+ "\n"


                val productPicture = ImageView(context)
                productPicture.setImageResource(product.asJsonObject.get("picture").asInt)
                productPicture.adjustViewBounds = true
                productPicture.maxWidth = 150
                productPicture.maxHeight = 150
                productPicture.height
                // create card
                val card = context?.let { CardView(it) }

                // set card
                card?.setCardBackgroundColor(1)
                card?.minimumHeight = 50
                card?.minimumWidth = 50
                card?.radius = 20f
                card?.addView(productPicture)
                card?.addView(productContext)
                card?.setOnClickListener {addOne(product)}
                ProductView.addView(card)
            }
        }

        return 0
    }

    private fun addOne(product: JsonElement){
        val name: String = product.asJsonObject.get("name").asString
        for(each in orderProduct){
            if(each.name == name) {
                each.quantity += 1
                Log.v("addOne", orderProduct.toString())
                return
            }
        }
        val price: Int = product.asJsonObject.get("price").asInt
        val picture: Int = product.asJsonObject.get("picture").asInt
        val quantity = 1
        val tmp = Product(name, price, quantity, picture)

        orderProduct.add(tmp)
        Log.v("addOne", orderProduct.toString())

        val toast = "新增一份 $name"
        Toast. makeText (activity, toast, Toast. LENGTH_SHORT ).show()
    }

    private fun burgerMenu(): String? {
        val productDisplay = ArrayList<ProductDisplay>()

        productDisplay.add(ProductDisplay("燻雞漢堡", 35, R.drawable.chicken_burger))
        productDisplay.add(ProductDisplay("豬肉漢堡", 30, R.drawable.pork_burger))
        productDisplay.add(ProductDisplay("鮪魚漢堡", 35, R.drawable.tuna_burger))
        productDisplay.add(ProductDisplay("雙層牛肉起司漢堡", 65, R.drawable.beef_cheese_burger))

        val gson = GsonBuilder().setPrettyPrinting().create()
        Log.v("burger", gson.toJson(productDisplay))
        return gson.toJson(productDisplay)
    }
    private fun toastMenu(): String? {
        Log.v("burger", "burger")
        val productDisplay = ArrayList<ProductDisplay>()

        productDisplay.add(ProductDisplay("培根吐司", 35, R.drawable.bakon_toast))
        productDisplay.add(ProductDisplay("燻雞吐司", 35, R.drawable.chicken_toast))
        productDisplay.add(ProductDisplay("豬肉吐司", 35, R.drawable.pork_toast))
        productDisplay.add(ProductDisplay("鮪魚吐司", 35, R.drawable.tuna_toast))

        val gson = GsonBuilder().setPrettyPrinting().create()

        return gson.toJson(productDisplay)
    }
    private fun omeletteMenu(): String? {
        Log.v("burger", "burger")
        val productDisplay = ArrayList<ProductDisplay>()

        productDisplay.add(ProductDisplay("原味蛋餅", 25, R.drawable.origin_omelette))
        productDisplay.add(ProductDisplay("玉米蛋餅", 35, R.drawable.corn_omelette))
        productDisplay.add(ProductDisplay("鮪魚蛋餅", 40, R.drawable.tuna_omelette))
        productDisplay.add(ProductDisplay("培根起司蛋餅", 45, R.drawable.bacon_cheese_omelette))

        val gson = GsonBuilder().setPrettyPrinting().create()

        return gson.toJson(productDisplay)
    }
    private fun drinkMenu() : String? {
        Log.v("burger", "burger")
        val productDisplay = ArrayList<ProductDisplay>()

        productDisplay.add(ProductDisplay("奶茶", 20, R.drawable.milk_tea))
        productDisplay.add(ProductDisplay("紅茶拿鐵", 35, R.drawable.black_tea_latte))
        productDisplay.add(ProductDisplay("美式咖啡", 40, R.drawable.coffee))
        productDisplay.add(ProductDisplay("拿鐵", 50, R.drawable.latte))
        productDisplay.add(ProductDisplay("巧克力牛奶", 55, R.drawable.choco_milk))

        val gson = GsonBuilder().setPrettyPrinting().create()

        return gson.toJson(productDisplay)
    }
}
