package com.example.test.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.example.test.ProductsActivity
import com.example.test.ProductsFragment
import com.example.test.R
import com.example.test.databinding.FragmentHomeBinding
import com.example.test.models.Product
import com.example.test.models.ProductCategory
import com.example.test.utils.CategorySelection


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    fun Fragment.setActivityTitle(title: String) {
        (activity as? AppCompatActivity)?.supportActionBar?.title = title
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        _binding!!.genderSelectionView.visibility = View.VISIBLE
        _binding!!.costumeSelectionView.visibility = View.INVISIBLE
        _binding!!.buttonMale.setOnClickListener {
            //Toast.makeText(this@HomeFragment.context, "Login Failed!", Toast.LENGTH_SHORT).show()
            this@HomeFragment.setActivityTitle("Select Category")
            CategorySelection.selectedGender = CategorySelection.MALE
            binding.buttonBottom.setImageResource(R.drawable.bottom_mens_icon)
            binding.buttonUpper.setImageResource(com.example.test.R.drawable.upper_mens_icon)
            _binding!!.genderSelectionView.visibility = View.INVISIBLE
            _binding!!.costumeSelectionView.visibility = View.VISIBLE


            binding.buttonBottom.setOnClickListener {
                this@HomeFragment.setActivityTitle("Select Product")
                val categoryJeans = ProductCategory()
                categoryJeans.categoryId = 0
                categoryJeans.categoryName = "Jeans"
                categoryJeans.productsList = getBottomProductsForMale()
                categoryJeans.categoryThumbnailImage = R.drawable.mens_jeans
                CategorySelection.selectedCategoryForMale = categoryJeans
                val ft: FragmentTransaction = requireFragmentManager().beginTransaction()
                ft.replace(R.id.nav_host_fragment_content_gender_selection, ProductsFragment(), "NewFragmentTag")
                ft.addToBackStack(null)
                ft.commit()
            }
            //binding.buttonUpper.setImageResource(com.example.test.R.drawable.upper_mens_icon)
            binding.buttonUpper.setOnClickListener {
                this@HomeFragment.setActivityTitle("Select Product")
                val categoryJeans = ProductCategory()
                categoryJeans.categoryId = 1
                categoryJeans.categoryName = "Shirts"
                categoryJeans.productsList = getUpperProductsForMale()
                categoryJeans.categoryThumbnailImage = R.drawable.mens_jeans
                CategorySelection.selectedCategoryForMale = categoryJeans
                //val intent = Intent(this@HomeFragment.context, ProductsActivity::class.java)
                //startActivity(intent)
                val ft: FragmentTransaction = requireFragmentManager().beginTransaction()
                ft.replace(R.id.nav_host_fragment_content_gender_selection, ProductsFragment(), "NewFragmentTag")
                ft.addToBackStack(null)
                ft.commit()
            }

            //val intent = Intent(this@HomeFragment.context, BottomOrUpperSelectionActivity::class.java)
            //startActivity(intent)
        }

        _binding!!.buttonFemale.setOnClickListener {
            //Toast.makeText(this@HomeFragment.context, "Login Female!", Toast.LENGTH_SHORT).show()
            CategorySelection.selectedGender = CategorySelection.FEMALE
            this@HomeFragment.setActivityTitle("Select Category")
            _binding!!.genderSelectionView.visibility = View.INVISIBLE
            _binding!!.costumeSelectionView.visibility = View.VISIBLE
            binding.buttonBottom.setImageResource(R.drawable.botton_women_icon)
            binding.buttonBottom.setOnClickListener {
                this@HomeFragment.setActivityTitle("Select Product")
                val categoryJeans = ProductCategory()
                categoryJeans.categoryId = 10
                categoryJeans.categoryName = "Jeans"
                categoryJeans.productsList = getBottomProductsForFemale()
                categoryJeans.categoryThumbnailImage = R.drawable.female_jeans
                CategorySelection.selectedCategoryForFemale = categoryJeans
                val ft: FragmentTransaction = requireFragmentManager().beginTransaction()
                ft.replace(R.id.nav_host_fragment_content_gender_selection, ProductsFragment(), "NewFragmentTag")
                ft.addToBackStack(null)
                ft.commit()
            }
            binding.buttonUpper.setImageResource(R.drawable.upper_womens_icon)
            binding.buttonUpper.setOnClickListener {
                this@HomeFragment.setActivityTitle("Select Product")
                val categoryJeans = ProductCategory()
                categoryJeans.categoryId = 11
                categoryJeans.categoryName = "Upper"
                categoryJeans.productsList = getUpperProductsForFemale()
                categoryJeans.categoryThumbnailImage = R.drawable.female_jeans
                CategorySelection.selectedCategoryForFemale = categoryJeans
                val ft: FragmentTransaction = requireFragmentManager().beginTransaction()
                ft.replace(R.id.nav_host_fragment_content_gender_selection, ProductsFragment(), "NewFragmentTag")
                ft.addToBackStack(null)
                ft.commit()
            }
        }


        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                this@HomeFragment.setActivityTitle("Select Gender")
                _binding!!.genderSelectionView.visibility = View.VISIBLE
                _binding!!.costumeSelectionView.visibility = View.INVISIBLE
            }
        })

        return root
    }


    private fun getUpperProductsForFemale(): ArrayList<Product> {
        val productCategoriesList = ArrayList<Product>()
        val product1 = Product()
        product1.productId = "ft1"
        product1.productName = "Pink Top"
        product1.productType = CategorySelection.UPPER
        product1.gender = CategorySelection.FEMALE
        product1.productThumbnailImage = R.drawable.female_top_1
        productCategoriesList.add(product1)

        val product2 = Product()
        product2.productId = "ft2"
        product2.productName = "Black Top"
        product2.productType = CategorySelection.UPPER
        product2.gender = CategorySelection.FEMALE
        product2.productThumbnailImage = R.drawable.female_top_2
        productCategoriesList.add(product2)

        val product3 = Product()
        product3.productId = "ft3"
        product3.productName = "Red Top"
        product3.productType = CategorySelection.UPPER
        product3.gender = CategorySelection.FEMALE
        product3.productThumbnailImage = R.drawable.female_top_3
        productCategoriesList.add(product3)

        val product4 = Product()
        product4.productId = "fts2"
        product4.productName = "Blue T-Shirt"
        product4.productType = CategorySelection.UPPER
        product4.gender = CategorySelection.FEMALE
        product4.productThumbnailImage = R.drawable.female_t_shirt_1
        productCategoriesList.add(product4)

        val product5 = Product()
        product5.productId = "fts3"
        product5.productName = "Black T-Shirt"
        product5.productType = CategorySelection.UPPER
        product5.gender = CategorySelection.FEMALE
        product5.productThumbnailImage = R.drawable.female_t_shiet_2
        productCategoriesList.add(product5)

        val product6 = Product()
        product6.productId = "fts2"
        product6.productName = "Green T-Shirt"
        product6.productType = CategorySelection.UPPER
        product6.gender = CategorySelection.FEMALE
        product6.productThumbnailImage = R.drawable.female_t_shirt_3
        productCategoriesList.add(product6)
        return productCategoriesList
    }

    private fun getBottomProductsForFemale(): ArrayList<Product> {
        val productCategoriesList = ArrayList<Product>()
        val product1 = Product()
        product1.productId = "fj1"
        product1.productName = "Blue Slim fit"
        product1.productType = CategorySelection.LOWER
        product1.gender = CategorySelection.FEMALE
        product1.productThumbnailImage = R.drawable.female_jeans
        productCategoriesList.add(product1)

        val product2 = Product()
        product2.productId = "fj2"
        product2.productName = "Gray jeans"
        product2.productType = CategorySelection.LOWER
        product2.gender = CategorySelection.FEMALE
        product2.productThumbnailImage = R.drawable.men_jeans_2
        productCategoriesList.add(product2)
        return productCategoriesList
    }

    private fun getBottomProductsForMale(): ArrayList<Product> {
        val productCategoriesList = ArrayList<Product>()
        val product1 = Product()
        product1.productId = "mj1"
        product1.productName = "Blue Slim fit"
        product1.productType = CategorySelection.LOWER
        product1.gender = CategorySelection.MALE
        product1.productThumbnailImage = R.drawable.men_jeans_1
        productCategoriesList.add(product1)

        val product2 = Product()
        product2.productId = "mj2"
        product2.productName = "Gray jeans"
        product2.productType = CategorySelection.LOWER
        product2.gender = CategorySelection.MALE
        product2.productThumbnailImage = R.drawable.men_jeans_2
        productCategoriesList.add(product2)
        return productCategoriesList
    }

    private fun getUpperProductsForMale(): ArrayList<Product> {
        val productCategoriesList = ArrayList<Product>()
        val product1 = Product()
        product1.productId = "mj2"
        product1.productName = "Black T-Shirt"
        product1.productType = CategorySelection.UPPER
        product1.gender = CategorySelection.MALE
        product1.productThumbnailImage = R.drawable.mens_t_shirt_1
        productCategoriesList.add(product1)

        val product2 = Product()
        product2.productId = "mj2"
        product2.productName = "Yellow T-Shirt"
        product2.productType = CategorySelection.UPPER
        product2.gender = CategorySelection.MALE
        product2.productThumbnailImage = R.drawable.men_t_shirt_2
        productCategoriesList.add(product2)

        val product3 = Product()
        product3.productId = "mj2"
        product3.productName = "Red T-Shirt"
        product3.productType = CategorySelection.UPPER
        product3.gender = CategorySelection.MALE
        product3.productThumbnailImage = R.drawable.men_t_shirt_3
        productCategoriesList.add(product3)

        val product4 = Product()
        product4.productId = "mj2"
        product4.productName = "Green T-Shirt"
        product4.productType = CategorySelection.UPPER
        product4.gender = CategorySelection.MALE
        product4.productThumbnailImage = R.drawable.mens_t_shirt_4
        productCategoriesList.add(product4)

        val product5 = Product()
        product5.productId = "ms1"
        product5.productName = "Red shirt"
        product5.productType = CategorySelection.UPPER
        product5.gender = CategorySelection.MALE
        product5.productThumbnailImage = R.drawable.mens_shirt_1
        productCategoriesList.add(product5)

        val product6 = Product()
        product6.productId = "ms2"
        product6.productName = "Pink shirt"
        product6.productType = CategorySelection.UPPER
        product6.gender = CategorySelection.MALE
        product6.productThumbnailImage = R.drawable.mens_shirt_2
        productCategoriesList.add(product6)

        val product7 = Product()
        product7.productId = "ms3"
        product7.productName = "Blue shirt"
        product7.productType = CategorySelection.UPPER
        product7.gender = CategorySelection.MALE
        product7.productThumbnailImage = R.drawable.mens_shirt_3
        productCategoriesList.add(product7)
        return productCategoriesList
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}