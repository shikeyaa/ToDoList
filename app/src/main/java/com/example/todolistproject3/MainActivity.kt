package com.example.todolistproject3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistproject3.model.UserData
import com.example.todolistproject3.view.UserAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var addsBtn:FloatingActionButton
    private lateinit var recv:RecyclerView
    private lateinit var userList:ArrayList<UserData>
    private lateinit var userAdapter: UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //set List
        userList = ArrayList()

        //set Find Id
        addsBtn = findViewById(R.id.addingBtn)
        recv = findViewById(R.id.mRecycler)

        //set adapter
        userAdapter = UserAdapter(this,userList)

        //set recycler view adapter
        recv.layoutManager = LinearLayoutManager(this)
        recv.adapter = userAdapter

        //set dialog
        addsBtn.setOnClickListener {addInfo() }

    }

    private fun addInfo() {
        val inflater = LayoutInflater.from(this)
        val v = inflater.inflate(R.layout.add_item, null)
        //set view
        val userName = v.findViewById<EditText>(R.id.userName)
        val userDue = v.findViewById<EditText>(R.id.userDue)

        val addDialog = AlertDialog.Builder(this)

        addDialog.setView(v)
        addDialog.setPositiveButton("Ok"){
            dialog,_->
            val names = userName.text.toString()
            val Due = userDue.text.toString()
            userList.add(UserData("Task: $names","Due Date: $Due"))
            userAdapter.notifyDataSetChanged()
            Toast.makeText(this, "Adding New Task Success",Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        addDialog.setNegativeButton("Cancel"){
            dialog,_->
            dialog.dismiss()
            Toast.makeText(this, "Cancel",Toast.LENGTH_SHORT).show()
        }
        addDialog.create()
        addDialog.show()
    }

}