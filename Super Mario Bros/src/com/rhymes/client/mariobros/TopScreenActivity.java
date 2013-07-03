package com.rhymes.client.mariobros;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.rhymes.client.gp.pigshotclone.R;
import com.rhymes.client.mariobros.world.Screen;

public class TopScreenActivity extends Activity {
	private Button bGameA;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.topscreen);        
        
        init();
//        Frame.gameController.test();
    }

	private void init() {
		Frame.init(getApplicationContext(), getResources());
		bGameA = (Button) findViewById(R.id.buttonGameA);
		bGameA.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Frame.gameController.test();
			}
		});
	}
	
}