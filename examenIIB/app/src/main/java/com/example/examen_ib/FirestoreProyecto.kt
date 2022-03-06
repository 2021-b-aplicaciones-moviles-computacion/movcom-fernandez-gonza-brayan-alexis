package com.example.examen_ib

import android.os.Parcel
import android.os.Parcelable

class FirestoreProyecto(
    var proyectoId: String?,
    var nombreProyecto: String?,
    var costo: String?,
    var arquitectoProyecto: String?
) :Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ){
    }

    override fun toString(): String {
        return "${nombreProyecto}"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(proyectoId)
        parcel.writeString(nombreProyecto)
        parcel.writeString(costo)
        parcel.writeString(arquitectoProyecto)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FirestoreProyecto> {
        override fun createFromParcel(parcel: Parcel): FirestoreProyecto {
            return FirestoreProyecto(parcel)
        }

        override fun newArray(size: Int): Array<FirestoreProyecto?> {
            return arrayOfNulls(size)
        }
    }
}