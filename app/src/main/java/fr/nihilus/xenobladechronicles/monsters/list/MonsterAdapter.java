package fr.nihilus.xenobladechronicles.monsters.list;

import android.content.Context;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Comparator;

import fr.nihilus.xenobladechronicles.R;
import fr.nihilus.xenobladechronicles.model.Monster;

class MonsterAdapter extends Adapter<MonsterAdapter.Holder> {

    private final MonsterActionListener mListener;
    private int mPlayerLevel;
    private Monster[] mData = new Monster[0];
    private Comparator<Monster> mSortingRule = Monster.comparator(Monster.ORDERING_LEVEL);

    MonsterAdapter(@IntRange(from = 1, to = 99) int playerLevel,
                   MonsterActionListener listener) {
        setPlayerLevel(playerLevel);
        mListener = listener;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_unique_monster, parent, false);
        return new Holder(itemView);
    }

    @Override
    public void onBindViewHolder(final Holder holder, int position) {
        Context ctx = holder.itemView.getContext();
        final Monster monster = mData[position];

        holder.levelIndicator.setText(String.valueOf(monster.getLevel()));
        int dangerLevel = monster.getDangerLevel(mPlayerLevel);
        holder.levelIndicator.getBackground().setLevel(dangerLevel);

        holder.name.setText(monster.getName());
        holder.location.setText(monster.getArea().getName(ctx));

        holder.defeatedButton.setVisibility(monster.isDefeated() ? View.GONE : View.VISIBLE);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    Monster selected = mData[holder.getAdapterPosition()];
                    mListener.onMonsterSelected(selected);
                }
            }
        });

        holder.defeatedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    Monster defeated = mData[holder.getAdapterPosition()];
                    mListener.onMonsterDefeated(defeated);
                }
            }
        });
    }

    @Override
    public long getItemId(int position) {
        if (!hasStableIds()) return RecyclerView.NO_ID;
        return mData[position].getId();
    }

    @Override
    public int getItemCount() {
        return mData.length;
    }

    void setPlayerLevel(@IntRange(from = 1, to = 99) int playerLevel) {
        if (playerLevel < 1 || playerLevel > 99) {
            throw new IllegalArgumentException("Player level must be in 1..99");
        }

        mPlayerLevel = playerLevel;
    }

    void setSorting(@Monster.Ordering int orderStategy) {
        mSortingRule = Monster.comparator(orderStategy);
        Arrays.sort(mData, mSortingRule);
        notifyDataSetChanged();
    }

    void setMonsters(@NonNull Monster[] data) {
        // FIXME: l'adapter ne prend pas en compte le chargement initial parfois
        mData = new Monster[data.length];
        System.arraycopy(data, 0, mData, 0, data.length);
        Arrays.sort(mData, mSortingRule);
        notifyDataSetChanged();
    }

    interface MonsterActionListener {
        void onMonsterSelected(@NonNull Monster monster);

        void onMonsterDefeated(@NonNull Monster monster);
    }

    static class Holder extends RecyclerView.ViewHolder {
        final TextView levelIndicator;
        final TextView name;
        final TextView location;
        final ImageView defeatedButton;

        Holder(@NonNull View itemView) {
            super(itemView);
            levelIndicator = (TextView) itemView.findViewById(R.id.level_indicator);
            name = (TextView) itemView.findViewById(R.id.monster_name);
            location = (TextView) itemView.findViewById(R.id.monster_location);
            defeatedButton = (ImageView) itemView.findViewById(R.id.btn_defeated);
        }
    }
}
