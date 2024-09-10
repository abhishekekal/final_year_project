package com.example.test

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.test.interfaces.CategoryInterface
import com.example.test.models.Product
import com.example.test.models.ProductCategory
import com.example.test.placeholder.PlaceholderContent
import com.example.test.utils.CategorySelection
import java.util.ArrayList

/**
 * A fragment representing a list of Items.
 */
class MaleFragment : Fragment(), CategoryInterface {

    private var columnCount = 1
    private var productCategoriesList = mutableListOf<ProductCategory>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }

        //for (i in 1..5 step 1) {
        val categoryJeans = ProductCategory()
        categoryJeans.categoryId = 0
        categoryJeans.categoryName = "Jeans"
        categoryJeans.productsList = getProductsForJeans()
        categoryJeans.categoryThumbnailImage = R.drawable.mens_jeans
        productCategoriesList.add(categoryJeans)

        val categoryTshirts = ProductCategory()
        categoryTshirts.categoryId = 1
        categoryTshirts.categoryName = "T-Shirts"
        categoryTshirts.productsList = getProductsForTShirts()
        categoryTshirts.categoryThumbnailImage = R.drawable.mens_t_shirts
        productCategoriesList.add(categoryTshirts)

        val categoryShirts = ProductCategory()
        categoryShirts.categoryId = 2
        categoryShirts.categoryName = "Shirts"
        categoryShirts.productsList = getProductsForShirts()
        categoryShirts.categoryThumbnailImage = R.drawable.mens_shirts
        productCategoriesList.add(categoryShirts)
        //}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_male_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = GridLayoutManager(context, 2)
                adapter = ItemRecyclerViewAdapter(context, true, this@MaleFragment, productCategoriesList)
            }
        }
        return view
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            MaleFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }


    private fun getProductsForJeans(): ArrayList<Product> {
        val productCategoriesList = ArrayList<Product>()
        val product1 = Product()
        product1.productId = "mj1"
        product1.productName = "Blue Slim fit"
        product1.productThumbnailImage = R.drawable.men_jeans_1
        productCategoriesList.add(product1)

        val product2 = Product()
        product2.productId = "mj2"
        product2.productName = "Gray jeans"
        product2.productThumbnailImage = R.drawable.men_jeans_2
        productCategoriesList.add(product2)
        return productCategoriesList
    }

    private fun getProductsForTShirts(): ArrayList<Product> {
        var productCategoriesList = ArrayList<Product>()
        val product1 = Product()
        product1.productId = "mj2"
        product1.productName = "Black T-Shirt"
        product1.productThumbnailImage = R.drawable.mens_t_shirt_1
        productCategoriesList.add(product1)

        val product2 = Product()
        product2.productId = "mj2"
        product2.productName = "Yellow T-Shirt"
        product2.productThumbnailImage = R.drawable.men_t_shirt_2
        productCategoriesList.add(product2)

        val product3 = Product()
        product3.productId = "mj2"
        product3.productName = "Red T-Shirt"
        product3.productThumbnailImage = R.drawable.men_t_shirt_3
        productCategoriesList.add(product3)

        val product4 = Product()
        product4.productId = "mj2"
        product4.productName = "Green T-Shirt"
        product4.productThumbnailImage = R.drawable.mens_t_shirt_4
        productCategoriesList.add(product4)
        return productCategoriesList
    }

    private fun getProductsForShirts(): ArrayList<Product> {
        val productCategoriesList = ArrayList<Product>()
        val product1 = Product()
        product1.productId = "ms1"
        product1.productName = "Red shirt"
        product1.productThumbnailImage = R.drawable.mens_shirt_1
        productCategoriesList.add(product1)

        val product2 = Product()
        product2.productId = "ms2"
        product2.productName = "Pink shirt"
        product2.productThumbnailImage = R.drawable.mens_shirt_2
        productCategoriesList.add(product2)

        val product3 = Product()
        product3.productId = "ms3"
        product3.productName = "Blue shirt"
        product3.productThumbnailImage = R.drawable.mens_shirt_3
        productCategoriesList.add(product3)

        return productCategoriesList
    }


    override fun selectedCategory(position: Int, selectedCategory: ProductCategory) {
        CategorySelection.selectedCategoryForMale = selectedCategory
        val intent = Intent(this.context, ProductsActivity::class.java)
        startActivity(intent)
        //Toast.makeText(context, "Clicked on ${CategorySelection.selectedCategoryForMale.productsList.count()} for male ", Toast.LENGTH_SHORT).show()
    }
}