package tambowskip.com.free.remoteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class RunningApps extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
dialogView.NoticeDialogListener{


    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    View view;
    DialogInterface.OnClickListener dialogClickListener;
    AlertDialog.Builder builder;
    String action;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running_apps);
        init();
        setSupportActionBar(toolbar);
        mergeNavigationView();

        builder = new AlertDialog.Builder(this);
        dialogClickListener=new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                switch(i) {
                    case DialogInterface.BUTTON_POSITIVE:
                        sendActionToServer(action.toUpperCase());
                        dialog.dismiss();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        dialog.dismiss();
                        break;
                }
            }
        };
    }


    @Override
    protected void onResume() {
        super.onResume();
//        navigationView.setCheckedItem(R.id.RunningApps);
    }

    void mergeNavigationView(){
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    void init(){
        drawerLayout=findViewById(R.id.RunningAppsDrawerLayout);
        navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        toolbar=findViewById(R.id.toolbar);
        navigationView.setCheckedItem(R.id.RunningApps);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.mousePad:
                drawerLayout.closeDrawer(GravityCompat.START);
                startActivity(new Intent(this,MousePad.class));
                finish();
                break;
            case R.id.KeyBoard:
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.RunningApps:
                drawerLayout.closeDrawer(GravityCompat.START);
                finish();
                break;
            case R.id.camera:
                drawerLayout.closeDrawer(GravityCompat.START);
                Toast.makeText(this, "camera", Toast.LENGTH_SHORT).show();
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
                showDialog();
                break;
        }
        return true;
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
                Intent intent=new Intent(this,MainActivity.class);
                startActivity(intent);
                finishAffinity();
                break;
        }
        return true;
    }


    public  void showDialog(){
        DialogFragment dialogFragment=new dialogView();
        dialogFragment.show(getSupportFragmentManager(),"tag");
    }


    @Override
    public void onDialogClick(int position) {
        String[] list=getResources().getStringArray(R.array.Power_Off);
        action=list[position];
        showConfirmDialog(action);
    }

    private void showConfirmDialog(String title) {
        builder.setTitle(title)
                .setMessage("Are you sure?")
                .setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener)
                .show();
    }

    private void sendActionToServer(final String action) {
        AppExecutors.getInstance().getNetWorkCall().execute(new Runnable() {
            @Override
            public void run() {
                MainActivity.printWriter.println(action);
                MainActivity.printWriter.flush();
            }
        });
    }
}
