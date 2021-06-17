package kz.one.lab.repository

import com.sksamuel.elastic4s.ElasticClient
import com.typesafe.config.Config
import kz.one.lab.domain.Book
import kz.one.lab.elastic.ElasticRepository
import kz.one.lab.serializer.Serializer

import scala.concurrent.ExecutionContext

class BookRepository(val client: ElasticClient)
                    (implicit val ec: ExecutionContext, config: Config) extends ElasticRepository[Book] with Serializer {
  def indexName: String = config.getString("elastic.indexes.books")

  override implicit val manifest: Manifest[Book] = Manifest.classType[Book](classOf[Book])
}
