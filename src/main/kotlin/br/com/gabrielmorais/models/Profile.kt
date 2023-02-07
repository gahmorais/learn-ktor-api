package br.com.gabrielmorais.models

data class Profile(val valuser: User, val desserts: List<Dessert> = emptyList())