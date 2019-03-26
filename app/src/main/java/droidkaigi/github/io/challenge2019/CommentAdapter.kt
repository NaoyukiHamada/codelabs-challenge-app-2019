package droidkaigi.github.io.challenge2019

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import droidkaigi.github.io.challenge2019.data.api.response.Item

class CommentAdapter(emptyList: List<Any>, var comments: List<Item>) :
    RecyclerView.Adapter<CommentAdapter.Companion.RecyclerViewHolder>() {


    lateinit var context: Context


    companion object {
        val TYPE_LIST = 0
        val TYPE_FOOTER = 1

        class RecyclerViewHolder : RecyclerView.ViewHolder {
            var view_type: Int = 0
            lateinit var tvAuth: TextView
            lateinit var tvComment: TextView
            lateinit var tvAdv: TextView

            constructor(rv: View, viewType: Int) : super(rv) {

                if (viewType == TYPE_LIST) {
                    tvAuth = rv.findViewById(R.id.comment_author) as TextView
                    tvComment = rv.findViewById(R.id.comment_text) as TextView
                    view_type = 1

                } else if (viewType == TYPE_FOOTER) {
                    tvAdv = rv.findViewById(R.id.advTextview) as TextView
                    view_type = 0
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        var rv: View
        var holder: RecyclerViewHolder
        if (viewType == TYPE_LIST) {
            rv = LayoutInflater.from(parent.context).inflate(R.layout.item_comment, parent, false)
            holder = RecyclerViewHolder(rv, viewType)
            return holder
        } else {
            rv = LayoutInflater.from(parent.context).inflate(R.layout.item_adv_in_comment, parent, false)
            holder = RecyclerViewHolder(rv, viewType)
            return holder

        }


    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, p1: Int) {
        var comment: Item
        if (holder.view_type == TYPE_FOOTER) {
            comment = comments.get(p1)
            holder.tvAuth.setText(comments.get(p1).author)
            holder.tvComment.setText(comments.get(p1).text)
        } else if (holder!!.view_type == TYPE_LIST)
            holder.tvAdv.setText("Adv")

    }

    override fun getItemViewType(position: Int): Int {
        if (itemCount - position > 1)
            return TYPE_LIST
        return TYPE_FOOTER

    }

    override fun getItemCount(): Int = comments.size + 1

}
