package com.example.prema.prema.Data;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prema.prema.Model.Contacts;
import com.example.prema.prema.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private Context context;
    private List<Contacts> contactList;
    public ContactAdapter(Context context, List<Contacts> contacts) {
        this.context = context;
        contactList = contacts;
    }

    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_row,parent,false);

        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(ContactAdapter.ViewHolder holder, int position) {

        Contacts contact = contactList.get(position);
        String imageLink = contact.getImage();
        holder.name.setText(contact.getName());

        Picasso.with(context)
                .load(imageLink)
                .placeholder(android.R.drawable.ic_menu_gallery)
                .into(holder.image);
    }



    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView name;
        public ImageView image;
        public ViewHolder(View itemView, Context ctx) {
            super(itemView);
            context =ctx;
            name = (TextView) itemView.findViewById(R.id.contactTextviewid);
            image = (ImageView) itemView.findViewById(R.id.contactimageviewid);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(context,"Row Tapped!",Toast.LENGTH_LONG).show();

                }
            });
        }

        @Override
        public void onClick(View view) {

        }
    }
}
