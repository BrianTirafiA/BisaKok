package com.example.ranimalexe.api

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.DocumentSnapshot
//import com.google.android.gms.tasks.Task

class FirestoreAPI {
    private val firestore = FirebaseFirestore.getInstance()

    /**
     * Add a new document to a Firestore collection.
     * @param collectionName Name of the Firestore collection.
     * @param data Data to add (map of field names to values).
     * @param onSuccess Callback for successful document addition.
     * @param onFailure Callback for handling errors.
     */
    fun addDocument(
        collectionName: String,
        data: Map<String, Any>,
        onSuccess: (String) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        firestore.collection(collectionName)
            .add(data)
            .addOnSuccessListener { documentReference ->
                onSuccess(documentReference.id)
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
            }
    }

    /**
     * Get all documents from a Firestore collection.
     * @param collectionName Name of the Firestore collection.
     * @param onSuccess Callback for successful data retrieval.
     * @param onFailure Callback for handling errors.
     */
    fun getDocuments(
        collectionName: String,
        onSuccess: (QuerySnapshot) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        firestore.collection(collectionName)
            .get()
            .addOnSuccessListener { querySnapshot ->
                onSuccess(querySnapshot)
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
            }
    }

    /**
     * Get a specific document by ID.
     * @param collectionName Name of the Firestore collection.
     * @param documentId ID of the document to retrieve.
     * @param onSuccess Callback for successful data retrieval.
     * @param onFailure Callback for handling errors.
     */
    fun getDocumentById(
        collectionName: String,
        documentId: String,
        onSuccess: (DocumentSnapshot) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        firestore.collection(collectionName)
            .document(documentId)
            .get()
            .addOnSuccessListener { documentSnapshot ->
                onSuccess(documentSnapshot)
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
            }
    }

    /**
     * Update a specific document in Firestore.
     * @param collectionName Name of the Firestore collection.
     * @param documentId ID of the document to update.
     * @param data Data to update (map of field names to values).
     * @param onSuccess Callback for successful update.
     * @param onFailure Callback for handling errors.
     */
    fun updateDocument(
        collectionName: String,
        documentId: String,
        data: Map<String, Any>,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        firestore.collection(collectionName)
            .document(documentId)
            .update(data)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
            }
    }

    /**
     * Delete a specific document from Firestore.
     * @param collectionName Name of the Firestore collection.
     * @param documentId ID of the document to delete.
     * @param onSuccess Callback for successful deletion.
     * @param onFailure Callback for handling errors.
     */
    fun deleteDocument(
        collectionName: String,
        documentId: String,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        firestore.collection(collectionName)
            .document(documentId)
            .delete()
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
            }
    }
}