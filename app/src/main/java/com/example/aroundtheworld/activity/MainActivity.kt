package com.example.aroundtheworld.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aroundtheworld.adapter.MyAdapter
import com.example.aroundtheworld.data.Blog
import com.example.aroundtheworld.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() ,
    MyAdapter.OnBlogItemClicked {

    private lateinit var dbref : DatabaseReference
    private lateinit var blogRecyclerView: RecyclerView
    private lateinit var blogArrayList : ArrayList<Blog>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        blogRecyclerView  = binding.userList
        blogRecyclerView.layoutManager = LinearLayoutManager(this)
        blogRecyclerView.setHasFixedSize(true)

        blogArrayList = arrayListOf<Blog>()

        getUserData()
    }

    private fun getUserData() {

        dbref = FirebaseDatabase.getInstance().getReference("blogs")

        dbref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){

                    for (userSnapshot in snapshot.children){


                        val user = userSnapshot.getValue(Blog::class.java)
                        blogArrayList.add(user!!)

//                        Log.i("yjg", "hello")

                    }

                    blogRecyclerView.adapter =
                            MyAdapter(
                                    blogArrayList,
                                    this@MainActivity
                            )

                }
                else{
                    val toast = Toast.makeText(applicationContext, "No Snapshot", Toast.LENGTH_SHORT)
                    toast.show()

                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }

    override fun onItemClick(item: Blog, position: Int) {
//        Toast.makeText(this,item.author,Toast.LENGTH_SHORT).show()
          val intent = Intent(this,BlogDetailsActivity::class.java)
            intent.putExtra("BlogAuthor",item.author)
            intent.putExtra("BlogTitle",item.title)
            intent.putExtra("BlogImageUrl",item.imageUrl)
            intent.putExtra("BlogBody",item.body)

            startActivity(intent)

    }


    }
