package models

import play.api.libs.json.Json
import scala.collection.mutable.ListBuffer

object Product {

  case class Product(ean:Long, name: String, price: String)

  implicit val ProductWrites = Json.writes[Product]
  implicit val ProductReads = Json.reads[Product]

  var products = ListBuffer(Product(401120545,"Moto X", "1800 Rs"), Product(401334,"Moto G", "12000 Rs"))

  
  /**
   * Products sorted by EAN code.
   */
  def findAll = products.toList.sortBy(_.ean)

  /**
   * The product with the given EAN code.
   */
  def findByEan(ean: Long) = products.find(_.ean == ean)

  /**
   * Products whose name matches the given query.
   */
  def findByName(query: String) = products.filter(_.name.contains(query))

  /**
   * Deletes a product from the catalog.
   */
  def remove(product: Product) = {
    val oldProducts = products
    products -=product;
   oldProducts.contains(product)
  }

  def addProduct(b: Product) {  
    println(b);
    products +=b;
  }
  println("Updated products are :");
  
println(products);
}
