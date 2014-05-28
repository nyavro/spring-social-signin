package com.nyavro.dienstleustigen.service

import net.nyavro.spring.social.signinmvc.model.Category

/**
 * Created by eny on 5/29/14.
 */
trait CategoryService {

  def findAll: java.util.List[Category]

  def findById(id: String): Category
}
