package br.com.gabrielmorais.models

data class Profile(val user: User, val desserts: List<Dessert> = emptyList())