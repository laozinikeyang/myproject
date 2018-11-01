package entity;

import java.util.Date;

public class QuestionnaireMsg {
    private String msgId;

    private String msgText;

    private String mainId;

    private String msgCreatuser;

    private Date msgCreattime;

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getMsgText() {
        return msgText;
    }

    public void setMsgText(String msgText) {
        this.msgText = msgText;
    }

    public String getMainId() {
        return mainId;
    }

    public void setMainId(String mainId) {
        this.mainId = mainId;
    }

    public String getMsgCreatuser() {
        return msgCreatuser;
    }

    public void setMsgCreatuser(String msgCreatuser) {
        this.msgCreatuser = msgCreatuser;
    }

    public Date getMsgCreattime() {
        return msgCreattime;
    }

    public void setMsgCreattime(Date msgCreattime) {
        this.msgCreattime = msgCreattime;
    }
}