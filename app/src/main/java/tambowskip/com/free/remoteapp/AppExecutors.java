package tambowskip.com.free.remoteapp;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppExecutors {

    private static final Object LOCK =new Object();
    private static AppExecutors sInstance;
    private final Executor netWorkCall;

    private AppExecutors(Executor netWorkCall){
        this.netWorkCall=netWorkCall;
    }

    public static AppExecutors getInstance(){
        if (sInstance==null){
            synchronized (LOCK){
                sInstance=new AppExecutors(Executors.newSingleThreadExecutor());
            }
        }
        return sInstance;
    }

    public Executor getNetWorkCall(){
        return netWorkCall;
    }
}
