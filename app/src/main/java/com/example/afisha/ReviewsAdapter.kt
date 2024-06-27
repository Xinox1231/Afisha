package com.example.afisha

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.afisha.pojo.Review

class ReviewsAdapter: RecyclerView.Adapter<ReviewHolder>() {

    companion object{
        private const val NEGATIVE_TYPE = "Негативный"
        private const val NEUTRAL_TYPE = "Нейтральный"
        private const val POSITIVE_TYPE = "Позитивный"
    }

    var reviewsList: List<Review> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var onReachEndListener: (() -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.review_item,
            parent,
            false
        )
        return ReviewHolder(view)
    }

    override fun getItemCount(): Int {
        return reviewsList.size
    }

    override fun onBindViewHolder(holder: ReviewHolder, position: Int) {
        val review = reviewsList[position]
        val backGroundColorId = when (review.type){
            NEGATIVE_TYPE -> android.R.color.holo_red_light
            NEUTRAL_TYPE -> android.R.color.holo_orange_light
            POSITIVE_TYPE -> android.R.color.holo_green_light
            else -> throw Exception("Unknown review type")
        }
        val color = ContextCompat.getColor(holder.itemView.context, backGroundColorId)
        holder.tvAuthorName.text = review.author
        holder.tvReviewText.text = review.review
        holder.linearLayoutContainer.setBackgroundColor(color)
        if (position == itemCount - 24){
            onReachEndListener?.invoke()
        }
    }
}

class ReviewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    val linearLayoutContainer: LinearLayout = itemView.findViewById(R.id.linearLayoutContainer)
    val tvAuthorName: TextView = itemView.findViewById(R.id.tvAuthorName)
    val tvReviewText: TextView = itemView.findViewById(R.id.tvReviewText)

}
