package com.example.ranimalexe.model
import java.io.Serializable


data class Users (
    val userId: Int,
    val username: String,
    val password: String,
    val age: Int,
    val email: String,
    val totalExp: Int=0,
    val totalScore: Int=0,
    val currentExp: Int=0
    ) : Serializable