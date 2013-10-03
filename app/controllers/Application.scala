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
				"ref" -> longNumber,
				"name" -> text,
				"timestamp" -> longNumber
			)(Config.apply)(Config.unapply)
	)
  
	def index = Action {
		Redirect(routes.Application.list)
	}
	
	def list = Action {
		val configs = dao.findAll;
		Ok(views.html.list(configs))
	}
	
	def create() = Action {
		val config = Config(0, "", 0)
		Ok(views.html.edit(config, editConfigForm, false))
	}
	
	def edit(ref: Long) = Action {
		dao.findByRef(ref).map { config =>
			Ok(views.html.edit(config, editConfigForm, false))
		}.getOrElse(NotFound)
	}
	
	def editpost(ref: Long) = Action { implicit request =>
		dao.findByRef(ref).map { config =>
			editConfigForm.bindFromRequest.fold(
				errors => BadRequest(views.html.edit(config, errors, true)),
				config => Ok(views.html.edit(config, editConfigForm, true))
			)
		}.getOrElse(NotFound)
	}
	
	def delete(ref: Long) = TODO
  
}