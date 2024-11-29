package com.example.ranimalexe.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.ranimalexe.Constants
import com.example.ranimalexe.R
import com.example.ranimalexe.storage.UserData

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

        return rootView
    }

    // Function to bind data from another class
    fun bindData(usernameText: String, emailText: String, totalExpValue: Int, totalScoreValue: Int, ageValue: Int, userIdValue: String) {
        username2.text = UserData.user.username
        email2.text = UserData.user.email
        totalExp.text = UserData.user.currentExp.toString()
        totalScore.text = UserData.user.totalScore.toString()
        age.text = UserData.user.age.toString()
        userId.text = UserData.user.userId.toString()
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
