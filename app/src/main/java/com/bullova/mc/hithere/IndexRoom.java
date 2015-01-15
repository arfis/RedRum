package com.bullova.mc.hithere;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Michal on 1/15/2015.
 */

public class IndexRoom extends Activity{
    private TextView tHiScreen;
    private String sName;
    final private String HI_TAG = "Hi there ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.room);
        tHiScreen = (TextView) findViewById(R.id.HelloTextView);

        Intent i = getIntent();
        Bundle extras = i.getExtras();

        sName = extras.getString("loginName");
        tHiScreen.setText(HI_TAG + sName + "!");
    }
}
