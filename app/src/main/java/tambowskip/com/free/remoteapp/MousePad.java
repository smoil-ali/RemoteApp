package tambowskip.com.free.remoteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GestureDetectorCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MousePad extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
dialogView.NoticeDialogListener{


    private float initX =0;
    private float initY =0;
    private float disX =0;
    private float disY =0;


    private boolean mouseMoved=false;
    Button rightClick,leftClick,Wheel;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    RelativeLayout mousepad;
    private Paint paint = new Paint();
    private Path path = new Path();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mouse_pad);
        init();
        setSupportActionBar(toolbar);
        mergeNavigationView();
    }





    @Override
    protected void onResume() {
        super.onResume();
        navigationView.setCheckedItem(R.id.mousePad);
    }

    void mergeNavigationView(){
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    void init(){
        rightClick=findViewById(R.id.R_click);
        leftClick=findViewById(R.id.L_click);
        Wheel=findViewById(R.id.wheel);
        drawerLayout=findViewById(R.id.mousePadDrawerLayout);
        navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        toolbar=findViewById(R.id.toolbar);
        navigationView.setCheckedItem(R.id.mousePad);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(6f);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.function_menus,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.log_out:
                Toast.makeText(this, "log out", Toast.LENGTH_SHORT).show();
                Intent newIntent = new Intent(this,MainActivity.class);
                startActivity(newIntent);
                finishAffinity();
                break;
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.mousePad:
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.KeyBoard:
                drawerLayout.closeDrawer(GravityCompat.START);
                startActivity(new Intent(this,KeyBoardActivity.class));
                finish();
                break;
            case R.id.RunningApps:
                drawerLayout.closeDrawer(GravityCompat.START);
                startActivity(new Intent(this,RunningApps.class));
                finish();
                break;
            case R.id.camera:
                drawerLayout.closeDrawer(GravityCompat.START);
                Toast.makeText(this, "camera", Toast.LENGTH_SHORT).show();;
                break;
            case R.id.settings:
                drawerLayout.closeDrawer(GravityCompat.START);
                startActivity(new Intent(this,SettingActivity.class));
                finish();
                break;
            case R.id.home_screen:
                drawerLayout.closeDrawer(GravityCompat.START);
                startActivity(new Intent(this,functionalities_Activity.class));
                finish();
                break;
            case R.id.power:
                drawerLayout.closeDrawer(GravityCompat.START);
                showDialog();
                break;
        }
        return true;
    }

    public void MouseButtons(View view) {
        int id=view.getId();
        switch (id){
            case R.id.L_click:
                Toast.makeText(this, "left click", Toast.LENGTH_SHORT).show();
                break;
            case R.id.R_click:
                Toast.makeText(this, "right click", Toast.LENGTH_SHORT).show();
                break;
            case R.id.wheel:
                Toast.makeText(this, "wheek click", Toast.LENGTH_SHORT).show();
        }
    }






    public  void showDialog(){
        DialogFragment dialogFragment=new dialogView();
        dialogFragment.show(getSupportFragmentManager(),"Power Off");
    }


    @Override
    public void onDialogClick(int position) {
        String[] list=getResources().getStringArray(R.array.Power_Off);
        Toast.makeText(this, list[position], Toast.LENGTH_SHORT).show();
    }




}
