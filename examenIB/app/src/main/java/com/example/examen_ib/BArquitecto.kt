package com.example.examen_ib

import android.os.Parcel
import android.os.Parcelable

class BArquitecto(
    val cedulaArquitecto: Int,
    var nombreArquitecto: String?,
    var salario: String?,
    ) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun toString():String{
        return "${nombreArquitecto}"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(cedulaArquitecto)
        parcel.writeString(nombreArquitecto)
        parcel.writeString(salario)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BArquitecto> {
        override fun createFromParcel(parcel: Parcel): BArquitecto {
            return BArquitecto(parcel)
        }

        override fun newArray(size: Int): Array<BArquitecto?> {
            return arrayOfNulls(size)
        }
    }


}