package com.example.edupredict.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.edupredict.R
import com.example.edupredict.model.ModelPredictor
import kotlinx.coroutines.*

class SimulationFragment : Fragment() {

    private lateinit var predictor: ModelPredictor
    private lateinit var progressBar: ProgressBar
    private lateinit var result: TextView
    private lateinit var btn: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_simulation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        predictor = ModelPredictor(requireContext())

        val spinnerGender = view.findViewById<Spinner>(R.id.spinnerGender)
        val spinnerRace = view.findViewById<Spinner>(R.id.spinnerRace)
        val spinnerParentEdu = view.findViewById<Spinner>(R.id.spinnerParentEdu)
        val spinnerLunch = view.findViewById<Spinner>(R.id.spinnerLunch)
        val spinnerTestPrep = view.findViewById<Spinner>(R.id.spinnerTestPrep)
        val editReading = view.findViewById<EditText>(R.id.editReading)
        val editWriting = view.findViewById<EditText>(R.id.editWriting)

        btn = view.findViewById(R.id.btnPredict)
        result = view.findViewById(R.id.txtResult)
        progressBar = view.findViewById(R.id.progressBar)

        // Isi spinner
        spinnerGender.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, arrayOf("female", "male"))
        spinnerRace.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, arrayOf("group A", "group B", "group C", "group D", "group E"))
        spinnerParentEdu.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, arrayOf("some high school", "high school", "some college", "associate's degree", "bachelor's degree", "master's degree"))
        spinnerLunch.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, arrayOf("standard", "free/reduced"))
        spinnerTestPrep.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, arrayOf("none", "completed"))

        btn.setOnClickListener {
            val reading = editReading.text.toString().toFloatOrNull()
            val writing = editWriting.text.toString().toFloatOrNull()

            if (reading == null || writing == null) {
                Toast.makeText(requireContext(), "Masukkan nilai membaca dan menulis dengan benar", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val input = encodeInputs(
                spinnerGender.selectedItem.toString(),
                spinnerRace.selectedItem.toString(),
                spinnerParentEdu.selectedItem.toString(),
                spinnerLunch.selectedItem.toString(),
                spinnerTestPrep.selectedItem.toString(),
                reading,
                writing
            )

            progressBar.visibility = View.VISIBLE
            btn.isEnabled = false
            result.text = ""

            CoroutineScope(Dispatchers.Main).launch {
                delay(1000)
                try {
                    val prediction = predictor.predict(input)
                    val isPass = if (prediction >= 0.5f) "✅ Lulus Matematika" else "❌ Tidak Lulus Matematika"
                    result.text = "$isPass\nProbabilitas: %.2f".format(prediction)
                } catch (e: Exception) {
                    result.text = "Terjadi kesalahan prediksi: ${e.message}"
                }
                progressBar.visibility = View.GONE
                btn.isEnabled = true
            }
        }
    }

    private fun encodeInputs(
        gender: String,
        race: String,
        parentEdu: String,
        lunch: String,
        testPrep: String,
        reading: Float,
        writing: Float
    ): FloatArray {
        // Raw values
        val rawInput = floatArrayOf(
            if (gender == "female") 1f else 0f,
            when (race) {
                "group A" -> 0f
                "group B" -> 1f
                "group C" -> 2f
                "group D" -> 3f
                "group E" -> 4f
                else -> 0f
            },
            when (parentEdu) {
                "some high school" -> 0f
                "high school" -> 1f
                "some college" -> 2f
                "associate's degree" -> 3f
                "bachelor's degree" -> 4f
                "master's degree" -> 5f
                else -> 0f
            },
            if (lunch == "standard") 1f else 0f,
            if (testPrep == "completed") 1f else 0f,
            reading,
            writing
        )

        val means = floatArrayOf(0.508f, 2.187f, 2.541f, 0.66f, 0.656f, 70.382f, 69.14f)
        val stds = floatArrayOf(0.499936f, 1.143692f, 1.786706f, 0.473709f, 0.475041f, 14.100357f, 15.018402f)

        return FloatArray(rawInput.size) { i ->
            (rawInput[i] - means[i]) / stds[i]
        }
    }
}
