package com.example.test.interfaces

import com.example.test.models.ProductCategory

interface CategoryInterface {

    fun selectedCategory(position: Int, selectedCategory: ProductCategory)

}