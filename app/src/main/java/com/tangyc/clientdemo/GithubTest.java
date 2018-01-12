package com.tangyc.clientdemo;

import java.util.List;

/**
 * @author tangyichao.
 */

public class GithubTest {

    /**
     * error : false
     * results : [{"_id":"5a3b1599421aa90fe50c029b","createdAt":"2017-12-21T09:59:53.864Z","desc":"最近把天气模块重写了，可能还挺好看的","images":["http://img.gank.io/d594a11e-b514-4523-b58a-1d191173996f"],"publishedAt":"2018-01-02T08:43:32.216Z","source":"web","type":"Android","url":"https://github.com/li-yu/FakeWeather/blob/master/README.md","used":true,"who":"liyu"},{"_id":"5a438750421aa90fe72536ea","createdAt":"2017-12-27T19:43:12.953Z","desc":"一个支持定制的树状 Android 自定义View","images":["http://img.gank.io/2237a2b8-ddab-4acb-a86e-185812aacaac"],"publishedAt":"2018-01-02T08:43:32.216Z","source":"web","type":"Android","url":"https://github.com/LeeReindeer/Tree2View","used":true,"who":null},{"_id":"5a44b1b2421aa90fef2035ce","createdAt":"2017-12-28T16:56:18.473Z","desc":"Android差分补丁库，通过native层合并APK，实现增量更新升级，让你更新的APK更小。","publishedAt":"2018-01-02T08:43:32.216Z","source":"web","type":"Android","url":"https://github.com/yale8848/EasyIncrementalUpdate","used":true,"who":"Yale"},{"_id":"5a464f9d421aa90fe72536f8","createdAt":"2017-12-29T22:22:21.772Z","desc":"Android通用圆角布局全解析","images":["http://img.gank.io/656dfb2a-c242-4b40-a002-526c4dfd3e24"],"publishedAt":"2018-01-02T08:43:32.216Z","source":"web","type":"Android","url":"http://www.gcssloop.com/gebug/rclayout","used":true,"who":"sloop"},{"_id":"5a473972421aa90fe72536f9","createdAt":"2017-12-30T15:00:02.277Z","desc":"一个多module自动打包并合并成单个jar包的gradle插件，适合sdk模块化打包。","images":["http://img.gank.io/6124dadc-f17d-4a17-86ac-63eb100528c8"],"publishedAt":"2018-01-02T08:43:32.216Z","source":"chrome","type":"Android","url":"https://github.com/bboylin/FatJar","used":true,"who":"bboylin"},{"_id":"5a4a3d9f421aa90fe50c02b5","createdAt":"2018-01-01T21:54:39.706Z","desc":"我是如何从流水线工人到程序员？（2008-2018）","publishedAt":"2018-01-02T08:43:32.216Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s?__biz=MzIwMzYwMTk1NA==&mid=2247489005&idx=1&sn=b52639b3a8d8ff70d9d71c79e5f9932d","used":true,"who":"陈宇明"},{"_id":"5a3a4654421aa90fe72536cc","createdAt":"2017-12-20T19:15:32.928Z","desc":"Git 使用之重写历史记录","publishedAt":"2017-12-27T12:13:22.418Z","source":"web","type":"Android","url":"http://www.jianshu.com/p/8f46e13a8ada","used":true,"who":"ZhangTitanjum"},{"_id":"5a40663d421aa90fe2f02cfa","createdAt":"2017-12-25T10:45:17.267Z","desc":"利用AMS Hook和APT去构建的一个Activity路由框架，妥妥的AOP骚操作","publishedAt":"2017-12-27T12:13:22.418Z","source":"web","type":"Android","url":"https://github.com/Zane96/EasyRouter","used":true,"who":"Zane Xu"},{"_id":"5a41c2ef421aa90fe2f02cff","createdAt":"2017-12-26T11:33:03.379Z","desc":"Android图片压缩的几种方案","publishedAt":"2017-12-27T12:13:22.418Z","source":"web","type":"Android","url":"http://mp.weixin.qq.com/s/-ixGY5E34Fbsy0N3-XTk-Q","used":true,"who":"D_clock"},{"_id":"5a41eb32421aa90fe2f02d02","createdAt":"2017-12-26T14:24:50.93Z","desc":"一行代码快速解耦Application的逻辑，让Application代码更简洁好维护","images":["http://img.gank.io/1dcccce4-fbef-43f7-84b0-ca267d915bd4"],"publishedAt":"2017-12-27T12:13:22.418Z","source":"web","type":"Android","url":"https://www.jianshu.com/p/23b9ba9b685d","used":true,"who":"阿韦"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * _id : 5a3b1599421aa90fe50c029b
         * createdAt : 2017-12-21T09:59:53.864Z
         * desc : 最近把天气模块重写了，可能还挺好看的
         * images : ["http://img.gank.io/d594a11e-b514-4523-b58a-1d191173996f"]
         * publishedAt : 2018-01-02T08:43:32.216Z
         * source : web
         * type : Android
         * url : https://github.com/li-yu/FakeWeather/blob/master/README.md
         * used : true
         * who : liyu
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;
        private List<String> images;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}