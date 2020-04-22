package com.sergo.wic.dto;

public class ImageDto {

    private long id;
    private String format;
    private byte[] image;

    public ImageDto() {
    }

    public ImageDto(long id, String format, byte[] image) {
        this.id = id;
        this.format = format;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
