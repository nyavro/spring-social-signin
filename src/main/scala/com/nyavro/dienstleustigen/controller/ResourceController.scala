package com.nyavro.dienstleustigen.controller

import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation._
import java.net.{MalformedURLException, URL}
import javax.servlet.http.{HttpServletResponse, HttpServletRequest}
import com.jcabi.log.Logger
import java.io.IOException
import org.apache.commons.io.IOUtils
import org.springframework.web.multipart.MultipartFile
import org.springframework.beans.factory.annotation.Autowired
import com.nyavro.dienstleustigen.service.FileStorage

@Controller
@RequestMapping(value = Array("/resource"), produces = Array(MediaType.APPLICATION_OCTET_STREAM_VALUE))
class ResourceController {

  @Autowired
  var storage:FileStorage = _

  @RequestMapping(value = Array("/avatar/{id}"), method = Array(RequestMethod.GET))
  def avatar(@PathVariable(value = "id") id: String, request: HttpServletRequest, response: HttpServletResponse) = {
    Logger.debug(this, "Fetching avatar of user {}", id)
    response.setContentType("image/png")
    IOUtils.copy(new URL(host(request) + "/img/account_100x100.png").openConnection.getInputStream, response.getOutputStream)
  }
  
  @RequestMapping(value = Array("/upload"), method = Array(RequestMethod.POST))
  @ResponseBody def uploadFile(@RequestParam("file") file: MultipartFile): Map[String, String] = {
    try {
      Map("status" -> "OK", "id" -> storage.upload(file.getInputStream))
    }
    catch {
      case e: IOException => Map("status" -> "error", "message" -> ("Unable to store file: " + e.getMessage))
      case e: Any => throw new RuntimeException(e)
    }
  }

  @RequestMapping(value = Array("/download"), method = Array(RequestMethod.GET))
  def downloadFile(@RequestParam(value = "id") id: String, request: HttpServletRequest, response: HttpServletResponse) = {
    Logger.debug(this, "Fetching file {}", id)
    storage.download(id, response.getOutputStream)
  }

  private def host(request: HttpServletRequest): String = request.getScheme + "://" + request.getLocalAddr + ":" + request.getLocalPort
}
