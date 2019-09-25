package com.odstudio.ourdiet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import org.w3c.dom.Text


class PersonalFragment: Fragment() {
    companion object {
        fun newInstance(): PersonalFragment = PersonalFragment()
    }
    var center: TextView? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_personal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
     center = getView()?.findViewById(R.id.text_center)!!
     center?.text =  FirebaseAuth.getInstance().currentUser!!.uid
    }
}