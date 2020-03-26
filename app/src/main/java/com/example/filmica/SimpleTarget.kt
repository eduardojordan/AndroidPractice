package com.example.filmica

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.lang.Exception

 class SimpleTarget(
     val prepareCallBack: ((placeHolderDrawable: Drawable?) -> Unit)? = null,
     val successCallback: ((bitmap: Bitmap, from: Picasso.LoadedFrom) -> Unit)? = null,
     val failedCallback: ((e: Exception?, errorDrawable: Drawable?) -> Unit)? = null

):Target{
    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
        prepareCallBack?.invoke((placeHolderDrawable))
}

    override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
        failedCallback?.invoke(e,errorDrawable)
}

    override fun onBitmapLoaded(bitmap: Bitmap, from: Picasso.LoadedFrom) {
        successCallback?.invoke(bitmap,from)
}

}