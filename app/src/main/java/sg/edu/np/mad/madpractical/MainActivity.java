package sg.edu.np.mad.madpractical;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final String Title = "Main Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        User myUser = new User();

        TextView header = findViewById(R.id.textView);
        header.setText("  MAD Practical");

        TextView userName = findViewById(R.id.nameText);
        TextView userDescription = findViewById(R.id.descText);

        userName.setText(getIntent().getExtras().getString("userName"));
        userDescription.setText(getIntent().getExtras().getString("description"));
        Button myButton = findViewById(R.id.button);
        myButton.setOnClickListener(new View.OnClickListener() { //inner class
            @Override
            public void onClick(View v) { //effecting what it would do after u click on the button
                Log.v(Title, "Button is pressed");
                if (myUser.isFollowed() == true){
                    myButton.setText("UNFOLLOW");
                    Toast.makeText(getApplicationContext(), "Followed", Toast.LENGTH_SHORT).show();
                    myUser.setFollowed(false);
                }
                else {
                    myButton.setText("FOLLOW");
                    Toast.makeText(getApplicationContext(), "Unfollowed", Toast.LENGTH_SHORT).show();
                    myUser.setFollowed(true);
                }
            }
        });


    }


}