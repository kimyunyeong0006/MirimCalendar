package kr.hs.emirim.gjy00727.mirimcalendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by KJY on 2017-10-22.
 */

public class ProjectList extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.projectlist);


        Intent intent = getIntent();
        String[] result = intent.getStringArrayExtra("결과");
        for(int j=0; j<result.length; j++){
            if(result[j]!=null){
                Log.v("결과 ["+j+"] : ", result[j]);
            }
        }

    }
}
