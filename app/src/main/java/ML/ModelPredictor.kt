package com.example.edupredict.model

import android.content.Context
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.support.common.FileUtil

class ModelPredictor(context: Context) {

    private var interpreter: Interpreter

    init {
        val model = FileUtil.loadMappedFile(context, "edupredict_model.tflite")
        interpreter = Interpreter(model)
    }

    fun predict(inputData: FloatArray): Float {
        if (inputData.size != 7) {
            throw IllegalArgumentException("Input must have 7 features")
        }

        val input = arrayOf(inputData)
        val output = Array(1) { FloatArray(1) }
        interpreter.run(input, output)
        return output[0][0]
    }
}
