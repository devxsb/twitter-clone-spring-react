package com.safalifter.twitterclone.service;

import com.safalifter.twitterclone.bucket.BucketName;
import com.safalifter.twitterclone.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static org.apache.http.entity.ContentType.*;

@Service
@RequiredArgsConstructor
public class FileService {
    private final UserService userService;
    private final S3Service s3Service;

    public void uploadProfileImage(String username, MultipartFile file) {
        if (file.isEmpty())
            throw new IllegalStateException("Cannot upload empty file");
        if (!Arrays.asList(IMAGE_JPEG.getMimeType(), IMAGE_PNG.getMimeType(), IMAGE_GIF.getMimeType())
                .contains(file.getContentType()))
            throw new IllegalStateException("File must be an image [" + file.getContentType() + "]");

        User inDB = userService.findUserByUsername(username);

        Map<String, String> metaData = new HashMap<>();
        metaData.put("Content-Type", file.getContentType());
        metaData.put("Content-Length", String.valueOf(file.getSize()));

        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), inDB.getUsername());
        String fileName = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());
        try {
            s3Service.save(path, fileName, file.getInputStream(), Optional.of(metaData));
            userService.updateUserProfileImage(fileName, username);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public byte[] downloadProfileImage(String username) {
        User inDB = userService.findUserByUsername(username);
        String path = String.format("%s/%s",
                BucketName.PROFILE_IMAGE.getBucketName(),
                inDB.getUsername());
        return Optional.ofNullable(inDB.getProfileImageLink())
                .map(key -> s3Service.download(path, key))
                .orElse(new byte[0]);
    }
}
