package com.app.android.june.alcmeetup20

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.app.android.june.alcmeetup20.java.user
import com.google.firebase.database.*

class Main2Activity : AppCompatActivity() {

    internal lateinit var nameText: TextView
    internal lateinit var phoneText:TextView
    internal lateinit var emailText:TextView

    internal var name: String = "name"
    internal var phone:String = "phone"
    internal var email:String = "email"

    private var mDatabase: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        nameText = findViewById(R.id.nameText)
        phoneText = findViewById(R.id.phoneText)
        emailText = findViewById(R.id.emailText)

        mDatabase = FirebaseDatabase.getInstance().reference
        getDetails()
    }
        private fun getDetails() {
            try {
                mDatabase?.child("users")?.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        val details = dataSnapshot.getValue(user::class.java)
                        name = details!!.name
                        phone = details!!.phone
                        email = details!!.email

                        nameText.text = "Name: $name"
                        phoneText.text = "Phone: $phone"
                        emailText.text = "Email: $email"
                    }

                    override fun onCancelled(databaseError: DatabaseError) {

                    }
                })
            } catch (e: Exception) {
                Toast.makeText(this@Main2Activity, "Error occured" + e.toString(), Toast.LENGTH_LONG).show()
            }

        }
    }