package com.sergo.wic.controller;

import com.sergo.wic.dto.Response;
import com.sergo.wic.dto.ShareResponse;
import com.sergo.wic.dto.entity.CreateShareDto;
import com.sergo.wic.dto.entity.ImageDto;
import com.sergo.wic.facade.ImageFacade;
import com.sergo.wic.facade.ShareFacade;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class WicRestController {

    @Autowired
    private ShareFacade shareFacade;

    @Autowired
    private ImageFacade imageFacade;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/shares")
    public ShareResponse publicShare(@Valid @RequestBody final CreateShareDto createShareDto) {
        return new ShareResponse(shareFacade.saveShare(createShareDto).getId());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/shares/productPhoto/{shareId}")
    public Response setPhotoForShareProduct(@RequestParam(value = "photo") MultipartFile photo,
                                            @PathVariable Long shareId) {
        shareFacade.uploadPhotoForShareProduct(photo, shareId);
        return new Response();
    }

    @GetMapping("/productImage/{id}")
    public void getProductImageAsResource(HttpServletResponse response, @PathVariable("id") long id) throws IOException {
        final ImageDto imageDto = imageFacade.getImageById(id);
        InputStream in = new ByteArrayInputStream(imageDto.getImage());
        response.setContentType(imageDto.getFormat());
        IOUtils.copy(in, response.getOutputStream());
    }
}
