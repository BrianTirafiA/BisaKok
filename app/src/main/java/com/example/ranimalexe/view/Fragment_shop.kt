package com.example.ranimalexe.view

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.view.animation.RotateAnimation
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ranimalexe.Constants
import com.example.ranimalexe.R
import com.example.ranimalexe.storage.UserData
import com.example.ranimalexe.view.adapter.ShopAdapter
import com.example.ranimalexe.view.adapter.ShopHatAdapter
import com.example.ranimalexe.view.adapter.ShopShellAdapter
import com.example.ranimalexe.viewmodel.HatViewModel
import com.example.ranimalexe.viewmodel.ShellViewModel
import com.example.ranimalexe.viewmodel.ShopViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_shop.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_shop : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var shopAdapter: ShopAdapter
    private lateinit var shopViewModel: ShopViewModel
    private lateinit var recyclerViewHat: RecyclerView
    private lateinit var recyclerViewShell: RecyclerView
    private lateinit var hatAdapter: ShopHatAdapter
    private lateinit var hatViewModel: HatViewModel
    private lateinit var shellAdapter: ShopShellAdapter
    private lateinit var shellViewModel: ShellViewModel
    private lateinit var spinStatus: TextView
    private lateinit var currentExp: TextView



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
        val binding = inflater.inflate(R.layout.fragment_shop, container, false)

        recyclerView = binding.findViewById(R.id.recyclerView)
        recyclerViewHat = binding.findViewById(R.id.recyclerViewHat)
        recyclerViewShell = binding.findViewById(R.id.recyclerViewShell)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewHat.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewShell.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        val spinButton: Button = binding.findViewById(R.id.spinButton)
        val spinner: View = binding.findViewById(R.id.spinnerWheel)
        spinStatus = binding.findViewById(R.id.spinStatus)
        currentExp = binding.findViewById(R.id.bindEXP)

        shopViewModel = ViewModelProvider(this)[ShopViewModel::class.java]
        hatViewModel = ViewModelProvider(this)[HatViewModel::class.java]
        shellViewModel = ViewModelProvider(this)[ShellViewModel::class.java]

//        shopViewModel.fruitList.observe(viewLifecycleOwner) { shopItems ->
//            shopAdapter = ShopAdapter(shopItems) { selectedFood ->
//                val message = "Congrats! You got an item!"
//                val builder = AlertDialog.Builder(requireContext())
//                    .setMessage(message)
//                    .setPositiveButton("OK") { dialog, _ ->
//                        dialog.dismiss()
//                    }
//            }
//            recyclerView.adapter = shopAdapter
//        }

//        hatViewModel.filteredHat.observe(viewLifecycleOwner) { hatItems ->
//            hatAdapter = ShopHatAdapter(hatItems) { selectedHat ->
//                val message = "Congrats! You got an item!"
//                val builder = AlertDialog.Builder(requireContext())
//                    .setMessage(message)
//                    .setPositiveButton("OK") { dialog, _ ->
//                        dialog.dismiss()
//                    }
//            }
//            recyclerViewHat.adapter = hatAdapter
//        }

        currentExp.text = UserData.user.currentExp.toString()

        hatViewModel.filteredHat.observe(viewLifecycleOwner) { hatItems ->
            hatAdapter = ShopHatAdapter(
                hatItemList = hatItems,
                onHatSelected = { selectedHat ->
                    val message = "Congrats! You got an item: ${selectedHat.name}"
                    val builder = AlertDialog.Builder(requireContext())
                        .setMessage(message)
                        .setPositiveButton("OK") { dialog, _ ->
                            dialog.dismiss()
                        }
                    builder.show()
                },
                onUnlockHat = { hatToUnlock ->
                    hatViewModel.unlockHat(hatToUnlock.id)
                    val message = "Congrats! You got an item:${hatToUnlock.name}"
                    val builder = AlertDialog.Builder(requireContext())
                        .setMessage(message)
                        .setPositiveButton("OK") { dialog, _ ->
                            dialog.dismiss()
                        }
                    builder.show()
                    hatViewModel.refreshHats()
                }
            )
            recyclerViewHat.adapter = hatAdapter
        }

        shellViewModel.filteredShells.observe(viewLifecycleOwner) { shellItems ->
            shellAdapter = ShopShellAdapter(shellItems) { selectedShell ->
                val message = "Congrats! You got an item!"
                val builder = AlertDialog.Builder(requireContext())
                    .setMessage(message)
                    .setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss()
                    }
            }
            recyclerViewShell.adapter = shellAdapter
        }

        spinButton.setOnClickListener {
            val randomDegree = (2160..2520).random()

            val rotateAnim = RotateAnimation(
                0f,
                randomDegree.toFloat(),
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f
            ).apply {
                duration = 3000
                fillAfter = true
                interpolator = DecelerateInterpolator()
            }

            spinner.startAnimation(rotateAnim)
            Handler().postDelayed({
                val landingDegree = randomDegree % 360
                showResultPopup(landingDegree)
            }, rotateAnim.duration)
        }

        return binding
    }

    private fun showResultPopup(degree: Int) {
        val randomNumber: Int
        val category: String
        val color: Int

        when (degree) {
            in 355..360 -> {
                category = "Legendary"
                color = R.color.legendaryColor
                randomNumber = (29..31).random()
            }
            in 101..354 -> {
                category = "Common"
                color = R.color.commonColor
                randomNumber = (4..15).random()
            }
            in 31..100 -> {
                category = "Uncommon"
                color = R.color.uncommonColor
                randomNumber = (16..21).random()
            }
            in 0..30 -> {
                category = "Rare"
                color = R.color.light_blue_600
                randomNumber = (22..28).random()
            }
            else -> {
                category = "Error"
                color = R.color.white
                randomNumber = (1..10).random()
            }
        }

        val message = "Congrats! You got a $category item number $randomNumber"
        val builder = AlertDialog.Builder(requireContext())
            .setTitle(category)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }

        builder.setIcon(color)

        builder.show()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fragment_shop.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment_shop().apply {
                arguments = Bundle().apply {
                    putString(Constants.ARG_PARAM1, param1)
                    putString(Constants.ARG_PARAM2, param2)
                }
            }
    }
}