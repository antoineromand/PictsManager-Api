package com.epitech.pictmanager.components.image_manager.services;

import com.epitech.pictmanager.components.image_manager.dto.ImageUploadDto;
import com.epitech.pictmanager.components.user.repositories.UserJpaRepository;
import com.epitech.pictmanager.models.User;
import com.epitech.pictmanager.responses.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.TimeUnit;


@Service()
public class UploadService {
//    @Autowired
//    private UserJpaRepository userJpaRepository;
//    @Autowired
//    private ImageService imageService;
//
//    public Object upload(String id, MultipartFile image, String description) throws IOException {
//        try {
//            User user = userJpaRepository.findUserById(Long.parseLong(id));
//            ClassPathResource resource = new ClassPathResource("key-gcs.json");
//            Credentials credentials = GoogleCredentials.fromStream(resource.getInputStream());
//
//            Storage storage = StorageOptions.newBuilder()
//                    .setCredentials(credentials)
//                    .setProjectId("enduring-rush-384017")
//                    .build()
//                    .getService();
//
//            Bucket bucket = storage.get("pictsmanager_api_bucket");
//
//
//            // Créer un préfixe pour simuler un dossier
//            String userFolderPrefix = id + "/";
//
//            // Ajouter un fichier vide avec le préfixe pour créer la "structure du dossier"
//            BlobId blobId = BlobId.of(bucket.getName(), userFolderPrefix);
//            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
//            Blob createdFolder = storage.create(blobInfo, new byte[0]);
//            String imagePath = userFolderPrefix + image.getOriginalFilename();
//            InputStream inputStream = image.getInputStream();
//            byte[] imageData = inputStream.readAllBytes();
//            BlobId imageBlobId = BlobId.of(bucket.getName(), imagePath);
//            BlobInfo imageBlobInfo = BlobInfo.newBuilder(imageBlobId).build();
//            Blob createdImage = storage.create(imageBlobInfo, imageData);
//            ImageUploadDto imageUploadDto = new ImageUploadDto(image.getOriginalFilename(), image, description);
//            if (imageService.save(imageUploadDto, imagePath, user)) {
//                return ResponseEntity.status(HttpStatus.CREATED).body(new GenericResponse("Image uploaded successfully", HttpStatus.CREATED.value()));
//            } else {
//                throw new RuntimeException("Error while saving image");
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GenericResponse("Error while uploading image", HttpStatus.INTERNAL_SERVER_ERROR.value()));
//        }
//    }
//
//    public URL get(String id, String imageName) throws IOException {
//        try {
//            ClassPathResource resource = new ClassPathResource("key-gcs.json");
//            Credentials credentials = GoogleCredentials.fromStream(resource.getInputStream());
//
//            Storage storage = StorageOptions.newBuilder()
//                    .setCredentials(credentials)
//                    .setProjectId("enduring-rush-384017")
//                    .build()
//                    .getService();
//
//            Bucket bucket = storage.get("pictsmanager_api_bucket");
//            String imagePath = id + "/" + imageName;
//            Blob imageBlob = storage.get(BlobId.of(bucket.getName(), imagePath));
//            long urlExpiration = 3600; // Expiration en secondes (1 heure)
//            URL signedUrl = imageBlob.signUrl(urlExpiration, TimeUnit.SECONDS);
//            return signedUrl;
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            return null;
//        }
//    }
}