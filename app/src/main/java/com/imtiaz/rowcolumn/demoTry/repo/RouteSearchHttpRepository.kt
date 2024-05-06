package com.imtiaz.rowcolumn.demoTry.repo

import android.annotation.SuppressLint
import com.imtiaz.rowcolumn.demoTry.model.BrandWiseData
import android.content.Context
import kotlinx.serialization.json.Json


class RouteSearchHttpRepository (private val context: Context){

    private val jsonMapper = Json { ignoreUnknownKeys = true }

    private val jsonTransactionContent: String by lazy {
        loadJson("brandwise_data")
    }

    // Deserialize JSON content into a list of BrandWiseData objects
    private val transactions: List<BrandWiseData> by lazy {
        Json.decodeFromString<List<BrandWiseData>>(jsonTransactionContent)
    }

    fun searchRoutes(): List<BrandWiseData> {
        return transactions
    }

    @SuppressLint("DiscouragedApi")
    private fun loadJson(fileName: String): String {
        // Get the package name of the app
        val packageName = context.packageName
        // Get the resource ID of the raw resource file
        val resourceId = context.resources.getIdentifier(fileName, "raw", packageName)
        // Open an input stream to read the raw resource file
        val inputStream = context.resources.openRawResource(resourceId)
        // Use a buffered reader to efficiently read the text from the input stream
        return inputStream.bufferedReader().use { it.readText() }
    }
}
