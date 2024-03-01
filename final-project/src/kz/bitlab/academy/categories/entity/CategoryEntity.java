package kz.bitlab.academy.categories.entity;

public class CategoryEntity {

    private Long id;
    private String name;

    public CategoryEntity(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryEntity(String name) {
        this.name = name;


    }
}
