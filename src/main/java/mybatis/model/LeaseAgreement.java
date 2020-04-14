package mybatis.model;

import java.util.Date;

public class LeaseAgreement {
    private Integer appartmentId;

    private Integer partyId;

    private Date startDate;

    private Date endDate;

    private Boolean primaryLeaser;

    public Integer getAppartmentId() {
        return appartmentId;
    }

    public void setAppartmentId(Integer appartmentId) {
        this.appartmentId = appartmentId;
    }

    public Integer getPartyId() {
        return partyId;
    }

    public void setPartyId(Integer partyId) {
        this.partyId = partyId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Boolean getPrimaryLeaser() {
        return primaryLeaser;
    }

    public void setPrimaryLeaser(Boolean primaryLeaser) {
        this.primaryLeaser = primaryLeaser;
    }
}