package controllers

import play.api._
import play.api.mvc._
import models._
import play.api.data._
import play.api.data.Forms._

object Application extends Controller {
	val dao = new ConfigDAO
  
	val editConfigForm = Form(
		"config" ->
			mapping(
				"ref" -> optional(longNumber),
				"name" -> nonEmptyText(3, 20),
				"timestamp" -> optional(longNumber)
			)(Config.apply)(Config.unapply)
	)
  
	def index = Action {
		Redirect(routes.Application.list)
	}
	
	def list = Action {
		val configs = dao.findAll
		Ok(views.html.list(configs))
	}
	
	def create = Action {
		val config = Config()
		Ok(views.html.edit(config, editConfigForm, false))
	}
	
	def edit(ref: Long) = Action {
		dao.findByRef(ref).map { config =>
			Ok(views.html.edit(config, editConfigForm, false))
		}.getOrElse(NotFound)
	}
	
	def editpost(ref: Long) = Action { implicit request =>
		val config = if (ref == 0) Some(Config()) else dao.findByRef(ref)
		config.map { config =>
			editConfigForm.bindFromRequest.fold(
				formWithErrors => BadRequest(views.html.edit(config, formWithErrors, true)),
				config => Ok(views.html.edit(config, editConfigForm, true))
			)
		}.getOrElse(NotFound)
	}
	
	def delete(ref: Long) = TODO

}