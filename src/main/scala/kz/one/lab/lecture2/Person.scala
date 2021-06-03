package kz.one.lab.lecture2

class Person(val name: String, val surname: String) {

  def getFullName(implicit person1: Person1): String = s"${person1.name} ${person1.surname}"

}
