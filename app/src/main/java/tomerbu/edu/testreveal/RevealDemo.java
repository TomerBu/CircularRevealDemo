package tomerbu.edu.testreveal;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.OvershootInterpolator;

public class RevealDemo extends AppCompatActivity {

    private FloatingActionButton fab;
    boolean isMenuVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reveal_demo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        fab = (FloatingActionButton) findViewById(R.id.fab);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reveal_demo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_reveal:
                animateMenu();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void animateMenu() {
        final ViewGroup layout = (ViewGroup) findViewById(R.id.fabContainer);
        int startRadius, endRadius;
        final int visibility = isMenuVisible ? View.INVISIBLE : View.VISIBLE;
        int cx = (int) (layout.getWidth() - fab.getWidth() * 1.4);
        int cy = 0;
        if (!isMenuVisible) {
            layout.setVisibility(visibility);
            startRadius = fab.getSize();
            endRadius = layout.getWidth();
        } else {

            startRadius = layout.getWidth();
            endRadius = 0;
        }


        Animator reveal = ViewAnimationUtils.createCircularReveal(layout, cx, cy, startRadius, endRadius);
        reveal.setInterpolator(new AccelerateDecelerateInterpolator());
        reveal.start();
        if (isMenuVisible)
            reveal.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    layout.setVisibility(visibility);
                }
            });

        isMenuVisible = !isMenuVisible;
    }
}
