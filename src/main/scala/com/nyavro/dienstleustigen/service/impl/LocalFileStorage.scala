package com.nyavro.dienstleustigen.service.impl

import org.springframework.stereotype.Service
import com.nyavro.dienstleustigen.service.FileStorage
import java.io._
import org.joda.time.DateTime
import java.util.UUID
import org.apache.commons.io.{FileUtils, IOUtils}
import com.jcabi.log.Logger

@Service
class LocalFileStorage(private val root:String) extends FileStorage {

  def this() = this("files")

  override def upload(stream: InputStream): String = {
    val time:DateTime = new DateTime(System.currentTimeMillis())
    val components = List(time.getYear, time.getMonthOfYear, time.getDayOfMonth)
    val uuid = UUID.randomUUID.toString
    val dir: File = new File((root::components).mkString(File.separator))
    FileUtils.forceMkdir(dir)
    val output: FileOutputStream = new FileOutputStream(new File(dir, uuid))
    try {
      IOUtils.copy(stream, output)
    } finally {
      IOUtils.closeQuietly(output)
    }
    (components:+uuid).mkString("_")
  }

  override def download(id:String, stream: OutputStream) = {
    val file = new File(root + File.separator + id.split("_").mkString(File.separator))
    if(file.exists()) {
      val input: FileInputStream = new FileInputStream(file)
      try {
        IOUtils.copy(input, stream)
      } finally {
        IOUtils.closeQuietly(input)
      }
    }
    else {
      Logger.debug(this, "File not found {}", file.getPath)
      stream.close()
    }
  }
}
