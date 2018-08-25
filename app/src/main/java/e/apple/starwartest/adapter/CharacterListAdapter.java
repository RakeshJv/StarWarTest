package e.apple.starwartest.adapter;


import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import e.apple.starwartest.R;
import e.apple.starwartest.interfaces.ItemClickListener;
import e.apple.starwartest.model.Character;

public class CharacterListAdapter extends RecyclerView.Adapter<CharacterListAdapter.ViewHolder>{

     List<Character> items;
    private int itemLayout;
    private ItemClickListener clickListener;

    public CharacterListAdapter(List<Character> items, int itemLayout)
    {


        this.items = items;
        Log.d("","CharacterListAdapter--"+ this.items.size());

        this.itemLayout = itemLayout;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate( this.itemLayout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        Character item = this.items.get(position);

        Log.d("","position--"+position + " item.getName()" +item.getName());
        holder.text.setText(item.getName());

    }

    @Override
    public int getItemCount() {

        Log.d("","getItemCount--"+ this.items.size());
        return  this.items.size();
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener
    {
        public TextView text;
        public ViewHolder(View itemView)
        {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.characterNames);
            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view)
        {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }
}