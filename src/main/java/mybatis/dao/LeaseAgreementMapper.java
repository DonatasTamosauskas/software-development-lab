package mybatis.dao;

import java.util.Date;
import java.util.List;
import mybatis.model.LeaseAgreement;
import org.apache.ibatis.annotations.Param;

public interface LeaseAgreementMapper {
    int deleteByPrimaryKey(@Param("appartmentId") Integer appartmentId, @Param("partyId") Integer partyId, @Param("startDate") Date startDate);

    int insert(LeaseAgreement record);

    LeaseAgreement selectByPrimaryKey(@Param("appartmentId") Integer appartmentId, @Param("partyId") Integer partyId, @Param("startDate") Date startDate);

    List<LeaseAgreement> selectAll();

    int updateByPrimaryKey(LeaseAgreement record);
}