package vkr.sinicin.shiftlab_sinicin2020;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Welcome_page extends AppCompatActivity {

    private Button nWelcomeButton;

    private String nName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
        getSupportActionBar().setTitle("Добро пожаловать!");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#009b76")));


        nWelcomeButton = findViewById(R.id.welcomeButton);

        nName = getIntent().getStringExtra("name_user");


        nWelcomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();

            }
        });


    }
    // Всплывающее окно с именем
    public void openDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Добро пожаловать!").setMessage(nName).setCancelable(false).setNegativeButton("Выход", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Bye", Toast.LENGTH_SHORT).show();

            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
