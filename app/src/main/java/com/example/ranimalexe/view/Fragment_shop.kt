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
import com.example.ranimalexe.view.adapter.ShopAdapter
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
    private lateinit var spinButton: Button
    private lateinit var spinner: View
    private lateinit var spinStatus: TextView


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
        recyclerView.layoutManager = LinearLayoutManager(context)

        val spinButton: Button = binding.findViewById(R.id.spinButton)
        val spinner: View = binding.findViewById(R.id.spinnerWheel)
        spinStatus = binding.findViewById(R.id.spinStatus)
        shopViewModel = ViewModelProvider(this).get(ShopViewModel::class.java)

        shopViewModel.fruitList.observe(viewLifecycleOwner, { shopItems ->
            shopAdapter = ShopAdapter(shopItems)
            recyclerView.adapter = shopAdapter
        })

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
        val category: String
        val color: Int

        when (degree) {
            in 0..90 -> {
                category = "Legendary"
                color = R.color.legendaryColor
            }
            in 90..270 -> {
                category = "Common"
                color = R.color.commonColor
            }
            in 270..360 -> {
                category = "Uncommon"
                color = R.color.uncommonColor
            }
            else -> {
                category = "Error"
                color = R.color.white
            }
        }

        val message = "Congrats! You got a $category item!"
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