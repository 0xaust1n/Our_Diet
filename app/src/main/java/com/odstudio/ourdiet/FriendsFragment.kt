package com.odstudio.ourdiet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment



class FriendsFragment: Fragment(){
    companion object{
        fun  newInstance(): FriendsFragment = FriendsFragment()
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_friends, container, false)
}
}