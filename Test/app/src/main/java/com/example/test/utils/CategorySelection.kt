package com.example.test.utils

import com.example.test.models.Product
import com.example.test.models.ProductCategory

class CategorySelection {
    companion object {
        var selectedCategoryForMale = ProductCategory()
        var selectedCategoryForFemale = ProductCategory()
        //var selectedProductForMale = Product()
        //var selectedProductForFemale = Product()
        var selectedGender = ""
        var cartProducts = mutableListOf<Product>()

        val MALE = "Male"
        val FEMALE = "Female"

        val UPPER = "Upper"
        val LOWER = "Lower"
    }
}