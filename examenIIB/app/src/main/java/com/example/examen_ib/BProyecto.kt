package com.example.examen_ib

import android.os.Parcel
import android.os.Parcelable

class BProyecto (
    val numProyecto: Int,
    var nombreProyecto: String?,
    var costo: String?
    ): Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(numProyecto)
        parcel.writeString(nombreProyecto)
        parcel.writeString(costo)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BProyecto> {
        override fun createFromParcel(parcel: Parcel): BProyecto {
            return BProyecto(parcel)
        }

        override fun newArray(size: Int): Array<BProyecto?> {
            return arrayOfNulls(size)
        }
    }

}