package com.example.breakfast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_dashboard.view.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.json.JSONObject

class HomePage : Fragment() {

    companion object {
        //圖片 名字 價格 數量
        private val jsonObject = JSONObject()
        val PRODUCTS : String  = "PRODUCTS"
    }

    private lateinit var viewOfLayout: View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        viewOfLayout = inflater!!.inflate(R.layout.fragment_home, container, false)
        //viewOfLayout.text_dashboard.text = "hello"


        // Return the fragment view/layout
        return view

    }

    /*
    private fun menu(f : Fragment){
        val jsonObject = JSONObject()
        jsonObject.put("name","toast")
        jsonObject.put("price",30)
        jsonObject.put("quantity",1)
        jsonObject.put("picture","week")

        val intent = Intent()
        intent.setClass(this@,
            ShoppingCart::class.java)
        intent.putExtra(MainActivity.PRODUCTS, jsonObject.toString())
        startActivity(intent)

    }*/

    private fun showProducts(productsJson: String){


        val products: JSONObject? = JSONObject(productsJson)
/*
        val productName = TextView(this)
        productName.textSize = 20f
        productName.text = products?.get("name").toString()


        val productPrice = TextView(this)
        productPrice.textSize = 20f
        productPrice.text = products?.getInt("price").toString()

        val productQuantity = TextView(this)
        productQuantity.textSize = 20f
        productQuantity.text = products?.getInt("quantity").toString()

        val productPicture = TextView(this)
        productPicture.textSize = 20f
        productPicture.text = products?.get("picture").toString()


        // add TextView to LinearLayout
        shopping_cart.addView(productName)
        //shopping_cart.addView(productPrice)
        //shopping_cart.addView(productQuantity)
        //shopping_cart.addView(productPicture)
*/
    }

}
