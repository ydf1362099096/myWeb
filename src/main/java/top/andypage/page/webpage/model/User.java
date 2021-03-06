package top.andypage.page.webpage.model;

public class User {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.ID
     *
     * @mbg.generated Sun Apr 12 13:52:39 CST 2020
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.USERNAME
     *
     * @mbg.generated Sun Apr 12 13:52:39 CST 2020
     */
    private String username;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.PASSWORD
     *
     * @mbg.generated Sun Apr 12 13:52:39 CST 2020
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.CREATE_TIME
     *
     * @mbg.generated Sun Apr 12 13:52:39 CST 2020
     */
    private Long createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.MODIFIED_TIME
     *
     * @mbg.generated Sun Apr 12 13:52:39 CST 2020
     */
    private Long modifiedTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.TOKEN
     *
     * @mbg.generated Sun Apr 12 13:52:39 CST 2020
     */
    private String token;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.AVATAR
     *
     * @mbg.generated Sun Apr 12 13:52:39 CST 2020
     */
    private String avatar;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.VIEWRECORD
     *
     * @mbg.generated Sun Apr 12 13:52:39 CST 2020
     */
    private String viewrecord;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.ID
     *
     * @return the value of USER.ID
     *
     * @mbg.generated Sun Apr 12 13:52:39 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.ID
     *
     * @param id the value for USER.ID
     *
     * @mbg.generated Sun Apr 12 13:52:39 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.USERNAME
     *
     * @return the value of USER.USERNAME
     *
     * @mbg.generated Sun Apr 12 13:52:39 CST 2020
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.USERNAME
     *
     * @param username the value for USER.USERNAME
     *
     * @mbg.generated Sun Apr 12 13:52:39 CST 2020
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.PASSWORD
     *
     * @return the value of USER.PASSWORD
     *
     * @mbg.generated Sun Apr 12 13:52:39 CST 2020
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.PASSWORD
     *
     * @param password the value for USER.PASSWORD
     *
     * @mbg.generated Sun Apr 12 13:52:39 CST 2020
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.CREATE_TIME
     *
     * @return the value of USER.CREATE_TIME
     *
     * @mbg.generated Sun Apr 12 13:52:39 CST 2020
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.CREATE_TIME
     *
     * @param createTime the value for USER.CREATE_TIME
     *
     * @mbg.generated Sun Apr 12 13:52:39 CST 2020
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.MODIFIED_TIME
     *
     * @return the value of USER.MODIFIED_TIME
     *
     * @mbg.generated Sun Apr 12 13:52:39 CST 2020
     */
    public Long getModifiedTime() {
        return modifiedTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.MODIFIED_TIME
     *
     * @param modifiedTime the value for USER.MODIFIED_TIME
     *
     * @mbg.generated Sun Apr 12 13:52:39 CST 2020
     */
    public void setModifiedTime(Long modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.TOKEN
     *
     * @return the value of USER.TOKEN
     *
     * @mbg.generated Sun Apr 12 13:52:39 CST 2020
     */
    public String getToken() {
        return token;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.TOKEN
     *
     * @param token the value for USER.TOKEN
     *
     * @mbg.generated Sun Apr 12 13:52:39 CST 2020
     */
    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.AVATAR
     *
     * @return the value of USER.AVATAR
     *
     * @mbg.generated Sun Apr 12 13:52:39 CST 2020
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.AVATAR
     *
     * @param avatar the value for USER.AVATAR
     *
     * @mbg.generated Sun Apr 12 13:52:39 CST 2020
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.VIEWRECORD
     *
     * @return the value of USER.VIEWRECORD
     *
     * @mbg.generated Sun Apr 12 13:52:39 CST 2020
     */
    public String getViewrecord() {
        return viewrecord;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.VIEWRECORD
     *
     * @param viewrecord the value for USER.VIEWRECORD
     *
     * @mbg.generated Sun Apr 12 13:52:39 CST 2020
     */
    public void setViewrecord(String viewrecord) {
        this.viewrecord = viewrecord == null ? null : viewrecord.trim();
    }
}