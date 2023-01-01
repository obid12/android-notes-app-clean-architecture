package com.obidia.testagrii.presentation.listnote

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.obidia.testagrii.databinding.ItemNoteBinding
import com.obidia.testagrii.domain.entity.NoteEntity


class ListAdapter(private val onClick: OnClick) :
    RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<NoteEntity>() {
        override fun areItemsTheSame(
            oldItem: NoteEntity,
            newItem: NoteEntity
        ): Boolean =
            oldItem.id == newItem.id


        override fun areContentsTheSame(
            oldItem: NoteEntity,
            newItem: NoteEntity
        ): Boolean =
            oldItem.hashCode() == newItem.hashCode()


    }

    private val differ = AsyncListDiffer(this, diffCallback)
    fun submitData(value: MutableList<NoteEntity>) = differ.submitList(value)

    class ListViewHolder(private var binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: NoteEntity) {
            binding.dataEntity = data
            binding.cardView.setCardBackgroundColor(data.color)
            binding.executePendingBindings()
        }
    }

    class OnClick(val click: (entity: NoteEntity?) -> Unit) {
        fun onClick(entity: NoteEntity?) = click(entity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            ItemNoteBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = differ.currentList[position] as NoteEntity
        holder.bind(data)
        holder.itemView.setOnClickListener {
            onClick.onClick(data)
        }
    }

    override fun getItemCount(): Int = differ.currentList.size
}