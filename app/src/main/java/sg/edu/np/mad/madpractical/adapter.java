package sg.edu.np.mad.madpractical;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adapter extends RecyclerView.Adapter<ViewHolder> {
    ArrayList<User> data;
    private selectListener listener;
    private Context context;

    public adapter(ArrayList<User> input) {
        data = input;
    }


    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (viewType == 0) {
            View itemView = inflater.inflate(R.layout.recyclerview, parent, false);
            return new ViewHolder(itemView);
        } else {
            View itemView = inflater.inflate(R.layout.viewholder_extra, parent, false);
            return new ViewHolderExtra(itemView);
        }
        /*View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;*/
    }

    public void onBindViewHolder(ViewHolder holder, int position){
        User user = data.get(position); //finding the position of the place
        holder.Nametxt.setText(user.getName());
        holder.Desctxt.setText((user.getDescription()));
        //holder.image.setImageResource(user.getImageView());

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Profile");
                builder.setMessage(user.getName());
                builder.setCancelable(false);
                builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent toMainPage = new Intent(v.getContext(), MainActivity.class);
                        toMainPage.putExtra("userName", data.get(holder.getAdapterPosition()).getName());
                        toMainPage.putExtra("description", data.get(holder.getAdapterPosition()).getDescription());
                        v.getContext().startActivity(toMainPage);
                    }
                });

                builder.setNegativeButton("Close", (DialogInterface.OnClickListener) (dialog, which) -> {
                    // If user click no then dialog box is canceled.
                    dialog.cancel();
                });

                AlertDialog alert = builder.create();
                alert.show();



            }

        });

        if (holder instanceof ViewHolderExtra) {
            // Additional ImageView is present in ViewHolderExtra
            ViewHolderExtra extraHolder = (ViewHolderExtra) holder;
            extraHolder.bigImage.setImageResource(R.drawable.download);

        }
    }

    public int getItemCount(){
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        User user = data.get(position);
        String name = user.getName();
        int lastDigit = Character.getNumericValue(name.charAt(name.length() - 1));

        if (lastDigit == 7) {

            return 1; // View type for layout with additional ImageView
        } else {
            return 0; // View type for default layout
        }
    }
}
