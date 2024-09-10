package com.example.test

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
class FemaleFragment : Fragment(), CategoryInterface {

    private var columnCount = 2
    private var productCategoriesList = mutableListOf<ProductCategory>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
        val categoryJeans = ProductCategory()
        categoryJeans.categoryId = 10
        categoryJeans.categoryName = "Jeans"
        categoryJeans.productsList = getProductsForFemaleJeans()
        categoryJeans.categoryThumbnailImage = R.drawable.female_jeans
        productCategoriesList.add(categoryJeans)

        val categoryTshirts = ProductCategory()
        categoryTshirts.categoryId = 11
        categoryTshirts.categoryName = "T-Shirts"
        categoryTshirts.productsList = getProductsForFemaleTShirts()
        categoryTshirts.categoryThumbnailImage = R.drawable.female_t_shirts
        productCategoriesList.add(categoryTshirts)

        val categoryTops = ProductCategory()
        categoryTops.categoryId = 12
        categoryTops.categoryName = "Tops"
        categoryTops.productsList = getProductsForFemaleTops()
        categoryTops.categoryThumbnailImage = R.drawable.female_tops
        productCategoriesList.add(categoryTops)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_female_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = GridLayoutManager(context, 2)
                adapter = ItemRecyclerViewAdapter(context, false, this@FemaleFragment, productCategoriesList)
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
            FemaleFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }

    private fun getProductsForFemaleJeans(): ArrayList<Product> {
        val productCategoriesList = ArrayList<Product>()
        val product1 = Product()
        product1.productId = "fj1"
        product1.productName = "Blue Slim fit"
        product1.productThumbnailImage = R.drawable.men_jeans_1
        productCategoriesList.add(product1)

        val product2 = Product()
        product2.productId = "fj2"
        product2.productName = "Gray jeans"
        product2.productThumbnailImage = R.drawable.men_jeans_2
        productCategoriesList.add(product2)
        return productCategoriesList
    }

    private fun getProductsForFemaleTShirts(): ArrayList<Product> {
        val productCategoriesList = ArrayList<Product>()
        val product1 = Product()
        product1.productId = "fts2"
        product1.productName = "Blue T-Shirt"
        product1.productThumbnailImage = R.drawable.female_t_shirt_1
        productCategoriesList.add(product1)

        val product2 = Product()
        product2.productId = "fts3"
        product2.productName = "Black T-Shirt"
        product2.productThumbnailImage = R.drawable.female_t_shiet_2
        productCategoriesList.add(product2)

        val product3 = Product()
        product3.productId = "fts2"
        product3.productName = "Green T-Shirt"
        product3.productThumbnailImage = R.drawable.female_t_shirt_3
        productCategoriesList.add(product3)

        return productCategoriesList
    }

    private fun getProductsForFemaleTops(): ArrayList<Product> {
        val productCategoriesList = ArrayList<Product>()
        val product1 = Product()
        product1.productId = "ft1"
        product1.productName = "Pink Top"
        product1.productThumbnailImage = R.drawable.female_top_1
        productCategoriesList.add(product1)

        val product2 = Product()
        product2.productId = "ft2"
        product2.productName = "Black Top"
        product2.productThumbnailImage = R.drawable.female_top_2
        productCategoriesList.add(product2)

        val product3 = Product()
        product3.productId = "ft3"
        product3.productName = "Red Top"
        product3.productThumbnailImage = R.drawable.female_top_3
        productCategoriesList.add(product3)
        return productCategoriesList
    }

    override fun selectedCategory(position: Int, selectedCategory: ProductCategory) {
        CategorySelection.selectedCategoryForMale = selectedCategory
        val intent = Intent(this.context, ProductsActivity::class.java)
        startActivity(intent)
        //Toast.makeText(context, "Clicked on ${selectedCategory.categoryName} for female ", Toast.LENGTH_SHORT).show()
    }

}