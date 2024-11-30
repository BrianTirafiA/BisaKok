package com.example.ranimalexe.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.ranimalexe.Constants
import com.example.ranimalexe.R
import com.example.ranimalexe.storage.UserData
import com.example.ranimalexe.viewmodel.ProfileViewModel
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_profile.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_profile : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    // Declare your views
    private lateinit var editProfile: Button
    private lateinit var username: TextView
    private lateinit var username2: TextView
    private lateinit var email: TextView
    private lateinit var email2: TextView
    private lateinit var totalExp: TextView
    private lateinit var totalScore: TextView
    private lateinit var age: TextView
    private lateinit var userId: TextView

    private val profileViewModel: ProfileViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(Constants.ARG_PARAM1)
            param2 = it.getString(Constants.ARG_PARAM2)
        }
    }

    // Inflate the layout and initialize views in onViewCreated
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout
        val rootView = inflater.inflate(R.layout.fragment_profile, container, false)

        // Initialize views after view is inflated
        username = rootView.findViewById(R.id.username)
        username2 = rootView.findViewById(R.id.lblUsername_Result)
        email = rootView.findViewById(R.id.email)
        email2 = rootView.findViewById(R.id.lblEmail_Result)
        totalExp = rootView.findViewById(R.id.lblTotalEXPResult)
        totalScore = rootView.findViewById(R.id.lblTotalScoreResult)
        age = rootView.findViewById(R.id.lblAge_Result)
        userId = rootView.findViewById(R.id.lblUserID_Result)

        // Retrieve user UID from SharedPreferences
        val sharedPrefs = requireActivity().getSharedPreferences("user_session", Context.MODE_PRIVATE)
        val userUid = sharedPrefs.getString("user_uid", null) // Correct way to retrieve a String value from SharedPreferences

        if (userUid != null) {
            // Fetch user data using the userId from the ViewModel
            profileViewModel.getUserById(userUid)

            // Observe the LiveData in ViewModel
            profileViewModel.user.observe(viewLifecycleOwner, Observer { user ->
                if (user != null) {
                    // Bind data to UI components when data is successfully fetched
                    Log.d("Fragment Profile","user uid profile : ${userUid}")
                    bindData(
                        user.username,  // Assuming user object has a 'username' property
                        user.email,     // Assuming user object has an 'email' property
                        user.totalExp,  // Assuming user object has 'totalExp' property
                        user.totalScore,// Assuming user object has 'totalScore' property
                        user.age,       // Assuming user object has 'age' property
                        user.userId.toString(),
                        user.username,
                        user.email,// Assuming user object has 'userId' property
                    )
                }
            })
        } else {
            Log.e("fragment_profile", "No user UID found in SharedPreferences.")
        }

        return rootView
    }

    // Function to bind data from another class
    fun bindData(username2Text: String, email2Text: String, totalExpValue: Int, totalScoreValue: Int, ageValue: Int, userIdValue: String, usernameText: String, emailText: String) {
        username2.text = username2Text
        email2.text = email2Text
        totalExp.text = totalExpValue.toString()
        totalScore.text = totalScoreValue.toString()
        age.text = ageValue.toString()
        userId.text = userIdValue
        username.text = usernameText
        email.text = emailText
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment_profile().apply {
                arguments = Bundle().apply {
                    putString(Constants.ARG_PARAM1, param1)
                    putString(Constants.ARG_PARAM2, param2)
                }
            }
    }
}

