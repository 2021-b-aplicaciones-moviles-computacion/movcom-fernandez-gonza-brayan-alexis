package com.example.examen_ib

import android.os.Parcel
import android.os.Parcelable

class FirestoreArquitecto (
    var arquitectoId: String?,
    var arquitectoNombre: String?,
    var salario: String?
    ) :Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun toString(): String {
        return "${arquitectoNombre}"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(arquitectoId)
        parcel.writeString(arquitectoNombre)
        parcel.writeString(salario)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FirestoreArquitecto> {
        override fun createFromParcel(parcel: Parcel): FirestoreArquitecto {
            return FirestoreArquitecto(parcel)
        }

        override fun newArray(size: Int): Array<FirestoreArquitecto?> {
            return arrayOfNulls(size)
        }
    }
}