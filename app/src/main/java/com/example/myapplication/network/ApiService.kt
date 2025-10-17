package com.example.myapplication.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.http.Header

object ApiService {
    // URL de tu backend en Render
    private const val BASE_URL = "https://ecovive-backend-1.onrender.com"
    
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    
    val api: EcoViveApi = retrofit.create(EcoViveApi::class.java)
}

interface EcoViveApi {
    @GET("/")
    suspend fun getHome(): ApiResponse
    
    @GET("/health")
    suspend fun getHealth(): HealthResponse
    
    @GET("/api")
    suspend fun getApi(): ApiInfoResponse
    
    // Endpoints para reportes (cuando los implementes)
    @POST("/api/reports")
    suspend fun createReport(
        @Header("Authorization") token: String,
        @Body report: ReportRequest
    ): ApiResponse
}

data class ApiResponse(
    val message: String,
    val status: String,
    val version: String
)

data class HealthResponse(
    val status: String,
    val service: String
)

data class ApiInfoResponse(
    val message: String,
    val endpoints: List<String>
)

data class ReportRequest(
    val category: String,
    val title: String,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val address: String
)
