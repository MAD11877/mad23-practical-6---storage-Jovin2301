package sg.edu.np.mad.madpractical;

import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolderExtra extends ViewHolder {
    String title = "ViewHolder";
    TextView Nametxt;
    TextView Desctxt;
    ImageView image;
    ImageView bigImage;

    ViewHolderExtra(View itemView){
        super(itemView);
        Log.i(title, "viewHolder");
        Nametxt = itemView.findViewById(R.id.NameText);
        Desctxt = itemView.findViewById(R.id.DescriptionText);
        image = itemView.findViewById(R.id.imageView);
        bigImage = itemView.findViewById(R.id.enlargeImage);

    }
}
