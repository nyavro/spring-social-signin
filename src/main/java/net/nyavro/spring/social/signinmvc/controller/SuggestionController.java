package net.nyavro.spring.social.signinmvc.controller;

import com.jcabi.log.Logger;
import com.nyavro.dienstleustigen.model.Suggestion;
import net.nyavro.spring.social.signinmvc.model.dto.CustomUserDetails;
import net.nyavro.spring.social.signinmvc.services.SuggestionService;
import net.nyavro.spring.social.signinmvc.services.UserService;
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
@RequestMapping(value = "/service", produces = MediaType.APPLICATION_JSON_VALUE)
public class SuggestionController extends CommonController {

    private static final int DEFAULT_PAGE = 0;

    private static final int DEFAULT_BATCH = 10;

    @Autowired
    private SuggestionService suggestionService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List<Suggestion> list(
        final @RequestParam(value = "page", required = false) Integer page,
        final @RequestParam(value = "batch", required = false) Integer batch) {
        Logger.debug(this, "Fetching presentation page {}, batch {}", page, batch);
        return suggestionService.findAll(
            page==null ? DEFAULT_PAGE : page,
            batch==null ? DEFAULT_BATCH : batch
        );
    }

    @RequestMapping(value = "/search/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<Suggestion> list(
            final @PathVariable(value = "id") String id,
            final @RequestParam(value = "page", required = false) Integer page,
            final @RequestParam(value = "batch", required = false) Integer batch) {
        Logger.debug(this, "Fetching presentation page {}, batch {}", page, batch);
        return suggestionService.findByCategory(
                id,
                page==null ? DEFAULT_PAGE : page,
                batch==null ? DEFAULT_BATCH : batch
        );
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String editForm(Model model, Principal principal) {
        final String id = userDetails(principal).getId();
        Logger.debug(this, "Loading suggestion for edit: {}", id);
        model.addAttribute("edit", true);
        model.addAttribute("suggestion", suggestionService.findByCreator(id));
        return "suggestion";
    }

    private CustomUserDetails userDetails(Principal principal) {
        return (CustomUserDetails) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String edit(@Valid Suggestion suggestion, BindingResult result, Model model, Principal principal)
            throws Exception {
        if (!result.hasErrors()) {
            suggestion.setCreator(userDetails(principal).getId());
            suggestionService.save(suggestion);
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
        Logger.debug(this, "Loading presentation for view: {}", id);
        final Suggestion suggestion = suggestionService.findById(id);
        model.addAttribute("suggestion", suggestion);
        List<String> list = new ArrayList<>();
        list.add("first");
        list.add("second");
        list.add("third");
        suggestion.setCategory(list);
        model.addAttribute("providerContact", userService.getContact(suggestion.getCreator()));
        model.addAttribute("edit", false);
        return "suggestion-details";
    }
}