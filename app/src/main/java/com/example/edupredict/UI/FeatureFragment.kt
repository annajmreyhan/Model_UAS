package com.example.edupredict.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.edupredict.R
import com.example.edupredict.model.FeatureItem

class FeatureFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FeatureAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_feature, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.rvFeatureList)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val featureList = listOf(
            FeatureItem("📊 Dataset Siswa", "Dataset berisi data siswa seperti gender, ras, pendidikan orang tua, jenis makan siang, nilai membaca, nilai menulis, dan nilai matematika."),
            FeatureItem("📈 Pemrosesan Data", "Data dikodekan dan dinormalisasi menggunakan StandardScaler. Target dikonversi menjadi label biner (lulus/tidak)."),
            FeatureItem("🤖 Model ANN", "Model dibuat menggunakan 3 layer (64, 32, dan 1 neuron) dengan aktivasi relu & sigmoid, serta binary crossentropy."),
            FeatureItem("✅ Evaluasi Model", "Model diuji menggunakan akurasi, confusion matrix, dan classification report."),
            FeatureItem("📱 Simulasi Prediksi", "Fitur simulasi memungkinkan pengguna memasukkan data dan melihat apakah siswa diprediksi lulus matematika."),
            FeatureItem("🔄 Deployment TFLite", "Model ANN diekspor ke format .tflite dan digunakan di Android menggunakan TensorFlow Lite."),
        )

        adapter = FeatureAdapter(featureList)
        recyclerView.adapter = adapter
    }
}
