package models

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
}

