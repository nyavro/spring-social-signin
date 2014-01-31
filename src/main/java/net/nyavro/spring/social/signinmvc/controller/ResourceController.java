package net.nyavro.spring.social.signinmvc.controller;

import org.h2.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@Controller
@RequestMapping(value = "/resource", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
public class ResourceController {

    private static final Logger log = LoggerFactory.getLogger(ResourceController.class);

    @RequestMapping(value = "/avatar/{id}", method = RequestMethod.GET)
    public void avatar(final @PathVariable(value = "id") String id, HttpServletRequest request, HttpServletResponse response) {
        log.debug("Fetching avatar of user {}", id);
        try {
            response.setContentType("image/png");
            //Placeholder for now:
            IOUtils.copy(
                new URL(host(request) + "/img/account_100x100.png").openConnection().getInputStream(),
                response.getOutputStream()
            );
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String host(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getLocalAddr() + ":" + request.getLocalPort();
    }
}
