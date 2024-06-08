package com.example.onlineshop.models.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Product(


    @field:SerializedName("id")
    val id: Int? =  null,

    @field:SerializedName("name")
    val name: String? =  null,

    @field:SerializedName("size")
    val size: String? =  null,

    @field:SerializedName("price")
    val price: Int? = null,

    @field:SerializedName("img")
    val imageUrl: String? = null,

    @field:SerializedName("merk")
    val merk: String? =  null,

    @field:SerializedName("motif")
    val motif: String? = null,

    @field:SerializedName("category_id")
    val CategoryId: String? = null,




): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(name)
        parcel.writeString(size)
        parcel.writeValue(price)
        parcel.writeString(imageUrl)
        parcel.writeString(merk)
        parcel.writeString(motif)
        parcel.writeString(CategoryId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }
}