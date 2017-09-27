package fr.indianacroft.wildhunt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by pierre on 27/09/17.
 */


public class HomeJoueurLobbyAdapter extends BaseAdapter {
    private Context context; //context
    private ArrayList<BDD> items; //data source of the list adapter

    //public constructor
public HomeJoueurLobbyAdapter(Context context, ArrayList<BDD> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size(); //returns total of items in the list
    }

    @Override
    public Object getItem(int position) {
        return items.get(position); //returns list item at the specified position
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.homejoueur_lobby, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        BDD currentItem = (BDD) getItem(position);
        viewHolder.buttonLobbyName.setText(currentItem.getNom());
        //viewHolder.imageViewLobbyPhoto.setDrawable(currentItem.getImage());

        return convertView;
    }

    private class ViewHolder {
        ImageView imageViewLobbyPhoto;
        TextView buttonLobbyName;
        ;

        public ViewHolder(View view) {
            buttonLobbyName = (Button) view.findViewById(R.id.buttonLobbyName);
            imageViewLobbyPhoto = (ImageView) view.findViewById(R.id.imageViewLobbyPhoto)
        }
    }
}
