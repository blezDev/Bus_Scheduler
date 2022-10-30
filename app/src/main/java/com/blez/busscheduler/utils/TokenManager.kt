package com.blez.busscheduler.utils

import android.content.Context
import android.content.SharedPreferences
import com.blez.busscheduler.utils.Contants.PREFS_TOKEN_FILE
import com.blez.busscheduler.utils.Contants.USER_EMAIL
import com.blez.busscheduler.utils.Contants.USER_TOKEN
import com.blez.busscheduler.utils.Contants.USER_TYPE

class TokenManager(context: Context) {
    private var prefs : SharedPreferences = context.getSharedPreferences(PREFS_TOKEN_FILE, Context.MODE_PRIVATE)

    fun saveToken(token : String)
    {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN,token)
        editor.apply()
    }
    fun saveEmail(email : String)
    {
        val editor = prefs.edit()
        editor.putString(USER_EMAIL,email)
        editor.apply()
    }

    fun saveUserType(userType : String){
        val editor = prefs.edit()
        editor.putString(USER_TYPE,userType)
        editor.apply()
    }

    fun getToken() : String?
    {
        return prefs.getString(USER_TOKEN,null)
    }
    fun getUserType() : String?
    {
        return prefs.getString(USER_TYPE,null)
    }

    fun getEmail() : String?{
        return prefs.getString(USER_EMAIL,null)
    }
    fun deteleCredit(){
        if(prefs.getString(USER_TOKEN,null) != null && prefs.getString(USER_EMAIL,null) != null)
        {
            val editor = prefs.edit()
            editor.clear()
            editor.apply()
        }

    }


}