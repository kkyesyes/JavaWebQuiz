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

    // 通过 id 查询家居信息
    Furn queryFurnById(int id);

    // 添加家居信息
    int addFurn(Furn furn);

    // 删除家居信息
    int deleteFurnById(int id);

    // 修改家居信息
    int updateFurn(Furn furn);

    // 查询表行数
    int getTotalRow();

    // 获取当前页显示数据
    List<Furn> getPageFurns(int begin, int pageSize);
}
