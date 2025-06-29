package com.example.edupredict.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.edupredict.R

class AboutFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val txtAbout = view.findViewById<TextView>(R.id.txtAboutApp)
        txtAbout.text = """
📱 Tentang Aplikasi EduPredict

EduPredict adalah aplikasi prediksi kelulusan mata pelajaran Matematika bagi siswa sekolah menengah, berdasarkan faktor latar belakang seperti jenis kelamin, ras/etnis, tingkat pendidikan orang tua, jenis makan siang, kursus persiapan ujian, serta nilai membaca dan menulis.

Aplikasi ini memanfaatkan model Artificial Neural Network (ANN) yang telah dilatih dan di-convert ke format TensorFlow Lite (TFLite) agar bisa berjalan langsung secara offline di perangkat Android.

📊 Dataset:
https://www.kaggle.com/datasets/spscientist/students-performance-in-exams

📌 Fitur Utama:
• Simulasi prediksi nilai matematika
• Input data siswa
• Model ANN offline
• Evaluasi model (akurasi, confusion matrix)
• Navigasi multi-halaman

👨‍💻 Pembuat Aplikasi:
Nama: An Najm Reyhan  
NIM : 221351008  
Prodi: S1 Teknik Informatika  
Mata Kuliah: Pemrograman Mobile 2

📅 Tahun: 2025
""".trimIndent()
    }
}
