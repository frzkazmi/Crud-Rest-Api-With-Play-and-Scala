package controllers

import play.api.libs.json._
import play.api.mvc._
import models.Product._

object Application extends Controller {

  def listProducts = Action {
    Ok(Json.toJson(products))
  }
  
   def listProductsByEan(ean: Long) = Action {
  //   findByEan(ean)
    Ok(Json.toJson(findByEan(ean)))
  }
   
   def removeProduct(ean: Long) = Action {
    remove(findByEan(ean).get);
   Ok(Json.obj("status" -> "OK"))
  }

  def saveProduct = Action(BodyParsers.parse.json) { request =>
    val b = request.body.validate[Product]
    b.fold(
      errors => {
        BadRequest(Json.obj("status" -> "OK", "message" -> JsError.toFlatJson(errors)))
      },
      Product => {
        addProduct(Product)
        Ok(Json.obj("status" -> "OK"))
      }
    )
  }
  
  def updateProduct(ean: Long) = Action(BodyParsers.parse.json) { request =>
     if (findByEan(ean).isEmpty)
      NotFound
    else {
     
    val b = request.body.validate[Product]
    b.fold(
      errors => {
        BadRequest(Json.obj("status" -> "OK", "message" -> JsError.toFlatJson(errors)))
      },
      Product => {
       remove(findByEan(ean).get)
         addProduct(Product);
        Ok(Json.obj("status" -> "OK"))
      }
    )
  }
}
}