package com.sergo.wic.exception;

import java.io.IOException;

public class ImageNotUploadedException extends IOException {
    public ImageNotUploadedException(String message){
        super(message);
    }
}
