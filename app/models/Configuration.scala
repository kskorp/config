package models

import scala.slick.session.Database
import Database.threadLocalSession
import scala.slick.jdbc.{GetResult, StaticQuery => Q}
import Q.interpolation

case class Config(ref: Option[Long] = None, name: String = "", timestamp: Option[Long] = None)

trait ConfigDAOTrait {
	def findAll: Iterable[Config]
	def findByRef(ref: Long): Option[Config]
	def deleteByRef(ref: Long): Unit
}

class ConfigDAO extends ConfigDAOTrait {
	val configs = Seq(
	    Config(Option(1), "Conf1", Option(1380728153)),
	    Config(Option(2), "Conf2", Option(1380693842)),
	    Config(Option(3), "Conf3", Option(1380684275))
	)
	
	def findAll = this.configs
	
	def findByRef(ref: Long) = this.configs.find(_.ref.get == ref)
	
	def deleteByRef(ref: Long) = {}
	
	def testrun = {
		implicit val getConfigResult = GetResult(r => Config(r.<<, r.<<, r.<<))
	  
		Database.forURL("jdbc:postgresql://localhost/config?user=smartdb&password=TowarWydano01!@&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory", driver = "org.postgresql.Driver") withSession {
			Q.queryNA[Config]("select reference, name, extract(epoch from timestamp)::int as timestamp from a.configuration") foreach { c =>
				println("  " + c.ref + "\t" + c.name + "\t" + c.timestamp)
			}
		}
	}
}

