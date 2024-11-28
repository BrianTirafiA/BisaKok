package com.example.ranimalexe.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ranimalexe.Constants
import com.example.ranimalexe.R
import com.example.ranimalexe.view.adapter.HatAdapter
import com.example.ranimalexe.view.adapter.ShellAdapter
import com.example.ranimalexe.viewmodel.HatViewModel
import com.example.ranimalexe.viewmodel.PetViewModel
import com.example.ranimalexe.viewmodel.ShellViewModel
import androidx.fragment.app.activityViewModels


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
    private lateinit var recyclerViewHat: RecyclerView
    private lateinit var recyclerViewShell: RecyclerView
    private lateinit var hatAdapter: HatAdapter
    private lateinit var hatViewModel: HatViewModel
    private lateinit var shellAdapter: ShellAdapter
    private lateinit var shellViewModel: ShellViewModel
    private val petViewModel: PetViewModel by activityViewModels()

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

        recyclerViewHat = binding.findViewById(R.id.recyclerViewHat)
        recyclerViewShell = binding.findViewById(R.id.recyclerViewShell)
        val selectedShellImage: ImageView = binding.findViewById(R.id.selectedShellImage)
        val selectedHatImage: ImageView = binding.findViewById(R.id.selectedHatImage)

        recyclerViewHat.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewShell.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        hatViewModel = ViewModelProvider(this)[HatViewModel::class.java]
        shellViewModel = ViewModelProvider(this)[ShellViewModel::class.java]

        hatViewModel.hatList.observe(viewLifecycleOwner) { hatItems ->
            hatAdapter = HatAdapter(hatItems) { selectedHat ->
                petViewModel.updateHat(selectedHat) // Update the pet's hat
                selectedHatImage.setImageResource(selectedHat) // Update selected hat image
            }
            recyclerViewHat.adapter = hatAdapter
        }

        shellViewModel.shellList.observe(viewLifecycleOwner) { shellItems ->
            shellAdapter = ShellAdapter(shellItems) { selectedShell ->
                petViewModel.updateClothes(selectedShell) // Update the pet's clothes
                selectedShellImage.setImageResource(selectedShell) // Update selected shell image
            }
            recyclerViewShell.adapter = shellAdapter
        }

        petViewModel.customization.observe(viewLifecycleOwner) { customization ->
            selectedShellImage.setImageResource(customization.clothes)
            selectedHatImage.setImageResource(customization.hat)
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