package kz.one.lab.serializer

import org.json4s.{Formats, ShortTypeHints}
import org.json4s.native.Serialization

trait Serializer extends CoreSerializer with Serialization {

  implicit val formats: Formats = Serialization.formats(
    ShortTypeHints(
      hints
    )
  )

}
