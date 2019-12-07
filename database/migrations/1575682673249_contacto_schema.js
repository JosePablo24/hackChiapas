'use strict'

/** @type {import('@adonisjs/lucid/src/Schema')} */
const Schema = use('Schema')

class ContactoSchema extends Schema {
  up () {
    this.create('contactos', (table) => {
      table.increments()
      table.string('name').notNullable()
      table.string('lastname').notNullable()
      table.string('parent')
      table.string('phone').notNullable()
      table.integer('user_id').references('id').inTable('users').onDelete('cascade')
      table.timestamps()
    })
  }

  down () {
    this.drop('contactos')
  }
}

module.exports = ContactoSchema
