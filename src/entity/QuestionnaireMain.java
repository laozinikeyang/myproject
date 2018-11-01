package entity;

import java.util.Date;

public class QuestionnaireMain {
    private String mainId;

    private String mainTitle;

    private Date mainCreat;

    private String mainIsuse;

    private Date mainEndtime;

    private String mainCreatuser;

    private Integer mainCreatdep;

    public String getMainId() {
        return mainId;
    }

    public void setMainId(String mainId) {
        this.mainId = mainId;
    }

    public String getMainTitle() {
        return mainTitle;
    }

    public void setMainTitle(String mainTitle) {
        this.mainTitle = mainTitle;
    }

    public Date getMainCreat() {
        return mainCreat;
    }

    public void setMainCreat(Date mainCreat) {
        this.mainCreat = mainCreat;
    }

    public String getMainIsuse() {
        return mainIsuse;
    }

    public void setMainIsuse(String mainIsuse) {
        this.mainIsuse = mainIsuse;
    }

    public Date getMainEndtime() {
        return mainEndtime;
    }

    public void setMainEndtime(Date mainEndtime) {
        this.mainEndtime = mainEndtime;
    }

    public String getMainCreatuser() {
        return mainCreatuser;
    }

    public void setMainCreatuser(String mainCreatuser) {
        this.mainCreatuser = mainCreatuser;
    }

    public Integer getMainCreatdep() {
        return mainCreatdep;
    }

    public void setMainCreatdep(Integer mainCreatdep) {
        this.mainCreatdep = mainCreatdep;
    }
}