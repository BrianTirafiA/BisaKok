package com.example.ranimalexe.model

import java.io.Serializable

data class Users(
    val userId: Int = 0, // Menambahkan default value
    var username: String = "",
    var password: String = "",
    var age: Int = 0,
    var email: String = "",
    var totalExp: Int = 0,
    var totalScore: Int = 0,
    var currentExp: Int = 0,
    var health : Float = 100f
) : Serializable {
    // Constructor default (tanpa argumen)
    constructor() : this(0, "", "", 0, "", 0, 0, 0, 100f) // Konstruktor tanpa argumen
}
