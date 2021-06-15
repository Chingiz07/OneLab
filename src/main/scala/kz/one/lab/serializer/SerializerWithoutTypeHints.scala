package kz.one.lab.serializer

import org.json4s.{Formats, NoTypeHints, ShortTypeHints}
import org.json4s.jackson.Serialization

trait SerializerWithoutTypeHints extends Serialization {

  implicit val formats: Formats = Serialization.formats(
    NoTypeHints
  )

}
