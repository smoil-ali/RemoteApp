package tambowskip.com.free.remoteapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

;import java.util.ArrayList;
import java.util.List;

public class ServerList extends RecyclerView.Adapter<ServerList.ViewHolder> {

    Context context;
    List<String> hostList=new ArrayList<>();
    List<String> IpList=new ArrayList<>();

    public ServerList(Context context, List<String> hostList, List<String> ipList) {
        this.context = context;
        this.hostList = hostList;
        IpList = ipList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View listItem=layoutInflater.inflate(R.layout.server_list,parent,false);
        ViewHolder viewHolder=new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ServerList.ViewHolder holder, final int position) {
        holder.hostName.setText(hostList.get(position));
        holder.Ip.setText(IpList.get(position));

        holder.serverLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, hostList.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return hostList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout serverLayout;
        TextView hostName,Ip;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            serverLayout=itemView.findViewById(R.id.serverLayout);
            hostName=itemView.findViewById(R.id.serverText);
            Ip=itemView.findViewById(R.id.textIp);
        }
    }
}
