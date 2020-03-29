package com.aixl.m.model;

public class PicData {
    private String url; //图片base64编码
    private String name;//图片文件名
    //private String path="D:/AixlProject/AiWeb/img/cacheImg";
    private String path = "D:\\nginx-1.17.7\\html\\xlrzpc\\img\\cacheImg";
    private String scaleName; //图片所属量表

    public String getScaleName() {
        return scaleName;
    }

    public void setScaleName(String scaleName) {
        this.scaleName = scaleName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        if(path.length()<=0)
            return;
       this.path = path;
    }

    @Override
    public String toString() {
        return "PicData{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", scaleName='" + scaleName + '\'' +
                '}';
    }
}