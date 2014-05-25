package com.nyavro.dienstleustigen.model

import java.util.Date
import scala.beans.BeanProperty

class Presentation(
  @BeanProperty var id: String,
  @BeanProperty var shortDescription: String,
  @BeanProperty var fullDescription: String,
  @BeanProperty var executorId: String,
  @BeanProperty var title: String,
  @BeanProperty var created: Date,
  @BeanProperty var published: Date,
  @BeanProperty var creator: String,
  @BeanProperty var category: List[String]) extends Serializable {
  def this() = this(null, null, null, null, null, null, null, null, null)
}
