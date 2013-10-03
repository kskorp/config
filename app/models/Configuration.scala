package models

case class Configuration(val ref: Long, val timestamp: Long, val name: String)

trait ConfigurationDAOTrait {
	def findAll: List[Configuration]
	
	def findByRef(ref: Long): Option[Configuration]
}

class ConfigurationDAO extends ConfigurationDAOTrait {
	val configurations = Set(
	    Configuration(1, 1380728153, "Conf1"),
	    Configuration(2, 1380693842, "Conf2"),
	    Configuration(3, 1380684275, "Conf3")
	)
	
	def findAll = this.configurations.toList.sortBy(_.ref)
	
	def findByRef(ref: Long) = this.configurations.find(_.ref == ref)
}

