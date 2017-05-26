package fr.nihilus.xenobladechronicles.monsters;

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

import fr.nihilus.xenobladechronicles.R;

class MonsterAdapter extends Adapter<MonsterAdapter.Holder> {

    private final Context mContext;
    private int mPlayerLevel;
    private MonsterActionListener mListener;
    private Monster[] mData = new Monster[0];

    MonsterAdapter(@NonNull Context ctx, @IntRange(from = 1, to = 99) int playerLevel) {
        if (playerLevel < 1 || playerLevel > 99) {
            throw new IllegalArgumentException("Player level must be in 1..99");
        }

        mPlayerLevel = playerLevel;
        mContext = ctx;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_unique_monster, parent, false);
        return new Holder(itemView);
    }

    @Override
    public void onBindViewHolder(final Holder holder, int position) {
        Monster monster = mData[position];
        holder.levelIndicator.setText(monster.getLevel());
        holder.levelIndicator.getBackground().setLevel(monster.getDangerLevel(mPlayerLevel));

        holder.name.setText(monster.getName());
        holder.location.setText(monster.getArea().getName(mContext));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    final Monster selected = mData[holder.getAdapterPosition()];

                    if (R.id.btn_defeated == v.getId()) {
                        mListener.onMonsterDefeated(selected);
                    } else {
                        mListener.onMonsterSelected(selected);
                    }
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

    void setActionListener(MonsterActionListener listener) {
        mListener = listener;
    }

    void setMonsters(Monster[] data) {
        mData = data;
        notifyDataSetChanged();
    }

    interface MonsterActionListener {
        void onMonsterSelected(Monster monster);

        void onMonsterDefeated(Monster monster);
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
