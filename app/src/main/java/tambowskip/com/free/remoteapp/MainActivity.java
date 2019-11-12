package tambowskip.com.free.remoteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import io.evercam.network.discovery.IpScan;
import io.evercam.network.discovery.IpTranslator;
import io.evercam.network.discovery.ScanRange;

import com.google.android.material.navigation.NavigationView;
import com.stealthcopter.networktools.ARPInfo;
import io.evercam.network.discovery.ScanResult;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,View.OnKeyListener {


    EditText desktop_name;
    Button btn1;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        desktop_name=findViewById(R.id.ip);
        btn1=findViewById(R.id.btn1);
        drawerLayout=findViewById(R.id.drawerlayout);
        navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        toolbar=findViewById(R.id.toolbar);
        desktop_name.setOnKeyListener(this);
        navigationView.setCheckedItem(R.id.home_screen);
    }

    public void Send_to_pc(View view) {
        startActivity(new Intent(this,functionalities_Activity.class));
        Toast.makeText(this, "button pressed", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.discover_menu1,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.discover:
                Toast.makeText(this, "Discover Activity", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,DiscoverAcitivity.class));
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
            case R.id.home_screen:
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.power:
                drawerLayout.closeDrawer(GravityCompat.START);
                Toast.makeText(this, "No connection", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        switch (view.getId()){
            case R.id.ip:
                if (i==KeyEvent.KEYCODE_ENTER && keyEvent.getAction()==KeyEvent.ACTION_DOWN){
                    Send_to_pc(view);
                    return true;
                }

        }
        return false;
    }
}
