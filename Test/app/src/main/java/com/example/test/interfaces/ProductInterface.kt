package com.example.test.interfaces

import com.example.test.models.Product

interface ProductInterface {
    fun selectedProduct(position: Int, selectedCategory: Product)
}