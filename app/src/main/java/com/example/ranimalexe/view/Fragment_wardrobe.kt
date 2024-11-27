package com.example.ranimalexe.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ranimalexe.Constants
import com.example.ranimalexe.R
import com.example.ranimalexe.view.adapter.WardrobeAdapter
import com.example.ranimalexe.viewmodel.WardrobeViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_wardrobe.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_wardrobe : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var wardrobeAdapter: WardrobeAdapter
    private lateinit var wardrobeViewModel: WardrobeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(Constants.ARG_PARAM1)
            param2 = it.getString(Constants.ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = inflater.inflate(R.layout.fragment_wardrobe, container, false)

        recyclerView = binding.findViewById(R.id.recyclerView)

        recyclerView.layoutManager = GridLayoutManager(context, 2)

        wardrobeViewModel = ViewModelProvider(this).get(WardrobeViewModel::class.java)

        wardrobeViewModel.cosmeticList.observe(viewLifecycleOwner) { wardrobeItems ->
            wardrobeAdapter = WardrobeAdapter(wardrobeItems)
            recyclerView.adapter = wardrobeAdapter
        }

        return binding
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fragment_wardrobe.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment_wardrobe().apply {
                arguments = Bundle().apply {
                    putString(Constants.ARG_PARAM1, param1)
                    putString(Constants.ARG_PARAM2, param2)
                }
            }
    }
}