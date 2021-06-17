package kz.one.lab.service

import com.sksamuel.elastic4s.ElasticClient
import com.sksamuel.elastic4s.requests.searches.queries.term.TermQuery
import com.typesafe.config.Config
import kz.one.lab.domain.Book
import kz.one.lab.repository.BookRepository

import scala.concurrent.{ExecutionContext, Future}

class BookService(client: ElasticClient)(
                 implicit config: Config,
                 ec: ExecutionContext){

  private def bookRepository: BookRepository = new BookRepository(client)

  def searchBooks(author: String): Future[Seq[Book]] =
    bookRepository.search(
      bookRepository.search(
        bookRepository.indexName
      ).query(
        TermQuery(
          field = "author",
          value = author
        )
      )
    )

  def createBook(book: Book): Future[Book] =
    bookRepository.upsert(book.id, book)

}
