package com.assignment.assignmentapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.toolbar.*

class LeaderBoardActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard)



    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.buttonSort_Toolbar ->
                Toast.makeText(this, "Sort is clicked", Toast.LENGTH_SHORT).show()
        }
    }


}