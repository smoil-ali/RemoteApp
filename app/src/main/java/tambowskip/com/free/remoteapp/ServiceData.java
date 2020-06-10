package tambowskip.com.free.remoteapp;

public class ServiceData {
    private String serviceName;
    private String ip;
    ServiceData(String serviceName,String ip){
        this.serviceName=serviceName;
        this.ip=ip;
    }

    public String getIp() {
        return ip;
    }

    public String getServiceName() {
        return serviceName;
    }
}
