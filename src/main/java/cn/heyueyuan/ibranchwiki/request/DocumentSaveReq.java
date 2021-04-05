package cn.heyueyuan.ibranchwiki.request;

import javax.validation.constraints.NotNull;

public class DocumentSaveReq {
    private Long id;

    @NotNull(message = "Can not be empty ")
    private Long ebookId;

    @NotNull(message = "Can not be empty ")
    private Long parent;

    @NotNull(message = "Can not be empty ")
    private String name;

    @NotNull(message = "Can not be empty ")
    private Integer sort;

    @Override
    public String toString() {
        return "DocumentSaveReq{" +
                "id=" + id +
                ", ebookId=" + ebookId +
                ", parent=" + parent +
                ", name='" + name + '\'' +
                ", sort=" + sort +
                ", viewCount=" + viewCount +
                ", voteCount=" + voteCount +
                ", content='" + content + '\'' +
                '}';
    }

    private Integer viewCount;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private Integer voteCount;

    @NotNull(message = "Can not be empty ")
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEbookId() {
        return ebookId;
    }

    public void setEbookId(Long ebookId) {
        this.ebookId = ebookId;
    }

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

}