package com.example.ranimalexe.service
import com.example.ranimalexe.model.Pet
import com.example.ranimalexe.model.Users
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot

class FirestoreApi {

    private val db = FirebaseFirestore.getInstance()

    //USER
    private fun getNextUserId(): Task<Int> {
        val usersRef = db.collection("Users")
        return usersRef.orderBy("userId", Query.Direction.DESCENDING).limit(1)
            .get()
            .continueWith { task ->
                if (task.isSuccessful) {
                    val lastUser = task.result?.documents?.firstOrNull()
                    val lastUserId = lastUser?.getLong("userId")?.toInt() ?: 0
                    lastUserId + 1 // Mengembalikan userId berikutnya
                } else {
                    1 // Jika tidak ada user, mulai dari 1
                }
            }
    }

    fun writeUser(user: Users): Task<Void> {
        return getNextUserId().continueWithTask { task ->
            val newUserId = task.result
            val newUser = user.copy(userId = newUserId ?: 1)

            // Menulis data user ke Firestore
            val userRef = db.collection("Users").document(newUserId.toString())
            userRef.set(newUser)
        }
    }

    //PET
    private fun getNextPetId(): Task<Int> {
        val petsRef = db.collection("Pet")
        return petsRef.orderBy("id", Query.Direction.DESCENDING).limit(1)
            .get()
            .continueWith { task ->
                if (task.isSuccessful) {
                    val lastPet = task.result?.documents?.firstOrNull()
                    val lastPetId = lastPet?.getLong("id")?.toInt() ?: 0
                    lastPetId + 1 // Mengembalikan userId berikutnya
                } else {
                    1 // Jika tidak ada user, mulai dari 1
                }
            }
    }

    fun writePet(user: Users): Task<Void> {
        return getNextUserId().continueWithTask { task ->
            val newUserId = task.result
            val newUser = user.copy(userId = newUserId ?: 1)

            // Menulis data user ke Firestore
            val userRef = db.collection("Users").document(newUserId.toString())
            userRef.set(newUser)
        }
    }

    fun writePet(pet: Pet): Task<Void> {
        return getNextPetId().continueWithTask { task ->
            val newPetId = task.result ?: 1 // ID baru yang didapatkan
            val newPet = pet.copy(id = newPetId) // Membuat salinan pet dengan id yang baru

            // Menyimpan pet ke koleksi 'pets' di Firestore dengan id sebagai dokumen
            val petRef = db.collection("Pet").document(newPetId.toString())
            petRef.set(newPet)
        }
    }


    // Fungsi untuk mengupdate informasi pengguna
//    fun updateUsers(userId: Int, username: String, password: String, age: Int, email: String): Task<Void> {
//        val db = FirebaseFirestore.getInstance()
//        val auth = FirebaseAuth.getInstance()
//        val userRef = db.collection("Users").document(userId.toString())  // Ambil referensi ke dokumen User
//
//        val currentUser = auth.currentUser
//        val userUpdateTask = if (currentUser != null && currentUser.email != email) {
//            // Perbarui email dan password di Firebase Authentication
//            val emailUpdateTask = currentUser.updateEmail(email)
//            val passwordUpdateTask = currentUser.updatePassword(password)
//
//            emailUpdateTask.continueWithTask { emailTask ->
//                if (emailTask.isSuccessful) {
//                    return@continueWithTask passwordUpdateTask
//                }
//                throw emailTask.exception ?: Exception("Failed to update email")
//            }
//        }
//        else {
//            // Jika email tidak berubah, tidak perlu melakukan update di Firebase Authentication
//            Task.forResult(null)  // Task yang selesai tanpa perubahan
//        }
//
//        // Setelah update berhasil di Authentication, update data pengguna di Firestore
//        return userUpdateTask.continueWithTask { task ->
//            if (task.isSuccessful) {
//                val user = hashMapOf(
//                    "username" to username,
//                    "password" to password, // Pastikan Anda tidak menyimpan password secara langsung
//                    "age" to age,
//                    "email" to email,
//                    "totalExp" to 0,  // Nilai default
//                    "totalScore" to 0,  // Nilai default
//                    "currentExp" to 0   // Nilai default
//                )
//                // Update data pengguna di Firestore
//                userRef.update(user)
//            } else {
//                throw task.exception ?: Exception("Failed to update user")
//            }
//        }
//    }

    fun getUsersById(userId: Int): Task<DocumentSnapshot> {
        val db = FirebaseFirestore.getInstance()
        return db.collection("Users")
            .document(userId.toString())
            .get()  // Mengambil data pengguna berdasarkan userId
    }

    fun getPetById(petId: Int): Task<DocumentSnapshot> {
        val db = FirebaseFirestore.getInstance()
        return db.collection("Pets")
            .document(petId.toString())
            .get()  // Mengambil data pet berdasarkan petId
    }

    fun updatePet(petId: Int, newName: String): Task<Void> {
        val db = FirebaseFirestore.getInstance()
        val petRef =
            db.collection("Pets").document(petId.toString())  // Ambil referensi ke dokumen Pet

        // Membuat HashMap dengan tipe yang sesuai untuk Firestore
        val petUpdate: MutableMap<String, Any> = hashMapOf(
            "name" to newName  // Update nama pet
        )

        // Lakukan update pada data pet di Firestore dengan tipe yang benar
        return petRef.update(petUpdate)

    }
}
