package com.example.examen_ib

import android.os.Parcel
import android.os.Parcelable

class BArquitecto_Proyecto (
    val cedulaArquitecto: Int,
    val numProyecto: Int
    ):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(cedulaArquitecto)
        parcel.writeInt(numProyecto)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BArquitecto_Proyecto> {
        override fun createFromParcel(parcel: Parcel): BArquitecto_Proyecto {
            return BArquitecto_Proyecto(parcel)
        }

        override fun newArray(size: Int): Array<BArquitecto_Proyecto?> {
            return arrayOfNulls(size)
        }
    }
}