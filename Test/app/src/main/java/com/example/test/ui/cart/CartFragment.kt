package com.example.test.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.test.CartItemRecyclerViewAdapter
import com.example.test.ProductsFragment
import com.example.test.R
import com.example.test.interfaces.ProductInterface
import com.example.test.models.Product
import com.example.test.ui.ar.ARFragment
import com.example.test.utils.CategorySelection

class CartFragment : Fragment(), ProductInterface {

    var customAdapter: CartItemRecyclerViewAdapter? = null
    var recyclerView: RecyclerView? = null
    var textView: TextView? = null
    var buttonGoToAR: Button? = null

        override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_cart, container, false)
        //val textView: TextView = view.findViewById(R.id.text_cart)
        //textView.text = "Work in progress"
        recyclerView = view.findViewById(R.id.cart_recycler_view)
        textView = view.findViewById(R.id.textViewEmptyCart)
        buttonGoToAR = view.findViewById(R.id.buttonGoToAR)
        if (CategorySelection.cartProducts.isEmpty()) {
            this.textView?.visibility = View.VISIBLE
            this.buttonGoToAR?.visibility = View.GONE
            this.recyclerView?.visibility = View.GONE
        }
        else {
            this.textView?.visibility = View.GONE
            this.recyclerView?.visibility = View.VISIBLE
            this.buttonGoToAR?.visibility = View.VISIBLE
        }
        customAdapter = CartItemRecyclerViewAdapter(this@CartFragment, CategorySelection.cartProducts)
        buttonGoToAR?.setOnClickListener{
            val ft: FragmentTransaction = requireFragmentManager().beginTransaction()
            ft.replace(R.id.nav_host_fragment_content_gender_selection, ARFragment(), "NewFragmentTag")
            ft.addToBackStack(null)
            ft.commit()
        }
        this.recyclerView?.adapter = customAdapter

        return view
    }

    private fun getUpperProductsForMale(): ArrayList<Product> {
        var productCategoriesList = ArrayList<Product>()
        val product1 = Product()
        product1.productId = "mj2"
        product1.productName = "Black T-Shirt"
        product1.gender = CategorySelection.MALE
        product1.productThumbnailImage = R.drawable.mens_t_shirt_1
        productCategoriesList.add(product1)

        val product2 = Product()
        product2.productId = "mj2"
        product2.productName = "Yellow T-Shirt"
        product2.gender = CategorySelection.MALE
        product2.productThumbnailImage = R.drawable.men_t_shirt_2
        productCategoriesList.add(product2)

        val product3 = Product()
        product3.productId = "mj2"
        product3.productName = "Red T-Shirt"
        product3.gender = CategorySelection.MALE
        product3.productThumbnailImage = R.drawable.men_t_shirt_3
        productCategoriesList.add(product3)

        val product4 = Product()
        product4.productId = "mj2"
        product4.productName = "Green T-Shirt"
        product4.gender = CategorySelection.MALE
        product4.productThumbnailImage = R.drawable.mens_t_shirt_4
        productCategoriesList.add(product4)

        val product5 = Product()
        product5.productId = "ms1"
        product5.productName = "Red shirt"
        product5.gender = CategorySelection.MALE
        product5.productThumbnailImage = R.drawable.mens_shirt_1
        productCategoriesList.add(product5)

        val product6 = Product()
        product6.productId = "ms2"
        product6.productName = "Pink shirt"
        product6.gender = CategorySelection.MALE
        product6.productThumbnailImage = R.drawable.mens_shirt_2
        productCategoriesList.add(product6)

        val product7 = Product()
        product7.productId = "ms3"
        product7.productName = "Blue shirt"
        product7.gender = CategorySelection.MALE
        product7.productThumbnailImage = R.drawable.mens_shirt_3
        productCategoriesList.add(product7)
        return productCategoriesList
    }

    override fun selectedProduct(position: Int, selectedCategory: Product) {
        CategorySelection.cartProducts.removeAt(position)
        if (CategorySelection.cartProducts.isEmpty()) {
            this.textView?.visibility = View.VISIBLE
            recyclerView?.visibility = View.GONE
            this.buttonGoToAR?.visibility = View.GONE
        }
        else {
            this.textView?.visibility = View.GONE
            recyclerView?.visibility  = View.VISIBLE
            this.buttonGoToAR?.visibility = View.VISIBLE
        }
        customAdapter?.notifyDataSetChanged()
        //Toast.makeText(context, "delete product", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}