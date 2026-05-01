package app.models;

public class USERS {

    public USERS() {
    }

    public USERS eUSERS;

    public USERS(USERS eUSERS) {
        this.eUSERS = eUSERS;
    }

    private String SEN_OPER;
    private String sen_senh;

    public String getSen_oper() {
        return SEN_OPER;
    }

    public void setSen_oper(String SEN_OPER) {
        this.SEN_OPER = SEN_OPER;
    }

    public String getSen_senh() {
        return sen_senh;
    }

    public void setSen_senh(String sen_senh) {
        this.sen_senh = sen_senh;
    }

}
