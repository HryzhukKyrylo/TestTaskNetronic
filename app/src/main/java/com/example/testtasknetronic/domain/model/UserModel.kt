package com.example.testtasknetronic.domain.model

import android.os.Parcelable
import com.example.testtasknetronic.data.model.User
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    val firstName: String,
    val lastName: String,
    val pictureThumb: String,
    val pictureLarge: String,
    val cell: String,
    val email: String,
    val phone: String,
    val city: String,
    val country: String,
) : Parcelable

fun User.mapToDomain(): UserModel =
    UserModel(
        firstName = this.name.first,
        lastName = this.name.last,
        pictureThumb = this.picture.thumbnail,
        pictureLarge = this.picture.large,
        cell = this.cell,
        email = this.email,
        phone = this.phone,
        city = this.location.city,
        country = this.location.country
    )