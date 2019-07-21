package com.app.android.june.alcmeetup20

import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.app.android.june.alcmeetup20.java.Main4Activity
import com.app.android.june.alcmeetup20.java.user
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private var editName: EditText? = null
    private var editPhone:EditText? = null
    private var editEmail:EditText? = null

     var Name: String = "name"
     var Phone:String = "phone"
     var Email:String = "email"

    private var mDatabase: DatabaseReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editName = findViewById(R.id.name)
        editPhone = findViewById(R.id.phone)
        editEmail = findViewById(R.id.email)

        mDatabase = FirebaseDatabase.getInstance().reference
    }

    fun submitData(view: View) {
        Name = editName?.text.toString()
        Phone = editPhone?.text.toString()
        Email = editEmail?.text.toString()

        if (TextUtils.isEmpty(Name) || TextUtils.isEmpty(Phone) || TextUtils.isEmpty(Email)) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
        } else {
            sendToDatabase(Name!!, Phone!!, Email!!)
        }

    }


    private fun sendToDatabase(Name: String, Phone: String, Email: String) {
        val details = user(Name, Phone, Email)
        mDatabase?.child("users")?.setValue(details)?.addOnSuccessListener {
            displayDialog() }
                ?.addOnFailureListener { e -> Toast.makeText(this@MainActivity, "An error has occured" + e.toString(), Toast.LENGTH_LONG).show() }
    }


    private fun displayDialog() {
        val alertDialogBuilder = AlertDialog.Builder(this@MainActivity)
        alertDialogBuilder.setTitle("Data sent successfully")
        alertDialogBuilder.setCancelable(false).setIcon(R.drawable.ic_done)
                .setMessage("You have sent your details to firebase database")
                .setPositiveButton("Okay") { dialog, id ->
                    dialog.cancel()
                    editName?.setText(" ")
                    editPhone?.setText(" ")
                    editEmail?.setText(" ")
                }


        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    fun goToNextActivity(view: View) {
        val intent = Intent(this, Main2Activity::class.java)
        startActivity(intent)
    }
}
