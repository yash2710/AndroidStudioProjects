package yash.mp1.itnusip.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import yash.mp1.itnusip.R;
import yash.mp1.itnusip.model.Contact;


/**
 * Created by Yash on 04-Apr-15.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ContactViewHolder> {

    List<Contact> contactList;
    private LayoutInflater inflater;
    Context context;

    public RecyclerViewAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
    }



    public void setContactList(List<Contact> contacts){
        this.contactList=contacts;
        notifyItemRangeChanged(0,contacts.size());
    }
    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.contact_list_row, viewGroup, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder contactViewHolder, int i) {
        Contact current = contactList.get(i);
        contactViewHolder.contactName.setText(current.getName());
        contactViewHolder.contactBranch.setText(current.getBranch());
        //contactViewHolder.contactImage.setImageResource(current.getImage());
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    class ContactViewHolder extends RecyclerView.ViewHolder {
        //public ImageView contactImage;
        public TextView contactName, contactBranch;

        public ContactViewHolder(View itemView) {
            super(itemView);
            //contactImage = (ImageView) itemView.findViewById(R.id.contactImage);
            contactName = (TextView) itemView.findViewById(R.id.contactName);
            contactBranch = (TextView) itemView.findViewById(R.id.contactBranch);
        }

    }


}
