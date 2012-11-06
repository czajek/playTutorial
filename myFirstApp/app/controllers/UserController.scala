package controllers

import play.api._
import play.api.mvc._
import scala.collection.mutable.HashMap
import play.api.data._
import play.api.data.Forms._
import models.Account
import views.html.index

object UserController extends Controller {

  val userForm = Form(
    tuple(
      "userName" -> text,
      "password" -> text))

  val usersMap = new HashMap[String, Account]

  def index = Action {
    Ok(views.html.addUser("test", userForm))
  }

  def add = Action {

    implicit request => 
    	val (user, password) = userForm.bindFromRequest.get
      if (usersMap.contains(user)) {
        Ok(views.html.userExists(user))
      } else {
        def account = new Account(user, password)
        usersMap.put(user, account)
        Ok(views.html.userAdded(user))
      }
      
  }

}
