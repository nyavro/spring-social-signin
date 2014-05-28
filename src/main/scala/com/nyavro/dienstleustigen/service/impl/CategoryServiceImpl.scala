package com.nyavro.dienstleustigen.service.impl

import com.nyavro.dienstleustigen.service.CategoryService
import net.nyavro.spring.social.signinmvc.repository.CategoryRepository
import org.springframework.data.domain.{Sort, PageRequest}
import net.nyavro.spring.social.signinmvc.model.Category
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by eny on 5/29/14.
 */
@Service
class CategoryServiceImpl extends CategoryService {

  val BATCH: Int = 1024
  @Autowired
  var repository: CategoryRepository = _

  def findAll: java.util.List[Category] =
    repository.findAll(new PageRequest(0, BATCH, new Sort(Sort.Direction.DESC, "published"))).getContent

  def findById(id: String): Category =
    repository.findById(id)
}
