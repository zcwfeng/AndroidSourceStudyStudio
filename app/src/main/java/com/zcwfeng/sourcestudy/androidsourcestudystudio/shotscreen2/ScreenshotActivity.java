package com.zcwfeng.sourcestudy.androidsourcestudystudio.shotscreen2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;

import com.zcwfeng.sourcestudy.androidsourcestudystudio.R;

public class ScreenshotActivity extends Activity {
    private Panel mPanel;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        mPanel = new Panel(this);
        setContentView(mPanel);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.screenshot:
                mPanel.saveScreenshot();
                break;
        }
        return true;
    }
}