package com.epitech.pictmanager.components.image_manager.controllers;

import com.epitech.pictmanager.components.image_manager.services.UploadService;
import com.epitech.pictmanager.components.user.repositories.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController()
@RequestMapping("private/api/image-manager")
public class ImageManagerController {
    private final UploadService uploadService;
    private final UserJpaRepository userJpaRepository;

    public ImageManagerController(UploadService uploadService, UserJpaRepository userJpaRepository) {
        this.uploadService = uploadService;
        this.userJpaRepository = userJpaRepository;
    }

//    @PostMapping("upload")
//    public Object test(@AuthenticationPrincipal() String id,
//                       @RequestParam("image") MultipartFile image,
//                       @RequestParam("description") String description) throws IOException {
//        return uploadService.upload(id, image, description);
//    }


//    @GetMapping("/{userId}/{imageName}")
//    public URL serveImage(@AuthenticationPrincipal() String id,
//                          @PathVariable("userId") String userId,
//                          @PathVariable("imageName") String imageName,
//                          HttpServletRequest request) throws IOException {
//        if(userJpaRepository.findUserById(Long.parseLong(id)) != null) {
//            return this.uploadService.get(userId, imageName);
//        } else {
//            throw new RuntimeException("User not found");
//        }
//
//    }
}
