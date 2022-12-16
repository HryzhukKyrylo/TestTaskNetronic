package com.example.testtasknetronic.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.testtasknetronic.domain.model.UserModel

@Entity(tableName = "my_user_table")
data class UserDTO(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val firstName: String,
    val lastName: String,
    val pictureThumb: String,
    val pictureLarge: String,
    val cell: String,
    val email: String,
    val phone: String,
    val city: String,
    val country: String,
)

fun UserModel.toDTO(): UserDTO =
    UserDTO(
        firstName = this.firstName,
        lastName = this.lastName,
        pictureThumb = this.pictureThumb,
        pictureLarge = this.pictureLarge,
        cell = this.cell,
        email = this.email,
        phone = this.phone,
        city = this.city,
        country = this.country
    )

fun UserDTO.toModel(): UserModel =
    UserModel(
        firstName = this.firstName,
        lastName = this.lastName,
        pictureThumb = this.pictureThumb,
        pictureLarge = this.pictureLarge,
        cell = this.cell,
        email = this.email,
        phone = this.phone,
        city = this.city,
        country = this.country
    )