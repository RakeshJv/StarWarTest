package e.apple.starwartest.adapter;


import android.arch.lifecycle.ViewModel;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import e.apple.starwartest.R;
import e.apple.starwartest.model.Character;

public class CharacterList extends RecyclerView.Adapter<CharacterList.ViewHolder> {

    private List<Character> items;
    private int itemLayout;

    public CharacterList(List<Character> items, int itemLayout)
    {
        this.items = items;
        this.itemLayout = itemLayout;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Character item = this.items.get(position);
        holder.itemView.setTag(item);
        holder.text.setText(item.getName());

    }

    @Override
    public int getItemCount() {
        return  this.items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView text;
        public ViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.characterNames);
        }
    }
}