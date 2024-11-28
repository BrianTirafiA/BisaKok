package com.example.ranimalexe.model

import java.io.Serializable

data class Users(
    val userId: Int = 0, // Menambahkan default value
    val username: String = "",
    val password: String = "",
    val age: Int = 0,
    val email: String = "",
    val totalExp: Int = 0,
    val totalScore: Int = 0,
    val currentExp: Int = 0
) : Serializable {
    // Constructor default (tanpa argumen)
    constructor() : this(0, "", "", 0, "", 0, 0, 0) // Konstruktor tanpa argumen
}
