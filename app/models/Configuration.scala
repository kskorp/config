package models

case class Config(val ref: Long, val name: String, val timestamp: Long)

trait ConfigDAOTrait {
	def findAll: List[Config]
	
	def findByRef(ref: Long): Option[Config]
}

class ConfigDAO extends ConfigDAOTrait {
	val configs = Set(
	    Config(1, "Conf1", 1380728153),
	    Config(2, "Conf2", 1380693842),
	    Config(3, "Conf3", 1380684275)
	)
	
	def findAll = this.configs.toList.sortBy(_.ref)
	
	def findByRef(ref: Long) = this.configs.find(_.ref == ref)
}

