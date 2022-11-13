package com.carefer.homemodule.platform.view.fixtureFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.carefer.basemodule.data.models.fixturemodels.MatchesItem
import com.carefer.basemodule.utility.toDate
import com.carefer.basemodule.utility.toHour
import com.carefer.homemodule.R
import com.carefer.homemodule.databinding.ListItemMatchesBinding
import com.carefer.homemodule.databinding.ListItemMatchesDateBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class FixtureAdapter(val onFavouritesClick :(MatchesItem)->Unit) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var recyclerView: RecyclerView? = null
    var matches = ArrayList<Any>()

    fun addMatches(matches: List<MatchesItem>) {
        this.matches.clear()
        var scrollToPosition = 0
        val groupedMatches: Map<String, List<MatchesItem>> = matches.groupBy { toDate(it.date!!) }
        val dates = groupedMatches.keys
        val currentDate = SimpleDateFormat("EEE, d MMM yyyy", Locale.getDefault()).format(Date(System.currentTimeMillis()))

        dates.forEach { date ->
            val dateMatches: List<MatchesItem>? = groupedMatches[date]
            dateMatches?.let {
                this.matches.add(date)
                if (date == currentDate) scrollToPosition = this.matches.size - 1
                this.matches.addAll(dateMatches)
            }
        }

        notifyDataSetChanged()

        recyclerView?.scrollToPosition(scrollToPosition)
    }

    fun clearAllMatches(){
        this.matches.clear()
        notifyDataSetChanged()
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == FixtureItems.DATES.value)
            DatesHolder(ListItemMatchesDateBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        else FixtureHolder(ListItemMatchesBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = matches.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is DatesHolder -> {
                holder.mBinding.TVDate.text = matches[holder.bindingAdapterPosition].toString()
            }

            is FixtureHolder -> {
                val match = matches[position] as MatchesItem
                holder.bind(match)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val matches = matches[position]
        return if (matches is String) FixtureItems.DATES.value else FixtureItems.MATCHES.value
    }

    inner class FixtureHolder(private val mBinding: ListItemMatchesBinding) :
            RecyclerView.ViewHolder(mBinding.root) {
        fun bind(matchesItem: MatchesItem) {
            with(mBinding){
                tvHomeTeam.text = matchesItem.homeTeam.name
                tvAwayTeam.text = matchesItem.awayteam.name
                tvMatchStatus.text = matchesItem.status
                tvAwayScore.text = matchesItem.score?.fullTime?.awayTeam.toString()
                tvHomeScore.text = matchesItem.score?.fullTime?.homeTeam.toString()
                if (matchesItem.isFavourite)
                    Glide.with(itemView.context).load(R.drawable.ic_enable_favorite).into(btnFavourite)
                else
                    Glide.with(itemView.context).load(R.drawable.ic_disable_favorite).into(btnFavourite)

                when (matchesItem.status) {
                    "FINISHED" -> {
                        groupAfterFinish.visibility = View.VISIBLE
                        tvMatchTime.visibility = View.INVISIBLE

                    }
                    else -> {
                        groupAfterFinish.visibility = View.INVISIBLE
                        tvMatchTime.visibility = View.VISIBLE

                        if (!matchesItem.date.isNullOrEmpty())
                            tvMatchTime.text = toHour(matchesItem.date)
                    }
                }
                btnFavourite.setOnClickListener {
                    matchesItem.isFavourite = !matchesItem.isFavourite
                    notifyItemChanged(absoluteAdapterPosition)
                    onFavouritesClick.invoke(matchesItem)
                }
            }
        }
    }

    inner class DatesHolder(val mBinding: ListItemMatchesDateBinding) :
            RecyclerView.ViewHolder(mBinding.root)

    enum class FixtureItems(val value: Int) {
        DATES(0), MATCHES(1)
    }

}