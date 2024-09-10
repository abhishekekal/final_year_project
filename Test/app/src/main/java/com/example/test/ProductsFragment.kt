package com.example.test

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.databinding.ActivityProductsBinding
import com.example.test.interfaces.ProductInterface
import com.example.test.models.Product
import com.example.test.utils.CategorySelection

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProductsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductsFragment : Fragment(), ProductInterface {
    // TODO: Rename and change types of parameters
    private var columnCount = 2
    private var param1: String? = null
    private var param2: String? = null

    private var productsList = mutableListOf<Product>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            columnCount = it.getInt(FemaleFragment.ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_products, container, false)
        //val categoryJeans = Product()
        //productsList.add(categoryJeans)

        //val categoryTshirts = Product()
        //categoryTshirts.categoryId = 11
        //categoryTshirts.categoryName = "T-Shirts"
        //categoryTshirts.categoryThumbnailImage = R.drawable.female_t_shirts
        //productsList.add(categoryTshirts)

        //binding = ActivityProductsBinding.inflate(layoutInflater)
        //val view = inflater.inflate(R.layout.fragment_female_list, container, false)
        //setContentView(binding.root)

        if (CategorySelection.selectedGender == CategorySelection.FEMALE) {
            productsList = CategorySelection.selectedCategoryForFemale.productsList
        }
        else {
            productsList = CategorySelection.selectedCategoryForMale.productsList
        }

        if (view is RecyclerView) {
            with(view) {
               layoutManager = GridLayoutManager(context, 2)
                adapter = ProductItemRecyclerView( this@ProductsFragment, productsList)
            }
        }


        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                this@ProductsFragment.setActivityTitle("Select Gender")
                activity!!.supportFragmentManager.popBackStack()
            }
        })
        return view
    }

    fun Fragment.setActivityTitle(title: String) {
        (activity as? AppCompatActivity)?.supportActionBar?.title = title
    }

    override fun selectedProduct(position: Int, selectedCategory: Product) {
        var message = "Product has been added to cart"
        if (CategorySelection.cartProducts.isEmpty()) {
            CategorySelection.cartProducts.add(selectedCategory)
        }
        else {
            val found = CategorySelection.cartProducts.firstOrNull { it.gender == selectedCategory.gender } != null
            if (found) {
                CategorySelection.cartProducts.add(selectedCategory)
            } else {
                message = "Change in gender"
                CategorySelection.cartProducts.removeAll(CategorySelection.cartProducts)
            }
        }

        val builder = this@ProductsFragment.context?.let { AlertDialog.Builder(it) }
        builder?.setTitle("Alert")
        //builder?.setMessage("${selectedCategory.productName} has been added for trial for ${selectedCategory.gender}")
        //CategorySelection.cartProducts.add(selectedCategory)
        builder?.setMessage(message)
        builder?.setPositiveButton("Ok", null)
        builder?.show()
    }
}