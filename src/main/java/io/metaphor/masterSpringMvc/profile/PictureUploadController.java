package io.metaphor.masterSpringMvc.profile;

import io.metaphor.masterSpringMvc.config.PictureUploadProperties;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@SessionAttributes("picturePath")
public class PictureUploadController {
    private final Resource picturesDir;
    private final Resource anonymousPicture;

    @Autowired
    public PictureUploadController(PictureUploadProperties uploadProperties) {
        this.picturesDir = uploadProperties.getUploadPath();
        this.anonymousPicture = uploadProperties.getAnonymousPicture();
    }
    @ModelAttribute("picturePath")
    public Resource picturePath(){
        return anonymousPicture;
    }

    @RequestMapping("upload")
    public String uploadPage() {
        return "profile/uploadPage";
    }

    @RequestMapping(value = "upload",method = RequestMethod.POST)
    public String onUpload(MultipartFile file, RedirectAttributes redirectAttributes,
                           Model model) throws IOException {
        throw new IOException("Some message");
//        if(file.isEmpty() || !isImage(file)) {
//            redirectAttributes.addFlashAttribute("error","Incorrect file. " +
//                    "Please upload a picture.");
//            return "redirect:/upload";
//        }
//        Resource picturePath = copyFileToPictrues(file);
//        model.addAttribute("picturePath",picturePath);
//        return "profile/uploadPage";
    }
    @RequestMapping(value = "/uploadedPicture")
    public void getUploadedPicture(HttpServletResponse response,
                                   @ModelAttribute("picturePath")Resource picturePath) throws IOException {

        response.setHeader("Content-Type", URLConnection.guessContentTypeFromName(picturePath.toString()));
        IOUtils.copy(picturePath.getInputStream(),response.getOutputStream());
    }
    @ExceptionHandler(IOException.class)
    public ModelAndView handleIOException(IOException exception) {
        ModelAndView modelAndView = new ModelAndView("profile/uploadPage");
        modelAndView.addObject("error",exception.getMessage());
        return modelAndView;
    }

    @RequestMapping("uploadError")
    public ModelAndView onUploadError(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("profile/uploadPage");
        modelAndView.addObject("error",request.getAttribute(WebUtils.ERROR_MESSAGE_ATTRIBUTE));
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
//        String filePath=tempFile.getPath();
//        return Paths.get(filePath);
        return new FileSystemResource(tempFile);
    }

    private String getFileExtension(String filename) {
        return  filename.substring(filename.lastIndexOf("."));
    }
}
