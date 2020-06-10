package tambowskip.com.free.remoteapp;

import android.app.Service;
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
    List<ServiceData> serviceData=new ArrayList<ServiceData>();

    public ServerList(Context context, List<ServiceData> serviceData) {
        this.context = context;
        this.serviceData = serviceData;
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
        holder.hostName.setText(serviceData.get(position).getServiceName());
        holder.Ip.setText(serviceData.get(position).getIp());

        holder.serverLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, serviceData.get(position).getServiceName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return serviceData.size();
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
