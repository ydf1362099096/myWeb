package top.andypage.page.webpage.model;

public class Comment {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COMMENT.ID
     *
     * @mbg.generated Tue Apr 07 21:55:50 CST 2020
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COMMENT.PARENTID
     *
     * @mbg.generated Tue Apr 07 21:55:50 CST 2020
     */
    private Long parentid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COMMENT.TYPE
     *
     * @mbg.generated Tue Apr 07 21:55:50 CST 2020
     */
    private Integer type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COMMENT.COMMENTORID
     *
     * @mbg.generated Tue Apr 07 21:55:50 CST 2020
     */
    private Long commentorid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COMMENT.COMMENTTIME
     *
     * @mbg.generated Tue Apr 07 21:55:50 CST 2020
     */
    private Long commenttime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COMMENT.LIKECOUNT
     *
     * @mbg.generated Tue Apr 07 21:55:50 CST 2020
     */
    private Integer likecount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COMMENT.CONTENT
     *
     * @mbg.generated Tue Apr 07 21:55:50 CST 2020
     */
    private String content;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COMMENT.ID
     *
     * @return the value of COMMENT.ID
     *
     * @mbg.generated Tue Apr 07 21:55:50 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COMMENT.ID
     *
     * @param id the value for COMMENT.ID
     *
     * @mbg.generated Tue Apr 07 21:55:50 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COMMENT.PARENTID
     *
     * @return the value of COMMENT.PARENTID
     *
     * @mbg.generated Tue Apr 07 21:55:50 CST 2020
     */
    public Long getParentid() {
        return parentid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COMMENT.PARENTID
     *
     * @param parentid the value for COMMENT.PARENTID
     *
     * @mbg.generated Tue Apr 07 21:55:50 CST 2020
     */
    public void setParentid(Long parentid) {
        this.parentid = parentid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COMMENT.TYPE
     *
     * @return the value of COMMENT.TYPE
     *
     * @mbg.generated Tue Apr 07 21:55:50 CST 2020
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COMMENT.TYPE
     *
     * @param type the value for COMMENT.TYPE
     *
     * @mbg.generated Tue Apr 07 21:55:50 CST 2020
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COMMENT.COMMENTORID
     *
     * @return the value of COMMENT.COMMENTORID
     *
     * @mbg.generated Tue Apr 07 21:55:50 CST 2020
     */
    public Long getCommentorid() {
        return commentorid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COMMENT.COMMENTORID
     *
     * @param commentorid the value for COMMENT.COMMENTORID
     *
     * @mbg.generated Tue Apr 07 21:55:50 CST 2020
     */
    public void setCommentorid(Long commentorid) {
        this.commentorid = commentorid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COMMENT.COMMENTTIME
     *
     * @return the value of COMMENT.COMMENTTIME
     *
     * @mbg.generated Tue Apr 07 21:55:50 CST 2020
     */
    public Long getCommenttime() {
        return commenttime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COMMENT.COMMENTTIME
     *
     * @param commenttime the value for COMMENT.COMMENTTIME
     *
     * @mbg.generated Tue Apr 07 21:55:50 CST 2020
     */
    public void setCommenttime(Long commenttime) {
        this.commenttime = commenttime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COMMENT.LIKECOUNT
     *
     * @return the value of COMMENT.LIKECOUNT
     *
     * @mbg.generated Tue Apr 07 21:55:50 CST 2020
     */
    public Integer getLikecount() {
        return likecount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COMMENT.LIKECOUNT
     *
     * @param likecount the value for COMMENT.LIKECOUNT
     *
     * @mbg.generated Tue Apr 07 21:55:50 CST 2020
     */
    public void setLikecount(Integer likecount) {
        this.likecount = likecount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COMMENT.CONTENT
     *
     * @return the value of COMMENT.CONTENT
     *
     * @mbg.generated Tue Apr 07 21:55:50 CST 2020
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COMMENT.CONTENT
     *
     * @param content the value for COMMENT.CONTENT
     *
     * @mbg.generated Tue Apr 07 21:55:50 CST 2020
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}