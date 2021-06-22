package com.mi.walkthroughandroid.data

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.mi.walkthroughandroid.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class WalkThroughScreenModel(
    @DrawableRes val image: Int = R.drawable.placeholder,
    val imageUrl: String = "",
    @StringRes val title: Int,
    @StringRes val description: Int
) : Parcelable


