package io.metaphor.masterSpringMvc.profile;

import io.metaphor.masterSpringMvc.config.PictureUploadProperties;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.util.Locale;

@Controller
public class PictureUploadController {
    private final Resource picturesDir;
    private final Resource anonymousPicture;
    private final MessageSource messageSource;
    private final UserProfileSession userProfileSession;
    @Autowired
    public PictureUploadController(PictureUploadProperties uploadProperties, MessageSource messageSource, UserProfileSession userProfileSession) {
        this.picturesDir = uploadProperties.getUploadPath();
        this.anonymousPicture = uploadProperties.getAnonymousPicture();
        this.messageSource = messageSource;
        this.userProfileSession = userProfileSession;
    }

    @RequestMapping(value = "profile",params = {"upload"},method = RequestMethod.POST)
    public String onUpload(MultipartFile file, RedirectAttributes redirectAttributes,
                           Model model) throws IOException {
        if(file.isEmpty() || !isImage(file)) {
            redirectAttributes.addFlashAttribute("error","Incorrect file. " +
                    "Please upload a picture.");
            return "redirect:/profile";
        }
        Resource picturePath = copyFileToPictrues(file);
//        model.addAttribute("picturePath",picturePath);
        userProfileSession.setPicturePath(picturePath);
        return "redirect:/profile";
    }
    @RequestMapping(value = "/uploadedPicture")
    public void getUploadedPicture(HttpServletResponse response) throws IOException {
        Resource picturePath=userProfileSession.getPicturePath();
        if(picturePath==null) {
            picturePath=anonymousPicture;
        }
        response.setHeader("Content-Type", URLConnection.guessContentTypeFromName(picturePath.toString()));
        IOUtils.copy(picturePath.getInputStream(),response.getOutputStream());
    }
    @ExceptionHandler(IOException.class)
    public ModelAndView handleIOException(Locale locale) {
        ModelAndView modelAndView = new ModelAndView("profile/profilePage");
        modelAndView.addObject("error",messageSource.getMessage("upload.io.exception",null,locale));
        modelAndView.addObject("profileForm",userProfileSession.toForm());
        return modelAndView;
    }

    @RequestMapping("uploadError")
    public ModelAndView onUploadError(Locale locale) {
        ModelAndView modelAndView = new ModelAndView("profile/profilePage");
        modelAndView.addObject("error",
                messageSource.getMessage("upload.file.too.big",null,locale));
        modelAndView.addObject("profileForm",userProfileSession.toForm());
        return modelAndView;
    }
    private boolean isImage(MultipartFile file) {
        return file.getContentType().startsWith("image");
    }

    private Resource copyFileToPictrues(MultipartFile file) throws IOException {
        String filename= file.getOriginalFilename();
        String fileExtension = getFileExtension(filename);
        File tempFile = File.createTempFile("pic",fileExtension,picturesDir.getFile());
        try (InputStream in = file.getInputStream();
             OutputStream out = new FileOutputStream(tempFile)) {
            IOUtils.copy(in,out);
        }
        return new FileSystemResource(tempFile);
    }

    private String getFileExtension(String filename) {
        return  filename.substring(filename.lastIndexOf("."));
    }
}
