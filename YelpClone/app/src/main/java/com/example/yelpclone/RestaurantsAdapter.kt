package com.example.yelpclone

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_restaurant.view.*

class RestaurantsAdapter(val context: Context, val restaurants: List<YelpRestaurant>) :
    RecyclerView.Adapter<RestaurantsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_restaurant, parent, false))
    }

    override fun getItemCount() = restaurants.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val restaurant = restaurants[position]
        holder.bind(restaurant)
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(restaurant: YelpRestaurant) {
            //Display
            itemView.tvName.text = restaurant.name
            itemView.tvPrice.text = restaurant.price
            itemView.tvDistance.text = restaurant.displayDistance()
            itemView.tvNumReviews.text = restaurant.numReviews + " Reviews"
            itemView.ratingBar.rating = restaurant.rating.toFloat()
            itemView.Address.text = restaurant.location.address
            itemView.tvCategory.text = restaurant.categories[0].title
            //image

            Picasso
                .get()
                .load(restaurant.imageUrl)
                .into(itemView.imageView);

        }

    }



}
