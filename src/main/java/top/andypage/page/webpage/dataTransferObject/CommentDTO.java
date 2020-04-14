package top.andypage.page.webpage.dataTransferObject;

import top.andypage.page.webpage.model.User;

public class CommentDTO {
    private Long id;
    private Long parentid;
    private Integer type;
    private String content;
    private Long commenttime;
    private User uesr;
    private Integer likecount;

    public Long getCommenttime() {
        return commenttime;
    }

    public void setCommenttime(Long commenttime) {
        this.commenttime = commenttime;
    }

    public Integer getLikecount() {
        return likecount;
    }

    public void setLikecount(Integer likecount) {
        this.likecount = likecount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUesr() {
        return uesr;
    }

    public void setUesr(User uesr) {
        this.uesr = uesr;
    }

    public Long getParentid() {
        return parentid;
    }

    public void setParentid(Long parentid) {
        this.parentid = parentid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
