package sg.edu.np.mad.madpractical;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity2 extends AppCompatActivity implements selectListener {

    String title;
    int count = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list2);
        MyDBhandler dbhandler = new MyDBhandler(this, null, null, 1);

        ArrayList<User> userList = new ArrayList<>();

        if (dbhandler.rowCount() == 0){
            for (int i = 0; i < 20; i++) {
                User user = createNewUser();

                System.out.println("HELLLLLLLLLLLLLOOOOOOOOOOO" + user.description);
                dbhandler.addUser(user);


            }
        }

        userList = dbhandler.getUsers();

        RecyclerView recyclerView = findViewById(R.id.recycleView2);
        adapter cuApapter = new adapter(userList);
        LinearLayoutManager sLayout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(sLayout);
        recyclerView.setAdapter(cuApapter);

        TextView header1 = findViewById(R.id.textView4);
        header1.setText("    MAD Practical");
    }


    @Override
    public void onImageClicked(User user) {
        Intent toCataloguePage = new Intent(ListActivity2.this, MainActivity.class);
        startActivity(toCataloguePage);
    }

    private User createNewUser(){
        Random random2 = new Random();
        int randomNameNum = random2.nextInt(999999999);
        int randomDescriptionNum = random2.nextInt(999999999);
        boolean randomFollow = random2.nextBoolean();

        User userNum = new User();
        userNum.setName("Name " + String.valueOf(randomNameNum));
        userNum.setId(count);
        userNum.setFollowed(randomFollow);
        userNum.setDescription("Description " + String.valueOf(randomDescriptionNum));
        //userNum.setImageView(R.drawable.download);
        count++;
        return userNum;
    }



};
