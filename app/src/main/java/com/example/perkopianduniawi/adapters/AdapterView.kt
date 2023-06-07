package com.example.perkopianduniawi.adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.perkopianduniawi.*
import com.example.perkopianduniawi.models.CafeData

class AdapterView(
    private var itemList: List<CafeData>
) : RecyclerView.Adapter<AdapterView.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.cafeName)
        val region: TextView = view.findViewById(R.id.cafeRegion)
        val clock: TextView = view.findViewById(R.id.cafeClock)
        val rating: TextView = view.findViewById(R.id.cafeRating)
        val items: CardView = view.findViewById(R.id.cafe_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.cafe_list, parent, false)
    )

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = itemList[position].name
        holder.region.text = itemList[position].region
        holder.rating.text = "${itemList[position].rating} / 5.0"
        holder.clock.text = if(itemList[position].status) "${itemList[position].open} s/d ${itemList[position].close}" else "CLOSED!"

        // set text danger if is closed
        if(!itemList[position].status) holder.clock.setTextColor(Color.parseColor("#ff0000"))

        holder.items.setOnClickListener { view ->
            val intent = Intent(view.context, CafeDetailActivity::class.java)
            intent.putExtra("cafeName", itemList[position].name)
            intent.putExtra("cafeDescription", itemList[position].desc)
            intent.putExtra("cafeRating", itemList[position].rating)
            intent.putExtra("cafeOpen", itemList[position].open)
            intent.putExtra("cafeClose", itemList[position].close)
            intent.putExtra("cafeLocation", itemList[position].location)
            intent.putExtra("cafeLink", itemList[position].link)
            intent.putExtra("cafeStatus", itemList[position].status)
            intent.putExtra("cafeRegion", itemList[position].region)
            view.context.startActivity(intent)
        }
    }

    override fun getItemCount() = itemList.size

    fun updateData(newData: List<CafeData>) {
        itemList = newData
        notifyDataSetChanged()
    }
}
