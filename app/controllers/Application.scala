package controllers

import play.api._
import play.api.mvc._
import models.Configuration._
import models.ConfigurationDAO

object Application extends Controller {
  
	def index = Action {
		Redirect(routes.Application.list)
	}
	
	def list = Action {
		val dao = new ConfigurationDAO
		val configurations = dao.findAll;
		Ok(views.html.list(configurations))
	}
	
	def create() = TODO
	
	def edit(ref: Long) = TODO
	
	def delete(ref: Long) = TODO
  
}