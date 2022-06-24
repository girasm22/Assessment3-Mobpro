package org.d3if4078.limassegiempat.ui.bangun_ruang

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import org.d3if4078.limassegiempat.R
import org.d3if4078.limassegiempat.model.BangunRuang
import org.d3if4078.limassegiempat.ui.PicassoClient

class BangunRuangAdapter(var context: Context): RecyclerView.Adapter<BangunRuangAdapter.ViewHolder>() {

    var bangunRuangList: List<BangunRuang> = listOf()
    var bangunRuangListFiltered: List<BangunRuang> = listOf()

    fun setBangunRuangList(context: Context, bangunRuangList: List<BangunRuang>) {
        this.context = context
        if (bangunRuangList == null) {
            this.bangunRuangList = bangunRuangList
            this.bangunRuangListFiltered = bangunRuangList
            notifyItemChanged(0, bangunRuangListFiltered.size)
        } else {
            val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun getOldListSize(): Int {
                    return this@BangunRuangAdapter.bangunRuangList.size
                }

                override fun getNewListSize(): Int {
                    return bangunRuangList.size
                }

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return this@BangunRuangAdapter.bangunRuangList.get(oldItemPosition)
                        .nama === bangunRuangList[newItemPosition].nama
                }

                override fun areContentsTheSame(
                    oldItemPosition: Int,
                    newItemPosition: Int
                ): Boolean {
                    val newQuote: BangunRuang = this@BangunRuangAdapter.bangunRuangList.get(oldItemPosition)
                    val oldQuote: BangunRuang = bangunRuangList[newItemPosition]
                    return newQuote.nama === oldQuote.nama
                }
            })
            this.bangunRuangList = bangunRuangList
            this.bangunRuangListFiltered = bangunRuangList
            result.dispatchUpdatesTo(this)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_bangun_ruang, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nama!!.text = bangunRuangListFiltered.get(position).nama

        val bangunRuang: BangunRuang = bangunRuangListFiltered.get(position)

        val nama: String = bangunRuang.nama
        val ciri: String = bangunRuang.ciri
        val gambar: String = bangunRuang.gambar

        holder.nama!!.text = nama
        holder.ciri!!.text = ciri
        holder.gambar!!.setImageURI((Uri.parse(gambar)))

        PicassoClient.downloadImage(context, bangunRuangListFiltered.get(position).gambar, holder.gambar)
    }


    class ViewHolder(
        itemView:View?
    ) : RecyclerView.ViewHolder(itemView!!) {
        var nama: TextView? = null
        var ciri: TextView? = null
        var gambar: ImageView? = null

        init {
            nama = itemView!!.findViewById<View>(R.id.nama) as TextView
            ciri = itemView.findViewById<View>(R.id.ciri) as TextView
            gambar = itemView.findViewById<View>(R.id.thumbnail) as ImageView
        }
    }

    override fun getItemCount(): Int {
        return bangunRuangListFiltered.size
    }
}