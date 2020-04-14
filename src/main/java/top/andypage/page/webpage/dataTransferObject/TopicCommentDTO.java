package top.andypage.page.webpage.dataTransferObject;

import top.andypage.page.webpage.model.User;

import java.util.List;

public class TopicCommentDTO {
    private List<CommentDTO> comment;
    private boolean prevButton;
    private boolean firstButton;
    private boolean nextButton;
    private boolean finalButton;
    private Integer currentPage;
    private List<Integer> pageShow;
    private Integer finalPageIndex;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getFinalPageIndex() {
        return finalPageIndex;
    }

    public void setFinalPageIndex(Integer finalPageIndex) {
        this.finalPageIndex = finalPageIndex;
    }

    public TopicCommentDTO(){
        prevButton=true;
        firstButton=true;
        nextButton=true;
        finalButton=true;
    }

    public List<CommentDTO> getComment() {
        return comment;
    }

    public void setComment(List<CommentDTO> comment) {
        this.comment = comment;
    }

    public boolean isPrevButton() {
        return prevButton;
    }

    public void setPrevButton(boolean prevButton) {
        this.prevButton = prevButton;
    }

    public boolean isFirstButton() {
        return firstButton;
    }

    public void setFirstButton(boolean firstButton) {
        this.firstButton = firstButton;
    }

    public boolean isNextButton() {
        return nextButton;
    }

    public void setNextButton(boolean nextButton) {
        this.nextButton = nextButton;
    }

    public boolean isFinalButton() {
        return finalButton;
    }

    public void setFinalButton(boolean finalButton) {
        this.finalButton = finalButton;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public List<Integer> getPageShow() {
        return pageShow;
    }

    public void setPageShow(List<Integer> pageShow) {
        this.pageShow = pageShow;
    }
}
