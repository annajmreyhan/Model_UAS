package com.example.edupredict.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.edupredict.R

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Temukan tombol di layout
        val btnStartSim = view.findViewById<Button>(R.id.btnStartSimulation)

        // Navigasi ke SimulationFragment saat diklik
        btnStartSim.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_simulationFragment)
        }
    }
}
