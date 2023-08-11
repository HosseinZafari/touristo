package com.github.hosseinzafari.touristo.base.helper

import android.content.Context
import android.net.Uri
import android.os.FileUtils
import android.provider.OpenableColumns
import android.util.Log
import java.io.File
import java.io.OutputStream

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 10/08/2023 - 2:00 PM
 * @project Touristo
 */


fun Uri.toTemporaryFile(context: Context , fileName: String) : File? {
    return try {
        val inputStream = context.contentResolver.openInputStream(this)
        val tempFile = File(context.cacheDir  , fileName)

        tempFile.outputStream().use {
            inputStream?.copyTo(it)
        }

        inputStream?.close()
        tempFile
    } catch (e: Exception) {
        Log.i("Test" , "Error in toTemporaryFile() " + e.printStackTrace())
        null
    }
}

fun Uri.getName(context: Context) : String ? {
    context.contentResolver.query(this, arrayOf(OpenableColumns.DISPLAY_NAME), null, null, null)
        .use {
            if (it == null || !it.moveToFirst()) {
                return null
            }

            val columnIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            if (columnIndex == -1) {
                return null
            }

            return it.getString(columnIndex)
        }
}

fun deleteTemporaryFile(context: Context , fileName: String) : Boolean{
    val file = File(context.cacheDir , fileName)
    if (file.exists()) {
        Log.i("Test" , "file founded")
        return file.delete()
    } else {
        Log.i("Test" , "file not found")
        return false
    }
}