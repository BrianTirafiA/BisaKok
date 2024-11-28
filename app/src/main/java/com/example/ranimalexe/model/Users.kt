package com.example.ranimalexe.model

data class Users (
    val userId: Int,
    val username: String,
    val password: String,
    val age: Int,
    val email: String,
    val totalExp: Int,
    val totalScore: Int,
    val currentExp: Int
    )