package mybatis.dao;

import java.util.List;
import mybatis.model.Party;

public interface PartyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Party record);

    Party selectByPrimaryKey(Integer id);

    List<Party> selectAll();

    int updateByPrimaryKey(Party record);
}