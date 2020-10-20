package com.example.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Hit {
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("points")
    @Expose
    private Integer points;
    @SerializedName("story_text")
    @Expose
    private String storyText;
    @SerializedName("comment_text")
    @Expose
    private Object commentText;
    @SerializedName("num_comments")
    @Expose
    private Integer numComments;
    @SerializedName("story_id")
    @Expose
    private Object storyId;
    @SerializedName("story_title")
    @Expose
    private Object storyTitle;
    @SerializedName("story_url")
    @Expose
    private Object storyUrl;
    @SerializedName("parent_id")
    @Expose
    private Object parentId;
    @SerializedName("created_at_i")
    @Expose
    private Integer createdAtI;
    @SerializedName("relevancy_score")
    @Expose
    private Integer relevancyScore;
    @SerializedName("_tags")
    @Expose
    private List<String> tags = null;
    @SerializedName("objectID")
    @Expose
    private String objectID;
    @SerializedName("_highlightResult")
    @Expose
    private HighlightResult highlightResult;

    public String getCreatedAt() {
        return createdAt;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getPoints() {
        return points;
    }

    public String getStoryText() {
        return storyText;
    }

    public Object getCommentText() {
        return commentText;
    }

    public Integer getNumComments() {
        return numComments;
    }

    public Object getStoryId() {
        return storyId;
    }

    public Object getStoryTitle() {
        return storyTitle;
    }

    public Object getStoryUrl() {
        return storyUrl;
    }

    public Object getParentId() {
        return parentId;
    }

    public Integer getCreatedAtI() {
        return createdAtI;
    }

    public Integer getRelevancyScore() {
        return relevancyScore;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getObjectID() {
        return objectID;
    }

    public HighlightResult getHighlightResult() {
        return highlightResult;
    }
}
