package cn.heyueyuan.ibranchwiki.response;

import java.util.List;

public class PageResp<T> {
    private long total;

    private List<T> list;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        final StringBuffer stringBuffer = new StringBuffer("PageResp{");
        stringBuffer.append("total=").append(total);
        stringBuffer.append(", list=").append(list);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
