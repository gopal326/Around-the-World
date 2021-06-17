package com.example.aroundtheworld.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aroundtheworld.data.Blog
import com.example.aroundtheworld.databinding.BlogItemBinding
import com.squareup.picasso.Picasso


class MyAdapter(private val blogList: ArrayList<Blog>, var clickListener1: OnBlogItemClicked) : RecyclerView.Adapter<MyAdapter.MyViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = BlogItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(itemView)

    }

    override fun getItemCount(): Int {
        return blogList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        val currentItem = blogList[position]
//        holder.author.text=currentItem.author
////        holder.body.text=currentItem.body
//        holder.title.text=currentItem.title
//
//        val url_image: String? =currentItem.imageUrl
//
//        Picasso.get().load(url_image).into(holder.imageUrl)

        holder.initialize(blogList.get(position),clickListener1)

    }


    class MyViewHolder(itemView: BlogItemBinding): RecyclerView.ViewHolder(itemView.root){

        val author : TextView = itemView.blogAuthor
//        var body : TextView = itemView.blogBody
        lateinit var blog_body : String
        lateinit var url_image : String

        val title: TextView= itemView.blogTitle
        val imageUrl: ImageView=itemView.blogImageUrl



        fun initialize(item: Blog , action: OnBlogItemClicked){
            author.text=item.author
            title.text=item.title
            blog_body=item.body.toString()
            url_image=item.imageUrl.toString()


            Picasso.get().load(url_image).into(imageUrl)

            itemView.setOnClickListener{
                action.onItemClick(item,adapterPosition)
            }

        }

    }

    interface OnBlogItemClicked{
        fun onItemClick(item:Blog , position: Int)
    }

}