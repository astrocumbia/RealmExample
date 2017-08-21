package me.irvincastellanos.realmtest.storage

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import me.irvincastellanos.realmtest.model.Person

/**
 * Created by positr0nix on 8/18/17.
 */

@RealmClass
open class RealmPerson(
        @PrimaryKey
        var id: Long ?= 0,
        var name: String ?= "None"
) : RealmObject() {

        fun toModel(): Person {
                return Person(id ?: 0, name ?: "None")
        }

}