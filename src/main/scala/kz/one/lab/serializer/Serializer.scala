package kz.one.lab.serializer

import org.json4s.{Formats, ShortTypeHints, jackson}
import org.json4s.native.Serialization

trait Serializer extends CoreSerializer {

  implicit val formats: Formats = Serialization.formats(
    ShortTypeHints(
      hints
    )
  )

  implicit val serialization: jackson.Serialization.type = jackson.Serialization

}
