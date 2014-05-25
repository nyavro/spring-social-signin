package com.nyavro.dienstleustigen.service.impl

import org.scalatest.{BeforeAndAfter, FunSuite}
import java.io._
import org.apache.commons.io.input.ReaderInputStream
import org.apache.commons.io.{FileUtils, IOUtils}

class LocalFileStorageTest extends FunSuite with BeforeAndAfter {

  before {
    FileUtils.forceDelete(new File("teststorage"))
  }

  test("Saves file") {
    val id = new LocalFileStorage("teststorage").upload(new ReaderInputStream(new StringReader("test content"), "UTF-8"))
    def find(name:String):InputStream = {
      val components = name.split("_")
      assert(components.length===4)
      new FileInputStream("teststorage" + File.separator + components.mkString(File.separator))
    }
    val buffer:Array[Byte] = new Array[Byte](20)
    IOUtils.read(find(id), buffer)
    assert(new String(buffer).startsWith("test content"))
  }

}
