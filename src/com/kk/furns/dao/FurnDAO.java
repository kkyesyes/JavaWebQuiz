package src.com.kk.furns.dao;

import src.com.kk.furns.entity.Furn;

import java.util.List;

/**
 * @author KK
 * @version 1.0
 */
public interface FurnDAO {
    // 查询所有家居信息
    List<Furn> queryFurns();

    // 添加家居信息
    int addFurn(Furn furn);
}
