package me.irvincastellanos.realmtest.activitys

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import io.realm.Realm

import me.irvincastellanos.realmtest.R
import me.irvincastellanos.realmtest.storage.RealmPerson

import kotlinx.android.synthetic.main.activity_main.*
import me.irvincastellanos.realmtest.adapters.PersonAdapter
import me.irvincastellanos.realmtest.model.Person
import io.realm.RealmConfiguration
import android.support.design.widget.Snackbar



class MainActivity : AppCompatActivity() {

    val TAG: String = MainActivity::class.java.canonicalName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Make migration if a model change
        Realm.init(this)
        Realm.setDefaultConfiguration(RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build())

        // Create adapter for a recycler view
        val adapter = PersonAdapter(toListModel(getData(Realm.getDefaultInstance())))
        rcMainView.layoutManager = LinearLayoutManager(this)
        rcMainView.adapter = adapter

        floatActionBtnMain.setOnClickListener {
            startActivity(Intent(this, RegisterContactActivity::class.java))
        }

        btnSave.setOnClickListener {
            val id: Long = editTextId.text.toString().toLong()
            val name: String = editTextName.text.toString()
            val person = RealmPerson(id, name)

            saveData(Realm.getDefaultInstance(), person)

            val list = getData(Realm.getDefaultInstance())

            adapter.data = toListModel(list)
            adapter.notifyDataSetChanged()
        }
    }

    fun saveData(realmInstance: Realm, realmPerson: RealmPerson) {
        realmInstance.beginTransaction()
        realmInstance.insertOrUpdate(realmPerson)
        realmInstance.commitTransaction()
    }

    fun getData(realmInstance: Realm) : List<RealmPerson> {
        return realmInstance.where(RealmPerson::class.java).findAll().sort("id")
    }

    fun toListModel(realmList: List<RealmPerson>): List<Person> {
        val list = mutableListOf<Person>()
        for (item in realmList) {
            list.add( item.toModel() )
        }
        return list
    }

}
