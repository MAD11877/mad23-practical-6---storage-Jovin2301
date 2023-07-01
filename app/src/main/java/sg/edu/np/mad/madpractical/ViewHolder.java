package sg.edu.np.mad.madpractical;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {
    String title = "ViewHolder";
    TextView Nametxt;
    TextView Desctxt;
    ImageView image;

    public ViewHolder(View itemView){
        super(itemView);
        Log.i(title, "viewHolder");
        Nametxt = itemView.findViewById(R.id.NameText);
        Desctxt = itemView.findViewById(R.id.DescriptionText);
        image = itemView.findViewById(R.id.imageView);

    }

}
