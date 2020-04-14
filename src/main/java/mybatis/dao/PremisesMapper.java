package mybatis.dao;

import java.util.List;
import mybatis.model.Premises;

public interface PremisesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Premises record);

    Premises selectByPrimaryKey(Integer id);

    List<Premises> selectAll();

    int updateByPrimaryKey(Premises record);
}