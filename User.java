package charko.tester01.com.imageviewertop10;

import java.util.Date;

public class User {
    private int nUserID;
    private int nPoint;
    private String sLoginID; // email
    private String sPassword;
    private String sBirth;
    private boolean bGender; // true : male, false : female
    private String sNickName;
    private Date dtJoin;
    private String sSessionID;
    private String sDeviceInfo;
    private String sAddr;
    private int nType;
    private int nPower = 1;
    private String sUid;
    private boolean bAlarm;

    public User() {

    }

    public User(int nUserID, int nPoint, String sLoginID, String sPassword, String sBirth,
                boolean bGender, String sNickName, Date dtJoin, String sSessionID, String sDeviceInfo,
                String sAddr, int nType, int nPower, String sUid) {

        this.nUserID = nUserID;
        this.nPoint = nPoint;
        this.sLoginID = sLoginID;
        this.sPassword = sPassword;
        this.sBirth = sBirth;
        this.bGender = bGender;
        this.sNickName = sNickName;
        this.dtJoin = dtJoin;
        this.sSessionID = sSessionID;
        this.sDeviceInfo = sDeviceInfo;
        this.sAddr = sAddr;
        this.nType = nType;
        this.nPower = nPower;
        this.sUid = sUid;
    }

    public String getsUid() {
        return sUid;
    }

    public void setsUid(String sUid) {
        this.sUid = sUid;
    }

    private String sFcmID;

    public int getnUserID() {
        return nUserID;
    }

    public void setnUserID(int nUserID) {
        this.nUserID = nUserID;
    }

    public int getnPoint() {
        return nPoint;
    }

    public void setnPoint(int nPoint) {
        this.nPoint = nPoint;
    }

    public String getsLoginID() {
        return sLoginID;
    }

    public void setsLoginID(String sLoginID) {
        this.sLoginID = sLoginID;
    }

    public String getsPassword() {
        return sPassword;
    }

    public void setsPassword(String sPassword) {
        this.sPassword = sPassword;
    }

    public String getsBirth() {
        return sBirth;
    }

    public void setsBirth(String sBirth) {
        this.sBirth = sBirth;
    }

    public boolean isbGender() {
        return bGender;
    }

    public void setbGender(boolean bGender) {
        this.bGender = bGender;
    }

    public String getsNickName() {
        return sNickName;
    }

    public void setsNickName(String sNickName) {
        this.sNickName = sNickName;
    }

    public Date getDtJoin() {
        return dtJoin;
    }

    public void setDtJoin(Date dtJoin) {
        this.dtJoin = dtJoin;
    }

    public String getsSessionID() {
        return sSessionID;
    }

    public void setsSessionID(String sSessionID) {
        this.sSessionID = sSessionID;
    }

    public String getsDeviceInfo() {
        return sDeviceInfo;
    }

    public void setsDeviceInfo(String sDeviceInfo) {
        this.sDeviceInfo = sDeviceInfo;
    }

    public String getsAddr() {
        return sAddr;
    }

    public void setsAddr(String sAddr) {
        this.sAddr = sAddr;
    }

    public int getnType() {
        return nType;
    }

    public void setnType(int nType) {
        this.nType = nType;
    }

    private String sChangePw;

    public String getsChangePw() {
        return sChangePw;
    }

    public void setsChangePw(String sChangePw) {
        this.sChangePw = sChangePw;
    }

    public int getnPower() {
        return nPower;
    }

    public void setnPower(int nPower) {
        this.nPower = nPower;
    }

    public String getsFcmID() {
        return sFcmID;
    }

    public void setsFcmID(String sFcmID) {
        this.sFcmID = sFcmID;
    }

    public boolean isbAlarm() {
        return bAlarm;
    }

    public void setbAlarm(boolean bAlarm) {
        this.bAlarm = bAlarm;
    }
}
