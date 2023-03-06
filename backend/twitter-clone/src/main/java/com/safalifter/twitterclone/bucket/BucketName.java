package com.safalifter.twitterclone.bucket;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum BucketName {
    PROFILE_IMAGE("**********"); // bucket name
    private final String bucketName;
}
