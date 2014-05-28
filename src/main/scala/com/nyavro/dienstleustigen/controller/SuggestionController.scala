package com.nyavro.dienstleustigen.controller

import net.nyavro.spring.social.signinmvc.controller.CommonController
import org.springframework.beans.factory.annotation.Autowired
import net.nyavro.spring.social.signinmvc.services.{UserService, SuggestionService}
import org.springframework.web.bind.annotation._
import com.nyavro.dienstleustigen.model.Suggestion
import com.jcabi.log.Logger
import java.security.Principal
import net.nyavro.spring.social.signinmvc.model.dto.CustomUserDetails
import org.springframework.ui.Model
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.validation.BindingResult
import javax.validation.Valid
import collection.JavaConverters._
import com.nyavro.dienstleustigen.service.CategoryService
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller

/**
 * Created by eny on 5/29/14.
 */
@Controller
@RequestMapping(value = Array("/service"), produces = Array(MediaType.APPLICATION_JSON_VALUE))
class SuggestionController extends CommonController {
  private val DEFAULT_PAGE = 0
  private val DEFAULT_BATCH = 10

  @Autowired
  var suggestionService:SuggestionService = _

  @Autowired
  var userService:UserService = _

  @Autowired
  var categoryService:CategoryService = _

  @RequestMapping(value = Array("/list"), method = Array(RequestMethod.GET))
  @ResponseBody
  def list(
    @RequestParam(value = "page", required = false) page:java.lang.Integer,
    @RequestParam(value = "batch", required = false) batch:java.lang.Integer):java.util.List[Suggestion] = {
    Logger.debug(this, "Fetching presentation page {}, batch {}", page, batch)
    suggestionService.findAll(if(page==null) DEFAULT_PAGE else page, if(batch==null) DEFAULT_BATCH else batch)
  }

  @RequestMapping(value = Array("/search/{id}"), method = Array(RequestMethod.GET))
  @ResponseBody
  def list(
    @PathVariable(value = "id") id:String,
    @RequestParam(value = "page", required = false) page:java.lang.Integer,
    @RequestParam(value = "batch", required = false) batch:java.lang.Integer):java.util.List[Suggestion] = {
    Logger.debug(this, "Fetching presentation page {}, batch {}", page, batch)
    suggestionService.findByCategory(id, if(page==null) DEFAULT_PAGE else page, if(batch==null) DEFAULT_BATCH else batch)
  }

  @RequestMapping(value = Array("/"), method = Array(RequestMethod.GET))
  def editForm(model:Model, principal:Principal):String = {
    val id = userDetails(principal).getId
    Logger.debug(this, "Loading suggestion for edit: {}", id)
    model.addAttribute("edit", true)
    model.addAttribute("suggestion", suggestionService.findByCreator(id))
    "suggestion"
  }

  def userDetails(principal:Principal):CustomUserDetails = principal.asInstanceOf[UsernamePasswordAuthenticationToken].getPrincipal.asInstanceOf[CustomUserDetails]

  @RequestMapping(value = Array("/"), method = Array(RequestMethod.POST))
  def edit(@Valid suggestion:Suggestion, result:BindingResult, model:Model, principal:Principal):String = {
    if (!result.hasErrors) {
      suggestion.setCreator(userDetails(principal).getId)
      suggestionService.save(suggestion)
    } else
      model.addAttribute("errors", result.getAllErrors.asScala.map(item=>item.getDefaultMessage))
    "/index"
  }

  @RequestMapping(value = Array("/view/{id}"), method = Array(RequestMethod.GET))
  def view(@PathVariable("id") id:String, model:Model, principal:Principal):String = {
    Logger.debug(this, "Loading presentation for view: {}", id)
    val suggestion:Suggestion = suggestionService.findById(id)
    model.addAttribute("suggestion", suggestion)
    model.addAttribute("categories", suggestion.category.asScala.map(item=>categoryService.findById(item)))
    model.addAttribute("providerContact", userService.getContact(suggestion.getCreator))
    model.addAttribute("edit", false)
    "suggestion-details"
  }
}
