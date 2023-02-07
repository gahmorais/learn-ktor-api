package br.com.gabrielmorais.repository

import br.com.gabrielmorais.data.desserts
import br.com.gabrielmorais.models.Dessert

class DessertRepository : RepositoryInterface<Dessert> {
  override fun getById(id: String): Dessert {
    return try {
      desserts.find { it.id == id } ?: throw Exception("no dessert with that ID exists")
    } catch (e: Throwable) {
      throw Exception("Cannot find dessert")
    }
  }

  override fun getAll(): List<Dessert> {
    return desserts
  }

  override fun delete(id: String): Boolean {
    return try {
      val dessert = desserts.find { it.id == id } ?: throw Exception("No dessert with that ID exists")
      desserts.remove(dessert)
    } catch (e: Throwable) {
      throw Exception("Cannot find dessert")
    }
  }

  override fun update(entry: Dessert): Dessert {
    return try {
      val dessert = desserts.find { it.id == entry.id }?.apply {
        name = entry.name
        description = entry.description
        imageUrl = entry.imageUrl
      } ?: throw Exception("No dessert with that ID exists")
      dessert
    } catch (e: Throwable) {
      throw Exception("Cannot find dessert")
    }
  }

  override fun add(entry: Dessert): Dessert {
    desserts.add(entry)
    return entry
  }
}