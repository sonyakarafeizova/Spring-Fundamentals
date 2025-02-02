package com.philately.model.dto;

public class PaperDTO {
    private Long id;
    private String paperName;
    private String description;

    public PaperDTO(Long id, String paperName, String description) {
        this.id = id;
        this.paperName = paperName;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
