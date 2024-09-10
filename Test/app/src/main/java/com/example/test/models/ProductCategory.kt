package com.example.test.models

class ProductCategory {

    var categoryId:Int = 0
    var categoryName: String = ""
    var categoryThumbnailImage: Int = 0
    var productsList = mutableListOf<Product>()

}