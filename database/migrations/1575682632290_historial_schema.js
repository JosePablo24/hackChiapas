'use strict'

/** @type {import('@adonisjs/lucid/src/Schema')} */
const Schema = use('Schema')

class HistorialSchema extends Schema {
  up () {
    this.create('historial', (table) => {
      table.increments()
      table.string('latitud').notNullable()
      table.string('longitud').notNullable()
      table.string('descripcion')
      table.integer('user_id').references('id').inTable('users').onDelete('cascade')
      table.timestamps()
    })
  }

  down () {
    this.drop('historial')
  }
}

module.exports = HistorialSchema
