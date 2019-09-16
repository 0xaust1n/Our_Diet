package com.odstudio.ourdiet.Data_Class

data class StudentInfo(
    var studentList: ArrayList<User> = arrayListOf()
)
data class User(val uid:String?= null,
                val LastName:String?= null,
                val FirstName:String?= null)