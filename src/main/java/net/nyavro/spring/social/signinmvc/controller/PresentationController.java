package net.nyavro.spring.social.signinmvc.controller;

import net.nyavro.spring.social.signinmvc.model.Presentation;
import net.nyavro.spring.social.signinmvc.model.dto.CustomUserDetails;
import net.nyavro.spring.social.signinmvc.services.PresentationService;
import net.nyavro.spring.social.signinmvc.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/presentation", produces = MediaType.APPLICATION_JSON_VALUE)
public class PresentationController extends CommonController {

    private static final Logger log = LoggerFactory.getLogger(PresentationController.class);

    private static final int DEFAULT_PAGE = 0;

    private static final int DEFAULT_BATCH = 10;

    @Autowired
    private PresentationService presentationService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List<Presentation> list(
        final @RequestParam(value = "page", required = false) Integer page,
        final @RequestParam(value = "batch", required = false) Integer batch) {
        log.debug("Fetching presentation page {}, batch {}", page, batch);
        return presentationService.findAll(
            page==null ? DEFAULT_PAGE : page,
            batch==null ? DEFAULT_BATCH : batch
        );
    }

    @RequestMapping(value = "/search/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<Presentation> list(
            final @PathVariable(value = "id") String id,
            final @RequestParam(value = "page", required = false) Integer page,
            final @RequestParam(value = "batch", required = false) Integer batch) {
        log.debug("Fetching presentation page {}, batch {}", page, batch);
        return presentationService.findByCategory(
                id,
                page==null ? DEFAULT_PAGE : page,
                batch==null ? DEFAULT_BATCH : batch
        );
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String editForm(Model model, Principal principal) {
        final String id = userDetails(principal).getId();
        log.debug("Loading presentation for edit: {}", id);
        model.addAttribute("edit", true);
        model.addAttribute("presentation", presentationService.findByCreator(id));
        return "presentation";
    }

    private CustomUserDetails userDetails(Principal principal) {
        return (CustomUserDetails) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String edit(@Valid Presentation presentation, BindingResult result, Model model, Principal principal)
            throws Exception {
        if (!result.hasErrors()) {
            presentation.setCreator(userDetails(principal).getId());
            presentationService.save(presentation);
        } else {
            List<String> errors = new ArrayList<String>();
            for (ObjectError objError : result.getAllErrors()) {
                errors.add(objError.getDefaultMessage());
            }
            model.addAttribute("errors", errors);
        }
        return "/index";
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") String id, Model model, Principal principal) {
        log.debug("Loading presentation for view: {}", id);
        final Presentation presentation = presentationService.findById(id);
        model.addAttribute("presentation", presentation);
        model.addAttribute("providerContact", userService.getContact(presentation.getCreator()));
        model.addAttribute("edit", false);
        return "presentation-details";
    }
}