package service.outbound;

import org.freeswitch.esl.client.transport.event.EslEvent;

public class UserRegistrationInfo {
    private String userId;
    private String freeswitchIp;
    public String uniqueId;
    private String contactUri;
    private String expirationTimestamp;
    private String clientIp;
    private String clientPort;
    private String transportProtocol;
    private String userAgent;

    public UserRegistrationInfo(EslEvent event) {
//        String[] parts = csvLine.split(",");

//        this.userId = event.getEventHeaders().get("Channel-Call-UUID");
//        this.freeswitchIp = parts[1];
//        this.uniqueId = parts[2];
//        this.contactUri = parts[3];
//        this.expirationTimestamp = parts[4];
//        this.clientIp = parts[5];
//        this.clientPort = parts[6];
//        this.transportProtocol = parts[7];
//        this.userAgent = parts[8];

    }

    @Override
    public String toString() {
        return "UserRegistrationInfo{" +
                "userId='" + userId + '\'' +
                ", freeswitchIp='" + freeswitchIp + '\'' +
                ", uniqueId='" + uniqueId + '\'' +
                ", contactUri='" + contactUri + '\'' +
                ", expirationTimestamp='" + expirationTimestamp + '\'' +
                ", clientIp='" + clientIp + '\'' +
                ", clientPort='" + clientPort + '\'' +
                ", transportProtocol='" + transportProtocol + '\'' +
                ", userAgent='" + userAgent + '\'' +
                '}';
    }
}