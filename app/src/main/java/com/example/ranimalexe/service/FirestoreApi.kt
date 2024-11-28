package com.example.ranimalexe.service
import com.example.ranimalexe.model.Pet
import com.example.ranimalexe.model.Users
import com.google.android.gms.tasks.Task
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
}
