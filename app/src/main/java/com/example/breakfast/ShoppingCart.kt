package com.example.breakfast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_dashboard.view.*
import org.json.JSONObject

class ShoppingCart : Fragment() {

    companion object {
        //圖片 名字 價格 數量
        private val jsonObject = JSONObject()
        val PRODUCTS : String  = "PRODUCTS"
    }

    private lateinit var viewOfLayout: View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

       // val view: View = inflater!!.inflate(R.layout.fragment_dashboard, container, false)
        viewOfLayout = inflater!!.inflate(R.layout.fragment_dashboard, container, false)
        //viewOfLayout.text_dashboard.text = "hello"   //add your view before id else getting nullpointer exception

        // Return the fragment view/layout
        return view

    }
/*
    private fun showProducts(productsJson: String){


        val products: JSONObject? = JSONObject(productsJson)

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

    }*/
}
