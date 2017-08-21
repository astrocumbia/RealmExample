package me.irvincastellanos.realmtest.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_person.view.*
import me.irvincastellanos.realmtest.R
import me.irvincastellanos.realmtest.model.Person

/**
 * Created by positr0nix on 8/18/17.
 */
class PersonAdapter(var data: List<Person>) : RecyclerView.Adapter<PersonAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val person = data[position]
        holder?.editTextId?.text = person.id.toString()
        holder?.editTextName?.text = person.name
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context)
                .inflate(R.layout.item_person, parent, false)

        return PersonAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(view: View?) : RecyclerView.ViewHolder(view) {
        val editTextId = view?.textViewId
        val editTextName = view?.textViewName
    }
}