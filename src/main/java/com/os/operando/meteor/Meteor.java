package com.os.operando.meteor;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import java.io.File;
import java.io.IOException;

public class Meteor {

    private File uploadFile;
    private String token;
    private String channels;
    private String fileType;
    private String mediaType;
    private String title;
    private String initialComment;

    private Meteor(Builder builder) {
        if (builder.token == null) {
            new RuntimeException("token is required.");
        }
        if (builder.uploadFile == null) {
            new RuntimeException("uploadFile is required.");
        }
        uploadFile = builder.uploadFile;
        token = builder.token;
        channels = builder.channels;
        fileType = builder.fileType;
        mediaType = builder.mediaType;
        title = builder.title;
        initialComment = builder.initialComment;
    }

    public void post(Callback callback) throws IOException {
        MultipartBuilder multipartBuilder = new MultipartBuilder();
        multipartBuilder.type(MultipartBuilder.FORM);
        multipartBuilder.addFormDataPart("file", uploadFile.getName(),
                RequestBody.create(MediaType.parse(mediaType != null ? mediaType : ""), uploadFile));
        multipartBuilder.addFormDataPart("token", token);
        if (channels != null) {
            multipartBuilder.addFormDataPart("channels", channels);
        }
        if (fileType != null) {
            multipartBuilder.addFormDataPart("filetype", fileType);
        }
        if (title != null) {
            multipartBuilder.addFormDataPart("title", title);
        }
        if (initialComment != null) {
            multipartBuilder.addFormDataPart("initial_comment", initialComment);
        }
        RequestBody requestBody = multipartBuilder.build();

        Request request = new Request.Builder()
                .url("https://slack.com/api/files.upload")
                .post(requestBody)
                .build();

        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(callback);
    }

    public static final class Builder {
        private File uploadFile;
        private String token;
        private String channels;
        private String fileType;
        private String mediaType;
        private String title;
        private String initialComment;

        public Builder() {
        }

        public Builder uploadFile(File uploadFile) {
            this.uploadFile = uploadFile;
            return this;
        }

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public Builder channels(String channels) {
            this.channels = channels;
            return this;
        }

        public Builder fileType(String fileType) {
            this.fileType = fileType;
            return this;
        }

        public Builder mediaType(String mediaType) {
            this.mediaType = mediaType;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder initialComment(String initialComment) {
            this.initialComment = initialComment;
            return this;
        }

        public Meteor build() {
            return new Meteor(this);
        }
    }
}