package src.com.kk.furns.entity;

import java.util.List;

/**
 * 分页数据模型（含分页信息）
 * @author KK
 * @version 1.0
 */
public class Page<T> {
    public static final Integer PAGE_SIZE = 5;
    public static final Integer CUSTOMER_PAGE_SIZE = 4;
    // 页编号
    private Integer pageNo;
    // 页大小
    private Integer pageSize = PAGE_SIZE;
    // 页多少
    private Integer totalPages;
    // 行多少
    private Integer totalRows;
    // 当前页内容
    private List<T> items;
    // 分页导航定位
    private String url;

    public Page() {

    }

    public Page(Integer pageNo, Integer pageSize, Integer totalPages, Integer totalRows, List<T> items, String url) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalPages = totalPages;
        this.totalRows = totalRows;
        this.items = items;
        this.url = url;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Integer totalRows) {
        this.totalRows = totalRows;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
