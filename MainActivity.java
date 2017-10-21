package kr.hs.emirim.gjy00727.mirimcalendar;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "Project_DB.db", null, 1);
        Button mShowDialog = (Button) findViewById(R.id.btn);
        final DatePicker mdpStart = (DatePicker) findViewById(R.id.dpstart);
        final DatePicker mdpDead = (DatePicker) findViewById(R.id.dpdead);


        mShowDialog.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.project_insert, null);
                final EditText mProjectName = (EditText)mView.findViewById(R.id.etProjectName);
                final Button mInsert = (Button)mView.findViewById(R.id.btn_insert);

                mInsert .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        if(!mProjectName.getText().toString().isEmpty()){
                            Toast.makeText(MainActivity.this,
                                    "Insert successful",
                                    Toast.LENGTH_SHORT).show();
                            //DBHelper
                            int startyear = mdpStart.getYear();
                            int startmonth = mdpStart.getMonth();
                            int startday = mdpStart.getDayOfMonth();
                            final Date start = new Date(startyear, startmonth, startday);

                            int deadyear = mdpDead.getYear();
                            int deadmonth = mdpDead.getMonth();
                            int deadday = mdpDead.getDayOfMonth();
                            final Date dead = new Date(deadyear, deadmonth, deadday);

                            // DB에 데이터 추가
                        }else{
                            Toast.makeText(MainActivity.this,
                                    "Please fill any empty fields",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });
    }
}
