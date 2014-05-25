package com.nyavro.dienstleustigen.service

import java.io.{OutputStream, InputStream}

/**
 * Created by eny on 5/25/14.
 */
trait FileStorage {

  def upload(stream:InputStream):String

  def download(id:String, stream:OutputStream)
}
