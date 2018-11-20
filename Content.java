package charko.tester01.com.imageviewertop10;

import java.util.Date;

// TODO: 불편 글 

// 추가로 배스트 글 확인  
public class Content {
    private int nContentID; // id
    private int nWriterID; // 작성자
    private String sWriterNick; // 작성자 닉네임
    private Date dtWrite; // 작성일
    private String sCag1; // 첫번째 카테고리
    private String sCag2; // 두번째 카테고리 (하위 카테고리)
    private String sCompany; // 지점, 점포명
    private String sCompanyBranch; // 지점, 점포명
    private String sProduct; // 상품명 혹은 서비스명
    private String sLocation; // 위치
    private String sObject; // 물건?
    private boolean bPick;
    private boolean bPicture;

    public String getsObject() {
        return sObject;
    }

    public void setsObject(String sObject) {
        this.sObject = sObject;
    }

    private String sAffect; // 끼칠 영향
    private String sContent; // 내용 20자 이상
    private byte[] btImage;
    private int nPickCount = 0; // 픽 갯수
    private int nCommentCount = 0; // 코맨트 갯수
    private String sUid; // 작성 Uid

    public int getnContentID() {
        return nContentID;
    }

    public void setnContentID(int nContentID) {
        this.nContentID = nContentID;
    }

    public byte[] getBtImage() {
        return btImage;
    }

    public void setBtImage(byte[] btImage) {
        this.btImage = btImage;
    }

    public int getnWriterID() {
        return nWriterID;
    }

    public void setnWriterID(int nWriterID) {
        this.nWriterID = nWriterID;
    }

    public void setsWriterNick(String sWriterNick) {
        this.sWriterNick = sWriterNick;
    }

    public String getsWriterNick() {
        return sWriterNick;
    }

    public Date getDtWrite() {
        return dtWrite;
    }

    public void setDtWrite(Date dtWrite) {
        this.dtWrite = dtWrite;
    }

    public String getsCag1() {
        return sCag1;
    }

    public void setsCag1(String sCag1) {
        this.sCag1 = sCag1;
    }

    public String getsCag2() {
        return sCag2;
    }

    public void setsCag2(String sCag2) {
        this.sCag2 = sCag2;
    }

    public String getsCompany() {
        return sCompany;
    }

    public void setsCompany(String sCompany) {
        this.sCompany = sCompany;
    }

    public String getsProduct() {
        return sProduct;
    }

    public void setsProduct(String sProduct) {
        this.sProduct = sProduct;
    }

    public String getsLocation() {
        return sLocation;
    }

    public void setsLocation(String sLocation) {
        this.sLocation = sLocation;
    }

    public String getsContent() {
        return sContent;
    }

    public void setsContent(String sContent) {
        this.sContent = sContent;
    }

    public int getnPickCount() {
        return nPickCount;
    }

    public void setnPickCount(int nPickCount) {
        this.nPickCount = nPickCount;
    }

    public int getnCommentCount() {
        return nCommentCount;
    }

    public void setnCommentCount(int nCommentCount) {
        this.nCommentCount = nCommentCount;
    }

    public String getsCompanyBranch() {
        return sCompanyBranch;
    }

    public void setsCompanyBranch(String sCompanyBranch) {
        this.sCompanyBranch = sCompanyBranch;
    }

    public String getsAffect() {
        return sAffect;
    }

    public void setsAffect(String sAffect) {
        this.sAffect = sAffect;
    }

    public String getsUid() {
        return sUid;
    }

    public void setsUid(String sUid) {
        this.sUid = sUid;
    }

    private int nNowPage;

    public int getnNowPage() {
        return nNowPage;
    }

    public void setnNowPage(int nNowPage) {
        this.nNowPage = nNowPage;
    }

    public void setbPick(boolean bPick) {
        this.bPick = bPick;
    }

    public boolean isbPick() {
        return bPick;
    }

    public boolean isbPicture() {
        return bPicture;
    }

    public void setbPicture(boolean bPicture) {
        this.bPicture = bPicture;
    }
}
