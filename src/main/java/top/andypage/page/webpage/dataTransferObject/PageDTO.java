package top.andypage.page.webpage.dataTransferObject;

import java.util.List;

public class PageDTO
{
    private List<topicDTO> topic;
    private boolean prevButton;
    private boolean firstButton;
    private boolean nextButton;
    private boolean finalButton;
    private Integer currentPage;
    private List<Integer> pageShow;
    private Integer finalPageIndex;

    public Integer getFinalPageIndex() {
        return finalPageIndex;
    }

    public void setFinalPageIndex(Integer finalPageIndex) {
        this.finalPageIndex = finalPageIndex;
    }

    public PageDTO(){
        prevButton=true;
        firstButton=true;
        nextButton=true;
        finalButton=true;
    }

    public List<topicDTO> getTopic() {
        return topic;
    }

    public void setTopic(List<topicDTO> topic) {
        this.topic = topic;
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
