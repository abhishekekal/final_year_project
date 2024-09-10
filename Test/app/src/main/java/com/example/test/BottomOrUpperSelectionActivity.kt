package com.example.test

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.test.databinding.ActivityBottomOrUpperSelectionBinding
import com.example.test.models.Product
import com.example.test.models.ProductCategory
import com.example.test.utils.CategorySelection
import java.util.ArrayList

class BottomOrUpperSelectionActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityBottomOrUpperSelectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBottomOrUpperSelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (CategorySelection.selectedGender == CategorySelection.MALE) {
           binding.buttonBottom.setImageResource(R.drawable.bottom_mens_icon)
            binding.buttonBottom.setOnClickListener {
                val categoryJeans = ProductCategory()
                categoryJeans.categoryId = 0
                categoryJeans.categoryName = "Jeans"
                categoryJeans.productsList = getBottomProductsForMale()
                categoryJeans.categoryThumbnailImage = R.drawable.mens_jeans
                CategorySelection.selectedCategoryForMale = categoryJeans
                val intent = Intent(this@BottomOrUpperSelectionActivity, ProductsActivity::class.java)
                startActivity(intent)
            }
            binding.buttonUpper.setImageResource(R.drawable.upper_mens_icon)
            binding.buttonUpper.setOnClickListener {
                val categoryJeans = ProductCategory()
                categoryJeans.categoryId = 1
                categoryJeans.categoryName = "Shirts"
                categoryJeans.productsList = getUpperProductsForMale()
                categoryJeans.categoryThumbnailImage = R.drawable.mens_jeans
                CategorySelection.selectedCategoryForMale = categoryJeans
                val intent = Intent(this@BottomOrUpperSelectionActivity, ProductsActivity::class.java)
                startActivity(intent)
            }
        }
        else {
            binding.buttonBottom.setImageResource(R.drawable.botton_women_icon)
            binding.buttonBottom.setOnClickListener {
                val categoryJeans = ProductCategory()
                categoryJeans.categoryId = 10
                categoryJeans.categoryName = "Jeans"
                categoryJeans.productsList = getBottomProductsForFemale()
                categoryJeans.categoryThumbnailImage = R.drawable.female_jeans
                CategorySelection.selectedCategoryForMale = categoryJeans
                val intent = Intent(this@BottomOrUpperSelectionActivity, ProductsActivity::class.java)
                startActivity(intent)
            }
            binding.buttonUpper.setImageResource(R.drawable.upper_womens_icon)
            binding.buttonUpper.setOnClickListener {
                val categoryJeans = ProductCategory()
                categoryJeans.categoryId = 11
                categoryJeans.categoryName = "Upper"
                categoryJeans.productsList = getUpperProductsForFemale()
                categoryJeans.categoryThumbnailImage = R.drawable.female_jeans
                CategorySelection.selectedCategoryForMale = categoryJeans
                val intent = Intent(this@BottomOrUpperSelectionActivity, ProductsActivity::class.java)
                startActivity(intent)
            }
        }




        //setSupportActionBar(binding.toolbar)

        //val navController =
          //  findNavController(R.id.nav_host_fragment_content_bottom_or_upper_selection)
        //appBarConfiguration = AppBarConfiguration(navController.graph)
        //setupActionBarWithNavController(navController, appBarConfiguration)

    }

    private fun getProductsForFemaleTShirts(): ArrayList<Product> {
        val productCategoriesList = ArrayList<Product>()


        return productCategoriesList
    }

    private fun getUpperProductsForFemale(): ArrayList<Product> {
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

        val product4 = Product()
        product4.productId = "fts2"
        product4.productName = "Blue T-Shirt"
        product4.productThumbnailImage = R.drawable.female_t_shirt_1
        productCategoriesList.add(product4)

        val product5 = Product()
        product5.productId = "fts3"
        product5.productName = "Black T-Shirt"
        product5.productThumbnailImage = R.drawable.female_t_shiet_2
        productCategoriesList.add(product5)

        val product6 = Product()
        product6.productId = "fts2"
        product6.productName = "Green T-Shirt"
        product6.productThumbnailImage = R.drawable.female_t_shirt_3
        productCategoriesList.add(product6)
        return productCategoriesList
    }

    private fun getBottomProductsForFemale(): ArrayList<Product> {
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

    private fun getBottomProductsForMale(): ArrayList<Product> {
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

    private fun getUpperProductsForMale(): ArrayList<Product> {
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

        val product5 = Product()
        product5.productId = "ms1"
        product5.productName = "Red shirt"
        product5.productThumbnailImage = R.drawable.mens_shirt_1
        productCategoriesList.add(product5)

        val product6 = Product()
        product6.productId = "ms2"
        product6.productName = "Pink shirt"
        product6.productThumbnailImage = R.drawable.mens_shirt_2
        productCategoriesList.add(product6)

        val product7 = Product()
        product7.productId = "ms3"
        product7.productName = "Blue shirt"
        product7.productThumbnailImage = R.drawable.mens_shirt_3
        productCategoriesList.add(product7)
        return productCategoriesList
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController =
            findNavController(R.id.nav_host_fragment_content_bottom_or_upper_selection)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}