package kz.bitlab.academy.news.entity;

import kz.bitlab.academy.users.entity.UserEntity;

import java.time.LocalDateTime;

public class NewsEntity {

    private Long id;
    private LocalDateTime postDate = LocalDateTime.now();
    private String category;
    private String title;
    private String content;

    public NewsEntity(){}



    public void setId(Long id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPostDate(LocalDateTime postDate) {
        this.postDate = postDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public UserEntity getCategory() {
        return null;
    }
}
