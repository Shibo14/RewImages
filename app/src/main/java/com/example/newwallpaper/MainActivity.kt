package com.example.newwallpaper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), CategoryAdapter.OnItemClickListener {
    private lateinit var firstRecyclerView: RecyclerView
    private lateinit var secondRecyclerView: RecyclerView
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var secondAdapter: SecondRecyclerViewAdapter

    private val categoryItemsList = getCategoryItems()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firstRecyclerView = findViewById(R.id.category_rew)
        secondRecyclerView = findViewById(R.id.second_rew)

        // Birinchi RecyclerView uchun Adapter va LayoutManager
        categoryAdapter = CategoryAdapter(categoryItemsList, this)
        firstRecyclerView.adapter = categoryAdapter
        firstRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // Ikkinchi RecyclerView uchun boshlang'ich malumotlar (faqat rasmlar)
        val initialImageResIdsForSecondRecyclerView =
            categoryItemsList[0].categoryImages
        secondAdapter = SecondRecyclerViewAdapter(initialImageResIdsForSecondRecyclerView)
        secondRecyclerView.adapter = secondAdapter
        secondRecyclerView.layoutManager =
            GridLayoutManager(this, 2)
    }

    override fun onItemClick(position: Int) {
        // Tanlangan kategoriyani olish
        val selectedCategory = categoryItemsList[position]

        // Ikkinchi RecyclerViewga yangi malumotlarni o'zgartirish
        secondAdapter.updateData(selectedCategory.categoryImages)

        categoryAdapter.setSelectedPosition(position)
    }

    private fun getCategoryItems(): List<CategoryItem> {
        // Bu yerdan tegishli kategoriyalar ro'yxatini olishingiz kerak
        // Masalan, barcha kategoriyalarni yaratib, ularni listga qo'shib qaytarishingiz mumkin.
        // Bu o'rin maydoni sizning loyihangizga bog'liq bo'ladi.

        val categoryItemsList = mutableListOf<CategoryItem>()

        // Misol uchun:
        // Birinchi kategoriya uchun rasmlar ro'yxati
        val categoryImages1 = listOf(
            R.drawable.storm,
            R.drawable.sun,
            R.drawable.nest
        )

        // Ikkinchi kategoriya uchun rasmlar ro'yxati
        val categoryImages2 = listOf(
            R.drawable.cool,
            R.drawable.storm,
            R.drawable.cool
        )
        val categoryImages3 = listOf(
            R.drawable.cool,
            R.drawable.storm,
            R.drawable.cool
        )
        val categoryImages4 = listOf(
            R.drawable.cool,
            R.drawable.storm,
            R.drawable.cool
        )

        // Kategoriyalarni CategoryItem obyektlari sifatida listga qo'shib qaytarish
        categoryItemsList.add(CategoryItem(categoryImages1))
        categoryItemsList.add(CategoryItem(categoryImages2))
        categoryItemsList.add(CategoryItem(categoryImages3))
        categoryItemsList.add(CategoryItem(categoryImages4))

        // ...
        // Boshqa kategoriyalarni ham listga qo'shib qaytaring

        return categoryItemsList
    }
}