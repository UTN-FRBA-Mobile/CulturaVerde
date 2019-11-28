package com.example.culturaverde.Classes


//http://gpmess.com/blog/2013/10/02/como-cargar-fotos-en-una-aplicacion-android-desde-camara-galeria-y-otras-aplicaciones/


import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream


class PhotoUtils(context: Context) {
    private var generalOptions: BitmapFactory.Options? = null
    fun getImage(uri: Uri?): Bitmap? {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        var `is`: InputStream? = null
        try {
            `is` = mContext.getContentResolver().openInputStream(uri!!)
            BitmapFactory.decodeStream(`is`, null, options)
            `is`!!.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) { // TODO Auto-generated catch block
            e.printStackTrace()
        }
        generalOptions = options
        return scaleImage(options, uri, 300)
    }

    fun scaleImage(
        options: BitmapFactory.Options?, uri: Uri?,
        targetWidth: Int
    ): Bitmap? {
        var options = options
        if (options == null) options = generalOptions
        var bitmap: Bitmap? = null
        val ratioWidth: Double =
            targetWidth.toDouble() / options!!.outWidth.toDouble()
        val ratioHeight: Double =
            targetWidth.toDouble()/ options!!.outHeight.toDouble()
        var ratio = Math.min(ratioWidth, ratioHeight)
        val dstWidth = Math.round(ratio * options.outWidth).toInt()
        val dstHeight = Math.round(ratio * options.outHeight).toInt()
        ratio = Math.floor(1.0 / ratio)
        var sample = nearest2pow(ratio.toInt())
        options.inJustDecodeBounds = false
        if (sample <= 0) {
            sample = 1
        }
        options.inSampleSize = sample
        options.inPurgeable = true
        try {
            val `is`: InputStream
            `is` = mContext.getContentResolver().openInputStream(uri!!)!!
            bitmap = BitmapFactory.decodeStream(`is`, null, options)
            if (sample > 1) bitmap = Bitmap.createScaledBitmap(
                bitmap!!, dstWidth, dstHeight,
                true
            )
            `is`.close()
        } catch (e: OutOfMemoryError) {
            e.printStackTrace()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return bitmap
    }

    companion object {
        private lateinit var mContext: Context
        @Throws(Exception::class)
        fun createTemporaryFile(
            part: String?, ext: String?,
            myContext: Context
        ): File {
            val path: String = myContext.getExternalCacheDir()!!.getAbsolutePath()
                .toString() + "/temp/"
            val tempDir = File(path)
            if (!tempDir.exists()) {
                tempDir.mkdir()
            }
            return File.createTempFile(part, ext, tempDir)
        }

        fun nearest2pow(value: Int): Int {
            return if (value == 0) 0 else (32 - Integer.numberOfLeadingZeros(value - 1)) / 2
        }
    }

    init {
        mContext = context
    }
}