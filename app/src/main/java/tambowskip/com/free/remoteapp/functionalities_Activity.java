package tambowskip.com.free.remoteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class functionalities_Activity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener,
        dialogView.NoticeDialogListener{

    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    RelativeLayout mousePad,keyBoard,runningApps,Camera,power;
    TextView user_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_functionalities_);
        init();
        setSupportActionBar(toolbar);
        mergeNavigationView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        navigationView.setCheckedItem(R.id.home_screen);
    }

    void mergeNavigationView(){
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    void init(){
        mousePad=findViewById(R.id.mousePadLayout);
        keyBoard=findViewById(R.id.KeyBoardLayout);
        runningApps=findViewById(R.id.RunningAppsLayout);
        Camera=findViewById(R.id.cameraLayout);
        user_name=findViewById(R.id.user_name);
        drawerLayout=findViewById(R.id.drawerOfFunctionalitiesClass);
        navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        toolbar=findViewById(R.id.toolbar);
        toolbar.setElevation(0);
        navigationView.setCheckedItem(R.id.home_screen);
        power=findViewById(R.id.powerLayout);

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
                startActivity(new Intent(this,MousePad.class));
                break;
            case R.id.KeyBoard:
                drawerLayout.closeDrawer(GravityCompat.START);
                startActivity(new Intent(this,KeyBoardActivity.class));
                break;
            case R.id.RunningApps:
                drawerLayout.closeDrawer(GravityCompat.START);
                startActivity(new Intent(this,RunningApps.class));
                break;
            case R.id.camera:
                drawerLayout.closeDrawer(GravityCompat.START);
                Toast.makeText(this, "camera", Toast.LENGTH_SHORT).show();;
                break;
            case R.id.settings:
                drawerLayout.closeDrawer(GravityCompat.START);
                startActivity(new Intent(this,SettingActivity.class));
                break;
            case R.id.power:
                showDialog();
                break;
        }
        return true;
    }

    public void MovetoOtherHouse(View view) {
        int id =view.getId();
        switch (id){
            case R.id.mousePadLayout:
                Toast.makeText(this, "mousePad", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,MousePad.class));
                break;
            case R.id.KeyBoardLayout:
                Toast.makeText(this, "keyboard", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,KeyBoardActivity.class));
                break;
            case R.id.RunningAppsLayout:
                Toast.makeText(this, "running Apps", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,RunningApps.class));
                break;
            case R.id.cameraLayout:
                Toast.makeText(this, "camera", Toast.LENGTH_SHORT).show();
                break;
            case R.id.home_screen:
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.powerLayout:
                showDialog();
                break;
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
