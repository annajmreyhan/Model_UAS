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

        // Tombol Simulasi
        view.findViewById<Button>(R.id.btnStartSimulation).setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_simulationFragment)
        }

        // Tombol Dataset
        view.findViewById<Button>(R.id.btnViewDataset).setOnClickListener {
            findNavController().navigate(R.id.datasetFragment)
        }

        // Tombol Fitur Aplikasi
        view.findViewById<Button>(R.id.btnViewFeatures).setOnClickListener {
            findNavController().navigate(R.id.featureFragment)
        }

        // Tombol Arsitektur Model
        view.findViewById<Button>(R.id.btnViewModel).setOnClickListener {
            findNavController().navigate(R.id.modelArchitectureFragment)
        }

        // Tombol Tentang Aplikasi
        view.findViewById<Button>(R.id.btnAboutApp).setOnClickListener {
            findNavController().navigate(R.id.aboutFragment)
        }
    }
}
