package com.zcwfeng.sourcestudy.androidsourcestudystudio.shotscreen;



import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.zcwfeng.sourcestudy.androidsourcestudystudio.R;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.shotscreen.util.ScreenShot;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.shotscreen.view.ShotView;

public class MainActivity extends Activity {
	
	private Button shotButton = null;
	private ShotView shotView = null;

	private TextView textView = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shotscreen_main);
        textView= ( TextView ) findViewById( R.id.textview );
        shotButton = ( Button ) findViewById( R.id.shotBtn );
        shotButton.setOnClickListener( new OnClickListener( )
		{
			@Override
			public void onClick( View v )
			{
				if ( shotView == null )
				{
					// TODO Auto-generated method stub
					shotView = ( ShotView ) findViewById( R.id.shotView );
				}
				else 
				{
					shotView.setIsRunning(true);
				}
				
				Bitmap bgBitmap = shotView.getBitmap( );
				if ( bgBitmap != null )
				{
					bgBitmap.recycle( );
				}
				bgBitmap = ScreenShot.takeScreenShot(MainActivity.this);
				shotView.setBitmap( bgBitmap );
				shotView.setVisibility( View.VISIBLE );
				
			}
		} );
        
        
    }
}