package kz.one.lab.serializer

import kz.one.lab.domain.Book
import org.json4s.jackson.Serialization
import org.json4s.{Formats, ShortTypeHints}

trait Serializer extends Serialization {

  implicit val formats: Formats = Serialization.formats(
    ShortTypeHints(
      List(
        classOf[Book],
      )
    )
  )

}
