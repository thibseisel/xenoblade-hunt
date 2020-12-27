package fr.nihilus.xenobladechronicles.monsters.list

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fr.nihilus.xenobladechronicles.R
import fr.nihilus.xenobladechronicles.model.DangerLevel
import fr.nihilus.xenobladechronicles.util.inflate

internal class MonsterAdapter(
    private val listener: MonsterActionListener
) : ListAdapter<UiState.MonsterInfo, MonsterAdapter.Holder>(MonsterDiffer()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder = Holder(parent)

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val monster = getItem(position)
        holder.bind(monster)

        holder.itemView.setOnClickListener {
            val selected = getItem(holder.adapterPosition)
            listener.onMonsterSelected(selected)
        }

        holder.defeatedButton.setOnClickListener {
            val defeated = getItem(holder.adapterPosition)
            listener.onMonsterDefeated(defeated)
        }
    }

    override fun getItemId(position: Int): Long = when {
        !hasStableIds() -> RecyclerView.NO_ID
        else -> getItem(position).monster.id ?: 0
    }

    internal interface MonsterActionListener {
        fun onMonsterSelected(monster: UiState.MonsterInfo)
        fun onMonsterDefeated(monster: UiState.MonsterInfo)
    }

    internal class Holder(
        parent: ViewGroup
    ) : RecyclerView.ViewHolder(
        parent.inflate(R.layout.item_unique_monster)
    ) {
        val levelIndicator: TextView = itemView.findViewById(R.id.level_indicator)
        val name: TextView = itemView.findViewById(R.id.monster_name)
        val location: TextView = itemView.findViewById(R.id.monster_location)
        val defeatedButton: ImageView = itemView.findViewById(R.id.btn_defeated)

        fun bind(info: UiState.MonsterInfo) {
            val (monster, dangerLevel) = info
            levelIndicator.text = monster.level.toString()
            levelIndicator.background.level = when {
                monster.isDefeated -> 0
                else -> when (dangerLevel) {
                    DangerLevel.DANGER -> 5
                    DangerLevel.STRONG -> 4
                    DangerLevel.EQUAL -> 3
                    DangerLevel.WEAK -> 2
                    DangerLevel.EASY -> 1
                }
            }

            name.text = monster.name
            location.text = itemView.context.getString(monster.area.nameId)
            defeatedButton.visibility = if (monster.isDefeated) View.GONE else View.VISIBLE
        }
    }
}

private class MonsterDiffer : DiffUtil.ItemCallback<UiState.MonsterInfo>() {

    override fun areItemsTheSame(oldItem: UiState.MonsterInfo, newItem: UiState.MonsterInfo): Boolean {
        return oldItem.monster.id == newItem.monster.id
    }

    override fun areContentsTheSame(oldItem: UiState.MonsterInfo, newItem: UiState.MonsterInfo): Boolean {
        return oldItem == newItem
    }
}
