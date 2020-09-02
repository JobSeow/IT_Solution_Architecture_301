package com.itsa.gourds.masks.entities;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.annotations.SerializedName;

public class CartResponse {
    @SerializedName("id")
    private String id;
    @SerializedName("userid")
    private String userId;
    @SerializedName("content")
    private JsonNode content;

    public CartResponse() {
        super();
    }

    public CartResponse(String userId, JsonNode content) {
        this.userId = userId;
        this.content = content;
    }

    public CartResponse(String id, String userId, JsonNode content) {
        this.id = id;
        this.userId = userId;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public JsonNode getContent() {
        return content;
    }

    public void setContent(JsonNode content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Cart{" +
            "id='" + id + '\'' +
            ", userid='" + userId + '\'' +
            ", content='" + content + '\'' +
            '}';
    }
}
